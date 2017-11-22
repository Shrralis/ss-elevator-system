package com.shrralis.elevatorsystem.main;

import com.shrralis.elevatorsystem.model.Elevator;
import com.shrralis.elevatorsystem.model.Person;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private Main() {
    }

    public static Elevator calcMostProductElevator(List<Elevator> elevators, Person person) {
        Map<Elevator, Integer> minDistances = new HashMap<>();
        List<Map.Entry<Elevator, Integer>> elevatorSortedByMinDistance;
        Elevator result = null;

        if (person.getDestFloor() > person.getCurrFloor()) {
            // person wanna up
            for (Elevator e : elevators) {
                minDistances.put(e, calcMinDistanceOnUp(e, person));
            }
        } else if (person.getDestFloor() < person.getCurrFloor()) {
            // person wanna down
            for (Elevator e : elevators) {
                minDistances.put(e, calcMinDistanceOnDown(e, person));
            }
        }

        if (!minDistances.isEmpty()) {
            elevatorSortedByMinDistance = new LinkedList<>(minDistances.entrySet());

            elevatorSortedByMinDistance.sort(((o1, o2) -> {
                int firstComparison = o1.getValue().compareTo(o2.getValue());

                return firstComparison == 0 ? o1.getKey().getId().compareTo(o2.getKey().getId()) : firstComparison;
            }));

            elevatorSortedByMinDistance = elevatorSortedByMinDistance.stream()
                    .filter(e -> !e.getKey().getMaxCapacity().equals(e.getKey().getBusySlots()))
                    .collect(Collectors.toList());

            if (!elevatorSortedByMinDistance.isEmpty()) {
                result = elevatorSortedByMinDistance.get(0).getKey();
            }
        }
        return result;
    }

    private static Integer calcMinDistanceOnUp(Elevator elevator, Person person) {
        Integer result;

        if (elevator.getState().equals(Elevator.State.UP)) {
            if (elevator.getCurrFloor() <= person.getCurrFloor()) {
                result = person.getDestFloor() - elevator.getCurrFloor();
            } else {
                result = (elevator.getDestFloor() - elevator.getCurrFloor()) +
                        (elevator.getDestFloor() - person.getCurrFloor()) +
                        (person.getDestFloor() - person.getCurrFloor());
            }
        } else if (elevator.getState().equals(Elevator.State.DOWN)) {
            result = (elevator.getCurrFloor() - elevator.getDestFloor()) +
                    (person.getDestFloor() - person.getCurrFloor()) +
                    (person.getCurrFloor() > elevator.getCurrFloor() ?
                            person.getCurrFloor() - elevator.getDestFloor() :
                            elevator.getDestFloor() - person.getCurrFloor());
        } else {
            if (elevator.getCurrFloor() <= person.getCurrFloor()) {
                result = person.getDestFloor() - elevator.getCurrFloor();
            } else {
                result = (elevator.getCurrFloor() - person.getCurrFloor()) +
                        (person.getDestFloor() - person.getCurrFloor());
            }
        }
        return result;
    }

    private static Integer calcMinDistanceOnDown(Elevator elevator, Person person) {
        Integer result;

        if (elevator.getState().equals(Elevator.State.DOWN)) {
            if (elevator.getCurrFloor() >= person.getCurrFloor()) {
                result = elevator.getCurrFloor() - person.getDestFloor();
            } else {
                result = (elevator.getCurrFloor() - elevator.getDestFloor()) +
                        (person.getCurrFloor() - elevator.getDestFloor()) +
                        (person.getCurrFloor() - person.getDestFloor());
            }
        } else if (elevator.getState().equals(Elevator.State.UP)) {
            result = (elevator.getDestFloor() - elevator.getCurrFloor()) +
                    (person.getCurrFloor() - person.getDestFloor()) +
                    (elevator.getDestFloor() > person.getCurrFloor() ?
                            elevator.getDestFloor() - person.getCurrFloor() :
                            person.getCurrFloor() - elevator.getDestFloor());
        } else {
            if (elevator.getCurrFloor() >= person.getCurrFloor()) {
                result = elevator.getCurrFloor() - person.getDestFloor();
            } else {
                result = (person.getCurrFloor() - elevator.getCurrFloor()) +
                        (person.getCurrFloor() - person.getDestFloor());
            }
        }
        return result;
    }
}
