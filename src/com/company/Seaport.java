package com.company;

import java.util.Date;
import java.util.Random;

public class Seaport implements Runnable {
    private ContainerQueue queue = new ContainerQueue();
    private Random random = new Random();

    public String getQueueSize() {
        return String.format("%d", queue.size());
    }

    public ContainerQueue getQueue() {
        return queue;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true){
            try {
                int nextShip = random.nextInt(10);
                System.out.println(SeaportApp.df().format(new Date()) + " [" + Thread.currentThread().getName() + "] seaport unloading ship with " + nextShip + " containers");
                for(int ii = nextShip; ii > 0; ii--){
                    queue.add(SeaportApp.getRandomContainer());
                }
                Thread.currentThread().sleep(1000L * nextShip);
            } catch (InterruptedException ie){
                System.err.println(SeaportApp.df().format(new Date()) + " [" + Thread.currentThread().getName() + "] was interrupted");
                break;
            }
        }

    }
}
