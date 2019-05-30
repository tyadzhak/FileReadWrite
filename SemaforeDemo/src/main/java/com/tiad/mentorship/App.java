package com.tiad.mentorship;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        App app = new App();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() -> app.run());
        executor.submit(() -> app.run());
        executor.submit(() -> app.run());
    }

    private void run() {
        for (int i = 0; i < 5; i++) {
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
    }

}
