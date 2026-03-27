package Model;

public class UPI implements Payment {

    public void pay(double amount) {
        System.out.println("UPI Payment of " + amount + " completed.");
    }
}
