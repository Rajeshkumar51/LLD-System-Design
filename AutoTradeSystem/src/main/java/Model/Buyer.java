package Model;
import Enum.Role;
import java.util.*;
public class Buyer extends User {
    private List<Rating> buyerRatings = new ArrayList<>();
    public Buyer(int id, String name, String passkey) {
        super(id, name, passkey, Role.BUYER);
    }
    public void addBuyerRating(Rating r) {
        buyerRatings.add(r);
    }
    public List<Rating> getRatings() { return buyerRatings; }
}