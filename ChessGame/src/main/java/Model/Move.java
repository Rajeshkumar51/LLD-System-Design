package Model;


public class Move {
    private Cell from;
    private Cell to;
    private Piece movedPiece;
    private Piece capturedPiece;

    public Move(Cell from, Cell to, Piece movedPiece, Piece capturedPiece) {
        this.from = from;
        this.to = to;
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
    }
}