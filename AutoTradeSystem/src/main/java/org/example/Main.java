package org.example;

import Service.TradeService;
import Model.*;
import Enum.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AutoTrade auto = new AutoTrade();
        TradeService service = new TradeService(auto);
        while (true) {
            System.out.println("\n AUTO TRADE SYSTEM ");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Add Vehicle");
            System.out.println("5. View All Vehicles");
            System.out.println("6. Request Test Drive");
            System.out.println("7. Buy Vehicle");
            System.out.println("8. Make Payment");
            System.out.println("9. View Vehicle History");
            System.out.println("10.Rate Seller");
            System.out.println("11.Feedback Buyer");
            System.out.println("0.Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter the Passkey: ");
                    String passkey = sc.nextLine();
                    System.out.println("Role: 1=Buyer  2=Seller  3=Admin  4=Financier");
                    int r = sc.nextInt();
                    sc.nextLine();
                    if (r < 1 || r > 4) {
                        System.out.println("Invalid role! Please enter 1, 2, 3, or 4.");
                        break;
                    }
                    Role role = (r == 1) ? Role.BUYER
                            : (r == 2) ? Role.SELLER
                            : (r == 3) ? Role.ADMIN
                            : Role.FINANCIER;

                    service.register(uid, uname, passkey, role);
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    int lid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Passkey: ");
                    String loginPasskey = sc.nextLine();
                    service.login(lid, loginPasskey);
                    break;
                case 3:
                    service.logout();
                    break;
                case 4:
                    System.out.print("Brand: ");
                    String brand = sc.nextLine();
                    System.out.print("Model: ");
                    String model = sc.nextLine();
                    System.out.print("Year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Vehicle Type (1=FOURWHEELER 2=TWOWHEELER): ");
                    int typeInput = sc.nextInt();
                    sc.nextLine();
                    if (typeInput < 1 || typeInput > 2) {
                        System.out.println("Invalid vehicle type!");
                        break;
                    }
                    vehicleType type = (typeInput == 1) ? vehicleType.FOURWHEELER : vehicleType.TWOWHEELER;
                    System.out.print("Fuel (1=PETROL 2=DIESEL 3=EV): ");
                    int fuelInput = sc.nextInt();
                    sc.nextLine();
                    if (fuelInput < 1 || fuelInput > 3) {
                        System.out.println("Invalid fuel type!");
                        break;
                    }
                    fuelType fuel = fuelType.values()[fuelInput - 1];
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    service.addVehicle(brand, model, year, type, fuel, price);
                    break;
                case 5:
                    service.viewAllVehicles();
                    break;
                case 6:
                    System.out.print("Enter Vehicle ID: ");
                    int tvId = sc.nextInt();
                    sc.nextLine();
                    service.requestTestDrive(tvId);
                    break;
                case 7:
                    System.out.print("Enter Vehicle ID: ");
                    int bv = sc.nextInt();
                    sc.nextLine();
                    service.buyVehicle(bv);
                    break;

                case 8:
                    System.out.print("Enter Deal ID to pay for: ");
                    int dealId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Payment Mode: 1=Cash  2=Card  3=UPI  4=Loan");
                    int p = sc.nextInt();
                    sc.nextLine();
                    if (p < 1 || p > 4) {
                        System.out.println("Invalid payment mode!");
                        break;
                    }
                    paymentType pMode = (p == 1) ? paymentType.CASH
                            : (p == 2) ? paymentType.CARD
                            : (p == 3) ? paymentType.UPI
                            : paymentType.LOAN;
                    service.makePayment(dealId, pMode);
                    break;
                case 9:
                    System.out.print("Enter Vehicle ID: ");
                    int hv = sc.nextInt();
                    sc.nextLine();
                    service.viewVehicleHistory(hv);
                    break;
                case 10:
                    System.out.print("Enter Seller ID: ");
                    int sid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Rating (1-5): ");
                    int rate = sc.nextInt();
                    sc.nextLine();
                    service.rateSeller(sid, rate);
                    break;
                case 11:
                    System.out.print("Enter Buyer ID: ");
                    int bid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Rating (1-5): ");
                    int brate = sc.nextInt();
                    sc.nextLine();
                    service.feedbackBuyer(bid, brate);
                    break;

                case 0:
                    System.out.println("Exiting AutoTrade System...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option! Please choose from the menu.");
            }
        }
    }
}