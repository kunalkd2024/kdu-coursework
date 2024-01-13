package org.example.q1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
class MessageReceiver implements Runnable {
    private final String name;
    private final MessageQueue messageQueue;
    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    public MessageReceiver(String name, MessageQueue messageQueue) {
        this.name = name;
        this.messageQueue = messageQueue;
    }

    public void run() {
        String message = null;
        try {
            message = messageQueue.dequeue();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String ans="Message received from sender " + name + " :" + message;
        logger.info(ans);
    }
}