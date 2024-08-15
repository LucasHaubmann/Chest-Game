package application;

import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import exceptions.ChessException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();

                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces() ,possibleMoves);
                System.out.println();
                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if(capturedPiece != null){
                    captured.add(capturedPiece);
                }

                if (chessMatch.getPromoted() != null){
                    System.out.print("Enter piece to promotion (♝/♞/♜/♛): ");
                    String type = sc.nextLine();
                    while (!type.equals("♝") && !type.equals("♞") && !type.equals("♜") & !type.equals("♛")){
                        System.out.print("Invalid value! Enter piece to promotion (♝/♞/♜/♛): ");
                        type = sc.nextLine();
                    }
                    chessMatch.replacePromotedPiece(type);
                }
            }catch (ChessException e ){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch (InputMismatchException e ){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
