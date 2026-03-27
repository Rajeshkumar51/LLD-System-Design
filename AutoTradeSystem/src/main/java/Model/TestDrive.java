package Model;

public class TestDrive {
    private int id;
    private Buyer buyer;
    private Seller seller;
    private Vehicle vehicle;
    private boolean approved;

    public int getId() {
        return id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public TestDrive(int id, Buyer b, Seller s, Vehicle v) {
        this.id = id;
        this.buyer = b;
        this.seller = s;
        this.vehicle = v;
        this.approved = false;
    }
    public void approve() {
        approved = true;
        System.out.println("Test Drive Approved.");
    }
    public boolean isApproved() {
        return approved;
    }
    public String toString() {
        return "TestDrive" + id + " | Buyer=" + buyer.getUserName() +
                " | Vehicle=" + vehicle.getBrand() +
                " | Approved=" + approved;
    }
}