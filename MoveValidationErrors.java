public class MoveValidationErrors {

    /**
     * To be called in case of 5.2
     *
     * @param line The line number of the illegal move
     */
    public static void illegalMove(int line) {
        System.out.println("ERROR: Illegal move on line " + line);
        System.exit(11);
    }

    /**
     * To be called in case of 5.3
     *
     * @param line The line number of the illegal capture
     */
    public static void illegalCapture(int line) {
        System.out.println("ERROR: Illegal capture on line " + line);
        System.exit(12);
    }

    /**
     * To be called in case of 5.4
     *
     * @param line The line number of the illegal castling move
     */
    public static void illegalCastlingMove(int line) {
        System.out.println("ERROR: Illegal castling move on line " + line);
        System.exit(13);
    }

    /**
     * To be called in case of 5.5
     *
     * @param line The line number of the illegal promotion
     */
    public static void illegalPromotion(int line) {
        System.out.println("ERROR: Illegal promotion on line " + line);
        System.exit(14);
    }

    /**
     * To be called in case of 5.6
     *
     * @param line The line number of the move with the illegal check suffix
     */
    public static void illegalCheck(int line) {
        System.out.println("ERROR: Illegal check suffix on line " + line);
        System.exit(15);
    }

    /**
     * To be called in case of 5.7
     *
     * @param line The line number of the move with the illegal checkmate suffix
     */
    public static void illegalCheckmate(int line) {
        System.out.println("ERROR: Illegal checkmate suffix on line " + line);
        System.exit(16);
    }
}
