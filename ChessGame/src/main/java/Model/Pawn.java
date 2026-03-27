package Model;
import Enum.Color;
public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color,(color==color.WHITE ? 'P':'p'));
    }


    public boolean canMove(Board board, Cell from, Cell to) {
        int direction = (color == Color.WHITE) ? -1 : 1;
        int dx = to.getRow() - from.getRow();
        int dy = Math.abs(to.getCol() - from.getCol());

        if (dy == 0 && dx == direction && !to.isOccupied())
            return true;

        if (dy == 1 && dx == direction && to.isOccupied())
            return to.getPiece().getColor() != this.color;

        return false;
    }
    public Piece clonePiece() {
        return new King(this.color);
    }
}