package com.company;

import java.util.Date;

public class Informer implements Runnable {
    private Seaport seaport;

    public Informer (Seaport seaport){
        this.seaport = seaport;
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
                Thread.currentThread().sleep(2000L);
                inform();
            } catch (InterruptedException ie){
                System.err.println(SeaportApp.df().format(new Date()) + " [" + Thread.currentThread().getName() + "] was interrupted");
                break;
            }
        }
    }

    private void inform(){
        StringBuilder buff = new StringBuilder(SeaportApp.df().format(new Date()) + " [" + Thread.currentThread().getName() + "]:\n" +
                "\tseaport queue has " + seaport.getQueueSize() + " containers");
        for(Truck truck : SeaportApp.getTrucks()){
            if(null != truck){
                buff.append("\n\t" + truck.getStatus());
            }
        }
        System.out.println(buff.toString());
    }
}
