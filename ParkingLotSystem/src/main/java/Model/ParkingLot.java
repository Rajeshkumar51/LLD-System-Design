package Model;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Integer,User>users=new HashMap<>();
    private Map<Integer,Slot>slots=new HashMap<>();
    private Map<Integer,Payment>payments=new HashMap<>();

    public Map<Integer, Ticket> getTickets() {
        return tickets;
    }

    public Map<Integer, Payment> getPayments() {
        return payments;
    }

    public Map<Integer, Slot> getSlots() {
        return slots;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    private Map<Integer,Ticket>tickets=new HashMap<>();

}
