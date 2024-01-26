public class Time {
    private int duration;

    public Time() {
        this.duration = 0;
    }

    public void addSecond() {
        duration++;
    }

    public int getDuration() {
        return duration;
    }
}
