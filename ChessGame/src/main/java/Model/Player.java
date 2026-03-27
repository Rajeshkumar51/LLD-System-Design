package Model;

import Enum.Color;

public class Player {
    private int playerId;
    private String name;
    private Color color;
    public Player(int playerId, String name, Color color) {
        this.playerId = playerId;
        this.name = name;
        this.color = color;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}