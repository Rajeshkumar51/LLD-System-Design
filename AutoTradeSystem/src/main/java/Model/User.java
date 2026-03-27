package Model;
import Enum.Role;
public abstract class User {
    private int id;
    private String userName;
    private String passkey;
    private Role role;
    public User(int id, String userName, String passkey, Role role) {
        this.id = id;
        this.userName = userName;
        this.passkey = passkey;
        this.role = role;
    }
    public int getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public String getPasskey() {
        return passkey;
    }
    public Role getRole() {
        return role;
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        return this.id == ((User) obj).id;
    }
    public int hashCode() {
        return Integer.hashCode(id);
    }
}