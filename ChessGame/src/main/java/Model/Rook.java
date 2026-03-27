package Model;
import Enum.Color;
public class Rook extends Piece {
    public Rook(Color color) {
        super(color,(color==color.WHITE)?'R':'r');
    }

    public boolean canMove(Board b, Cell f, Cell t) {
        return f.getRow() == t.getRow() || f.getCol() == t.getCol();
    }
    public Piece clonePiece() {
        return new Rook(this.color);
    }
}