public class TimeCounter {
    private int duration;

    public TimeCounter() {
        this.duration = 0;
    }

    public void addSecond() {
        duration++;
    }

    public int getDuration() {
        return duration;
    }
}
