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

    public static Elevator calcMostProductElevator(List<Elevator> elevators, Person p) {
        Map<Elevator, Integer> minDistances = new HashMap<>();
        List<Map.Entry<Elevator, Integer>> elevatorSortedByMinDistance;
        Elevator result = null;

        if (p.getDestFloor() > p.getCurrFloor()) {
            // person wanna up
            for (Elevator e : elevators) {
                minDistances.put(e, calcMinDistanceOnUp(e, p));
            }
        } else if (p.getDestFloor() < p.getCurrFloor()) {
            // person wanna down
            for (Elevator e : elevators) {
                minDistances.put(e, calcMinDistanceOnDown(e, p));
            }
        }

        if (!minDistances.isEmpty()) {
            elevatorSortedByMinDistance = new LinkedList<>(minDistances.entrySet());

            elevatorSortedByMinDistance.sort(((o1, o2) -> {
                int firstComparison = o1.getValue().compareTo(o2.getValue());

                return firstComparison == 0 ? o1.getKey().getId().compareTo(o2.getKey().getId()) : firstComparison;
            }));

            elevatorSortedByMinDistance = elevatorSortedByMinDistance.stream()
                    .filter(e -> !e.getKey().getSlotMaxAmount().equals(e.getKey().getSlotBusyAmount()))
                    .collect(Collectors.toList());

            if (!elevatorSortedByMinDistance.isEmpty()) {
                result = elevatorSortedByMinDistance.get(0).getKey();
            }
        }
        return result;
    }

    private static Integer calcMinDistanceOnUp(Elevator e, Person p) {
        Integer result;

        if (e.getState().equals(Elevator.State.UP)) {
            if (e.getCurrFloor() <= p.getCurrFloor()) {
                result = p.getDestFloor() - e.getCurrFloor();
            } else {
                result = (e.getDestFloor() - e.getCurrFloor()) +
                        (e.getDestFloor() - p.getCurrFloor()) +
                        (p.getDestFloor() - p.getCurrFloor());
            }
        } else if (e.getState().equals(Elevator.State.DOWN)) {
            result = (e.getCurrFloor() - e.getDestFloor()) +
                    (p.getDestFloor() - p.getCurrFloor()) +
                    (p.getCurrFloor() > e.getCurrFloor() ?
                            p.getCurrFloor() - e.getDestFloor() :
                            e.getDestFloor() - p.getCurrFloor());
        } else {
            if (e.getCurrFloor() <= p.getCurrFloor()) {
                result = p.getDestFloor() - e.getCurrFloor();
            } else {
                result = (e.getCurrFloor() - p.getCurrFloor()) +
                        (p.getDestFloor() - p.getCurrFloor());
            }
        }
        return result;
    }

    private static Integer calcMinDistanceOnDown(Elevator e, Person p) {
        Integer result;

        if (e.getState().equals(Elevator.State.DOWN)) {
            if (e.getCurrFloor() >= p.getCurrFloor()) {
                result = e.getCurrFloor() - p.getDestFloor();
            } else {
                result = (e.getCurrFloor() - e.getDestFloor()) +
                        (p.getCurrFloor() - e.getDestFloor()) +
                        (p.getCurrFloor() - p.getDestFloor());
            }
        } else if (e.getState().equals(Elevator.State.UP)) {
            result = (e.getDestFloor() - e.getCurrFloor()) +
                    (p.getCurrFloor() - p.getDestFloor()) +
                    (e.getDestFloor() > p.getCurrFloor() ?
                            e.getDestFloor() - p.getCurrFloor() :
                            p.getCurrFloor() - e.getDestFloor());
        } else {
            if (e.getCurrFloor() >= p.getCurrFloor()) {
                result = e.getCurrFloor() - p.getDestFloor();
            } else {
                result = (p.getCurrFloor() - e.getCurrFloor()) +
                        (p.getCurrFloor() - p.getDestFloor());
            }
        }
        return result;
    }
}
