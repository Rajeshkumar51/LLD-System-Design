package Model;

public class Rating {
    private Seller seller;
    private Buyer buyer;
    private int stars;

    public Rating(Seller s, Buyer b, int stars) {
        this.seller = s;
        this.buyer = b;
        this.stars = stars;
    }
}