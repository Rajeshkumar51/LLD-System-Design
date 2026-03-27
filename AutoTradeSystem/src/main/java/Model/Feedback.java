package Model;

public class Feedback {
    private Buyer buyer;
    private Seller seller;
    private String message;

    public Feedback(Buyer b, Seller s, String msg) {
        this.buyer = b;
        this.seller = s;
        this.message = msg;
    }
}