package com.shrralis.elevatorsystem.model;

import java.util.Arrays;

public class Elevator {
    public static final int DEFAULT_MAX_CAPACITY = 5;

    private Integer id;
    private State state;
    private Integer currFloor;
    private Integer destFloor;
    private Integer maxCapacity;
    private Integer busySlots;

    public Elevator() {
        id = 0;
        state = State.STANDBY;
        currFloor = 0;
        maxCapacity = DEFAULT_MAX_CAPACITY;
        busySlots = 0;
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

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        if (maxCapacity < 0) {
            throw new RuntimeException("Busy slots cannot be less than 0!");
        } else if (maxCapacity < busySlots) {
            busySlots = 0;
        } else {
            this.maxCapacity = maxCapacity;
        }
    }

    public Integer getBusySlots() {
        return busySlots;
    }

    public void setBusySlots(Integer busySlots) {
        if (busySlots > maxCapacity) {
            throw new RuntimeException("Busy slots cannot be more than elevator capacity!");
        } else if (busySlots < 0) {
            throw new RuntimeException("Busy slots cannot be less than 0!");
        } else {
            this.busySlots = busySlots;
        }
    }

    public void increaseBusySlots() {
        if (busySlots < maxCapacity) {
            this.busySlots++;
        } else {
            throw new RuntimeException("Busy slots cannot be more than elevator capacity!");
        }
    }

    public void decreaseBusySlots() {
        if (busySlots > 0) {
            this.busySlots--;
        } else {
            throw new RuntimeException("Busy slots cannot be less than 0!");
        }
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", state=" + state +
                ", currFloor=" + currFloor +
                ", destFloor=" + destFloor +
                ", maxCapacity=" + maxCapacity +
                ", busySlots=" + busySlots +
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

        public Builder setMaxCapacity(Integer maxCapacity) {
            elevator.setMaxCapacity(maxCapacity);
            return this;
        }

        public Builder setBusySlots(Integer busySlots) {
            elevator.setBusySlots(busySlots);
            return this;
        }

        public Elevator build() {
            return elevator;
        }
    }
}
