package Model;
import Enum.Role;
import java.util.*;
public class Seller extends User {
    private List<Rating> sellerRatings = new ArrayList<>();
    public Seller(int id, String name, String passkey) {
        super(id, name, passkey, Role.SELLER);
    }
    public void addSellerRating(Rating r) { sellerRatings.add(r); }
    public List<Rating> getRatings() { return sellerRatings; }
}