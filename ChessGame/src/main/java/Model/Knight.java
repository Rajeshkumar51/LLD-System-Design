package Model;
import Enum.Color;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color,(color==color.WHITE ?'N':'n'));
    }

    public boolean canMove(Board b, Cell f, Cell t) {
        int dx = Math.abs(f.getRow() - t.getRow());
        int dy = Math.abs(f.getCol() - t.getCol());
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
    public Piece clonePiece() {
        return new Knight(this.color);
    }
}