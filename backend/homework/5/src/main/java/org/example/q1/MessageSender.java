package org.example.q1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MessageSender implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);
    private final String name;
    private final MessageQueue messageQueue;

    public MessageSender(String name, MessageQueue messageQueue) {
        this.name = name;
        this.messageQueue = messageQueue;
    }

    public void run() {
        String message=" Sender"+":"+Thread.currentThread().getName();
        String ans = name + " sending message: " + message;
        logger.info(ans);
        try {
            messageQueue.enqueue(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}