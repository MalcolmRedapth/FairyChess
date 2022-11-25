import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataExtraction {
    public static int[] blackPieceAllocationExtraction(String[] boardfile) {
        int[] blackPieces = new int[11];
        int count = 0;

        // Initialize the Scanner

        // Read the file line by line, printing out as we go along

        for (int i = 0; i < boardfile.length; i++) {
            String line = boardfile[i];
            // this is where i read stuff in

            // checking where I am
            if (line.contains("-")) {
                if (!line.contains("-----") && !line.contains(":")) {
                    BoardValidationErrors.illegalPieceAllocation(i + 1);
                }
                count++;
            }

            // This is for the allocations
            if (count == 0 && !line.contains("-") && !line.contains("%")) {
                switch (line.charAt(0)) {
                case 'k':
                    blackPieces[0] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.length()));
                    continue;

                case 'r':
                    blackPieces[1] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.length()));
                    continue;

                case 'q':
                    blackPieces[2] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.length()));
                    continue;

                case 'n':
                    blackPieces[3] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.length()));
                    continue;

                case 'b':
                    blackPieces[4] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.length()));
                    continue;

                case 'p':
                    blackPieces[5] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.length()));
                    continue;

                case 'd':
                    blackPieces[6] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.length()));
                    continue;

                case 'f':
                    blackPieces[7] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.length()));
                    continue;
                case 'e':
                    blackPieces[8] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.length()));
                    continue;
                case 'a':
                    blackPieces[9] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.length()));
                    continue;
                case 'w':
                    blackPieces[10] = Integer.parseInt(line.substring(line.indexOf(":") + 1, line.length()));
                    continue;

                }
            }
        }

        return blackPieces;

    }

    public static void whitePieceAllocationExtraction(String boardfile) {

    }

    public static char[][] boardArrangementExtraction(String[] boardfile, char[][] board) {
        int count = 0;
        int rank = 0;
        // Initialize the Scanner
        for (int i = 0; i < boardfile.length; i++) {
            String line = boardfile[i];

            if (line.contains("-----")) {
                count++;
            }

            if (count == 1 && !line.contains("%") && !line.contains("-")) {
                String boardLine = line.replace(" ", "");
                if (!(boardLine.length() == 10)) {
                    BoardValidationErrors.illegalBoardDimension();
                }
                for (int file = 0; file < 10; file++) {

                    board[rank][file] = boardLine.charAt(file);
                }

                rank++;
            }

        }

        return board;
    }

    public static boolean playerTurnExtraction(String[] boardfile) {
        boolean playerTurn = true;
        // standard stuff
        int count = 0;
        // Initialize the Scanner

        // Read the file line by line, printing out as we go along
        for (int i = 0; i < boardfile.length; i++) {
            String line = boardfile[i];
            // this is where i read stuff in

            // checking where I am
            if (line.contains("-----")) {
                count++;
            }
            if (count == 2 && !line.contains("-----") && !line.contains("%")) {
                
                if (line.charAt(0) == 'w') {
                    playerTurn = true;
                } else {
                    playerTurn = false;
                }

            }
        }

        return playerTurn;
    }

    public static boolean[] castelingOpportunitiesExtraction(String[] boardfile) {

        boolean[] castlingopportunities = new boolean[4];
        int count = 0;

        // Initialize the Scanner
        for (int i = 0; i < boardfile.length; i++) {
            String line = boardfile[i];
            
            // this is where i read stuff in

            // checking where I am

            if (count == 2 && !line.contains("%")) {

                String possibleCastlingOpportunities = line.substring(2, 6);

                if (possibleCastlingOpportunities.charAt(0) == '+') {
                    castlingopportunities[0] = true;
                } else {
                    castlingopportunities[0] = false;
                }

                if (possibleCastlingOpportunities.charAt(1) == '+') {
                    castlingopportunities[1] = true;
                } else {
                    castlingopportunities[1] = false;
                }
                if (possibleCastlingOpportunities.charAt(2) == '+') {
                    castlingopportunities[2] = true;
                } else {
                    castlingopportunities[2] = false;
                }
                if (possibleCastlingOpportunities.charAt(3) == '+') {
                    castlingopportunities[3] = true;

                } else {
                    castlingopportunities[3] = false;
                }

            }
            if (line.contains("-----")) {
                count++;
            }

        }

        return castlingopportunities;

    }

    public static int[] moveCounterExtraction(String[] boardfile) {
        int count = 0;
        int[] movesCounters = new int[2];

        // Initialize the Scanner
        for (int i = 0; i < boardfile.length; i++) {
            String line = boardfile[i];
            // this is where i read stuff in

            // checking where I am
            if (line.contains("-----")) {
                count++;
            }
            if (count == 2 && !line.contains("-") && !line.contains("%")) {
                String movesCounted = line.substring(7);
                movesCounters[0] = Integer.parseInt(movesCounted.charAt(0) + "");
                movesCounters[0] = Integer.parseInt(movesCounted.charAt(2) + "");
            }

        }

        return movesCounters;
    }
}
