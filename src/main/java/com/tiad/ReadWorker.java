package com.tiad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

public class ReadWorker implements Runnable {

    private ArrayDeque<String> queue;

    private File file;

    public ReadWorker(ArrayDeque<String> queue) {
        this.queue = queue;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        file = new File(contextClassLoader.getResource("data.txt").getFile());
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(file));
            String buffer;
            int i = 0;
            while ((buffer = br.readLine()) != null) {
                i++;
                queue.addLast(buffer);
                Thread.sleep(1000);

            }
            queue.addLast("END");


        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("interrupted: " + Thread.currentThread().getName());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
