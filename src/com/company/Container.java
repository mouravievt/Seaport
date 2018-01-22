package com.company;

public class Container {
    private long arrivalTime;
    private String destinationName;

    public Container(long arrivalTime, String destinationName){
        this.arrivalTime = arrivalTime;
        this.destinationName = destinationName;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public String getDestinationName() {
        return destinationName;
    }
}
