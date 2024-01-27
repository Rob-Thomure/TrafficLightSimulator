import java.util.Arrays;
import java.util.NoSuchElementException;

public class RoadCircularQue {
    private Road[] elements;
    private int size;
    private int current;
    private int front;
    private int rear;


    public RoadCircularQue(int length) {
        this.elements = new Road[length];
        this.size = 0;
        this.current = -1;
        this.front = -1;
        this.rear = -1;
    }

    public boolean enqueue(Road road) {
        if (size < elements.length) {
            rear = getNextPosition(rear);
            elements[rear] = road;
            size++;
            current++;
            if (front == -1) {
                initializeFront();
            }
            return true;
        } else {
            return false;
        }
    }

    private void initializeFront() {
        front = 0;
    }

    private int getNextPosition(int position) {
        return (position + 1) % elements.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    public Road dequeue() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            Road element = elements[front];
            elements[front] = null;
            front = (front + 1) % elements.length;
            size--;
            return element;

        }
    }
}
