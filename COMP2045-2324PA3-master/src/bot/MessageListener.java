/**
 * TODO
 *
 * You are asked to build an interface called MessageListener.
 *
 * This interface has one method called onMessageReceived that returns
 * a String. It has a parameter of type Message.
 *
 */
package bot;

import tools.Message;

public interface MessageListener {
   
    public String onMessageReceived(Message message);
}