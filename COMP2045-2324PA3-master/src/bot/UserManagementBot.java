package bot;


import tools.*;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This bot is to manage the registration of the user with the command '/registration'.
 * The user will be asked to provide a registration code. If the registration code is
 * correct, the user will be registered to the system.
 * <p>
 * The user cannot register again if the user has already registered (One discord ID
 * to one student ID).
 * <p>
 * We assume the registration code will be sent to students via email in advanced.
 * The registration information is given in a file (please check users.csv)
 * <p>
 * The file format of users.csv is as follows:
 * <p>
 * Each row may have three columns or five columns.
 * <p>
 * Three Columns, e.g.:
 * 20100001,g8xa9s,Bruce Lee
 * That represents the student ID is 20100001, the registration code is g8xa9s, and the name is Bruce Lee.
 * <p>
 * Five Columns, e.g.:
 * 20100002,-,Chan Tai Man,1004553330619580487,Kevin Wang
 * That represents the
 * student ID is 20100002,
 * the registration code is empty (registered already),
 * the student name is Chan Tai Man,
 * the discord ID is 1004553330619580487, and
 * the discord name is Kevin Wang.
 */
public class UserManagementBot extends CommandBot {
    //Add your data member here
    private List<User> userArrayList = new ArrayList();
    private String fileName = "";
    private int intLength = 3;
    String strWarnNoFile = "Unable to locate the file.";
    String strReplyRegCode = "Registration code : ";
    User userObject;

    //Constructor
    public UserManagementBot(String filename) {
        userArrayList = new ArrayList<>();

        //TODO
        File inputFile = new File(filename);

        if (!inputFile.exists()) {
            System.out.println("The file " + filename + " is not found.");
        }
        try {
            Scanner in = new Scanner(inputFile);

            while (in.hasNextLine()) {
                User user;

                String str = in.nextLine();

                String[] strList = str.split(",");

                if (strList.length == 3) {
                    user = new User(strList[0], strList[1]);
                    user.setStudentName(strList[2]);
                } else {
                    user = new User();

                    user.setStudentID(strList[0]);

                    user.setStudentName(strList[2]);

                    user.setID(strList[3]);

                    user.setUsername(strList[4]);
                }

                userArrayList.add(user);

                System.out.println();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        addOption("regcode", "Your registration code", true);
    }


    /**
     * Written for you. No need to change it
     */
    @Override
    public String getCommand() {
        return "registration";
    }

    /**
     * Written for you. No need to change it
     */
    @Override
    public String getCommandHelp() {
        return "Registers the user";
    }


    /**
     * to check if a user has been registered
     */
    boolean isRegistered(String id) {
        //TODO

        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).getID() != null) {
                    return true;
            }
        }

        return false;
    }


    /**
     * To respond to the command '/registration'.
     * <p>
     * If the user has already registered, return "You are already registered!"
     * If the registration code is correct, register the user and return "You are registered!".
     * If the registration code is incorrect, return "Invalid registration code!"
     * <p>
     * To avoid data lost, remember to save the data to the file after each user's registration.
     */
    @Override
    public String respond(Command command) {
        //TODO

        System.out.println("Registration Bot received a slash command 'registration' from user: " + command.getName());

        for (int i = 0; i < userArrayList.size(); i++) {

            if (!(command.getOption("regcode").equals(userArrayList.get(i).getRegistrationCode()))) {
                return "Invalid registration code";
            }

            if (command.getSenderID().equals(userArrayList.get(i).getID())) {
                return "You are already registered!";
            }

            if (isRegistered(command.getSenderID())) {
                if (command.getOption("regcode").equals(userArrayList.get(i).getRegistrationCode())) {
                    userArrayList.get(i).setID(command.getSenderID());
                    userArrayList.get(i).setUsername(command.getName());


                    //save data
                    try {
                        PrintWriter out = new PrintWriter("updated_user.txt");
                        String strAns = "";
                        for (int j = 0; j < userArrayList.size(); j++) {
                            User user = userArrayList.get(j);
                            if (user.getID() == null)
                            {break;}
                           else if (!(user.getID().equals(""))) {
                                strAns = user.getStudentID() + ", - ," + user.getStudentName() + "," + user.getID() + "," + user.getUsername() + "\n";
                                out.printf(strAns);
                            }

                        }

                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                    return "You are registered!";
                }



            }


        }


        return "Invalid registration code";
    }

    /**
     * return the student ID of the user with the given discord ID
     * <p>
     * throws an IDNotFoundException if the discord ID cannot be found
     */
    public String getStudentID(String id) throws IDNotFoundException {
        //TODO

        return getStudent(id).getStudentID();
    }

    /**
     * return the user object with the given discord ID
     * <p>
     * throws an IDNotFoundException if the discord ID cannot be found
     */
    public User getStudent(String id) throws IDNotFoundException {
        //TODO
        for (int i = 0; i < userArrayList.size(); i++) {
            User user = userArrayList.get(i);

            if (user != null) {
                if (user.getID() == null) {
                    throw new IDNotFoundException("ID [" + id + "] not found!");
                }else {if (user.getID().equals(id))
                    return user;}
            }
        }

        return null;
    }


    /**
     * Output how many number of users have registered.
     */
    @Override
    public void actionPerform() {

        int counter = 0;
        String replyRegBot = "Registration Bot : ";
        String strReplyNumberOfRegBot;
        for (int i = 0; i < userArrayList.size(); i++) {
            User user = userArrayList.get(i);

            try {
                if (!(user.getID() == null)) {
                    counter++;
                }
            }catch (Exception e){e.printStackTrace();}


        }
        strReplyNumberOfRegBot = replyRegBot + counter + " out of total number of " + userArrayList.size();
        System.out.println(strReplyNumberOfRegBot);
    }
}
