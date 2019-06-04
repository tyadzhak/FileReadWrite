package com.tiad;

import java.util.ArrayDeque;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class App {
    public static Object lock = new Object();
    public static void main(String[] args) {

        ArrayDeque<String> queue = new ArrayDeque<>();

        ReadWriteLock lock = new ReentrantReadWriteLock();
        ReadWorker reader = new ReadWorker(queue, lock);
        WriteWorker writer = new WriteWorker(queue, lock);

        new Thread(reader).start();
        Thread writeThread = new Thread(writer);
        writeThread.setName("writer");
        writeThread.start();
    }
}
