package com.shrralis.elevator_system.model;

import java.util.Arrays;

public class Elevator {
    private Integer id;
    private State state;
    private Integer currFloor;
    private Integer destFloor;

    {
        id = 0;
        state = State.STANDBY;
        currFloor = 0;
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
    }

    public Integer getDestFloor() {
        return destFloor;
    }

    public void setDestFloor(Integer destFloor) {
        this.destFloor = destFloor;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "state=" + state +
                ", currFloor=" + currFloor +
                ", destFloor=" + destFloor +
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

        {
            elevator = new Elevator();
        }

        public Builder setId(Integer id) {
            elevator.id = id;

            return this;
        }

        public Builder setCurrFloor(Integer floor) {
            elevator.currFloor = floor;

            if (elevator.currFloor != null && elevator.destFloor != null) {
                elevator.state = (elevator.currFloor > elevator.destFloor ? State.DOWN :
                        (elevator.currFloor < elevator.destFloor ? State.UP : State.STANDBY));
            }
            return this;
        }

        public Builder setDestFloor(Integer floor) {
            elevator.destFloor = floor;

            if (elevator.currFloor != null && elevator.destFloor != null) {
                elevator.state = (elevator.currFloor > elevator.destFloor ? State.DOWN :
                        (elevator.currFloor < elevator.destFloor ? State.UP : State.STANDBY));
            }
            return this;
        }

        public Elevator build() {
            return elevator;
        }
    }
}
