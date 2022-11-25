
public class King extends Piece {

    public King(int rank, int file, boolean player, boolean officer) {
        super(rank, file, player, officer);
    }

    public boolean moveCheck(Piece[][] pieceBoard, Move moves, boolean playerTurn, int[] movesCounter) {
        if (playerTurn != player) {
            return false;
        }
        // 1
        if (pieceBoard[moves.srank][moves.sfile] == null) {
            return false;
        }
        // 2
        if (pieceBoard[moves.drank][moves.dfile] != null) {
            return false;
        }
        // 3
        
        // Specific movement
        if (Math.abs(moves.sfile - moves.dfile)>1 ||  Math.abs(moves.srank - moves.drank)>1) {
            return false;
        }
        if (movesCounter[1] == 50 && pieceBoard[moves.dfile][moves.drank] != null) {
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
        if (Math.abs(moves.sfile - moves.dfile) + Math.abs(moves.srank - moves.drank) != 1) {
            return false;
        }

        // #5 The move puts the current player’s King in check

        // #6 The move puts the opposing player’s King in check, but the
        // appropriate
        // suffix is not present.
        return true;
    }

    

    public void Move(Piece[][] piecesBoard, Move move, boolean playerTurn, int[] movesCounter,
            boolean[] castlingOpportunities) {
        if (player) {
            castlingOpportunities[2] = false;
            castlingOpportunities[3] = false;
        } else if (!player) {
            castlingOpportunities[0] = false;
            castlingOpportunities[1] = false;
        }
        super.Move(piecesBoard, move, playerTurn, movesCounter, castlingOpportunities);

    }

    public String toString() {
        if (player) {
            return "K";
        } else {
            return "k";
        }

    }
    public String getPicName() {
        if (player) {
            return "WhiteKing.png";
        } else {
            return "BlackKing.png";
        }
    }

}
