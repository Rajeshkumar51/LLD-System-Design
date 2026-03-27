package Model;

import Enum.Type;

public class Slot {
    private int slotId;
    private Type type;
    private boolean available;

    public Slot(int slotId, Type type) {
        this.slotId = slotId;
        this.type = type;
        this.available = true;
    }

    public int getSlotId() {
        return slotId;
    }
    public Type getType() {
        return type;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}