package com.tiad;

import java.util.concurrent.BlockingQueue;

public class WriteWorker implements Runnable {

    private BlockingQueue<String> queue;

    public WriteWorker(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String buffer = queue.take();

                if (buffer.equals("END")) {
                    break;
                }
                System.out.println(buffer);
            }
        } catch(InterruptedException e){
            System.out.println("interrupted");
        }
    }
}
