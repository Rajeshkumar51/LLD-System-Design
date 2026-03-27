package Model;

public abstract class Fee {
    protected long startTime;
    protected long endTime;

    public Fee(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public abstract int calculateFee();
}