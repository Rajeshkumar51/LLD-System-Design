package Model;
import java.util.*;
public class AutoTrade {
    public Map<Integer, User> users = new HashMap<>();
    public Map<Integer, Vehicle> vehicles = new HashMap<>();
    public Map<Integer, TestDrive> testDrives = new HashMap<>();
    public Map<Integer, Deal> deals = new HashMap<>();

    public Map<Integer, User> getUsers() {
        return users;
    }
    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }
    public Map<Integer, Vehicle> getVehicles() {
        return vehicles;
    }
    public void setVehicles(Map<Integer, Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    public Map<Integer, TestDrive> getTestDrives() {
        return testDrives;
    }
    public void setTestDrives(Map<Integer, TestDrive> testDrives) {
        this.testDrives = testDrives;
    }
    public Map<Integer, Deal> getDeals() {
        return deals;
    }
    public void setDeals(Map<Integer, Deal> deals) {
        this.deals = deals;
    }
    public Map<Integer, Payment> getPayments() {
        return payments;
    }
    public void setPayments(Map<Integer, Payment> payments) {
        this.payments = payments;
    }
    public Map<Integer, Payment> payments = new HashMap<>();
}
