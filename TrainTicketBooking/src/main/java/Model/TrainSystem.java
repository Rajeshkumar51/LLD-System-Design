package Model;
import java.util.*;
public class TrainSystem {
    Map<Integer,User> users=new HashMap<>();
    Map<Integer,Train>trains=new HashMap<>();
    Map<Integer,Booking>bookings=new HashMap<>();
    Map<Integer,Payment>payments=new HashMap<>();

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }

    public Map<Integer, Train> getTrains() {
        return trains;
    }

    public void setTrains(Map<Integer, Train> trains) {
        this.trains = trains;
    }

    public Map<Integer, Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Map<Integer, Booking> bookings) {
        this.bookings = bookings;
    }

    public Map<Integer, Payment> getPayments() {
        return payments;
    }

    public void setPayments(Map<Integer, Payment> payments) {
        this.payments = payments;
    }
}
