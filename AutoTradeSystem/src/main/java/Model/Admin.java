package Model;

import Enum.Role;

public class Admin extends User {

    public Admin(int id, String name, String password) {
        super(id, name, password, Role.ADMIN);
    }
}