package org.example.q2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MessageReceiver implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(org.example.q2.MessageReceiver.class);
    private final String name;
    private final MessageQueue messageQueue;

    public MessageReceiver(String name, MessageQueue messageQueue) {
        this.name = name;
        this.messageQueue = messageQueue;
    }

    @Override
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