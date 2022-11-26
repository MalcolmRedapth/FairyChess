import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The driver class for the Fairy Chess project.
 */
public class FairyChess {
	// Allocate memory for the global variables
    public static String[] movefile;
    public static char[][] board;
    public static boolean playerTurn = true;
    public static int[] movesCounter = new int[2];
    public static boolean[] castlingOpportunities = new boolean[4];
    public static Piece[][] piecesBoard;

    public static String moveMove = "";
    public static String moveAffect = "";
    public static int movesrank = 0;
    public static int movesfile = 0;
    public static int movedrank = 0;
    public static int movedfile = 0;

    public static void main(String[] args) {
    	// A Whole bunch of variables
        int lines = 0;
        int mlines = 0;
        int count = 0;
        int countRanks = 0;
        boolean doWeHaveFile = false;
        int file = 0;
        int rank = 0;
        boolean show = true;
        boolean isNotAllocatedYet = true;
        
        int[] blackPieces = new int[11];
        int[] whitePieces = new int[11];
        // The Following code is used to read in and verify save files, it is rather complicated due
        // to the marking methods used in this project. Feel free to skip to line 129 (Where the game actually
        // start and the grading code stops)
        
        // Get the board file's name, and initialize a File object to represent it
        if (args.length > 0) {
            String boardFilename = args[0];
            File boardFile = new File(boardFilename);

            // Initialize the Scanner
            Scanner boardScanner = null;
            try {
                boardScanner = new Scanner(boardFile);
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("Board file does not exist");
            }
            while (boardScanner.hasNextLine()) {
                String line = boardScanner.nextLine();
                lines++;

            }
            boardScanner.close();
            String[] boardfile = makeString(boardFile, lines);

            for (int i = 0; i < boardfile.length; i++) {
                String line = boardfile[i];
                if (line.contains("-") && !line.contains(":")) {
                    count++;

                }
                //
                if (count == 1 && !line.contains("-") && !line.contains("%") && !doWeHaveFile) {
                    String boardLine = line.replace(" ", "");
                    file = boardLine.length();
                    doWeHaveFile = true;
                }
                if (count == 1 && !line.contains("-") && !line.contains("%")) {
                    countRanks++;
                }
                rank = countRanks;

            }

            BoardValidation.checkPawnAllocation(boardfile);
            BoardValidation.checkOfficerAllocation(boardfile);
            BoardValidation.checkNextPlayer(boardfile);
            BoardValidation.checkLargeIlligalPiece(boardFile);StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            StdDraw.rectangle(1.925 + movesfile, 9.375 - movesrank, 0.25, 0.4);
            StdDraw.show();
            BoardValidation.checkHalfmoveClock(boardfile);
            BoardValidation.checkMoveCounter(boardfile);

            board = new char[rank][file];

            // this is where I read stuff in
            blackPieces = DataExtraction.blackPieceAllocationExtraction(boardfile);
            board = DataExtraction.boardArrangementExtraction(boardfile, board);
            // This is where I read in the last of the meta data
            playerTurn = DataExtraction.playerTurnExtraction(boardfile);
            castlingOpportunities = DataExtraction.castelingOpportunitiesExtraction(boardfile);
            movesCounter = DataExtraction.moveCounterExtraction(boardfile);

            // this is where I check stuff
            BoardValidation.checkElephantPosition(board, blackPieces);
            BoardValidation.checkIllegalPiece(boardfile, blackPieces);
            BoardValidation.checkBoardDimensions(board);

            BoardValidation.checkPawnExceedingAllocation(boardfile, blackPieces);
            BoardValidation.checkDynamicExcedeingOfficerAllocation(boardfile, blackPieces);
            BoardValidation.checkPawnPosition(board);
            BoardValidation.checkCastlingOppertunities(board, castlingOpportunities, boardfile);
            Piece[][] piecesBoard = PiecesBoard.PiecesBoard(board);
        }
        // Bla Bla Bla Bla this is the next stage
        // here we read in move commands and later affect them onto the game from the save file (args[0])
        if (args.length > 1) {
            String moveFilename = args[1];
            File moveFile = new File(moveFilename);
            // Initialize the Scanner
            Scanner boardScannerNext = null;
            try {
                boardScannerNext = new Scanner(moveFile);
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("Move file does not exist");
            }
            while (boardScannerNext.hasNextLine()) {
                String mline = boardScannerNext.nextLine();
                mlines++;

            }
            boardScannerNext.close();

            movefile = makeString(moveFile, mlines);
            
            // I don't know why I commented this out. Maybe it was needed during grading
            
            // Move[] moves = Move.Moves(getRidOfComments(movefile));
        }
        // jdzffhwilauhfjauwgtaeryufgaeyugtsfyjavergfyWGFYUDWGURywfgeuy4rgdILQg3rulF2,3

        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(1418, 848);
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);

