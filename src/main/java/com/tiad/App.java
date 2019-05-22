package com.tiad;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {


    public static void main(String[] args) {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

        ReadWorker reader = new ReadWorker(queue);
        WriteWorker writer = new WriteWorker(queue);

        //InterruptionThread interruptionThread = new InterruptionThread();
        //new Thread(interruptionThread).start();
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
