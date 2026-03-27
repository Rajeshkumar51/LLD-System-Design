package Model;

public class User {
    private int userID;
    private String userName;
    private Vehicle vehicle;

    public User(int userID, String userName, Vehicle vehicle) {
        this.userID = userID;
        this.userName = userName;
        this.vehicle = vehicle;
    }

    public int getUserID() {
        return userID;
    }
    public String getUserName() {
        return userName;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
}