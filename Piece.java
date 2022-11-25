
public abstract class Piece {
    boolean playerTurn = FairyChess.playerTurn;
    int[] movesCounter = FairyChess.movesCounter;

    protected int rank;
    protected int file;
    protected final boolean player;
    protected final boolean officer;

    public Piece(int rank, int file, boolean player, boolean officer) {

        this.rank = rank;
        this.file = file;
        this.player = player;
        this.officer = officer;

    }

    public abstract boolean moveCheck(Piece[][] piecesBoard, Move move, boolean playerTurn, int[] movesCounter);

    public abstract boolean captureCheck(Piece[][] piecesBoard, Move move, boolean playerTurn, int[] movesCounter);

    public void Move(Piece[][] piecesBoard, Move move, boolean playerTurn, int[] movesCounter,
            boolean[] castlingOpportunities) {
        piecesBoard[move.drank][move.dfile] = piecesBoard[move.srank][move.sfile];
        piecesBoard[move.srank][move.sfile] = null;
        // affect the info of the file
        FairyChess.playerTurn = !FairyChess.playerTurn;
        if (officer && move.getMove().equals("MOVE")) {
            movesCounter[0]++;
        } else if (officer && move.getMove().equals("CAPTURE") || !officer) {
            movesCounter[0] = 0;
        }
        if (!player) {
            movesCounter[1]++;
        }

    }

    public boolean promotionCheck(Piece[][] piecesBoard, Move move, boolean playerTurn, int[] movesCounter) {
        if (officer) {
            return false;
        }
        if (move.getMove().equals("MOVE")) {
            if (!piecesBoard[move.srank][move.sfile].moveCheck(piecesBoard, move, playerTurn, movesCounter)) {

                return false;
            }
        }
        if (move.getMove().equals("CAPTURE")) {
            if (!piecesBoard[move.srank][move.sfile].captureCheck(piecesBoard, move, playerTurn, movesCounter)) {
                return false;
            }
        }
        return true;

    }

    public abstract String getPicName();

    public abstract String toString();

}
