package org.example.q2;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();

        ExecutorService senderThreadPool = Executors.newFixedThreadPool(3);
        ExecutorService receiverThreadPool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            senderThreadPool.submit(new MessageSender("Sender-" + i, messageQueue));
        }

        for (int i = 0; i < 3; i++) {
            receiverThreadPool.submit(new MessageReceiver("Receiver-" + i, messageQueue));
        }

        senderThreadPool.shutdown();
        receiverThreadPool.shutdown();
    }
}
