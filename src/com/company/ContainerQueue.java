package com.company;

import java.util.Date;
import java.util.LinkedList;

public class ContainerQueue {
    public static final int CAPACITY = 50;
    private LinkedList<Container> queue = new LinkedList<Container>();

    public void add(Container container){
        synchronized (queue) {
            if (queue.size() < CAPACITY) {
                queue.add(container);
            } else {
                System.err.println(SeaportApp.df().format(new Date()) + " [" + Thread.currentThread().getName() + "] is at capacity, you may not add anymore containers");
            }
        }
    }

    public Container remove(){
        Container removed = null;
        synchronized (queue) {
            if (queue.size() > 0) {
                removed = queue.remove();
            } else {
                System.err.println(SeaportApp.df().format(new Date()) + " [" + Thread.currentThread().getName() + "] is empty, there are no more containers");
            }
        }
        return removed;
    }

    public int size(){
        return queue.size();
    }
}
