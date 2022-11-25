/**
 * Validation methods to be used during the validation of a board file.
 */
public class BoardValidationErrors {

	/**
	 * To be called in case of 3.2.1 and 3.2.2
	 *
	 * @param line
	 *            The line number of the invalid allocation.
	 */
	public static void illegalPieceAllocation(int line) {
		System.out.println("ERROR: Invalid piece allocation on line " + line);
		System.exit(1);
	}

	/**
	 * To be called in case of 3.3.1
	 *
	 * @param file
	 *            The file component of the chess notation (the d in d6)
	 * @param rank
	 *            The rank component of the chess notation (the 6 in d6)
	 */
	public static void illegalPiece(char file, int rank) {
		System.out.println("ERROR: Illegal piece at position " + file + rank);
		System.exit(2);
	}

	/**
	 * To be called in case of 3.3.2
	 *
	 * @param file
	 *            The file component of the chess notation (the d in d6)
	 * @param rank
	 *            The rank component of the chess notation (the 6 in d6)
	 */
	public static void pawnAllocationExceeded(char file, int rank) {
		System.out.println("ERROR: Pawn allocation exceeded at position " + file + rank);
		System.exit(3);
	}

	/**
	 * To be called in case of 3.3.3
	 *
	 * @param file
	 *            The file component of the chess notation (the d in d6)
	 * @param rank
	 *            The rank component of the chess notation (the 6 in d6)
	 */
	public static void officerAllocationExceeded(char file, int rank) {
		System.out.println("ERROR: Officer allocation exceeded at position " + file + rank);
		System.exit(4);
	}

	/**
	 * To be called in the case 3.3.4
	 */
	public static void illegalBoardDimension() {
		System.out.println("ERROR: Illegal board dimension");
		System.exit(5);
	}

	/**
	 * To be called in case of 3.3.5
	 *
	 * @param file
	 *            The file component of the chess notation (the d in d6)
	 * @param rank
	 *            The rank component of the chess notation (the 6 in d6)
	 */
	public static void illegalPawnPosition(char file, int rank) {
		System.out.println("ERROR: Illegal Pawn at position " + file + rank);
		System.exit(6);
	}

	/**
	 * To be called in case of 3.3.6
	 *
	 * @param file
	 *            The file component of the chess notation (the d in d6)
	 * @param rank
	 *            The rank component of the chess notation (the 6 in d6)
	 */
	public static void illegalElephantPosition(char file, int rank) {
		System.out.println("ERROR: Illegal Elephant at position " + file + rank);
		System.exit(7);
	}

	/**
	 * To be called in case of 3.4.1
	 *
	 * @param line
	 *            The line number of the status line
	 */
	public static void illegalNextPlayerMarker(int line) {
		System.out.println("ERROR: Illegal next player marker on line " + line);
		System.exit(7);
	}

	/**
	 * To be called in case of 3.4.2
	 *
	 * @param line
	 *            The line number of the status line
	 * @param index
	 *            The index of the castling opportunity
	 */
	public static void illegalCastlingOpportunity(int line, int index) {
		System.out.println("ERROR: Illegal castling opportunity at index " + index + " on line " + line);
		System.exit(8);
	}

	/**
	 * To be called in case of 3.4.3
	 *
	 * @param line
	 *            The line number of the status line
	 */
	public static void illegalHalfmoveClock(int line) {
		System.out.println("ERROR: Illegal halfmove clock on line " + line);
		System.exit(9);
	}

	/**
	 * To be called in case of 3.4.4
	 *
	 * @param line
	 *            The line number of the status line
	 */
	public static void illegalMoveCounter(int line) {
		System.out.println("ERROR: Illegal move counter on line " + line);
		System.exit(10);
	}
}
