package com.shrralis.elevatorsystem.model;

import java.util.Arrays;

public class Elevator {
    private Integer id;
    private State state;
    private Integer currFloor;
    private Integer destFloor;
    private Integer slotMaxAmount;
    private Integer slotBusyAmount;

    public Elevator() {
        id = 0;
        state = State.STANDBY;
        currFloor = 0;
        slotMaxAmount = 5;
        slotBusyAmount = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getCurrFloor() {
        return currFloor;
    }

    public void setCurrFloor(Integer currFloor) {
        this.currFloor = currFloor;

        if (currFloor != null && destFloor != null) {
            state = (currFloor > destFloor ? State.DOWN :
                    (currFloor < destFloor ? State.UP : State.STANDBY));
        }
    }

    public Integer getDestFloor() {
        return destFloor;
    }

    public void setDestFloor(Integer destFloor) {
        this.destFloor = destFloor;

        if (currFloor != null && destFloor != null) {
            state = (currFloor > destFloor ? State.DOWN :
                    (currFloor < destFloor ? State.UP : State.STANDBY));
        }

    }

    public Integer getSlotMaxAmount() {
        return slotMaxAmount;
    }

    public void setSlotMaxAmount(Integer slotMaxAmount) {
        this.slotMaxAmount = slotMaxAmount;

        if (slotMaxAmount < slotBusyAmount) {
            slotBusyAmount = 0;
        }
    }

    public Integer getSlotBusyAmount() {
        return slotBusyAmount;
    }

    public void setSlotBusyAmount(Integer slotBusyAmount) {
        this.slotBusyAmount = slotBusyAmount;

        if (slotMaxAmount < slotBusyAmount) {
            slotMaxAmount = slotBusyAmount;
        }
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", state=" + state +
                ", currFloor=" + currFloor +
                ", destFloor=" + destFloor +
                ", slotMaxAmount=" + slotMaxAmount +
                ", slotBusyAmount=" + slotBusyAmount +
                '}';
    }

    public enum State {
        UP,
        STANDBY,
        DOWN;

        public static State get(String name) {
            return Arrays.stream(State.values())
                    .filter(s -> s.name().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(null);
        }
    }

    public static class Builder {
        private Elevator elevator;

        public Builder() {
            elevator = new Elevator();
        }

        public Builder setId(Integer id) {
            elevator.setId(id);
            return this;
        }

        public Builder setCurrFloor(Integer floor) {
            elevator.setCurrFloor(floor);
            return this;
        }

        public Builder setDestFloor(Integer floor) {
            elevator.setDestFloor(floor);
            return this;
        }

        public Builder setSlotMaxAmount(Integer maxAmount) {
            elevator.setSlotMaxAmount(maxAmount);
            return this;
        }

        public Builder setSlotBusyAmount(Integer busyAmount) {
            elevator.setSlotBusyAmount(busyAmount);
            return this;
        }

        public Elevator build() {
            return elevator;
        }
    }
}
