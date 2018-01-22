package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SeaportApp {
    private final static SeaportApp app = new SeaportApp();
    private Seaport seaport = new Seaport();
    private static String[] destination = {"Boston", "Austin", "Seattle", "Pittsburg", "New York", "Las Vegas", "Atlanta", "Orlando", "Kansas City", "Charleston"};
    private static long[] deliveryTime = { 5, 15, 30, 8, 2, 20, 15, 22, 15, 10};

    private static Truck [] trucks = new Truck[10];

    public static void main(String[] args) {
	    app.run();
        System.out.println(new Date().toString() + " [" + Thread.currentThread().getName() + "] SeaportApp END");
    }

    public static SimpleDateFormat df(){
        return new SimpleDateFormat("HH:mm:ss SSS");
    }

    public static Truck[] getTrucks() { return trucks; }

    public static Container getRandomContainer(){
        Random random = new Random();
        int index = random.nextInt(10);

        return new Container(deliveryTime[index], destination[index]);
    }

    private void run(){
        System.out.println(SeaportApp.df().format(new Date()) + " [" + Thread.currentThread().getName() + "] SeaportApp START");
        Thread inf = new Thread(new Informer(seaport), "Informer");
        inf.start();
        Thread sea = new Thread(seaport, "Seaport");
        sea.start();
        boolean keepRunning = true;


        // Start trucks
        Thread[] truckThreads = new Thread[10];
        trucks[0] = new Truck(seaport.getQueue(), "AB12");
        truckThreads[0] = new Thread(trucks[0], "Truck 1");

        trucks[1] = new Truck(seaport.getQueue(), "BB12");
        truckThreads[1] = new Thread(trucks[1], "Truck 2");

        trucks[2] = new Truck(seaport.getQueue(), "CB12");
        truckThreads[2] = new Thread(trucks[2], "Truck 3");

        trucks[3] = new Truck(seaport.getQueue(), "DB12");
        truckThreads[3] = new Thread(trucks[3], "Truck 4");

        trucks[4] = new Truck(seaport.getQueue(), "EB12");
        truckThreads[4] = new Thread(trucks[4], "Truck 5");

        trucks[5] = new Truck(seaport.getQueue(), "FB12");
        truckThreads[5] = new Thread(trucks[5], "Truck 6");

        trucks[6] = new Truck(seaport.getQueue(), "GB12");
        truckThreads[6] = new Thread(trucks[6], "Truck 7");

        trucks[7] = new Truck(seaport.getQueue(), "HB12");
        truckThreads[7] = new Thread(trucks[7], "Truck 8");

        trucks[8] = new Truck(seaport.getQueue(), "IB12");
        truckThreads[8] = new Thread(trucks[8], "Truck 9");

        trucks[9] = new Truck(seaport.getQueue(), "JB12");
        truckThreads[9] = new Thread(trucks[9], "Truck 10");

        while(keepRunning) {
            try {
                Thread.currentThread().sleep(10000L);
                for(int ii=0; ii<truckThreads.length; ii++) {
                    truckThreads[ii].start();
                }
                Thread.currentThread().sleep(120000L);
                inf.interrupt();
                sea.interrupt();
                for(int ii=0; ii<truckThreads.length; ii++) {
                    truckThreads[ii].interrupt();
                }
                keepRunning = false;
            } catch (InterruptedException ie) {
                System.err.println(SeaportApp.df().format(new Date()) + " [" + Thread.currentThread().getName() + "] was interrupted");
            }
        }
    }
}
