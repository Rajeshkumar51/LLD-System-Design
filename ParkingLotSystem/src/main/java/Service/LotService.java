package Service;

import Model.*;
import Enum.Type;

public class LotService {

    private int ticketCounter = 1;
    private int paymentCounter = 1;
    private ParkingLot parking;
    public LotService(ParkingLot parking) {
        this.parking = parking;
    }
    public void addUser(int id, String name, Vehicle vehicle) {
        User user = new User(id, name, vehicle);
        parking.getUsers().put(id, user);
        System.out.println("User Added Successfully!");
    }
    public void addSlot(int id, Type type) {
        Slot slot = new Slot(id, type);
        parking.getSlots().put(id, slot);
        System.out.println("Slot Added Successfully!");
    }
    public void getAvailableSlots(Type type) {
        for (Slot slt : parking.getSlots().values()) {
            if (slt.getType() == type && slt.isAvailable()) {
                System.out.println(slt.getSlotId() + " | " + slt.getType() + " | Available");
            }
        }
    }
    public void bookSlot(int slotId, int userId) {
        User user = parking.getUsers().get(userId);
        Slot slot = parking.getSlots().get(slotId);
        if (user == null || slot == null) {
            System.out.println("Invalid User or Slot!");
            return;
        }
        if (!slot.isAvailable()) {
            System.out.println("Slot already booked! Choose another.");
            return;
        }
        slot.setAvailable(false);
        System.out.println("Slot Booked Successfully!");
    }
    public Ticket generateTicket(int userId, int slotId) {
        User user = parking.getUsers().get(userId);
        Slot slot = parking.getSlots().get(slotId);
        if (slot.isAvailable()) {
            System.out.println("Slot must be booked before generating ticket!");
            return null;
        }
        Ticket ticket = new Ticket(ticketCounter++, user, slot, System.currentTimeMillis());
        parking.getTickets().put(ticket.getTicketId(), ticket);
        System.out.println("Ticket Generated: " + ticket.getTicketId());
        return ticket;
    }
    public Payment exitVehicle(int ticketId) {
        Ticket ticket = parking.getTickets().get(ticketId);
        if (ticket == null) {
            System.out.println("Invalid Ticket ID!");
            return null;
        }
        long endTime = System.currentTimeMillis();
        Fee feeCalculator = (ticket.getUser().getVehicle().getType() == Type.TWOWHEELER)
                ? new TwoWheeler(ticket.getStartTime(), endTime)
                : new FourWheeler(ticket.getStartTime(), endTime);
        int amount = feeCalculator.calculateFee();
        Payment payment = new Payment(paymentCounter++, ticket, amount);
        parking.getPayments().put(payment.getPaymentId(), payment);
        System.out.println("Please Pay Amount: " + amount + " | Payment ID: " + payment.getPaymentId());
        return payment;

    }
    public void payBill(int paymentId) {
        Payment p = parking.getPayments().get(paymentId);
        if (p == null) {
            System.out.println("Invalid Payment ID!");
            return;
        }
        p.pay();
        p.getTicket().getSlot().setAvailable(true);
        System.out.println("Payment Successful!");
    }
    public void getAllUsers() {
        for (User u : parking.getUsers().values()) {
            System.out.println(u.getUserID() + " | " + u.getUserName() +
                    " | Vehicle: " + u.getVehicle().getName());
        }
    }
    public void getAllSlots() {
        for (Slot s : parking.getSlots().values()) {
            System.out.println(
                    s.getSlotId() + " | " + s.getType() + " | " +
                            (s.isAvailable() ? "Available" : "Booked"));
        }
    }
}