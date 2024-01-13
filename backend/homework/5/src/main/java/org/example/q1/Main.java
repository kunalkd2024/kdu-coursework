package org.example.q1;

public class Main {
    /**
     * This class represents the main application that demonstrates the usage of MessageQueue,
     * MessageSender, and MessageReceiver for concurrent message processing.
     */
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();

        Thread[] senderThreads = new Thread[3];
        Thread[] receiverThreads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            MessageSender sender = new MessageSender("Sender-" + i, messageQueue);
            senderThreads[i] = new Thread(sender);
            senderThreads[i].start();

            MessageReceiver receiver = new MessageReceiver("Receiver-" + i, messageQueue);
            receiverThreads[i] = new Thread(receiver);
            receiverThreads[i].start();
        }

        for (Thread thread : senderThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (Thread thread : receiverThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
