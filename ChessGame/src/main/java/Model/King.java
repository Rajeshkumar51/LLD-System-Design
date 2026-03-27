package Model;
import Enum.Color;
public class King extends Piece {

    public King(Color color) {
        super(color,(color==color.WHITE ?'K':'k'));
    }


    public boolean canMove(Board board, Cell from, Cell to) {
        int dx = Math.abs(from.getRow() - to.getRow());
        int dy = Math.abs(from.getCol() - to.getCol());
        return dx <= 1 && dy <= 1;
    }
    public Piece clonePiece() {
        return new King(this.color);
    }
}