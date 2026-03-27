package Model;
import Enum.Color;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color,(color==color.WHITE ?'Q':'q'));
    }
    public boolean canMove(Board board, Cell from, Cell to) {
        int dx = Math.abs(from.getRow() - to.getRow());
        int dy = Math.abs(from.getCol() - to.getCol());
        return dx == dy || dx == 0 || dy == 0;
    }
    public Piece clonePiece() {
        return new Queen(this.color);
    }
}