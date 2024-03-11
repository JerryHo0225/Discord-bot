package bot;

import tools.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This bot will check the seat of a quiz or practical test for a student.
 * <p>
 * This message bot will only work in private message.
 * The user should first type "seat" to start the conversation.
 * The bot will then ask for the student ID. The bot is expecting
 * a 8-digit number as the student ID and ignore any other message.
 * After received the 8-digit number in a private message, the bot
 * will check the seat of the student and return the seat number.
 * <p>
 * The bot allows the user to check seat for other students or check
 * the seat even if the user did not register to UserManagementBot before.
 * <p>
 * We will assume the seat will never change during the execution of the
 * program. Any change of seat will require the program to restart.
 */
public class SeatChecker implements MessageListener {

    static final String DEFAULT_FILE = "seat.csv";
    private boolean isCheck;

    //TODO: Add your private data member here
    List<TextPair> studentIdAndSeatArrayList = new ArrayList<>();
    //List<String> seatArrayList = new ArrayList<>();

    //TODO: Add your methods here
    public SeatChecker() {
        this(DEFAULT_FILE);
        isCheck = false;
    }

    public SeatChecker(String filename) {
        try {
            Scanner scanner = new Scanner(new File(DEFAULT_FILE));
            while (scanner.hasNextLine()) {
                String[] arrUserInput = scanner.nextLine().split(",");
                if (arrUserInput.length == 2) {
                    studentIdAndSeatArrayList.add(new TextPair(arrUserInput[0], arrUserInput[1]));
                } else {
                    System.out.println("The invalid line : " + scanner.nextLine());
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("invalid file name : " + filename);
        }
    }


    @Override
    public String onMessageReceived(Message msg) {
        String strSeatInput = "seat";
        //String invaildInput = "This is an invaild input.Please input ";


        if (msg.isPrivate() == false && msg.getContent().equals(strSeatInput)) {
            return "For Private Message only.";
        }

        if (msg.getContent().equals(strSeatInput) ) {
            isCheck = true;
            return "Please input your ID : ";
        }

        for (int i = 0; i < studentIdAndSeatArrayList.size(); i++) {
            if (msg.getContent().equals(studentIdAndSeatArrayList.get(i).getName()) && isCheck) {
                isCheck =false;
                return "Your seat is: " + studentIdAndSeatArrayList.get(i).getValue();

            }
        }
        return null;
    }
}
