package test;

import com.shrralis.elevator_system.main.Main;
import com.shrralis.elevator_system.model.Elevator;
import com.shrralis.elevator_system.model.Person;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class ElevatorSystemTest {
    private int elevatorAmount = 0;

    @DataProvider
    public static Object[][] dataTest1() {
        return new Object[][]{
                {
                        new Person.Builder()
                                .setCurrFloor(1)
                                .setDestFloor(8)
                                .build(),
                        List.of(
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
                        ),
                        2
                },
                {
                        new Person.Builder()
                                .setCurrFloor(4)
                                .setDestFloor(1)
                                .build(),
                        List.of(
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
                        ),
                        1
                },
                {
                        new Person.Builder()
                                .setCurrFloor(1)
                                .setDestFloor(2)
                                .build(),
                        List.of(
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
                        ),
                        1
                }
        };
    }

    @Test
    @UseDataProvider("dataTest1")
    public void test1(Person person, List<Elevator> elevators, Integer resultElevatorId) {
        assertTrue(Main.calcMostProductElevator(elevators, person).getId() == resultElevatorId);
    }

    @Test
    public void test2() {
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
}
