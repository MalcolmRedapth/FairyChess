
public class PathingChecks {
    private static Piece[][] testBoard = new Piece[10][10];

    public static boolean checkDiagonal(Piece[][] pieceBoard, Move moves) {
        int movement = Math.abs(moves.srank - moves.drank);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                testBoard[i][j] = pieceBoard[i][j];
            }
        }
        // left up
        if (moves.srank - moves.drank > 0 && moves.sfile - moves.dfile > 0) {
            for (int i = 1; i < movement; i++) {
                if (testBoard[moves.srank - i][moves.sfile - i] != null) {
                    return false;
                }
            }
        }
        // right up
        else if (moves.srank - moves.drank > 0 && moves.sfile - moves.dfile < 0) {
            for (int i = 1; i < movement; i++) {
                if (testBoard[moves.srank - i][moves.sfile + i] != null) {
                    return false;
                }
            }
        }
        // left down
        else if (moves.srank - moves.drank < 0 && moves.sfile - moves.dfile > 0) {
            for (int i = 1; i < movement; i++) {
                if (testBoard[moves.srank + i][moves.sfile - i] != null) {
                    return false;
                }
            }
        }
        // right down
        else if (moves.srank - moves.drank < 0 && moves.sfile - moves.dfile < 0) {
            for (int i = 1; i < movement; i++) {
                if (testBoard[moves.srank + i][moves.sfile + i] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean checkHoriVert(Piece[][] pieceBoard, Move moves) {
        int movement = 0;
        if (moves.srank - moves.drank != 0) {
            movement = Math.abs(moves.srank - moves.drank);
        } else {
            movement = Math.abs(moves.sfile - moves.dfile);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                testBoard[i][j] = pieceBoard[i][j];
            }
        }
        // down
        if (moves.srank - moves.drank < 0 && moves.sfile - moves.dfile == 0) {
            for (int i = 1; i < movement; i++) {
                if (testBoard[moves.srank + i][moves.sfile] != null) {
                    return false;
                }

            }
        }

        // right
        if (moves.srank - moves.drank == 0 && moves.sfile - moves.dfile < 0) {
            for (int i = 1; i < movement; i++) {
                if (testBoard[moves.srank][moves.sfile + i] != null) {
                    return false;
                }

            }
        }

        // up
        if (moves.srank - moves.drank > 0 && moves.sfile - moves.dfile == 0) {
            for (int i = 1; i < movement; i++) {
                if (testBoard[moves.srank - i][moves.sfile] != null) {
                    return false;
                }

            }
        }

        if (moves.srank - moves.drank == 0 && moves.sfile - moves.dfile > 0) {
            for (int i = 1; i < movement; i++) {
                if (testBoard[moves.srank][moves.sfile - i] != null) {
                    return false;
                }

            }
        }

        return true;
    }

    public static boolean isInCheck(Piece[][] piecesBoard, boolean playerTurn, int[] movesCounter) {
        Piece[][] testBoard = new Piece[10][10];
        int drank = 0;
        int dfile = 0;
        String blackPieces = "rqnbpdfeaw";
        String whitePieces = "RQNBPDFEAW";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                testBoard[i][j] = piecesBoard[i][j];
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (testBoard[i][j] != null) {
                    if (playerTurn) {

                        if (testBoard[i][j].equals("K")) {
                            drank = i;
                            dfile = j;
                            break;
                        }

                    } else if (!playerTurn) {
                        if (testBoard[i][j].equals("k")) {
                            drank = i;
                            dfile = j;
                            break;
                        }
                    }
                }
            }

        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (playerTurn) {

                    if (testBoard[i][j] != null) {
                        if (blackPieces.contains(testBoard[i][j].toString())) {
                            Move Move = new Move("CAPTURE", i, j, drank, dfile);
                            if (testBoard[i][j].captureCheck(testBoard, Move, playerTurn, movesCounter)) {
                                return true;
                            }
                        }
                    }
                } else if (!playerTurn) {
                    if (testBoard[i][j] != null) {
                        if (whitePieces.contains(testBoard[i][j].toString())) {
                            Move Move = new Move("CAPTURE", i, j, drank, dfile);
                            if (testBoard[i][j].captureCheck(testBoard, Move, playerTurn, movesCounter)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;

    }
}
