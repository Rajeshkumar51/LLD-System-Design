package Model;
import Enum.Color;
public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color,(color==color.WHITE)?'B':'b');
    }

    public boolean canMove(Board b, Cell f, Cell t) {
        return Math.abs(f.getRow() - t.getRow()) ==
                Math.abs(f.getCol() - t.getCol());
    }
    public Piece clonePiece() {
        return new Bishop(this.color);
    }
}