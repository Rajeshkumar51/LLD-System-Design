package Model;
import Enum.Type;
public class Vehicle {
     private String name;
     private Type type;
     public Vehicle(String name ,Type type){
         this.name=name;
         this.type=type;
     }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

}
