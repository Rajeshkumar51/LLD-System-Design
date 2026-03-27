package org.example;
import Model.Library;
import Model.Role;
import Model.Users;
import Service.LibraryService;

import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library=new Library();
        LibraryService service=new LibraryService(library);
        Users admin = new Users();
        admin.setUserId(1);
        admin.setUserName("System Admin");
        admin.setuName("Rajesh");
        admin.setPassword("12345");
        admin.setRole(Role.ADMIN);

        library.getUsers().put(1, admin);

        while(true){
            System.out.println("\nLibrary Menu");
            System.out.println("1 Login");
            System.out.println("2 Logout");
            System.out.println("3 Add User");
            System.out.println("4 Add Book");
            System.out.println("5 Borrow Book");
            System.out.println("6 Return Book");
            System.out.println("7 View Books");
            System.out.println("Enter your choice:");
            int choice =sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    System.out.println("Enter the userName");
                    String username=sc.nextLine();
                    System.out.println("Enter the Password:");
                    String  password=sc.nextLine();
                    service.login(username,password);
                    break;
                case 2:
                      System.out.println("Do you want to logout(yes\no:");
                      String opt=sc.nextLine();
                      if(opt.equalsIgnoreCase("yes")){
                          service.logout();

                      }
                      break;
                case 3:
                    System.out.println("Enter the userId:");
                    int uid=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the name:");
                    String uname=sc.nextLine();
                    System.out.println("Enter the Username:");
                    username=sc.nextLine();
                    System.out.println("Enter the Password:");
                    password=sc.nextLine();
                    System.out.println("Enter the Role (ADMIN/MEMBER):");
                    String roleInput=sc.nextLine();
                    Role role= Role.valueOf(roleInput.toUpperCase());

                    service.addUser(uid,uname,username,password,role);
                    break;
                case 4:
                    System.out.println("Enter the book id:");
                    int bid=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the title:");
                    String title=sc.nextLine();
                    System.out.println("Enter the Author name:");
                    String  author=sc.nextLine();
                    service.addBook(bid,title,author);
                    break;

                case 5:
                    System.out.println("Enter the bookId:");
                    bid=sc.nextInt();
                    service.borrowBook(bid);
                    break;
                case 6:
                    System.out.println("Enter the bookId");
                    bid=sc.nextInt();
                    service.returnBook(bid);
                    break;
                case 7:
                     service.viewAllBooks();
                     break;

                case 0:
                    System.out.println("Exit");
                    return;

                default:
                    System.out.println("Invalid Choice:");

            }
        }

    }
}