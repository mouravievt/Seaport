package com.company;

import java.util.Date;

import static com.company.TruckStatus.*;

public class Truck implements Runnable{
    private TruckStatus status = LOADING;
    private Container container = null;
    private long driveBackTime = 0;
    private ContainerQueue queue;
    private String license;

    public Truck (ContainerQueue queue, String license) {
        this.queue = queue;
        this.license = license;
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
        while (true) {
            try {
                switch (status) {
                    case LOADING:
                        synchronized (license) {
                            container = queue.remove();
                            driveBackTime = 1000L * container.getArrivalTime();
                        }
                        Thread.currentThread().sleep(1000L);
                        status = DRIVING_TO_DESTINATION;
                        break;
                    case DRIVING_TO_DESTINATION:
                        Thread.currentThread().sleep(1000L * container.getArrivalTime());
                        synchronized (license) {
                            container = null;
                        }
                        status = UNLOADING;
                        break;
                    case UNLOADING:
                        Thread.currentThread().sleep(1000L);
                        status = DRIVING_TO_SEAPORT;
                        break;
                    case DRIVING_TO_SEAPORT:
                    default:
                        Thread.currentThread().sleep(driveBackTime);
                        status = LOADING;
                        break;
                }
            } catch(InterruptedException ie){
                System.err.println(SeaportApp.df().format(new Date()) + " [" + Thread.currentThread().getName() + "] was interrupted");
                break;
            } catch(Exception ee){
                System.err.println(SeaportApp.df().format(new Date()) + " [" + Thread.currentThread().getName() + "] Truck " + license + " is broken");
                ee.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public String getStatus(){
        synchronized (license) {
            return "Truck " + license + " is " + status + (container == null ? "" : "  to " + container.getDestinationName());
        }
    }
}