        if (show) {
            animation();
            show = false;
        }
        // this is for when there is no boardfile
        if (args.length == 0) {
        	// custom starting line-ups are allowed (like having 4 bishops), this is the selection menu for that
            if (isNotAllocatedYet) {
                GuiAllocation();
                isNotAllocatedYet = false;
            }

        }

        // the program was made to run without a GUI (as a full game is played out from the instructions
        // in the move file and the end game state is compared to the expected output). I have made a GUI
        // for this after the fact. To make a fully functional Chess game to better show-case my
        // back-end.
        
        while (true) {
            
            GuiOutPut(piecesBoard);

            GuiGetMove(piecesBoard);

            Move thisMove = new Move(moveMove, movesrank, movesfile, movedrank, movedfile);

            boolean castle = true;
            Move move = thisMove;
            int line = 1;
            // moves null check

            // board null check and Error throw
            //TODO: Implement GUI Error throws
            if (piecesBoard[move.srank][move.sfile] == null) {
                if (move.getMove().equals("MOVE")) {

                    MoveValidationErrors.illegalMove(line);
                }

                if (move.getMove().equals("CAPTURE")) {
                    MoveValidationErrors.illegalCapture(line);
                }
                // Checking moves
            }
            if (move.getMove().equals("MOVE") && !move.getAffect().equals("PROMOTION")) {
            		// moveCheck returns false for illegal moves, so !bla.moveCheck is used to trigger the "if" statement
                if (!piecesBoard[move.srank][move.sfile].moveCheck(piecesBoard, move, playerTurn, movesCounter)) {
                    MoveValidationErrors.illegalMove(line);
                }
                // affects move
                piecesBoard[move.srank][move.sfile].Move(piecesBoard, move, playerTurn, movesCounter,
                        castlingOpportunities);
            }
            if (move.getMove().equals("CAPTURE") && !move.getAffect().equals("PROMOTION")) {
                if (!piecesBoard[move.srank][move.sfile].captureCheck(piecesBoard, move, playerTurn, movesCounter)) {
                    MoveValidationErrors.illegalCapture(line);
                }
             // affects move
                piecesBoard[move.srank][move.sfile].Move(piecesBoard, move, playerTurn, movesCounter,
                        castlingOpportunities);
            }
            if (move.getAffect() == "PROMOTION") {

                if (!piecesBoard[move.srank][move.sfile].promotionCheck(piecesBoard, move, playerTurn, movesCounter)) {
                    MoveValidationErrors.illegalPromotion(line);
                }
                Promote(piecesBoard, move, line, blackPieces);

            }

            if (move.getMove().equals("CASTLING KINGSIDE")) {

                if (playerTurn) {
                    if (!castlingOpportunities[3]) {
                        MoveValidationErrors.illegalCastlingMove(line);
                    }
                    for (int j = 1; j < 4; j++) {
                        if (piecesBoard[9][5 + j] != null) {
                            castle = false;
                        }
                    }
                    if (!castle) {
                        MoveValidationErrors.illegalCastlingMove(line);
                    }

                } else if (!playerTurn) {
                    if (!castlingOpportunities[1]) {
                        MoveValidationErrors.illegalCastlingMove(line);
                    }
                    for (int j = 1; j < 4; j++) {
                        if (piecesBoard[0][5 + j] != null) {
                            castle = false;
                        }
                    }
                    if (!castle) {
                        MoveValidationErrors.illegalCastlingMove(line);
                    }
                }

            }
            if (move.getMove().equals("CASTLING QUEENSIDE")) {

                if (playerTurn) {
                    if (!castlingOpportunities[2]) {
                        MoveValidationErrors.illegalCastlingMove(line);
                    }
                    for (int j = 1; j < 5; j++) {
                        if (piecesBoard[9][5 - j] != null) {
                            castle = false;
                        }
                    }
                    if (!castle) {
                        MoveValidationErrors.illegalCastlingMove(line);
                    }
                } else if (!playerTurn) {
                    if (!castlingOpportunities[0]) {
                        MoveValidationErrors.illegalCastlingMove(line);
                    }
                    for (int j = 1; j < 5; j++) {
                        if (piecesBoard[0][5 - j] != null) {
                            castle = false;
                        }
                    }
                    if (!castle) {
                        MoveValidationErrors.illegalCastlingMove(line);
                    }
                }

            }
            castle(piecesBoard, move);

            // TODO: guess I still need to finish King related checks....... and Implement King related checks in all "Pieces"
            
            // if not left in check call illegalCheck
            // if(move.getCheck()){
            // if (!PathingChecks.isInCheck(piecesBoard, playerTurn, movesCounter)) {
            // MoveValidationErrors.illegalCheck(line);
            // }
            // }

            // Terminal output
            // terminalOutPut(boardfile, piecesBoard);

            // Gui base

        }

    }

    // responsible for redrawing the current game state
    public static void GuiOutPut(Piece[][] piecesBoard) {
        if (playerTurn) {
            StdDraw.picture(5, 5, "WhiteTurn.JPG", 10, 10);
        } else {
            StdDraw.picture(5, 5, "BlackTurn.JPG", 10, 10);
        }
        
        double x = 1.75;
        double y = 0;
        
        for (int i = 0; i < 10; i++) {
            y = -0.3;
            x = x + 0.59;
            for (int j = 0; j < 10; j++) {
                y = y + 0.96;
                if (piecesBoard[i][j] != null) {
                    StdDraw.picture(x, y, piecesBoard[i][j].getPicName(), 0.5, 0.85);
                }
            }
        }

        StdDraw.show();
    }
    
    // animations looks kinda ugly, but this took so long to make. I'll replace it last
    public static void animation() {
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);
        StdDraw.picture(5, 5, "intro.png", 10, 10);

        double randomh[] = new double[10];
        for (int k = 0; k < 10; k++) {
            randomh[k] = (Math.random() * 5) + 5;
        }
        double randoml[] = new double[10];
        for (int k = 0; k < 10; k++) {
            randoml[k] = (Math.random() * 5);
        }

        double randomc[] = new double[10];
        for (int k = 0; k < 10; k++) {
            randomc[k] = (Math.random() * 2.5);
        }
        // how cool does this look? Right
        for (double i = 0; i < 18; i = i + 0.03) {

            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(i - randoml[0], randomh[0], 0.1);
            StdDraw.filledCircle(i - randoml[1], randomh[1], 0.1);
            StdDraw.filledCircle(i - randoml[2], randomh[2], 0.1);
            StdDraw.filledCircle(i - randoml[3], randomh[3], 0.1);
            StdDraw.filledCircle(i - randoml[4], randomh[4], 0.1);

            StdDraw.setPenColor(StdDraw.ORANGE);
            StdDraw.filledCircle(i - randoml[5], randomh[5], 0.1);
            StdDraw.filledCircle(i - randoml[6], randomh[6], 0.1);
            StdDraw.filledCircle(i - randoml[7], randomh[7], 0.1);
            StdDraw.filledCircle(i - randoml[8], randomh[8], 0.1);
            StdDraw.filledCircle(i - randoml[9], randomh[9], 0.1);
            //
            StdDraw.setPenColor(StdDraw.MAGENTA);
            StdDraw.filledCircle(i - randoml[0] - randomc[0] / 2, randomc[0] + 5, 0.1);
            StdDraw.filledCircle(i - randoml[1] - randomc[1] / 2, randomc[1] + 5, 0.1);
            StdDraw.filledCircle(i - randoml[2] - randomc[2] / 2, randomc[2] + 5, 0.1);
            StdDraw.filledCircle(i - randoml[3] - randomc[3] / 2, randomc[3] + 5, 0.1);
            StdDraw.filledCircle(i - randoml[4] - randomc[4] / 2, randomc[4] + 5, 0.1);

            StdDraw.setPenColor(StdDraw.BOOK_RED);
            StdDraw.filledCircle(i - randoml[5] - randomc[5] / 2, randomc[5] + 5, 0.1);
            StdDraw.filledCircle(i - randoml[6] - randomc[6] / 2, randomc[6] + 5, 0.1);
            StdDraw.filledCircle(i - randoml[7] - randomc[7] / 2, randomc[7] + 5, 0.1);
            StdDraw.filledCircle(i - randoml[8] - randomc[8] / 2, randomc[8] + 5, 0.1);
            StdDraw.filledCircle(i - randoml[9] - randomc[9] / 2, randomc[9] + 5, 0.1);
            //

            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            StdDraw.filledCircle(10 - (i - randoml[0]), randomh[0] - 5, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[1]), randomh[1] - 5, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[2]), randomh[2] - 5, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[3]), randomh[3] - 5, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[4]), randomh[4] - 5, 0.1);

            StdDraw.setPenColor(StdDraw.CYAN);
            StdDraw.filledCircle(10 - (i - randoml[5]), randomh[5] - 5, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[6]), randomh[6] - 5, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[7]), randomh[7] - 5, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[8]), randomh[8] - 5, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[9]), randomh[9] - 5, 0.1);
            //
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.filledCircle(10 - (i - randoml[0]) + randomc[0], randomc[0] + 1, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[1]) + randomc[1], randomc[1] + 1, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[2]) + randomc[2], randomc[2] + 1, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[3]) + randomc[3], randomc[3] + 1, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[4]) + randomc[4], randomc[4] + 1, 0.1);

            StdDraw.setPenColor(StdDraw.PINK);
            StdDraw.filledCircle(10 - (i - randoml[5]) + randomc[5], randomc[5] + 1, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[6]) + randomc[6], randomc[6] + 1, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[7]) + randomc[7], randomc[7] + 1, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[8]) + randomc[8], randomc[8] + 1, 0.1);
            StdDraw.filledCircle(10 - (i - randoml[9]) + randomc[9], randomc[9] + 1, 0.1);
            //

            StdDraw.show();

        }

    }
    
    // GuiMove is used to select a piece to be moved. And a destination to be selected.
    public static void GuiGetMove(Piece[][] piecesBoard) {
        moveMove = "";
        moveAffect = "";
        movesrank = -1;
        movesfile = -1;
        movedrank = -1;
        movedfile = -1;
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);
        boolean notDone = true;
        boolean sourcePiece = true;
        boolean destinationPiece = true;
        while (notDone) {
        	// TODO: Should probably add some highlighting to indicate a selection has occurred
            while (sourcePiece) {
            	// making buttons with StdDraw is PAIN
                if (StdDraw.isMousePressed()) {
                    if (StdDraw.mouseY() < 10.0 && StdDraw.mouseY() > 9.0) {
                        movesrank = 0;
                        while (StdDraw.isMousePressed()) {
                            // do nothing || this exists because without it isMousePressed() spams an
                        	// impressive amount of inputs. This exits the while loops upon de-pressing the mouse
                        }
                    }
                    if (StdDraw.mouseY() < 9.0 && StdDraw.mouseY() > 8.0) {
                        movesrank = 1;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 8.0 && StdDraw.mouseY() > 7.0) {
                        movesrank = 2;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 7.0 && StdDraw.mouseY() > 6.0) {
                        movesrank = 3;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 6.0 && StdDraw.mouseY() > 5.0) {
                        movesrank = 4;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 5.0 && StdDraw.mouseY() > 4.0) {
                        movesrank = 5;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 4.0 && StdDraw.mouseY() > 3.0) {
                        movesrank = 6;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 3.0 && StdDraw.mouseY() > 2.0) {
                        movesrank = 7;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 2.0 && StdDraw.mouseY() > 1.0) {
                        movesrank = 8;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 1.0 && StdDraw.mouseY() > 0.0) {
                        movesrank = 9;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 2 && StdDraw.mouseX() < 2.6) {
                        movesfile = 0;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 2.6 && StdDraw.mouseX() < 3.2) {
                        movesfile = 1;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 3.2 && StdDraw.mouseX() < 3.8) {
                        movesfile = 2;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 3.8 && StdDraw.mouseX() < 4.4) {
                        movesfile = 3;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 4.4 && StdDraw.mouseX() < 5.0) {
                        movesfile = 4;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 5.0 && StdDraw.mouseX() < 5.6) {
                        movesfile = 5;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 5.6 && StdDraw.mouseX() < 6.2) {
                        movesfile = 6;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 6.2 && StdDraw.mouseX() < 6.8) {
                        movesfile = 7;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 6.8 && StdDraw.mouseX() < 7.4) {
                        movesfile = 8;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 7.4 && StdDraw.mouseX() < 8.0) {
                        movesfile = 9;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }

                }
                if (movesrank != -1 && movesfile != -1) {
                    if (piecesBoard[movesrank][movesfile] != null) {
                        if (piecesBoard[movesrank][movesfile].player == playerTurn) {
                            sourcePiece = false; // breaks the while loop after selection of piece to be moved is done
                            break;
                        }
                    }
                }
                
            }

            while (destinationPiece) {
                if (StdDraw.isMousePressed()) {
                    if (StdDraw.mouseY() < 10.0 && StdDraw.mouseY() > 9.0) {
                        movedrank = 0;
                        while (StdDraw.isMousePressed()) {
                            // do nothing || dealing with spammy inputs
                        }
                    }
                    if (StdDraw.mouseY() < 9.0 && StdDraw.mouseY() > 8.0) {
                        movedrank = 1;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 8.0 && StdDraw.mouseY() > 7.0) {
                        movedrank = 2;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 7.0 && StdDraw.mouseY() > 6.0) {
                        movedrank = 3;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 6.0 && StdDraw.mouseY() > 5.0) {
                        movedrank = 4;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 5.0 && StdDraw.mouseY() > 4.0) {
                        movedrank = 5;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 4.0 && StdDraw.mouseY() > 3.0) {
                        movedrank = 6;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 3.0 && StdDraw.mouseY() > 2.0) {
                        movedrank = 7;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 2.0 && StdDraw.mouseY() > 1.0) {
                        movedrank = 8;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseY() < 1.0 && StdDraw.mouseY() > 0.0) {
                        movedrank = 9;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 2.0 && StdDraw.mouseX() < 2.6) {
                        movedfile = 0;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 2.6 && StdDraw.mouseX() < 3.2) {
                        movedfile = 1;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 3.2 && StdDraw.mouseX() < 3.8) {
                        movedfile = 2;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 3.8 && StdDraw.mouseX() < 4.4) {
                        movedfile = 3;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 4.4 && StdDraw.mouseX() < 5) {
                        movedfile = 4;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 5 && StdDraw.mouseX() < 5.6) {
                        movedfile = 5;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 5.6 && StdDraw.mouseX() < 6.2) {
                        movedfile = 6;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 6.2 && StdDraw.mouseX() < 6.8) {
                        movedfile = 7;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 6.8 && StdDraw.mouseX() < 7.4) {
                        movedfile = 8;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 7.4 && StdDraw.mouseX() < 8) {
                        movedfile = 9;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }

                }
                if (movedrank != -1 && movedfile != -1) {
                    if (piecesBoard[movedrank][movedfile] == null) {
                        moveMove = "MOVE";
                        destinationPiece = false;
                        break;
                    } else if (piecesBoard[movesrank][movesfile].player != playerTurn) {
                        moveMove = "CAPTURE";
                        destinationPiece = false;
                        break;
                    }
                }
                // We deal with illegal moves elsewhere (In the "Pieces" themselves), we just make the "moveMove"/"moveCaptue" part of the move object here

            }
            if (!sourcePiece && !destinationPiece) {
                notDone = false;
                break;
            }
        }
    }

    public static void GuiAllocation() {
        boolean done = true;
        boolean[] pawn = new boolean[10];
        int[] officers = { 1, 3, 4, 2, 2, 0, 2, 4, 3, 1 };
        // displays allocation selection until a confirmation is made of the choices
        while (done) {
            StdDraw.picture(5, 5, "allocation.jpg", 11, 11);
            for (int i = 0; i < 10; i++) {
                if (pawn[i]) {
                    StdDraw.picture(0.5 + i, 5, "WhitePawn.png", 0.9, 1.5);
                } else {
                    StdDraw.picture(0.5 + i, 5, "WhiteDrunkenPawn.png", 0.9, 1.5);
                }

            }
            for (int i = 0; i < 10; i++) {
                switch (officers[i]) {
                case 0:
                    StdDraw.picture(0.5 + i, 2.5, "WhiteKing.png", 0.9, 2);
                    break;
                case 1:
                    StdDraw.picture(0.5 + i, 2.5, "WhiteCastle.png", 0.9, 2);
                    break;
                case 2:
                    StdDraw.picture(0.5 + i, 2.5, "WhiteQueen.png", 0.9, 2);
                    break;
                case 3:
                    StdDraw.picture(0.5 + i, 2.5, "WhiteKnight.png", 0.9, 2);
                    break;
                case 4:
                    StdDraw.picture(0.5 + i, 2.5, "WhiteBishop.png", 0.9, 2);
                    break;
                case 5:
                    StdDraw.picture(0.5 + i, 2.5, "WhiteFlyingDragon.png", 0.9, 2);
                    break;
                case 6:
                    StdDraw.picture(0.5 + i, 2.5, "WhiteElephant.png", 0.9, 2);
                    break;
                case 7:
                    StdDraw.picture(0.5 + i, 2.5, "WhiteAmazon.png", 0.9, 2);
                    break;
                case 8:
                    StdDraw.picture(0.5 + i, 2.5, "WhitePrincess.png", 0.9, 2);
                    break;

                }
            }
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledRectangle(8.5, 8, 1, 0.75);
            StdDraw.setPenColor(StdDraw.BLACK);
            Font confirm = new Font("Arial", Font.BOLD, 40);
            StdDraw.setFont(confirm);
            StdDraw.text(8.5, 8, "Confirm");
            StdDraw.show();
            // check for selections
            boolean selection = true;
            selection = true;
            while (selection) {
                if (StdDraw.mouseY() < 3.5 && StdDraw.mouseY() > 1.4) {

                    if (StdDraw.mouseX() > 8.1 && StdDraw.mouseX() < 9.0 && StdDraw.isMousePressed()) {
                        if (officers[8] == 8) {
                            officers[8] = 1;
                        } else {
                            officers[8]++;
                        }
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing || Spammy inputs (Why this exists)
                        }
                    }
                    if (StdDraw.mouseX() > 7.1 && StdDraw.mouseX() < 8.0 && StdDraw.isMousePressed()) {
                        if (officers[7] == 8) {
                            officers[7] = 1;
                        } else {
                            officers[7]++;
                        }
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 6.1 && StdDraw.mouseX() < 7.0 && StdDraw.isMousePressed()) {
                        if (officers[6] == 8) {
                            officers[6] = 1;
                        } else {
                            officers[6]++;
                        }
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }

                    if (StdDraw.mouseX() > 4.1 && StdDraw.mouseX() < 5.0 && StdDraw.isMousePressed()) {
                        if (officers[4] == 8) {
                            officers[4] = 1;
                        } else {
                            officers[4]++;
                        }
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 3.1 && StdDraw.mouseX() < 4.0 && StdDraw.isMousePressed()) {
                        if (officers[3] == 8) {
                            officers[3] = 1;
                        } else {
                            officers[3]++;
                        }
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 2.1 && StdDraw.mouseX() < 3.0 && StdDraw.isMousePressed()) {
                        if (officers[2] == 8) {
                            officers[2] = 1;
                        } else {
                            officers[2]++;
                        }
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 1.1 && StdDraw.mouseX() < 2.0 && StdDraw.isMousePressed()) {
                        if (officers[1] == 8) {
                            officers[1] = 1;
                        } else {
                            officers[1]++;
                        }
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }

                }
                if (StdDraw.mouseY() < 7 && StdDraw.mouseY() > 3.5) {
                    if (StdDraw.mouseX() > 9.1 && StdDraw.mouseX() < 10.0 && StdDraw.isMousePressed()) {
                        pawn[9] = !pawn[9];
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 8.1 && StdDraw.mouseX() < 9.0 && StdDraw.isMousePressed()) {
                        pawn[8] = !pawn[8];
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 7.1 && StdDraw.mouseX() < 8.0 && StdDraw.isMousePressed()) {
                        pawn[7] = !pawn[7];
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 6.1 && StdDraw.mouseX() < 7.0 && StdDraw.isMousePressed()) {
                        pawn[6] = !pawn[6];
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 5.1 && StdDraw.mouseX() < 6.0 && StdDraw.isMousePressed()) {
                        pawn[5] = !pawn[5];
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 4.1 && StdDraw.mouseX() < 5.0 && StdDraw.isMousePressed()) {
                        pawn[4] = !pawn[4];
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 3.1 && StdDraw.mouseX() < 4.0 && StdDraw.isMousePressed()) {
                        pawn[3] = !pawn[3];
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 2.1 && StdDraw.mouseX() < 3.0 && StdDraw.isMousePressed()) {
                        pawn[2] = !pawn[2];
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 1.1 && StdDraw.mouseX() < 2.0 && StdDraw.isMousePressed()) {
                        pawn[1] = !pawn[1];
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                    if (StdDraw.mouseX() > 0.1 && StdDraw.mouseX() < 1.0 && StdDraw.isMousePressed()) {
                        pawn[0] = !pawn[0];
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                }

                if (StdDraw.mouseX() > 7.5 && StdDraw.mouseX() < 9.5 && StdDraw.mouseY() > 7.25
                        && StdDraw.mouseY() < 8.75) {
                    if (StdDraw.isMousePressed()) {
                        piecesBoard = PiecesBoard.pieceAllocation(pawn, officers);
                        done = false;
                        selection = false;
                        while (StdDraw.isMousePressed()) {
                            // do nothing
                        }
                    }
                }

            }
        }
    }

    public static int findIndexOfPiece(String x) {
        int index = 0;
        switch (x) {
        case "K":
            index = 0;
            break;
        case "k":
            index = 0;
            break;
        case "R":
            index = 1;
            break;
        case "r":
            index = 1;
            break;
        case "Q":
            index = 2;
            break;
        case "q":
            index = 2;
            break;
        case "N":
            index = 3;
            break;
        case "n":
            index = 3;
            break;
        case "B":
            index = 4;
            break;
        case "b":
            index = 4;
            break;
        case "P":
            index = 5;
            break;
        case "p":
            index = 5;
            break;
        case "D":
            index = 6;
            break;
        case "d":
            index = 6;
            break;
        case "F":
            index = 7;
            break;
        case "f":
            index = 7;
            break;
        case "E":
            index = 8;
            break;
        case "e":
            index = 8;
            break;
        case "A":
            index = 9;
            break;
        case "a":
            index = 9;
            break;
        case "W":
            index = 10;
            break;
        case "w":
            index = 10;
            break;

        }
        return index;
    }

    public static void terminalOutPut(String[] boardfile, Piece[][] piecesBoard) {
        // print out stage 1 (used in grading)
        int a = 0;
        while (true) {
            String line = boardfile[a];
            if (line.contains("-")) {
                break;
            }
            if (line.charAt(0) == '%') {
                continue;
            } else if (line.contains("%")) {
                line = line.substring(0, line.indexOf('%'));
            }
            System.out.println(line);
            if (line.contains("-")) {
                break;
            }
            a++;
        }
        System.out.println("-----");
        // print out stage 2 (used in grading)
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (piecesBoard[i][j] != null) {
                    System.out.print(piecesBoard[i][j]);
                } else {
                    System.out.print('.');
                }
                if (j < 9) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("-----");
        // print out stage 3 (used in grading). Pretty useful for making save files though
        if (playerTurn) {
            System.out.print("w");
        } else {
            System.out.print("b");
        }
        System.out.print(':');
        for (int i = 0; i < 4; i++) {
            if (castlingOpportunities[i]) {
                System.out.print('+');
            } else {
                System.out.print('-');
            }
        }
        System.out.print(':');
        System.out.print(movesCounter[0]);
        System.out.print(':');
        System.out.print(movesCounter[1]);
        System.out.println();
    }

    public static void Promote(Piece[][] piecesBoard, Move move, int line, int[] blackPieces) {

        String white = "KRQNBPDFEAW";
        String black = "krqnbpdfeaw";
        if (blackPieces[findIndexOfPiece(move.getPromote())] == 0) {
            MoveValidationErrors.illegalPromotion(line);
        }
        if (playerTurn) {
            if (!white.contains(move.getPromote())) {

                MoveValidationErrors.illegalPromotion(line);
            }

            switch (move.getPromote()) {
            case "K":
                piecesBoard[move.drank][move.dfile] = new King(move.drank, move.dfile, true, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "R":
                piecesBoard[move.drank][move.dfile] = new Rook(move.drank, move.dfile, true, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "Q":
                piecesBoard[move.drank][move.dfile] = new Queen(move.drank, move.dfile, true, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "N":
                piecesBoard[move.drank][move.dfile] = new Knight(move.drank, move.dfile, true, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "B":
                piecesBoard[move.drank][move.dfile] = new Bishop(move.drank, move.dfile, true, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "P":
                piecesBoard[move.drank][move.dfile] = new Pawn(move.drank, move.dfile, true, false);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "D":
                piecesBoard[move.drank][move.dfile] = new DrunkenPawn(move.drank, move.dfile, true, false);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "F":
                piecesBoard[move.drank][move.dfile] = new FlyingDragon(move.drank, move.dfile, true, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "E":
                piecesBoard[move.drank][move.dfile] = new Elephant(move.drank, move.dfile, true, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "A":
                piecesBoard[move.drank][move.dfile] = new Amazon(move.drank, move.dfile, true, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "W":
                piecesBoard[move.drank][move.dfile] = new Princess(move.drank, move.dfile, true, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            }
        } else if (!playerTurn) {
            if (!black.contains(move.getPromote())) {
                MoveValidationErrors.illegalPromotion(line);
            }
            switch (move.getPromote()) {
            case "k":
                piecesBoard[move.drank][move.dfile] = new King(move.drank, move.dfile, false, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "r":
                piecesBoard[move.drank][move.dfile] = new Rook(move.drank, move.dfile, false, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "q":
                piecesBoard[move.drank][move.dfile] = new Queen(move.drank, move.dfile, false, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "n":
                piecesBoard[move.drank][move.dfile] = new Knight(move.drank, move.dfile, false, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "b":
                piecesBoard[move.drank][move.dfile] = new Bishop(move.drank, move.dfile, false, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "p":
                piecesBoard[move.drank][move.dfile] = new Pawn(move.drank, move.dfile, false, false);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "d":
                piecesBoard[move.drank][move.dfile] = new DrunkenPawn(move.drank, move.dfile, false, false);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "f":
                piecesBoard[move.drank][move.dfile] = new FlyingDragon(move.drank, move.dfile, false, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "e":
                piecesBoard[move.drank][move.dfile] = new Elephant(move.drank, move.dfile, false, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "a":
                piecesBoard[move.drank][move.dfile] = new Amazon(move.drank, move.dfile, false, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            case "w":
                piecesBoard[move.drank][move.dfile] = new Princess(move.drank, move.dfile, false, true);
                piecesBoard[move.srank][move.sfile] = null;
                break;
            }
            movesCounter[1]++;
        }
        playerTurn = !playerTurn;
        movesCounter[0] = 0;

    }

    //why does this exist? Maybe it was used to debug in early development
    public static int additions(String[] movefile) {
        int additions = 0;
        for (int i = 0; i < movefile.length; i++) {
            if (movefile[i].charAt(0) == '%') {
                additions++;
            }
        }
        return additions;
    }

    public static String[] getRidOfComments(String[] movefile) {
        int remove = 0;

        for (int i = 0; i < movefile.length; i++) {
            if (movefile[i].charAt(0) == '%') {
                remove++;
            }
        }
        String[] newMovefile = new String[movefile.length - remove];
        int j = 0;
        for (int i = 0; i < movefile.length; i++) {
            if (!(movefile[i].charAt(0) == '%')) {
                newMovefile[j] = movefile[i];
                j++;
            }
        }
        return newMovefile;

    }

    public static String[] makeString(File BoardFile, int lines) {
        int i = 0;
        String[] boardfile = new String[lines];
        Scanner boardScanner = null;
        try {
            boardScanner = new Scanner(BoardFile);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Board file does not exist");
        }
        while (boardScanner.hasNextLine()) {
            String line = boardScanner.nextLine();
            String newline = line.replace(" ", "");
            boardfile[i] = newline;
            i++;
        }
        boardScanner.close();
        return boardfile;
    }

    public static void castle(Piece[][] pieceBoard, Move moves) {
        if (playerTurn) {

            if (moves.getMove().equals("CASTLING QUEENSIDE")) {
                movesCounter[0]++;

                pieceBoard[9][2] = pieceBoard[9][5];
                pieceBoard[9][5] = null;
                pieceBoard[9][3] = pieceBoard[9][0];
                pieceBoard[9][0] = null;
                castlingOpportunities[2] = false;
                castlingOpportunities[3] = false;
                playerTurn = !playerTurn;
            } else if (moves.getMove().equals("CASTLING KINGSIDE")) {
                movesCounter[0]++;

                pieceBoard[9][8] = pieceBoard[9][5];
                pieceBoard[9][5] = null;
                pieceBoard[9][7] = pieceBoard[9][9];
                pieceBoard[9][9] = null;
                castlingOpportunities[2] = false;
                castlingOpportunities[3] = false;
                playerTurn = !playerTurn;
            }
        } else if (!playerTurn) {

            if (moves.getMove().equals("CASTLING QUEENSIDE")) {
                movesCounter[0]++;
                movesCounter[1]++;
                pieceBoard[0][2] = pieceBoard[0][5];
                pieceBoard[0][5] = null;
                pieceBoard[0][3] = pieceBoard[0][0];
                pieceBoard[0][0] = null;
                castlingOpportunities[0] = false;
                castlingOpportunities[1] = false;
                playerTurn = !playerTurn;
            } else if (moves.getMove().equals("CASTLING KINGSIDE")) {
                movesCounter[0]++;
                movesCounter[1]++;
                pieceBoard[0][8] = pieceBoard[0][5];
                pieceBoard[0][5] = null;
                pieceBoard[0][7] = pieceBoard[0][9];
                pieceBoard[0][9] = null;
                castlingOpportunities[0] = false;
                castlingOpportunities[1] = false;
                playerTurn = !playerTurn;
            }
        }

    }

}
