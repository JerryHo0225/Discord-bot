package bot;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import tools.*;
import java.io.FileNotFoundException;

/**
 * This bot handle the command '/score'.
 * When a user ask about his score, this bot will check the score of the user and return the score.
 * To know which student ID that the user has been registered, this bot will ask the UserManagementBot.
 * Prior to using this bot, you need to register this bot to the UserManagementBot using the command '/registration'.
 *
 */
public class ScoreBot extends CommandBot {
   
    public static final String DEFAULT_FILE = "dictation.csv";
    //TODO: Add your private data member here
    private UserManagementBot userManagementBot;
    private String filename = "";
    private String studentName = "";

    //private

    /**
     * The constructor of the bot, require a UserManagementBot object.
     * Since the filename of the score is not given, the default file name is used.
     */
    public ScoreBot(UserManagementBot r) {
        //TODO

        //this.userManagementBot = r;
        this(r, DEFAULT_FILE);
    }
    /**
     * The constructor of the bot, require a UserManagementBot object and the filename of the score.
     */
    public ScoreBot(UserManagementBot r, String filename) {
        //TODO
        this.filename = filename;
        this.userManagementBot = r;
        studentName = "";
    }
    /**
     * Which score that the object is listening to.
     */
    @Override
    public String getCommand() {
        //TODO
        return "score";
    }
    /**
     * The short description for this command.
     */
    @Override
    public String getCommandHelp() {
        //TODO
        return "This feature shows the score of the user.";
    }
    /**
     * This method is used to respond to a command.
     */
    @Override
    public String respond(Command command) {
        //TODO
        String studentId = "";
        String replyContent =  "Score : ";

        String noDictationScore = "Cannot get the dictation score.";
        String notRegistered = "You are not registered";

        studentName = command.getName();
        try {
            studentId = userManagementBot.getStudentID(command.getSenderID());
        }catch (IDNotFoundException e){
            return notRegistered;
        }
        System.out.println("Slash command which is 'score' has been received: " + command.getName());

        //Filter abnormal

        ArrayList<Double> scoreList = getScore(studentId);
        if (scoreList == null || scoreList.isEmpty()){
            return noDictationScore;
        } else {
            Double totalScore = 0.0;

            int counter = 0;

            for (Double score : scoreList){
                if(score==null){
                    replyContent = " NA , ";
                }
                else{
                    replyContent += score + ", ";
                    totalScore += score;
                    counter++;
                }

            }

            return replyContent + " and the average score : " + (totalScore/counter);
        }
    }

    public ArrayList<Double> getScore(String strStudentId){
        ArrayList<Double> scoreList = new ArrayList<>();
        
        try {
            Scanner scaUserInput = new Scanner(new File(filename));

            while (scaUserInput.hasNextLine()){
                String[] arrStringUserInput = scaUserInput.nextLine().split(",");

                if (arrStringUserInput[0].equals(strStudentId)) {
                    for (int i = 1; i < arrStringUserInput.length; i++) {
                        String currentValue = arrStringUserInput[i];

                        if (arrStringUserInput[i].equals("-"))
                            scoreList.add(null);
                        else
                            scoreList.add(Double.parseDouble(currentValue));
                    }

                    return scoreList;
                }
            }
            scaUserInput.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
    /**
     * Print the last user who queried the score bot service to console.
     */
    @Override
    public void actionPerform() {
        //TODO
        if (studentName != null){
            System.out.println("The last user queried ScoreBot is: " + studentName);
        }
    }
}
