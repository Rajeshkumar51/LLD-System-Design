package Model;
import java.util.*;
public class Library {
    Map<Integer,Book>books=new HashMap<>();
    Map<Integer,Users>users=new HashMap<>();

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<Integer, Book> books) {
        this.books = books;
    }

    public Map<Integer, Users> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, Users> users) {
        this.users = users;
    }

}
