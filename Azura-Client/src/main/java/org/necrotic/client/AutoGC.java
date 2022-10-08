package org.necrotic.client;

public class AutoGC implements Runnable {

    public AutoGC() {
        Thread thread = new Thread(this, "GCThread");
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            System.gc();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void init() {
        new AutoGC();
    }
}
