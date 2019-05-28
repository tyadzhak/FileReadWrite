package com.tiad;

import java.util.ArrayDeque;

public class WriteWorker implements Runnable {

    private ArrayDeque<String> queue;

    public WriteWorker(ArrayDeque<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String buffer = null;
        try {

            synchronized (App.lock) {
                while (true) {
                    buffer = queue.poll();

                    if (buffer == null) {
                        buffer = "wait";
                        App.lock.wait(50);
                        continue;
                    }

                    if (buffer.equals("END"))
                        break;

                    System.out.println(buffer);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted");
            if (buffer != null)
                System.out.println(buffer);
        }
    }
}
