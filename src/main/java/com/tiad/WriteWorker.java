package com.tiad;

import java.util.ArrayDeque;

public class WriteWorker implements Runnable {

    private ArrayDeque<String> queue;

    public WriteWorker(ArrayDeque<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        synchronized (App.lock) {
            String buffer = null;
            try {
                while (true) {
                    buffer = queue.poll();

                    if (buffer == null) {
                        App.lock.wait(3000);
                        continue;
                    }

                    if (buffer.equals("END"))
                        break;

                    System.out.println(buffer);
                }
            } catch (InterruptedException e) {
                System.out.println("interrupted: " + Thread.currentThread().getName());
                while (!queue.isEmpty())
                    System.out.println(queue.poll());
            }
        }
    }
}
