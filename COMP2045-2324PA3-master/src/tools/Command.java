package tools;


import java.util.List;
import java.util.ArrayList;

/**
 * This class is to store the command sent by the user.
 * It shares the same similarity as a Message object except
 * that a command may also has options.
 * 
 * Each option is modeled as a TextPair object. A command
 * may have no option, one option, or multiple options.
 * 
 * This class support the following methods:
 * - addOption (to add an option)
 * - getOption (to get the value of an option)
 * - getOptions (to get all options)
 */
public class Command extends Message {
    private List<TextPair> textPairList = new ArrayList<>();

    /**
     * Constructor of the Message class. Given.
     *
     * @param name
     * @param id
     * @param content
     * @param isPrivate
     */
    public Command(String name, String id, String content, boolean isPrivate) {
        super(name, id, content, isPrivate);
    }
    //TODO - add data members and methods

    public void addOption(String name, String value){
        textPairList.add(new TextPair(name, value));
    }

    public String getOption(String name){
        for (TextPair textPair:
             textPairList) {
            if (textPair.getName().equals(name)) {
                return textPair.getValue();
            }
        }

        return "";
    }

    public List<TextPair> getOptions(String name){
        return textPairList;
    }

}
