package Model;
import Enum.DealStatus;
public class Deal {
    private int id;
    private Buyer buyer;
    private Seller seller;
    private Vehicle vehicle;
    private double amount;
    private boolean paid;
    private DealStatus status;
    public Deal(int id, Buyer buyer, Seller seller, Vehicle vehicle, double amount) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.vehicle = vehicle;
        this.amount = amount;
        this.paid = false;
        this.status = DealStatus.INITIATED;
    }
    public int getId() { return id; }
    public Buyer getBuyer() { return buyer; }
    public Seller getSeller() { return seller; }
    public double getAmount() { return amount; }
    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }
    public void setStatus(DealStatus status) { this.status = status; }
}