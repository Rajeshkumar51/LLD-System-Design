package Model;

public class Loan implements Payment {


    public void pay(double amount) {
        System.out.println("Loan Requested for ₹" + amount);
        double interestRate = 0.10;
        double total = amount + (amount * interestRate);
        double emi = total / 12;
        System.out.println("Loan Approved!");
        System.out.println("Total Payable after Interest: ₹" + total);
        System.out.println("EMI for 12 months: ₹" + emi);
    }
}