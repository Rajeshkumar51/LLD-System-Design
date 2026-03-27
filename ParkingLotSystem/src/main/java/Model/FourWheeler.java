package Model;

public class FourWheeler extends Fee {
    public FourWheeler(long start, long end) {
        super(start, end);
    }
    public int calculateFee() {
        long hours = Math.max(1, (endTime - startTime) / (1000 * 60 * 60));
        return (int) (hours * 20);
    }
}