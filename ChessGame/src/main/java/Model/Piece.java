package Model;
import Enum.Color;

public abstract class Piece {
    protected Color color;
    protected char symbol;
    public Piece(Color color, char symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
    public Color getColor() {
        return color;
    }

    public abstract boolean canMove(Board board, Cell from, Cell to);

    public abstract Piece clonePiece();
}
