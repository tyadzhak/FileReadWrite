package com.tiad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ReadWorker implements Runnable {

    private BlockingQueue<String> queue;

    private File file = new File(
            "C:\\Source\\com.tiad\\mentorship\\FileReadWrite\\src\\main\\Resource\\data.txt");

    public ReadWorker(BlockingQueue<String> queue) {
        this.queue = queue;
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
                queue.put(buffer);
//                if(i == 5)
//                    Thread.currentThread().interrupt();
//                else
                    Thread.sleep(1000);

            }
            queue.put("END");

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
