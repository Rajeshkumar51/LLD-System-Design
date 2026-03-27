package Model;

public class Payment {
    private int paymentId;
    private Ticket ticket;
    private int amount;
    private boolean status;

    public Payment(int id, Ticket ticket, int amount) {
        this.paymentId = id;
        this.ticket = ticket;
        this.amount = amount;
        this.status = false;
    }

    public void pay() {
        status = true;
    }

    public int getPaymentId() {
        return paymentId;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public int getAmount() {
        return amount;
    }
    public boolean isPaid() {
        return status;
    }
}