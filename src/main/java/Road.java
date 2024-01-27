import java.util.Objects;

public class Road {
    String name;

    public Road(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Objects.equals(name, road.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
