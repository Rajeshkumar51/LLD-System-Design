package org.example;
import Model.*;
import Enum.Color;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player white = new Player(1, "Rajesh", Color.WHITE);
        Player black = new Player(2, "Suhain", Color.BLACK);
        Game game = new Game(white, black);
        game.start();
        System.out.println("Welcome to Chess Game");
        System.out.println("Enter moves in format: h2 h4");
        System.out.println("Enter 'Quit' to Exit \n");

        while (true) {
            printBoard(game.getBoard());
            System.out.print(game.getTurn().getName() +
                    " (" + game.getTurn().getColor() + ") move: ");

            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("quit")) {
                System.out.println("Game ended.");
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid format! Use: e2 e4");
                continue;
            }
            int[] from = convert(parts[0]);
            int[] to   = convert(parts[1]);
            if (from == null || to == null) {
                System.out.println("Invalid square. Use a1 to h8.");
                continue;
            }
            game.makeMove(from[0], from[1], to[0], to[1]);

            if (game.isCheckmate(Color.BLACK)) {
                printBoard(game.getBoard());
                System.out.println("CHECKMATE! White wins.");
                break;
            }
            if (game.isCheckmate(Color.WHITE)) {
                printBoard(game.getBoard());
                System.out.println("CHECKMATE! Black wins.");
                break;
            }
        }
    }
    private static int[] convert(String move) {
        if (move.length() != 2) return null;
        char file = move.charAt(0);
        char rank = move.charAt(1);
        if (file < 'a' || file > 'h')
            return null;
        if (rank < '1' || rank > '8')
            return null;

        int col = file - 'a';
        int row = 8 - (rank - '0');
        return new int[]{row, col};
    }
    private static void printBoard(Board board) {
        System.out.println("\n  a b c d e f g h");
        for (int r = 0; r < 8; r++) {
            System.out.print(8 - r + " ");
            for (int c = 0; c < 8; c++) {
                Piece p = board.getCell(r, c).getPiece();
                char sym = (p == null) ? '.' : p.getSymbol();
                System.out.print(sym + " ");
            }
            System.out.println(" " + (8 - r));
        }
        System.out.println("  a b c d e f g h\n");
    }
}