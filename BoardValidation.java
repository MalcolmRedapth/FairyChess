import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoardValidation {

    public static void checkPawnAllocation(String[] boardfile) {
        int countLine = 0;
        int countPawnPieces = 0;

        for (int i = 0; i < boardfile.length; i++) {
            String line = boardfile[i];
            countLine++;

            if (line.startsWith("p") || line.startsWith("d")) {
                countPawnPieces += Integer.parseInt(line.substring(2, line.length()));
            }

            if (countPawnPieces > 10) {

                BoardValidationErrors.illegalPieceAllocation(countLine);

            }
            if (countPawnPieces < 10 && line.contains("-")) {

                BoardValidationErrors.illegalPieceAllocation(countLine);

            }
            if (countPawnPieces == 10 && line.contains("-")) {
                if (!line.contains("-----")) {
                    BoardValidationErrors.illegalPieceAllocation(countLine);
                }
                break;
            }
        }
    }

    public static void checkOfficerAllocation(String[] boardfile) {
        int countLine = 0;
        int countOfficerPieces = 0;
        boolean ThereIsNoKing = true;
        boolean ThereIsNotAtLeast2Rooks = true;

        for (int i = 0; i < boardfile.length; i++) {
            String line = boardfile[i];
            countLine++;
            //
            if (countOfficerPieces < 10 && line.contains("-")) {

                BoardValidationErrors.illegalPieceAllocation(countLine);

                ;

            }
            if ((ThereIsNoKing || ThereIsNotAtLeast2Rooks) && line.contains("-")) {

                BoardValidationErrors.illegalPieceAllocation(countLine);

            }

            if (countOfficerPieces == 10 && line.contains("-")) {
                break;
            }
            // ending up

            if (line.startsWith("k")) {
                ThereIsNoKing = false;
            }
            if (line.startsWith("r") && (Integer.parseInt(line.substring(2, line.length())) > 1)
                    && !line.contains("-")) {
                ThereIsNotAtLeast2Rooks = false;
            }
            if (line.startsWith("k") || line.startsWith("r") || line.startsWith("q") || line.startsWith("n")
                    || line.startsWith("b") || line.startsWith("f") || line.startsWith("e") || line.startsWith("a")
                    || line.startsWith("w") && !line.contains("-")) {
                countOfficerPieces += Integer.parseInt(line.charAt(2) + "");
            }

            if (countOfficerPieces > 10) {

                BoardValidationErrors.illegalPieceAllocation(countLine);

            }

        }
    }

    public static void checkIllegalPiece(String[] boardfile, int[] blackPieces) {

        // standard stuff
        String list = "krqnbpdfeawKRQNBPDFEAW.";

        int count = 0;
        int rank = 11;
        for (int i = 0; i < boardfile.length; i++) {
            String line = boardfile[i];

            if (line.contains("-----")) {
                count++;
            }

            if (count == 1 && !line.contains("-") && !line.contains("%")) {
                rank--;

                String boardLine = line.replace(" ", "");

                for (int file = 0; file < boardLine.length(); file++) {
                    if (!list.contains(line.charAt(file) + "")) {

                        BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                    }
                    switch (line.charAt(file)) {
                    case 'k':

                        if (blackPieces[0] == 0) {
                            BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'r':

                        if (blackPieces[1] == 0) {
                            BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'q':

                        if (blackPieces[2] == 0) {

                            BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'n':

                        if (blackPieces[3] == 0) {

                            BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'b':

                        if (blackPieces[4] == 0) {

                            BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'p':

                        if (blackPieces[5] == 0) {

                            BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'd':

                        if (blackPieces[6] == 0) {

                            BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'f':

                        if (blackPieces[7] == 0) {

                            BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'e':
                        if (blackPieces[8] == 0) {

                            BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'a':

                        if (blackPieces[9] == 0) {

                            BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'w':

                        if (blackPieces[10] == 0) {

                            BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    }

                }

            }

        }
    }

    public static void checkPawnExceedingAllocation(String[] boardfile, int[] blackPieces) {
        int[] theseWhitePawns = new int[11];
        int count = 0;
        int rank = 11;
        int[] theseBlackPieces = new int[11];
        for (int i = 0; i < boardfile.length; i++) {
            String line = boardfile[i];

            if (line.contains("-----")) {
                count++;
            }

            if (count == 1 && !line.contains("-") && !line.contains("%")) {
                rank--;
                String boardLine = line.replace(" ", "");
                for (int file = 0; file < boardLine.length(); file++) {
                    switch (line.charAt(file)) {
                    case 'k':
                        theseBlackPieces[0]++;
                        // if (blackPieces[0] < theseBlackPieces[0]) {
                        //
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;

                    case 'r':
                        theseBlackPieces[1]++;
                        // if (blackPieces[1] < theseBlackPieces[1]) {
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;

                    case 'q':
                        theseBlackPieces[2]++;
                        // if (blackPieces[2] < theseBlackPieces[2]) {
                        //
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;

                    case 'n':
                        theseBlackPieces[3]++;
                        // if (blackPieces[3] < theseBlackPieces[3]) {
                        //
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;

                    case 'b':
                        theseBlackPieces[4]++;
                        // if (blackPieces[4] < theseBlackPieces[4]) {
                        //
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;

                    case 'p':
                        theseBlackPieces[5]++;

                        if (blackPieces[5] < theseBlackPieces[5]) {

                            BoardValidationErrors.pawnAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'd':
                        theseBlackPieces[6]++;
                        if (blackPieces[6] < theseBlackPieces[6]) {

                            BoardValidationErrors.pawnAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'f':
                        theseBlackPieces[7]++;
                        // if (blackPieces[7] < theseBlackPieces[7]) {
                        //
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    case 'e':
                        theseBlackPieces[8]++;
                        // if (blackPieces[8] < theseBlackPieces[8]) {
                        //
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    case 'a':
                        theseBlackPieces[9]++;
                        // if (blackPieces[9] < theseBlackPieces[9]) {
                        //
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    case 'w':
                        theseBlackPieces[10]++;
                        // if (blackPieces[10] < theseBlackPieces[10]) {
                        //
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    // whites stuff
                    case 'K':
                        theseWhitePawns[0]++;
                        // if (blackPieces[0] < theseWhitePawns[0]) {
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    case 'R':
                        theseWhitePawns[1]++;
                        // if (blackPieces[1] < theseWhitePawns[1]) {
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    case 'Q':
                        theseWhitePawns[2]++;
                        // if (blackPieces[2] < theseWhitePawns[2]) {
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    case 'N':
                        theseWhitePawns[3]++;
                        // if (blackPieces[3] < theseWhitePawns[3]) {
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    case 'B':
                        theseWhitePawns[4]++;
                        // if (blackPieces[4] < theseWhitePawns[4]) {
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    case 'P':
                        theseWhitePawns[5]++;
                        if (blackPieces[5] < theseWhitePawns[5]) {
                            BoardValidationErrors.pawnAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'D':
                        theseWhitePawns[6]++;
                        if (blackPieces[6] < theseWhitePawns[6]) {
                            BoardValidationErrors.pawnAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'F':
                        // theseWhitePawns[7]++;
                        // if (blackPieces[7] < theseWhitePawns[7]) {
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    case 'E':
                        theseWhitePawns[8]++;
                        // if (blackPieces[8] < theseWhitePawns[8]) {
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    case 'A':
                        theseWhitePawns[9]++;
                        // if (blackPieces[9] < theseWhitePawns[9]) {
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;
                    case 'W':
                        theseWhitePawns[10]++;
                        // if (blackPieces[10] < theseWhitePawns[10]) {
                        // BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file),
                        // rank);
                        // }
                        break;

                    }

                }
            }

        }

    }

    public static void checkDynamicExcedeingOfficerAllocation(String[] boardfile, int[] blackPieces) {

        int[] theseWhitePieces = new int[11];
        int count = 0;
        int rank = 11;
        int[] theseBlackPieces = new int[11];
        for (int i = 0; i < boardfile.length; i++) {
            String line = boardfile[i];

            if (line.contains("-----")) {
                count++;
            }

            if (count == 1 && !line.contains("-") && !line.contains("%")) {
                rank--;
                String boardLine = line.replace(" ", "");
                for (int file = 0; file < boardLine.length(); file++) {
                    switch (line.charAt(file)) {
                    case 'k':
                        theseBlackPieces[0]++;
                        if (theseBlackPieces[0] + (theseBlackPieces[5] + theseBlackPieces[6]) > blackPieces[0] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'r':
                        theseBlackPieces[1]++;
                        if (theseBlackPieces[1] + (theseBlackPieces[5] + theseBlackPieces[6]) > blackPieces[1] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'q':
                        theseBlackPieces[2]++;
                        if (theseBlackPieces[2] + (theseBlackPieces[5] + theseBlackPieces[6]) > blackPieces[2] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'n':
                        theseBlackPieces[3]++;
                        if (theseBlackPieces[3] + (theseBlackPieces[5] + theseBlackPieces[6]) > blackPieces[3] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'b':
                        theseBlackPieces[4]++;
                        if (theseBlackPieces[4] + (theseBlackPieces[5] + theseBlackPieces[6]) > blackPieces[4] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    case 'p':
                        theseBlackPieces[5]++;

                        break;

                    case 'd':
                        theseBlackPieces[6]++;

                        break;

                    case 'f':
                        theseBlackPieces[7]++;
                        if (theseBlackPieces[7] + (theseBlackPieces[5] + theseBlackPieces[6]) > blackPieces[7] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'e':
                        theseBlackPieces[8]++;
                        if (theseBlackPieces[8] + (theseBlackPieces[5] + theseBlackPieces[6]) > blackPieces[8] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'a':
                        theseBlackPieces[9]++;
                        if (theseBlackPieces[9] + (theseBlackPieces[5] + theseBlackPieces[6]) > blackPieces[9] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'w':
                        theseBlackPieces[10]++;
                        if (theseBlackPieces[10] + (theseBlackPieces[5] + theseBlackPieces[6]) > blackPieces[10] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    // whites stuff
                    case 'K':
                        theseWhitePieces[0]++;
                        if (theseWhitePieces[0] + (theseWhitePieces[5] + theseWhitePieces[6]) > blackPieces[0] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'R':
                        theseWhitePieces[1]++;
                        if (theseWhitePieces[1] + (theseWhitePieces[5] + theseWhitePieces[6]) > blackPieces[1] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'Q':
                        theseWhitePieces[2]++;
                        if (theseWhitePieces[2] + (theseWhitePieces[5] + theseWhitePieces[6]) > blackPieces[2] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'N':
                        theseWhitePieces[3]++;
                        if (theseWhitePieces[3] + (theseWhitePieces[5] + theseWhitePieces[6]) > blackPieces[3] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'B':
                        theseWhitePieces[4]++;
                        if (theseWhitePieces[4] + (theseWhitePieces[5] + theseWhitePieces[6]) > blackPieces[4] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'P':
                        theseWhitePieces[5]++;

                        break;
                    case 'D':
                        theseWhitePieces[6]++;

                        break;
                    case 'F':
                        theseWhitePieces[7]++;
                        if (theseWhitePieces[7] + (theseWhitePieces[5] + theseWhitePieces[6]) > blackPieces[7] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'E':
                        theseWhitePieces[8]++;
                        if (theseWhitePieces[8] + (theseWhitePieces[5] + theseWhitePieces[6]) > blackPieces[8] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'A':
                        theseWhitePieces[9]++;
                        if (theseWhitePieces[9] + (theseWhitePieces[5] + theseWhitePieces[6]) > blackPieces[9] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;
                    case 'W':
                        theseWhitePieces[10]++;
                        if (theseWhitePieces[10] + (theseWhitePieces[5] + theseWhitePieces[6]) > blackPieces[10] + 10) {
                            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
                        }
                        break;

                    }

                }
            }
        }

        int blackOfficersAllowed = (blackPieces[0] + blackPieces[1] + blackPieces[2] + blackPieces[3] + blackPieces[4]
                + blackPieces[7] + blackPieces[8] + blackPieces[9] + blackPieces[10]) + 10
                - (theseBlackPieces[5] + theseBlackPieces[6]);

        int whiteOfficersAllowed = (blackPieces[0] + blackPieces[1] + blackPieces[2] + blackPieces[3] + blackPieces[4]
                + blackPieces[7] + blackPieces[8] + blackPieces[9] + blackPieces[10]) + 10
                - (theseWhitePieces[5] + theseWhitePieces[6]);
        //
        for (int i = 0; i < 11; i++) {
            theseWhitePieces[i] = 0;
            theseBlackPieces[i] = 0;
        }
        count = 0;
        rank = 11;
        for (int i = 0; i < boardfile.length; i++) {
            String line = boardfile[i];

            if (line.contains("-----")) {
                count++;
            }

            if (count == 1 && !line.contains("-") && !line.contains("%")) {
                rank--;

                String boardLine = line.replace(" ", "");
                for (int file = 0; file < boardLine.length(); file++) {

                    switch (line.charAt(file)) {
                    case 'k':
                        theseBlackPieces[0]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;

                    case 'r':
                        theseBlackPieces[1]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;

                    case 'q':
                        theseBlackPieces[2]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;

                    case 'n':
                        theseBlackPieces[3]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;

                    case 'b':
                        theseBlackPieces[4]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;

                    case 'p':
                        theseBlackPieces[5]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;

                    case 'd':
                        theseBlackPieces[6]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;

                    case 'f':
                        theseBlackPieces[7]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'e':
                        theseBlackPieces[8]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'a':
                        theseBlackPieces[9]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'w':
                        theseBlackPieces[10]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    // whites stuff
                    case 'K':
                        theseWhitePieces[0]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'R':
                        theseWhitePieces[1]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'Q':
                        theseWhitePieces[2]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'N':
                        theseWhitePieces[3]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'B':
                        theseWhitePieces[4]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'P':
                        theseWhitePieces[5]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'D':
                        theseWhitePieces[6]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'F':
                        theseWhitePieces[7]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'E':
                        theseWhitePieces[8]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'A':
                        theseWhitePieces[9]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;
                    case 'W':
                        theseWhitePieces[10]++;
                        check(blackOfficersAllowed, theseBlackPieces, whiteOfficersAllowed, theseWhitePieces, file,
                                rank);
                        break;

                    }

                }
            }
        }

    }

    public static void check(int blackOfficersAllowed, int[] theseBlackPieces, int whiteOfficersAllowed,
            int[] theseWhitePieces, int file, int rank) {
        if (blackOfficersAllowed < (theseBlackPieces[0] + theseBlackPieces[1] + theseBlackPieces[2]
                + theseBlackPieces[3] + theseBlackPieces[4] + theseBlackPieces[7] + theseBlackPieces[8]
                + theseBlackPieces[9] + theseBlackPieces[10])) {

            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
        }
        if (whiteOfficersAllowed < (theseWhitePieces[0] + theseWhitePieces[1] + theseWhitePieces[2]
                + theseWhitePieces[3] + theseWhitePieces[4] + theseWhitePieces[7] + theseWhitePieces[8]
                + theseWhitePieces[9] + theseWhitePieces[10])) {

            BoardValidationErrors.officerAllocationExceeded(UsefulFunctions.findFile(file), rank);
        }
    }

    public static void checkBoardDimensions(char[][] board) {
        for (int i = 0; i < 10; i++) {
            try {
                if (!(board[i].length == 10)) {
                    BoardValidationErrors.illegalBoardDimension();
                }
            } catch (Exception e) {
                BoardValidationErrors.illegalBoardDimension();
            }
        }
    }

    public static void checkPawnPosition(char[][] board) {

        for (int i = 0; i < 10; i++) {

            if (board[0][i] == 'p' || board[0][i] == 'd') {

                BoardValidationErrors.illegalPawnPosition(UsefulFunctions.findFile(i), 1);
            }
            if (board[9][i] == 'P' || board[9][i] == 'D') {

                BoardValidationErrors.illegalPawnPosition(UsefulFunctions.findFile(i), 10);
            }
        }

    }

    public static void checkElephantPosition(char[][] board, int[] blackPieces) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 'E') {
                    BoardValidationErrors.illegalElephantPosition(UsefulFunctions.findFile(i), 10 - j);
                }
            }
        }
        for (int i = 5; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 'e') {
                    BoardValidationErrors.illegalElephantPosition(UsefulFunctions.findFile(i), 10 - j);
                }
            }
        }

    }

    public static void checkNextPlayer(String[] boardfile) {

        String lastLine = boardfile[boardfile.length - 1];

        if ((lastLine.charAt(0) == 'w' || lastLine.charAt(0) == 'b') && lastLine.charAt(1) == ':') {

        } else {
            BoardValidationErrors.illegalNextPlayerMarker(boardfile.length);
            ;
        }

    }

    public static void checkCastlingOppertunities(char[][] board, boolean[] castlingOpportunities, String[] boardfile) {

        if (castlingOpportunities[0]) {
            if (board[0][5] != 'k' || board[0][0] != 'r') {
                BoardValidationErrors.illegalCastlingOpportunity(boardfile.length, 0);
            }

        }
        if (castlingOpportunities[1]) {
            if (board[0][5] != 'k' || board[0][9] != 'r') {
                BoardValidationErrors.illegalCastlingOpportunity(boardfile.length, 1);
            }
        }
        if (castlingOpportunities[2]) {
            if (board[9][5] != 'K' || board[9][0] != 'R') {
                BoardValidationErrors.illegalCastlingOpportunity(boardfile.length, 2);
            }
        }
        if (castlingOpportunities[3]) {
            if (board[9][5] != 'K' || board[9][9] != 'R') {
                BoardValidationErrors.illegalCastlingOpportunity(boardfile.length, 3);
            }
        }

    }

    public static void checkHalfmoveClock(String[] boardfile) {
        String baseHalfMoveClock = "";
        boolean wait = false;
        int breaks = 0;
        int halfMoveClock = 0;
        String lastLine = boardfile[boardfile.length - 1];
        for (int i = 0; i < lastLine.length(); i++) {
            if (lastLine.charAt(i) == ':') {
                breaks++;
            }
            if (breaks == 2) {
                if (wait) {
                    baseHalfMoveClock = baseHalfMoveClock + lastLine.charAt(i);
                }
                wait = true;
            }
            if (breaks == 3) {
                break;
            }
        }

        try {
            halfMoveClock = Integer.parseInt(baseHalfMoveClock);

        } catch (Exception e) {
            BoardValidationErrors.illegalHalfmoveClock(boardfile.length);
        }
        if (halfMoveClock < 0 || halfMoveClock > 50) {
            BoardValidationErrors.illegalHalfmoveClock(boardfile.length);
        }

    }

    public static void checkMoveCounter(String[] boardfile) {
        String baseMoveCounter = "";
        int moveCounter = 0;
        int breaks = 0;
        boolean wait = false;
        String lastLine = boardfile[boardfile.length - 1];
        for (int i = 0; i < lastLine.length(); i++) {
            if (lastLine.charAt(i) == ':') {
                breaks++;
            }
            if (breaks == 3) {
                if (wait) {
                    baseMoveCounter = baseMoveCounter + lastLine.charAt(i);
                }
                wait = true;
            }

        }

        try {
            moveCounter = Integer.parseInt(baseMoveCounter);

        } catch (Exception e) {

            BoardValidationErrors.illegalMoveCounter(boardfile.length);
        }
        if (moveCounter < 0) {

            BoardValidationErrors.illegalMoveCounter(boardfile.length);
        }
    }

    public static void checkLargeIlligalPiece(File boardFile) {

        int count = 0;
        int rank = 11;

        Scanner boardScanner = null;
        try {
            boardScanner = new Scanner(boardFile);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Board file does not exist");
        }
        while (boardScanner.hasNextLine()) {
            String line = boardScanner.nextLine();

            if (line.contains("-----")) {
                count++;
            }
            if (line.contains("-") && count == 1 && !line.contains("-----")) {
                BoardValidationErrors.illegalBoardDimension();
            }

            if (count == 1 && !line.contains("-") && !line.contains("%")) {
                rank--;

                String largepiece = line;
                String[] splitty = largepiece.split("[ \n\r\t]");
                for (int j = 0; j < splitty.length; j++) {
                    if (splitty[j].length() > 1) {
                        BoardValidationErrors.illegalPiece(UsefulFunctions.findFile(j), rank);
                    }

                }
            }

        }
        boardScanner.close();
    }
}
