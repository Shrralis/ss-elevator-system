package com.shrralis.elevator_system.main;

import com.shrralis.elevator_system.model.Elevator;
import com.shrralis.elevator_system.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int elevatorAmount = 1;

    public static void main(String[] args) throws IOException {
        List<Elevator> elevators = new ArrayList<>();
        Integer count = null;

        do {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                System.out.print("Enter amount of elevators: ");

                count = Integer.valueOf(br.readLine());
            } catch (NumberFormatException ignored) {
            }
        } while (count == null);

        for (int i = 0; i < count; i++) {
            elevators.add(fillNewElevator());
        }

        Person p = fillNewPerson();

        System.out.println("-------------------------------");
        System.out.println("You've entered next data:");

        for (Elevator e : elevators) {
            System.out.printf("Elevator %d:\n", e.getId());
            System.out.println(e);
        }
        System.out.println("Person:");
        System.out.println(p);
        System.out.println("-------------------------------");
        System.out.println("RESULT");
        System.out.println("-------------------------------");

        Elevator result = calcMostProductElevator(elevators, p);

        if (result == null) {
            System.out.println("You don't need elevator!");
        } else {
            System.out.printf("You will get %d elevator!\n", result.getId());
        }
    }

    private static Elevator fillNewElevator() throws IOException {
        Elevator elevator = new Elevator();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer floor;

        System.out.printf("Enter elevator %d current floor (by default it is 1): ", elevatorAmount);

        floor = Integer.valueOf(br.readLine());

        elevator.setCurrFloor(floor == null ? 1 : floor);

        System.out.printf("Enter elevator %d destination floor (by default it is 9): ", elevatorAmount);

        floor = Integer.valueOf(br.readLine());

        elevator.setDestFloor(floor == null ? 9 : floor);
        elevator.setState(elevator.getCurrFloor().equals(elevator.getDestFloor()) ?
                Elevator.State.STANDBY : (elevator.getCurrFloor() < elevator.getDestFloor() ?
                Elevator.State.UP : Elevator.State.DOWN));
        elevator.setId(elevatorAmount++);
        return elevator;
    }

    private static Person fillNewPerson() throws IOException {
        Person person = new Person();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer currFloor = null;
        Integer destFloor = null;

        System.out.print("Enter your current floor: ");

        do {
            try {
                currFloor = Integer.valueOf(br.readLine());
            } catch (NumberFormatException ignored) {
            }
        } while (currFloor == null);
        person.setCurrFloor(currFloor);
        System.out.print("Enter your destination floor: ");

        do {
            try {
                destFloor = Integer.valueOf(br.readLine());
            } catch (NumberFormatException ignored) {
            }
        } while (destFloor == null);
        person.setDestFloor(destFloor);
        return person;
    }

    public static Elevator calcMostProductElevator(List<Elevator> elevators, Person p) {
        Map<Elevator, Integer> minDistances = new HashMap<>();
        List<Map.Entry<Elevator, Integer>> elevatorSortedByMinDistanceDesc;
        Elevator result = null;

        if (p.getDestFloor() > p.getCurrFloor()) {
            // person wanna up
            for (Elevator e : elevators) {
                minDistances.put(e, calcMinDistanceOnUp(e, p));
            }

            elevatorSortedByMinDistanceDesc = new LinkedList<>(minDistances.entrySet());

            Collections.sort(elevatorSortedByMinDistanceDesc, Comparator.comparing(Map.Entry::getValue));

            result = elevatorSortedByMinDistanceDesc.get(0).getKey();
        } else if (p.getDestFloor() < p.getCurrFloor()) {
            // person wanna down
            for (Elevator e : elevators) {
                minDistances.put(e, calcMinDistanceOnDown(e, p));
            }

            elevatorSortedByMinDistanceDesc = new LinkedList<>(minDistances.entrySet());

            Collections.sort(elevatorSortedByMinDistanceDesc, Comparator.comparing(Map.Entry::getValue));

            result = elevatorSortedByMinDistanceDesc.get(0).getKey();
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
                    (p.getCurrFloor() - e.getDestFloor()) +
                    (p.getDestFloor() - p.getCurrFloor());
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
                    (e.getDestFloor() - p.getCurrFloor()) +
                    (p.getCurrFloor() - p.getDestFloor());
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
