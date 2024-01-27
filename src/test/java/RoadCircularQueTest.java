import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class RoadCircularQueTest {
    RoadCircularQue roadCircularQue;
    static Road road1;
    static Road road2;
    static Road road3;
    static Road road4;
    static Road road5;
    static Road road6;
    static Road road7;
    static Road road8;
    static Road road9;

    @BeforeEach
    public void createRoadCircularQue() {
        roadCircularQue = new RoadCircularQue(5);
    }

    @BeforeAll
    public static void createRoads() {
        road1 = new Road("Road 1");
        road2 = new Road("Road 2");
        road3 = new Road("Road 3");
        road4 = new Road("Road 4");
        road5 = new Road("Road 5");
        road6 = new Road("Road 6");
        road7 = new Road("Road 7");
        road8 = new Road("Road 8");
        road9 = new Road("Road 9");
    }

    @Test
    public void testRoadCircularQueueInstantiation() {
        assertNotNull(roadCircularQue);
    }

    @Test
    public void testAdd1Road() {
        boolean added = roadCircularQue.enqueue(road1);
        assertTrue(added);
    }

    @Test
    public void addMaxRoads() {
        roadCircularQue.enqueue(road1);
        roadCircularQue.enqueue(road2);
        roadCircularQue.enqueue(road3);
        roadCircularQue.enqueue(road4);
        boolean added = roadCircularQue.enqueue(road5);
        assertTrue(added);
    }

    @Test
    public void addTooManyRoads() {
        roadCircularQue.enqueue(road1);
        roadCircularQue.enqueue(road2);
        roadCircularQue.enqueue(road3);
        roadCircularQue.enqueue(road4);
        roadCircularQue.enqueue(road5);
        boolean added = roadCircularQue.enqueue(road6);
        assertFalse(added);
    }

    @Test
    public void checkOrderOfRoadsWhenFull() {
        Road[] roads = {road1, road2, road3, road4, road5};
        roadCircularQue.enqueue(road1);
        roadCircularQue.enqueue(road2);
        roadCircularQue.enqueue(road3);
        roadCircularQue.enqueue(road4);
        roadCircularQue.enqueue(road5);
        String expected =  Arrays.toString(roads);
        String result = roadCircularQue.toString();
        assertEquals(expected, result);
    }

    @Test
    public void dequeueRoadFromEmptyRoadsReturnsFalse() {
        assertThrows(NoSuchElementException.class, () -> roadCircularQue.dequeue());
    }

    @Test void enqueue3RoadsDequeue2ShouldReturnSecondRoad() {
        roadCircularQue.enqueue(road1);
        roadCircularQue.enqueue(road2);
        roadCircularQue.enqueue(road3);
        roadCircularQue.dequeue();
        Road result = roadCircularQue.dequeue();
        Road expected = road2;
        assertEquals(expected, result);
    }

    @Test
    public void enqueue5dequeue2enqueue2dequeue2enqueue2dequeue2LastReturnedShouldBeRoad6() {
        roadCircularQue.enqueue(road1);
        roadCircularQue.enqueue(road2);
        roadCircularQue.enqueue(road3);
        roadCircularQue.enqueue(road4);
        roadCircularQue.enqueue(road5);
        roadCircularQue.dequeue();
        roadCircularQue.dequeue();
        roadCircularQue.enqueue(road6);
        roadCircularQue.enqueue(road7);
        roadCircularQue.dequeue();
        roadCircularQue.dequeue();
        roadCircularQue.enqueue(road8);
        roadCircularQue.enqueue(road9);
        roadCircularQue.dequeue();
        Road result = roadCircularQue.dequeue();
        Road expected = road6;
        assertEquals(expected, result);
    }




}
