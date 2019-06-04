package com.tiad;

import java.util.ArrayDeque;
import java.util.concurrent.locks.ReadWriteLock;

public class WriteWorker implements Runnable {

    private final ArrayDeque<String> queue;
    private final ReadWriteLock lock;

    public WriteWorker(ArrayDeque<String> queue, ReadWriteLock lock) {
        this.queue = queue;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.readLock().lock();
        try {
            Thread currentThread = Thread.currentThread();
            String buffer = null;

            while (!currentThread.isInterrupted()) {
                buffer = queue.poll();
                if (buffer == null) {
                    Thread.sleep(1000);
                    continue;
                }

                if (buffer.equals("END"))
                    break;

                System.out.println(buffer);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}

