package test;

import com.shrralis.elevatorsystem.main.Main;
import com.shrralis.elevatorsystem.model.Elevator;
import com.shrralis.elevatorsystem.model.Person;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(DataProviderRunner.class)
public class ElevatorSystemTest {
    @DataProvider
    public static Object[][] dataForGetElevatorPositive() {
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
                        2
                },
                {
                        new Person.Builder()
                                .setCurrFloor(7)
                                .setDestFloor(3)
                                .build(),
                        List.of(
                                new Elevator.Builder()
                                        .setId(1)
                                        .setCurrFloor(4)
                                        .setDestFloor(5)
                                        .build(),
                                new Elevator.Builder()
                                        .setId(2)
                                        .setCurrFloor(6)
                                        .setDestFloor(7)
                                        .build()
                        ),
                        2
                },
                {
                        new Person.Builder()
                                .setCurrFloor(5)
                                .setDestFloor(9)
                                .build(),
                        List.of(
                                new Elevator.Builder()
                                        .setId(1)
                                        .setCurrFloor(4)
                                        .setDestFloor(5)
                                        .build(),
                                new Elevator.Builder()
                                        .setId(2)
                                        .setCurrFloor(7)
                                        .setDestFloor(6)
                                        .build()
                        ),
                        1
                },
                {
                        new Person.Builder()
                                .setCurrFloor(7)
                                .setDestFloor(2)
                                .build(),
                        List.of(
                                new Elevator.Builder()
                                        .setId(1)
                                        .setCurrFloor(8)
                                        .setDestFloor(3)
                                        .build(),
                                new Elevator.Builder()
                                        .setId(2)
                                        .setCurrFloor(9)
                                        .setDestFloor(1)
                                        .build()
                        ),
                        1
                },
                {
                        new Person.Builder()
                                .setCurrFloor(6)
                                .setDestFloor(4)
                                .build(),
                        List.of(
                                new Elevator.Builder()
                                        .setId(1)
                                        .setCurrFloor(8)
                                        .setDestFloor(3)
                                        .build(),
                                new Elevator.Builder()
                                        .setId(2)
                                        .setCurrFloor(2)
                                        .setDestFloor(4)
                                        .build()
                        ),
                        2
                },
                {
                        new Person.Builder()
                                .setCurrFloor(6)
                                .setDestFloor(2)
                                .build(),
                        List.of(
                                new Elevator.Builder()
                                        .setId(1)
                                        .setCurrFloor(5)
                                        .setDestFloor(12)
                                        .build(),
                                new Elevator.Builder()
                                        .setId(2)
                                        .setCurrFloor(5)
                                        .setDestFloor(8)
                                        .build()
                        ),
                        2
                },
                {
                        new Person.Builder()
                                .setCurrFloor(4)
                                .setDestFloor(9)
                                .build(),
                        List.of(
                                new Elevator.Builder()
                                        .setId(1)
                                        .setCurrFloor(10)
                                        .setDestFloor(12)
                                        .build(),
                                new Elevator.Builder()
                                        .setId(2)
                                        .setCurrFloor(3)
                                        .setDestFloor(1)
                                        .build()
                        ),
                        2
                },
                {
                        new Person.Builder()
                                .setCurrFloor(5)
                                .setDestFloor(9)
                                .build(),
                        List.of(
                                new Elevator.Builder()
                                        .setId(1)
                                        .setCurrFloor(7)
                                        .setDestFloor(7)
                                        .build(),
                                new Elevator.Builder()
                                        .setId(2)
                                        .setCurrFloor(3)
                                        .setDestFloor(3)
                                        .build()
                        ),
                        1
                },
                {
                        new Person.Builder()
                                .setCurrFloor(8)
                                .setDestFloor(4)
                                .build(),
                        List.of(
                                new Elevator.Builder()
                                        .setId(1)
                                        .setCurrFloor(7)
                                        .setDestFloor(6)
                                        .build(),
                                new Elevator.Builder()
                                        .setId(2)
                                        .setCurrFloor(4)
                                        .setDestFloor(5)
                                        .build()
                        ),
                        1
                },
                {
                        new Person.Builder()
                                .setCurrFloor(6)
                                .setDestFloor(3)
                                .build(),
                        List.of(
                                new Elevator.Builder()
                                        .setId(1)
                                        .setCurrFloor(4)
                                        .setDestFloor(4)
                                        .build(),
                                new Elevator.Builder()
                                        .setId(2)
                                        .setCurrFloor(6)
                                        .setDestFloor(4)
                                        .build()
                        ),
                        1
                },
                {
                        new Person.Builder()
                                .setCurrFloor(6)
                                .setDestFloor(3)
                                .build(),
                        List.of(
                                new Elevator.Builder()
                                        .setId(1)
                                        .setCurrFloor(4)
                                        .setDestFloor(4)
                                        .build(),
                                new Elevator.Builder()
                                        .setId(2)
                                        .setCurrFloor(6)
                                        .setDestFloor(4)
                                        .setBusySlots(5)
                                        .build()
                        ),
                        1
                },
        };
    }

    @Test
    @UseDataProvider("dataForGetElevatorPositive")
    public void getElevatorPositive(Person person, List<Elevator> elevators, Integer resultElevatorId) {
        assertEquals(resultElevatorId, Main.calcMostProductElevator(elevators, person).getId());
    }

    @Test
    public void nullResultPositive() {
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

        assertNull(Main.calcMostProductElevator(elevators, p));
    }
}
