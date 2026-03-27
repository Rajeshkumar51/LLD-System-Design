package Model;
import Enum.Role;
public class Financier extends User {
    public Financier(int id, String name, String passkey) {
        super(id, name, passkey, Role.FINANCIER);
    }
}