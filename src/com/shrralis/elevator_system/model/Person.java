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

    public static class Builder {
        private Person p;

        {
            p = new Person();
        }

        public Builder setCurrFloor(Integer floor) {
            p.currFloor = floor;

            return this;
        }

        public Builder setDestFloor(Integer floor) {
            p.destFloor = floor;

            return this;
        }

        public Person build() {
            return p;
        }
    }
}
