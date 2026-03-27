package Model;

public class Train {
    private int trainId;
    private String trainName;
    private String fromStat;
    private String toStat;
    private int totalSeats;
    private boolean availability;

    public Train(int trainId,String trainName,String fromStat,String toStat,int totalSeats,boolean availability){
        this.trainId=trainId;
        this.trainName=trainName;
        this.fromStat=fromStat;
        this.toStat=toStat;
        this.totalSeats=totalSeats;
        this.availability=true;
    }

    public int getTrainId() {
        return trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getFromStat() {
        return fromStat;
    }

    public String getToStat() {
        return toStat;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public boolean isAvailability() {
        return availability;
    }
}
