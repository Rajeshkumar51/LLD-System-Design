package org.example;

import Enum.Type;
import Model.ParkingLot;
import Model.Ticket;
import Model.Payment;
import Model.Vehicle;
import Service.LotService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot parking = new ParkingLot();
        LotService service = new LotService(parking);
        while (true) {
            System.out.println("\n RK Parking Lot System ");
            System.out.println("1. Add User");
            System.out.println("2. Add Slot");
            System.out.println("3. Check Available Slots");
            System.out.println("4. Book Slot");
            System.out.println("5. Generate Ticket");
            System.out.println("6. Exit Vehicle (Calculate Fee)");
            System.out.println("7. Pay Fee");
            System.out.println("8. Get All Users");
            System.out.println("9. Get All Slots");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter User Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Vehicle Name: ");
                    String vname = sc.nextLine();
                    System.out.println("Vehicle Type: 1 = TWOWHEELER, 2 = FOURWHEELER");
                    int type = sc.nextInt();
                    Vehicle vehicle = new Vehicle(vname,
                            type == 1 ? Type.TWOWHEELER : Type.FOURWHEELER);
                    service.addUser(userId, name, vehicle);
                    break;
                case 2:
                    System.out.print("Enter Slot ID: ");
                    int slotId = sc.nextInt();
                    System.out.println("Slot Type: 1 = TWOWHEELER, 2 = FOURWHEELER");
                    int stype = sc.nextInt();
                    service.addSlot(slotId,
                            stype == 1 ? Type.TWOWHEELER : Type.FOURWHEELER);
                    break;
                    case 3:
                    System.out.println("Enter Type to Filter: 1 = TWOWHEELER, 2 = FOURWHEELER");
                    int ttype = sc.nextInt();
                    Type filterType = ttype == 1 ? Type.TWOWHEELER : Type.FOURWHEELER;
                    service.getAvailableSlots(filterType);
                    break;
                case 4:
                    System.out.print("Enter Slot ID to Book: ");
                    int bslotId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int bUserId = sc.nextInt();
                    service.bookSlot(bslotId, bUserId);
                    break;
                case 5:
                    System.out.print("Enter User ID: ");
                    int tUser = sc.nextInt();
                    System.out.print("Enter Booked Slot ID: ");
                    int tSlot = sc.nextInt();
                    Ticket ticket = service.generateTicket(tUser, tSlot);
                    if (ticket != null)
                        System.out.println("Ticket Generated! Ticket ID = " + ticket.getTicketId());
                    break;
                case 6:
                    System.out.print("Enter Ticket ID: ");
                    int exitTid = sc.nextInt();
                    Payment payment = service.exitVehicle(exitTid);
                    if (payment != null)
                        System.out.println("Payable Amount: ₹" + payment.getAmount() +
                                " | Payment ID = " + payment.getPaymentId());
                    break;
                case 7:
                    System.out.print("Enter Payment ID: ");
                    int payId = sc.nextInt();
                    service.payBill(payId);
                    break;
                case 8:
                    service.getAllUsers();
                    break;
                case 9:
                    service.getAllSlots();
                    break;
                case 0:
                    System.out.println("Exiting Parking System...");
                    return;

                default:
                    System.out.println("INVALID OPTION!");
            }
        }
    }
}