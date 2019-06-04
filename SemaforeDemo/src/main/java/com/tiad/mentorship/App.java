package com.tiad.mentorship;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class App {

    public static void main(String[] args) {
        App app = new App();
        Semaphore s = new Semaphore(1);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() -> app.run(s));
        executor.submit(() -> app.run(s));
        executor.submit(() -> app.run(s));
    }

    private void run(Semaphore s) {
        try {
            s.acquire();
            for (int i = 0; i < 50; i++) {
                String text =
                        "new line, thread: " + Thread.currentThread().getName() + " iteration: " + i
                                + "\n";
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(text);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            s.release();
        }
    }
}
