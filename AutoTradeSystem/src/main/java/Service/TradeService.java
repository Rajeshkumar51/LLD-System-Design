package Service;

import Model.*;
import java.util.*;
import Enum.*;

public class TradeService {
    private AutoTrade auto;
    private User currentUser = null;
    private int vehicleCounter = 1;
    private int paymentCounter = 1;
    private int dealCounter = 1;
    public TradeService(AutoTrade auto) {
        this.auto = auto;
    }
    public void register(int id, String name, String passkey, Role role) {
        if (auto.getUsers().containsKey(id)) {
            System.out.println("User Already Exists!");
            return;
        }
        User user;
        switch (role) {
            case BUYER:
                user = new Buyer(id, name, passkey);
                break;
            case SELLER:
                user = new Seller(id, name, passkey);
                break;
            case ADMIN:
                user = new Admin(id, name, passkey);
                break;
            case FINANCIER:
                user = new Financier(id, name, passkey);
                break;
            default:
                System.out.println("Invalid Role!");
                return;
        }
        auto.getUsers().put(id, user);
        System.out.println("User Registered Successfully!");
    }
    public void login(int id, String passkey) {
        User user = auto.getUsers().get(id);
        if (user == null) {
            System.out.println("Invalid User ID!");
            return;
        }
        if (!user.getPasskey().equals(passkey)) {
            System.out.println("Incorrect passkey!");
            return;
        }
        currentUser = user;
        System.out.println("Logged in as: " + currentUser.getUserName() + " | Role: " + currentUser.getRole());
    }
    public void logout() {
        if (currentUser == null) {
            System.out.println("No user logged in!");
            return;
        }
        System.out.println("User Logged Out: " + currentUser.getUserName());
        currentUser = null;
    }

    public void addVehicle(String brand, String model, int year,
                           vehicleType type, fuelType fuel, double price) {
        if (!requireRole(Role.SELLER)) {
            return;
        }
        if (price <= 0) {
            System.out.println("Price must be greater than zero!");
            return;
        }
        Seller seller = (Seller) currentUser;
        Vehicle v = new Vehicle(vehicleCounter++, brand, model, year, type, fuel, price, seller);
        auto.getVehicles().put(v.getId(), v);
        System.out.println("Vehicle Added Successfully! ID: " + v.getId());
    }
    public void viewAllVehicles() {
        if (auto.getVehicles().isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }
        for (Vehicle v : auto.getVehicles().values()) {
            System.out.println(
                    v.getId() + " | " + v.getBrand() + " " + v.getModel() +
                            " | " + v.getType() + " | " + v.getFuel() +
                            " | Rs." + v.getPrice() +
                            " | Seller: " + v.getSeller().getUserName() +
                            " | " + (v.isSold() ? "SOLD" : "AVAILABLE")
            );
        }
    }
    public void requestTestDrive(int vehicleId) {
        if (!requireRole(Role.BUYER)) {
            return;
        }
        Vehicle v = auto.getVehicles().get(vehicleId);
        if (v == null) {
            System.out.println("Invalid Vehicle ID!");
            return;
        }
        Buyer buyer = (Buyer) currentUser;
        Seller seller = v.getSeller();
        int tdId = auto.getTestDrives().size() + 1;
        TestDrive td = new TestDrive(tdId, buyer, seller, v);
        auto.getTestDrives().put(tdId, td);
        System.out.println("Test Drive Requested for: " + v.getBrand() + " " + v.getModel()
                + " | Test Drive ID: " + tdId);
    }
    public void buyVehicle(int vehicleId) {
        if (!requireRole(Role.BUYER)) {
            return;
        }
        Vehicle v = auto.getVehicles().get(vehicleId);
        if (v == null) {
            System.out.println("Invalid Vehicle ID!");
            return;
        }
        if (v.isSold()) {
            System.out.println("This vehicle has already been sold!");
            return;
        }
        Buyer buyer = (Buyer) currentUser;
        boolean hasTestDrived = auto.getTestDrives().values().stream()
                .anyMatch(td -> td.getBuyer().equals(buyer) &&
                        td.getVehicle().getId() == vehicleId);
        if (!hasTestDrived) {
            System.out.println("Access Denied! You must complete a Test Drive before buying.");
            return;
        }
        Seller seller = v.getSeller();
        Deal deal = new Deal(dealCounter++, buyer, seller, v, v.getPrice());
        v.setSold(true);
        auto.getDeals().put(deal.getId(), deal);
        System.out.println("Deal Created! Deal ID: " + deal.getId()
                + " | Amount: Rs." + v.getPrice());
    }

