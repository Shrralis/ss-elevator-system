package test;

import com.shrralis.elevator_system.main.Main;
import com.shrralis.elevator_system.model.Elevator;
import com.shrralis.elevator_system.model.Person;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ElevatorSystemTest {
    @Test
    public void test1() {
        Person p = new Person();

        p.setCurrFloor(1);
        p.setDestFloor(8);

        List<Elevator> elevators = List.of(
                new Elevator.Builder()
                        .setId(1)
                        .setCurrFloor(3)
                        .setDestFloor(7)
                        .build(),
                new Elevator.Builder()
                        .setId(2)
                        .setCurrFloor(9)
                        .setDestFloor(2)
                        .build()
        );

        assertTrue(Main.calcMostProductElevator(elevators, p).getId() == 2);
    }

    @Test
    public void test2() {
        Person p = new Person();

        p.setCurrFloor(4);
        p.setDestFloor(1);

        List<Elevator> elevators = List.of(
                new Elevator.Builder()
                        .setId(1)
                        .setCurrFloor(9)
                        .setDestFloor(9)
                        .build(),
                new Elevator.Builder()
                        .setId(2)
                        .setCurrFloor(12)
                        .setDestFloor(6)
                        .build()
        );

        assertTrue(Main.calcMostProductElevator(elevators, p).getId() == 1);
    }

    @Test
    public void test3() {
        Person p = new Person();

        p.setCurrFloor(5);
        p.setDestFloor(5);

        List<Elevator> elevators = List.of(
                new Elevator.Builder()
                        .setId(1)
                        .setCurrFloor(9)
                        .setDestFloor(9)
                        .build(),
                new Elevator.Builder()
                        .setId(2)
                        .setCurrFloor(12)
                        .setDestFloor(6)
                        .build()
        );

        assertTrue(Main.calcMostProductElevator(elevators, p) == null);
    }

    @Test
    public void test4() {
        Person p = new Person();

        p.setCurrFloor(1);
        p.setDestFloor(2);

        List<Elevator> elevators = List.of(
                new Elevator.Builder()
                        .setId(1)
                        .setCurrFloor(4)
                        .setDestFloor(3)
                        .build(),
                new Elevator.Builder()
                        .setId(2)
                        .setCurrFloor(3)
                        .setDestFloor(3)
                        .build()
        );

        assertTrue(Main.calcMostProductElevator(elevators, p).getId() == 1);
    }
}
