package bot;

import tools.Message;

/**
 * TODO
 * <p>
 * You are asked to build a class called PingMessageListener that
 * implements MessageListener. This class handles a ping message.
 * <p>
 * When user sends "ping", it replies "pong".
 *
 */

public class PingMessageListener implements MessageListener {
    @Override
    public String onMessageReceived(Message message) {
        if (message == null)
            return "";

        else if (message.getContent().equals("ping"))
            return "pong";
        else
            return null;
    }


}