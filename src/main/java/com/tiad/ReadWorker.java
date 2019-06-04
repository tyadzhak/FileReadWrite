package com.tiad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.concurrent.locks.ReadWriteLock;

public class ReadWorker implements Runnable {

    private final ArrayDeque<String> queue;

    private final File file;
    private final ReadWriteLock lock;

    public ReadWorker(ArrayDeque<String> queue, ReadWriteLock lock) {
        this.queue = queue;
        this.lock = lock;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        file = new File(contextClassLoader.getResource("data.txt").getFile());
    }

    @Override
    public void run() {
        lock.writeLock().lock();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String buffer;
            while ((buffer = br.readLine()) != null) {
                queue.addLast(buffer);
                Thread.sleep(1000);
            }
            queue.addLast("END");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
