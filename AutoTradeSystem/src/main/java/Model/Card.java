package Model;
public class Card implements Payment {
    @Override
    public void pay(double amount) { System.out.println("Card Payment of " + amount + " successful."); }
}