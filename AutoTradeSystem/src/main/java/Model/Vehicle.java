package Model;
import Enum.*;
public class Vehicle {
    private int id;
    private String brand, model;
    private int year;
    private vehicleType type;
    private fuelType fuel;
    private double price;
    private Seller seller;
    private boolean sold;
    private VehicleHistory history;
    public Vehicle(int id, String brand, String model, int year, vehicleType type, fuelType fuel, double price, Seller seller) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.type = type;
        this.fuel = fuel;
        this.price = price;
        this.seller = seller;
        this.sold = false;
    }
    public int getId() {
        return id;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public vehicleType getType() {
        return type;
    }
    public fuelType getFuel() {
        return fuel;
    }
    public double getPrice() {
        return price;
    }
    public Seller getSeller() {
        return seller;
    }
    public boolean isSold() {
        return sold;
    }
    public void setSold(boolean sold) {
        this.sold = sold;
    }
    public VehicleHistory getHistory() {
        return history;
    }
}