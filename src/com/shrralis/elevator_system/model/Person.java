package com.shrralis.elevator_system.model;

public class Person {
    private Integer currFloor;
    private Integer destFloor;

    public Integer getCurrFloor() {
        return currFloor;
    }

    public void setCurrFloor(Integer currFloor) {
        this.currFloor = currFloor;
    }

    public Integer getDestFloor() {
        return destFloor;
    }

    public void setDestFloor(Integer destFloor) {
        this.destFloor = destFloor;
    }

    @Override
    public String toString() {
        return "Person{" +
                "currFloor=" + currFloor +
                ", destFloor=" + destFloor +
                '}';
    }
}
