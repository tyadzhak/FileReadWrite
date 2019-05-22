package com.tiad;

public class InterruptionThread implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
