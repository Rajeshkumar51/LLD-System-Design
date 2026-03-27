package Model;

import java.util.HashMap;
import java.util.Map;

public class Users {
    private int userId;
    private String userName;
    private String uName;
    private String password;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    Map<Integer,Book> borrowedBook=new HashMap<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<Integer, Book> getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(Map<Integer, Book> borrowedBook) {
        this.borrowedBook = borrowedBook;
    }
}
