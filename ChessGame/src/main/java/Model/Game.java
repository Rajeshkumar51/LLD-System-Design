package Model;

import Enum.Color;

public class Game {

    private Player whitePlayer;
    private Player blackPlayer;
    private Player turn;
    private Board board;
    private boolean status = false;

    public Player getTurn() {
        return turn;
    }
    public Board getBoard() {
        return board;
    }
    public Game(Player white, Player black) {
        this.whitePlayer = white;
        this.blackPlayer = black;
        this.turn = white;
        this.board = new Board();
    }
    public void start() {
        status = true;
        System.out.println("Game Started");
    }
    public void swapTurn() {
        turn = (turn == whitePlayer) ? blackPlayer : whitePlayer;
    }
    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (!status) return false;

        Cell from = board.getCell(fromRow, fromCol);
        Cell to   = board.getCell(toRow, toCol);

        if (!from.isOccupied()) {
            System.out.println("No piece at from-cell");
            return false;
        }

        Piece piece = from.getPiece();
        if (piece.getColor() != turn.getColor()) {
            System.out.println("Not your turn");
            return false;
        }

        if (!piece.canMove(board, from, to)) {
            System.out.println("Invalid move");
            return false;
        }
        Board copy = board.deepCopy();
        copy.movePiece(copy.getCell(fromRow, fromCol), copy.getCell(toRow, toCol));
        if (isCheckOnBoard(copy, piece.getColor())) {
            System.out.println("Move leaves king in check! Illegal.");
            return false;
        }
        board.movePiece(from, to);
        System.out.println("Move successful");

        swapTurn();
        return true;
    }
    public boolean isCheck(Color color) {
        return isCheckOnBoard(board, color);
    }
    public boolean isCheckmate(Color color) {
        if (!isCheckOnBoard(board, color))
            return false;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getCell(r, c).getPiece();
                if (p == null || p.getColor() != color)
                    continue;
                for (int rr = 0; rr < 8; rr++) {
                    for (int cc = 0; cc < 8; cc++) {
                        Cell from = board.getCell(r, c);
                        Cell to   = board.getCell(rr, cc);
                        if (!p.canMove(board, from, to))
                            continue;
                        Board copy = board.deepCopy();
                        copy.movePiece(copy.getCell(r, c), copy.getCell(rr, cc));
                        if (!isCheckOnBoard(copy, color))
                            return false;
                    }
                }
            }
        }
        return true;
    }
    private boolean isCheckOnBoard(Board b, Color color) {
        Cell king = b.findKing(color);
        return b.isSquareAttacked(king, color);
    }

}