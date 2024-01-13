package org.example.q2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MessageSender implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(org.example.q2.MessageSender.class);
    private final String name;
    private final MessageQueue messageQueue;

    public MessageSender(String name, MessageQueue messageQueue) {
        this.name = name;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            String message = "Message from " + name;
            String ans = name + " sending message: " + message;
            logger.info(ans);
            messageQueue.enqueue(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}