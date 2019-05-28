package com.tiad;

import java.util.ArrayDeque;

public class App {
    public static Object lock = new Object();
    public static void main(String[] args) {

        ArrayDeque<String> queue = new ArrayDeque<>();

        ReadWorker reader = new ReadWorker(queue);
        WriteWorker writer = new WriteWorker(queue);

        new Thread(reader).start();
        Thread writeThread = new Thread(writer);
        writeThread.start();
        try {
            Thread.sleep(5000);
            writeThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
