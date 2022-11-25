
public class Amazon extends Piece {

    public Amazon(int rank, int file, boolean player, boolean officer) {
        super(rank, file, player, officer);
        // TODO Auto-generated constructor stub
    }

    public boolean moveCheck(Piece[][] pieceBoard, Move moves, boolean playerTurn, int[] movesCounter) {
        if (playerTurn != player) {
            return false;
        }
        // #1 The source square is not occupied by a piece of the current
        // player’s color.
        if (pieceBoard[moves.srank][moves.sfile] == null) {
            return false;
        } else if (pieceBoard[moves.srank][moves.sfile].player != player) {

        }
        // #2 The destination square is occupied (in this case, the capture
        // notation
        // should be used).
        if (pieceBoard[moves.drank][moves.dfile] != null) {
            return false;
        }

        // #3 The piece at the source square is not able to move to the
        // destination square according to its movement rules.
        // Rook
        if ((moves.srank == moves.drank && moves.sfile != moves.dfile)
                || (moves.sfile == moves.dfile && moves.srank != moves.drank)) {
            // do nothing

            // Bishop
        } else if (Math.abs(moves.srank - moves.drank) == Math.abs(moves.sfile - moves.dfile)) {
            // do nothing

            // Knight
        } else if ((Math.abs(moves.sfile - moves.dfile) == 1 && Math.abs(moves.srank - moves.drank) == 2)
                || (Math.abs(moves.sfile - moves.dfile) == 2 && Math.abs(moves.srank - moves.drank) == 1)) {
            // do nothing

        } else {
            return false;
        }
        if (PathingChecks.checkDiagonal(pieceBoard, moves) || PathingChecks.checkHoriVert(pieceBoard, moves)) {
            // do nothing
        } else {
            return false;
        }
        // #5 The Halfmove clock is equal to 50, and the piece being moved is
        // not a
        // Pawn or Drunken Soldier.
        if (movesCounter[1] == 50) {
            return false;
        }
        return true;
    }

    public boolean captureCheck(Piece[][] pieceBoard, Move moves, boolean playerTurn, int[] movesCounter) {
        if (playerTurn != player) {
            return false;
        }
        // #1 The source square is not occupied by a piece of the current
        // player’s color.
        if (pieceBoard[moves.srank][moves.sfile] == null) {
            return false;
        } else if (pieceBoard[moves.srank][moves.sfile].player != player) {
            return false;
        }
        // #2 The destination square is not occupied by a piece of the opposing
        // player’s
        // color.
        if (pieceBoard[moves.drank][moves.dfile] == null) {
            return false;
        } else if (pieceBoard[moves.drank][moves.dfile].player == player) {
            return false;
        }
        // #3 The piece at the source square is not able to capture the
        // destination square
        // according to its movement rules.
        if ((moves.srank == moves.drank && moves.sfile != moves.dfile)
                || (moves.sfile == moves.dfile && moves.srank != moves.drank)) {
            // do nothing
        } else if (Math.abs(moves.srank - moves.drank) == Math.abs(moves.sfile - moves.dfile)) {
            // do nothing
        } else if ((Math.abs(moves.sfile - moves.dfile) == 1 && Math.abs(moves.srank - moves.drank) == 2)
                || (Math.abs(moves.sfile - moves.dfile) == 2 && Math.abs(moves.srank - moves.drank) == 1)) {
            // do nothing
        } else {
            return false;
        }
        if (PathingChecks.checkDiagonal(pieceBoard, moves) || PathingChecks.checkHoriVert(pieceBoard, moves)) {
            // do nothing
        } else {
            return false;
        }
        // #4 The current player’s King is in check, and the result of the
        // capture does
        // not remove the King from check.

        // #5 The move puts the current player’s King in check

        // #6 The move puts the opposing player’s King in check, but the
        // appropriate
        // suffix is not present.
        return true;
    }

    public void Move(Piece[][] piecesBoard, Move move, boolean playerTurn, int[] movesCounter,
            boolean[] castlingOpportunities) {
        super.Move(piecesBoard, move, playerTurn, movesCounter, castlingOpportunities);

    }

    public String toString() {
        if (player) {
            return "A";
        } else {
            return "a";
        }

    }

    public String getPicName() {
        if (player) {
            return "WhiteAmazon.png";
        } else {
            return "BlackAmazon.png";
        }
    }

}
