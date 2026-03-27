package Service;

import Enum.Type;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {

    private ParkingLot parking;
    private int ticketCounter = 1;
    private int paymentCounter = 1;

    public ParkingLotService(ParkingLot parking) {
        this.parking = parking;
    }
    public void addUser(int id, String name, Vehicle vehicle) {
        if (parking.getUsers().containsKey(id)) {
            System.out.println("User already exists!");
            return;
        }
        parking.getUsers().put(id, new User(id, name, vehicle));
        System.out.println("User added successfully.");
    }
    public void addSlot(int slotId, Type type) {
        if (parking.getSlots().containsKey(slotId)) {
            System.out.println("Slot already exists!");
            return;
        }
        parking.getSlots().put(slotId, new Slot(slotId, type));
        System.out.println("Slot added successfully.");
    }
    public List<Slot> getAvailableSlots(Type type) {
        List<Slot> available = new ArrayList<>();
        for (Slot s : parking.getSlots().values()) {
            if (s.getType() == type && s.isAvailable()) {
                available.add(s);
            }
        }
        return available;
    }
    public Slot bookSlot(int slotId, int userId) {
        User user = parking.getUsers().get(userId);
        Slot slot = parking.getSlots().get(slotId);
        if (user == null) {
            System.out.println("Invalid user!");
            return null;
        }
        if (slot == null) {
            System.out.println("Invalid slot!");
            return null;
        }
        if (!slot.isAvailable()) {
            System.out.println("Slot already booked!");
            return null;
        }
        if (user.getVehicle().getType() != slot.getType()) {
            System.out.println("Vehicle type does not match slot type!");
            return null;
        }
        slot.setAvailable(false);
        System.out.println("Slot booked successfully.");
        return slot;
    }
    public Ticket generateTicket(int userId, int slotId) {
        User user = parking.getUsers().get(userId);
        Slot slot = parking.getSlots().get(slotId);

        if (user == null || slot == null) {
            System.out.println("User or Slot invalid!");
            return null;
        }
        if (slot.isAvailable()) {
            System.out.println("Slot is not booked yet!");
            return null;
        }
        Ticket ticket = new Ticket(ticketCounter++, user, slot, System.currentTimeMillis());
        parking.getTickets().put(ticket.getTicketId(), ticket);
        System.out.println("Ticket generated successfully: " + ticket.getTicketId());
        return ticket;
    }
    public Payment exitVehicle(int ticketId) {
        Ticket t = parking.getTickets().get(ticketId);
        if (t == null) {
            System.out.println("Invalid Ticket!");
            return null;
        }
        long end = System.currentTimeMillis();
        Fee fee;
        if (t.getUser().getVehicle().getType() == Type.TWOWHEELER) {
            fee = new TwoWheeler(t.getStartTime(), end);
        } else {
            fee = new FourWheeler(t.getStartTime(), end);
        }
        int amount = fee.calculateFee();
        Payment payment = new Payment(paymentCounter++, t, amount);
        parking.getPayments().put(payment.getPaymentId(), payment);
        System.out.println("Fee Calculated. Amount = ₹" + amount);
        return payment;
    }
    public void payBill(int paymentId) {
        Payment p = parking.getPayments().get(paymentId);
        if (p == null) {
            System.out.println("Invalid Payment ID");
            return;
        }
        p.pay();
        p.getTicket().getSlot().setAvailable(true);
        System.out.println("Payment successful! Thank you For Visiting!");
    }
    public void getAllUsers() {
        for (User u : parking.getUsers().values()) {
            System.out.println(u.getUserID() + " | " + u.getUserName() + " | Vehicle: " + u.getVehicle().getName());
        }
    }
    public void getAllSlots() {
        for (Slot s : parking.getSlots().values()) {
            System.out.println(
                    s.getSlotId() + " | " +
                            s.getType() + " | " +
                            (s.isAvailable() ? "Available" : "Booked")
            );
        }
    }
}