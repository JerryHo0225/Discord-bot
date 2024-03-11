package bot;

import tools.Message;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * TODO
 * <p>
 * You are asked to build a class called PingMessageListener that
 * implements MessageListener. This class handles a ping message.
 * <p>
 * When user sends "ping", it replies "pong".
 *
 */

public class TimeMessageListener implements MessageListener {
    @Override
    public String onMessageReceived(Message message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        if (message == null)
            return "";
        else if (message.getContent().equals("timenow"))
            return dtf.format(now);
        else
            return null;
    }


}