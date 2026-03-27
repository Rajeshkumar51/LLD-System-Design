package Model;

import Enum.Type;

public class TwoWheeler extends Fee {
    public TwoWheeler(long start, long end) {
        super(start, end);
    }
    public int calculateFee() {
        long hours = Math.max(1, (endTime - startTime) / (1000 * 60 * 60));
        return (int) (hours * 10);
    }
}