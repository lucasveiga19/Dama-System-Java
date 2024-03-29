package application;

import dama.Color;
import dama.DamaMatch;
import dama.DamaPiece;
import dama.DamaPosition;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static DamaPosition readDamaPosition(Scanner sc){
        try{
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new DamaPosition(column, row);
        }catch (RuntimeException e){
            throw new InputMismatchException("Error reading DamaPosition. Valid values are from a1 to h8");
        }
    }

    public static void printMatch(DamaMatch damaMatch, List<DamaPiece> captured){
        printBoard(damaMatch.getPieces());
        System.out.println();
        printCapturePieces(captured);
        System.out.println();
        System.out.println("Turn: "+damaMatch.getTurn());
        System.out.println("Waiting player: "+damaMatch.getCurrentPlayer());
    }

    public static void printBoard(DamaPiece[][] pieces) {
        for (int i=0; i < pieces.length; i++){
            System.out.print((8 -i) + " ");
            for (int j=0; j<pieces.length; j++){
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  ａ..ｂ..ｃ.ｄ..ｅ.ｆ..ｇ..h");
    }


    public static void printBoard(DamaPiece[][] pieces, boolean[][] possibleMoves){
        for (int i=0; i < pieces.length; i++){
            System.out.print((8 -i) + " ");
            for (int j=0; j<pieces.length; j++){
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
            System.out.println("  ａ..ｂ..ｃ.ｄ..ｅ.ｆ..ｇ..h");
        }

        private static void printPiece(DamaPiece piece, boolean background){
            if (background){
                System.out.print(ANSI_RED_BACKGROUND);
            }
        if (piece == null){
            System.out.print("➖"+ ANSI_RESET);
        }
        else{
            if (piece.getColor() == Color.RED) {
                System.out.print(ANSI_RED + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_BLUE + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printCapturePieces(List<DamaPiece> captured){
        List<DamaPiece> red = captured.stream().filter(x -> x.getColor() == Color.RED).collect(Collectors.toList());
        List<DamaPiece> blue = captured.stream().filter(x -> x.getColor() == Color.BLUE).collect(Collectors.toList());
        System.out.println("Captured pieces:");
        System.out.print("RED: ");
        System.out.print(ANSI_RED);
        System.out.println(Arrays.toString(red.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("BLUE: ");
        System.out.print(ANSI_BLUE);
        System.out.println(Arrays.toString(blue.toArray()));
        System.out.print(ANSI_RESET);
    }
}