    public void makePayment(int dealId, paymentType mode) {
        Deal deal = auto.getDeals().get(dealId);
        if (deal == null) {
            System.out.println("Invalid Deal ID!");
            return;
        }
        if (deal.isPaid()) {
            System.out.println("This deal is already paid!");
            return;
        }
        Payment method;
        switch (mode) {
            case CASH:  method = new Cash();  break;
            case CARD:  method = new Card();  break;
            case UPI:   method = new UPI();   break;
            case LOAN:  method = new Loan();  break;
            default:
                System.out.println("Invalid Payment Mode!");
                return;
        }
        System.out.println("Processing Payment for Deal ID: " + dealId + "...");
        method.pay(deal.getAmount());
        deal.setPaid(true);
        deal.setStatus(DealStatus.COMPLETED);
        auto.getPayments().put(paymentCounter++, method);
        System.out.println("Payment Successful! Deal " + dealId + " marked as COMPLETED.");
    }
    public void viewVehicleHistory(int vid) {
        Vehicle v = auto.getVehicles().get(vid);
        if (v == null) {
            System.out.println("Invalid Vehicle ID!");
            return;
        }
        VehicleHistory history = v.getHistory();
        if (history == null) {
            System.out.println("No history available for this vehicle.");
            return;
        }
        System.out.println("Accidents: " + history.getAccidents());
        System.out.println("Service History: " + history.getServices());
    }

    public void rateSeller(int sellerId, int stars) {
        if (!requireRole(Role.BUYER)) {
            return;
        }
        if (stars < 1 || stars > 5) {
            System.out.println("Rating must be between 1 and 5!");
            return;
        }
        User user = auto.getUsers().get(sellerId);
        if (!(user instanceof Seller)) {
            System.out.println("Invalid Seller!");
            return;
        }
        Seller seller = (Seller) user;
        Buyer buyer = (Buyer) currentUser;
        boolean hadDeal = auto.getDeals().values().stream()
                .anyMatch(d -> d.getBuyer().equals(buyer) && d.getSeller().equals(seller));
        if (!hadDeal) {
            System.out.println("You can only rate a seller after completing a deal with them!");
            return;
        }
        Rating rating = new Rating(seller, buyer, stars);
        seller.addSellerRating(rating);
        System.out.println("Seller Rated Successfully!");
    }

    public void feedbackBuyer(int buyerId, int stars) {
        if (!requireRole(Role.SELLER)) return;
        if (stars < 1 || stars > 5) {
            System.out.println("Rating must be between 1 and 5!");
            return;
        }
        User user = auto.getUsers().get(buyerId);
        if (!(user instanceof Buyer)) {
            System.out.println("Invalid Buyer!");
            return;
        }
        Buyer buyer = (Buyer) user;
        Seller seller = (Seller) currentUser;
        boolean hadDeal = auto.getDeals().values().stream()
                .anyMatch(d -> d.getBuyer().equals(buyer) && d.getSeller().equals(seller));
        if (!hadDeal) {
            System.out.println("You can only give feedback to a buyer after completing a deal with them!");
            return;
        }
        Rating rating = new Rating(seller, buyer, stars);
        buyer.addBuyerRating(rating);
        System.out.println("Buyer Feedback Submitted!");
    }
    private boolean requireRole(Role r) {
        if (currentUser == null) {
            System.out.println("Login required!");
            return false;
        }
        if (currentUser.getRole() != r) {
            System.out.println("Access Denied! Required role: " + r);
            return false;
        }
        return true;
    }
}