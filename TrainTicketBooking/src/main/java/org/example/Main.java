package org.example;
import Model.TrainSystem;
import Service.TrainBookingService;

import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
             Scanner sc=new Scanner(System.in);
          TrainSystem trainSys=new TrainSystem();
        TrainBookingService service=new TrainBookingService(trainSys);
            while(true) {
                System.out.println("Welcome To The TrainBooking System");
                System.out.println("1.Login");
                System.out.println("2.Logout");
                System.out.println("3.Check Train Availability");
                System.out.println("4.book Ticket");
                System.out.println("5.cancel Booking");
                System.out.println("6.add users");
                System.out.println("7.add trains");
                System.out.println("8.Booking Details");
                System.out.println("9.Travel History");
                System.out.println("0.Exit");
                System.out.println("Enter your choice:");
                int choice =sc.nextInt();
                switch(choice){

                    case 1:
                        System.out.println("Enter the username");
                        String  uName=sc.next();
                        sc.nextLine();
                        System.out.println("Enter the password");
                        String uPass=sc.next();
                        sc.nextLine();
                        service.login(uName,uPass);
                        break;
                    case 2:
                        System.out.println("Are you want to logout(yes/no)");
                        String opt=sc.nextLine();
                        if(opt.equalsIgnoreCase("yes")) {
                            service.logout();
                        }
                        break;
                    case 3:
                        System.out.println("Enter the trainId");
                        int tId=sc.nextInt();
                        System.out.println("Enter the Train name");
                        String tName=sc.nextLine();
                        service.checkTrain(tId,tName);
                        break;
                    case 4:
                        System.out.println("Enter train id");
                        tId = sc.nextInt();
                        System.out.println("Enter number of seats");
                        int count = sc.nextInt();
                        service.bookTicket(tId, count);
                        break;
                    case 5:
                        System.out.println("Enter booking id");
                        int bid = sc.nextInt();
                        service.cancelBooking(bid);
                        break;
                    case 6:
                        System.out.println("Enter user id");
                        int uid = sc.nextInt(); sc.nextLine();
                        System.out.println("Enter name");
                        String name = sc.nextLine();
                        System.out.println("Enter password");
                        String pass = sc.nextLine();
                        service.addUser(uid, name, pass);
                        break;
                    case 7:
                        System.out.println("Enter train id");
                        int tid = sc.nextInt(); sc.nextLine();
                        System.out.println("Enter train name");
                        String tname = sc.nextLine();
                        System.out.println("From station");
                        String from = sc.nextLine();
                        System.out.println("To station");
                        String to = sc.nextLine();
                        System.out.println("Total seats");
                        int seats = sc.nextInt();
                        service.addTrain(tid, tname, from, to, seats);
                        break;
                    case 8:
                        service.getBookingDetails();
                        break;
                    case 9:
                        service.getUserHistory();
                        break;
                    case 0:
                        return;
                    default :
                        System.out.println("Invalid choice");

                }
            }
    }
}