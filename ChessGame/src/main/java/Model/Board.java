package Model;

import Enum.Color;

public class Board {

    private Cell[][] grid = new Cell[8][8];

    public Board() {
        initialize();
    }
    public Board(boolean empty) {
        grid = new Cell[8][8];
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                grid[r][c] = new Cell(r, c, null);
    }

    public Cell getCell(int r, int c) {
        return grid[r][c];
    }
    public void initialize() {
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                grid[r][c] = new Cell(r,c,null);
        grid[7][0].setPiece(new Rook(Color.WHITE));
        grid[7][1].setPiece(new Knight(Color.WHITE));
        grid[7][2].setPiece(new Bishop(Color.WHITE));
        grid[7][3].setPiece(new Queen(Color.WHITE));
        grid[7][4].setPiece(new King(Color.WHITE));
        grid[7][5].setPiece(new Bishop(Color.WHITE));
        grid[7][6].setPiece(new Knight(Color.WHITE));
        grid[7][7].setPiece(new Rook(Color.WHITE));

        for (int i=0;i<8;i++)
            grid[6][i].setPiece(new Pawn(Color.WHITE));
        grid[0][0].setPiece(new Rook(Color.BLACK));
        grid[0][1].setPiece(new Knight(Color.BLACK));
        grid[0][2].setPiece(new Bishop(Color.BLACK));
        grid[0][3].setPiece(new Queen(Color.BLACK));
        grid[0][4].setPiece(new King(Color.BLACK));
        grid[0][5].setPiece(new Bishop(Color.BLACK));
        grid[0][6].setPiece(new Knight(Color.BLACK));
        grid[0][7].setPiece(new Rook(Color.BLACK));
        for (int i=0;i<8;i++)
            grid[1][i].setPiece(new Pawn(Color.BLACK));
    }
    public void movePiece(Cell from, Cell to) {
        to.setPiece(from.getPiece());
        from.setPiece(null);
    }
    public Cell findKing(Color color) {
        for (int r=0;r<8;r++) {
            for (int c=0;c<8;c++) {
                Piece p = grid[r][c].getPiece();
                if (p instanceof King && p.getColor()==color) {
                    return grid[r][c];
                }
            }
        }
        return null;
    }
    public boolean isSquareAttacked(Cell target, Color kingColor) {
        Color enemy = (kingColor==Color.WHITE?Color.BLACK:Color.WHITE);
        for (int r=0;r<8;r++) {
            for (int c=0;c<8;c++) {
                Piece p = grid[r][c].getPiece();
                if (p == null || p.getColor()!=enemy) continue;

                if (p.canMove(this, grid[r][c], target))
                    return true;
            }
        }
        return false;
    }
    public Board deepCopy() {
        Board copy = new Board();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {

                Piece p = this.grid[r][c].getPiece();
                if (p != null) {
                    copy.grid[r][c].setPiece(p.clonePiece());
                }
                else {
                    copy.grid[r][c].setPiece(null);
                }
            }
        }

        return copy;
    }
}