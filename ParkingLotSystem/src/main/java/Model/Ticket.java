package Model;

public class Ticket {
    private int ticketId;
    private User user;
    private Slot slot;
    private long startTime;

    public Ticket(int ticketId, User user, Slot slot, long startTime) {
        this.ticketId = ticketId;
        this.user = user;
        this.slot = slot;
        this.startTime = startTime;
    }

    public int getTicketId() {
        return ticketId;
    }
    public User getUser() {
        return user;
    }
    public Slot getSlot() {
        return slot;
    }
    public long getStartTime() {
        return startTime;
    }
}