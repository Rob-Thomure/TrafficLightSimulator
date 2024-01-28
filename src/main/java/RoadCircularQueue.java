import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class RoadCircularQueue {
    private final Road[] elements;
    private int size;
    private int front;
    private int rear;

    public RoadCircularQueue(int length) {
        this.elements = new Road[length];
        this.size = 0;
        this.front = 0;
        this.rear = -1;
    }

    public boolean enqueue(Road road) {
        if (elementsSizeIsLessThanElementsLength()) {
            setNextRearPosition();
            addElement(road);
            return true;
        } else {
            return false;
        }
    }

    private boolean elementsSizeIsLessThanElementsLength() {
        return size < elements.length;
    }

    private void setNextRearPosition() {
        rear = (rear + 1) % elements.length;
    }

    private void addElement(Road road) {
        elements[rear] = road;
        size++;
    }

    public Road dequeue() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            Road element = elements[front];
            removeElement();
            return element;
        }
    }

    private void removeElement() {
        elements[front] = null;
        setNextFrontPosition();
        size--;
    }

    private void setNextFrontPosition() {
        front = (front + 1) % elements.length;
    }

    public List<Road> getRoadList() {
        List<Road> roadList = new ArrayList<>();
        int index = front;
        boolean hasAddedRear = false;
        while (elements[index] != null && !hasAddedRear) {
            roadList.add(elements[index]);
            if (index == rear)
                hasAddedRear = true;
            index = (index + 1) % elements.length;
        }
        return roadList;
    }



    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
