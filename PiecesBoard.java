import java.util.ArrayList;

public class PiecesBoard {
    public static Piece[][] piecesBoard;
    private ArrayList<Move> moves = new ArrayList<Move>();

    public static Piece[][] PiecesBoard(char[][] board) {
        piecesBoard = new Piece[10][10];
        fillUpBoard(board);
        return piecesBoard;
    }

    private static void fillUpBoard(char[][] board) {
    	// hard-coding for the win?
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (board[i][j]) {
                case 'K':
                    piecesBoard[i][j] = new King(i, j, true, true);
                    break;
                case 'R':
                    piecesBoard[i][j] = new Rook(i, j, true, true);
                    break;
                case 'Q':
                    piecesBoard[i][j] = new Queen(i, j, true, true);
                    break;
                case 'N':
                    piecesBoard[i][j] = new Knight(i, j, true, true);
                    break;
                case 'B':
                    piecesBoard[i][j] = new Bishop(i, j, true, true);
                    break;
                case 'P':
                    piecesBoard[i][j] = new Pawn(i, j, true, false);
                    break;
                case 'D':
                    piecesBoard[i][j] = new DrunkenPawn(i, j, true, false);
                    break;
                case 'F':
                    piecesBoard[i][j] = new FlyingDragon(i, j, true, true);
                    break;
                case 'E':
                    piecesBoard[i][j] = new Elephant(i, j, true, true);
                    break;
                case 'A':
                    piecesBoard[i][j] = new Amazon(i, j, true, true);
                    break;
                case 'W':
                    piecesBoard[i][j] = new Princess(i, j, true, true);
                    break;
                //
                case 'k':
                    piecesBoard[i][j] = new King(i, j, false, true);
                    break;
                case 'r':
                    piecesBoard[i][j] = new Rook(i, j, false, true);
                    break;
                case 'q':
                    piecesBoard[i][j] = new Queen(i, j, false, true);
                    break;
                case 'n':
                    piecesBoard[i][j] = new Knight(i, j, false, true);
                    break;
                case 'b':
                    piecesBoard[i][j] = new Bishop(i, j, false, true);
                    break;
                case 'p':
                    piecesBoard[i][j] = new Pawn(i, j, false, false);
                    break;
                case 'd':
                    piecesBoard[i][j] = new DrunkenPawn(i, j, false, false);
                    break;
                case 'f':
                    piecesBoard[i][j] = new FlyingDragon(i, j, false, true);
                    break;
                case 'e':
                    piecesBoard[i][j] = new Elephant(i, j, false, true);
                    break;
                case 'a':
                    piecesBoard[i][j] = new Amazon(i, j, false, true);
                    break;
                case 'w':
                    piecesBoard[i][j] = new Princess(i, j, false, true);
                    break;

                case '.':
                    piecesBoard[i][j] = null;
                    break;

                }
            }
        }
    }

    public static Piece[][] pieceAllocation(boolean[] pawn, int[] officers) {
        Piece[][] allocation = new Piece[10][10];
        for (int i = 0; i < 10; i++) {
            if (pawn[i] == true) {
                allocation[1][i] = new Pawn(1, i, true, false);
                allocation[8][i] = new Pawn(8, i, false, false);
            } else {
                allocation[1][i] = new DrunkenPawn(1, i, true, false);
                allocation[8][i] = new DrunkenPawn(8, i, false, false);
            }
        }
        for (int i = 0; i < 10; i++) {
            switch (officers[i]) {
            case 0:
                allocation[0][i] = new King(0, i, true, true);
                allocation[9][i] = new King(9, i, false, true);
                break;
            case 1:
                allocation[0][i] = new Rook(0, i, true, true);
                allocation[9][i] = new Rook(9, i, false, true);
                break;
            case 2:
                allocation[0][i] = new Queen(0, i, true, true);
                allocation[9][i] = new Queen(9, i, false, true);
                break;
            case 3:
                allocation[0][i] = new Knight(0, i, true, true);
                allocation[9][i] = new Knight(9, i, false, true);
                break;
            case 4:
                allocation[0][i] = new Bishop(0, i, true, true);
                allocation[9][i] = new Bishop(9, i, false, true);
                break;
            case 5:
                allocation[0][i] = new FlyingDragon(0, i, true, true);
                allocation[9][i] = new FlyingDragon(9, i, false, true);
                break;
            case 6:
                allocation[0][i] = new Elephant(0, i, true, true);
                allocation[9][i] = new Elephant(9, i, false, true);
                break;
            case 7:
                allocation[0][i] = new Amazon(0, i, true, true);
                allocation[9][i] = new Amazon(9, i, false, true);
                break;
            case 8:
                allocation[0][i] = new Princess(0, i, true, true);
                allocation[9][i] = new Princess(9, i, false, true);
                break;

            }
        }
        return allocation;
    }

}
