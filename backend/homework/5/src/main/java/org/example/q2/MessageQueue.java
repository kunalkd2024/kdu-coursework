package org.example.q2;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class MessageQueue {
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public void enqueue(String message) throws InterruptedException {
        queue.put(message);
    }

    public String dequeue() throws InterruptedException {
        return queue.take();
    }
}
