package bot;


import tools.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherMessageListener implements MessageListener {
    private List<Teacher> teacherArrayList = new ArrayList();

    //Constructor
    public TeacherMessageListener(String filename) {
        teacherArrayList = new ArrayList<>();

        //TODO
        File inputFile = new File(filename);

        if (!inputFile.exists()) {
            System.out.println("The file " + filename + " is not found.");
        }

        try {
            Scanner in = new Scanner(inputFile);

            while (in.hasNextLine()) {
                Teacher user = new Teacher();

                String str = in.nextLine();

                String[] strList = str.split(",");

                if (strList.length == 3) {
                    user = new Teacher(strList[0], strList[1] , strList[2]);
                }

                teacherArrayList.add(user);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

          }


    @Override
    public String onMessageReceived(Message message) {
        if (teacherArrayList == null) {
            return "";
        }

        if (message.getContent().equals("teacher")) {


            //TODO
            String allTeacher = "";

            for (int i = 0; i < teacherArrayList.size(); i++) {

                allTeacher += i + "\n";

                allTeacher += "Teacher id is " + teacherArrayList.get(i).getId() + "\n";

                allTeacher += "Teacher name is " + teacherArrayList.get(i).getTeacherName() + "\n";

                allTeacher += "Teacher email is " + teacherArrayList.get(i).getEmail() + "\n";

                allTeacher += "\n";
            }
            return allTeacher;
        }
        return "";
    }
}
