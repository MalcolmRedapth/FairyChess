
public class Move {
    String[] movefile = FairyChess.movefile;
    public Move[] moves;
    private String Move;
    private String Affect;
    private String Promote;
    private boolean Check;
    public int sfile;
    public int srank;
    public int dfile;
    public int drank;

    //I have two Move constructors. They are distinguished by the input variables. MAybe I should give them different names after all?
    public Move(String line) {
        this.Affect = typeOfAffect(line);
        this.Move = typeOfMove(line);
        this.Promote = typeOfPromote(line);
        this.Check = Check(line);
        this.srank = sourceRank(line);
        this.sfile = sourceFile(line);
        this.drank = destinationRank(line);
        this.dfile = destinationFile(line);
    }
     public Move(String Move, int srank, int sfile, int drank, int dfile){
         this.Affect = "";
         this.Move = Move;
         this.srank = srank;
         this.sfile = sfile;
         this.drank = drank;
         this.dfile = dfile;
         
     }
    

    // here I store the moves to be verified and played out
    public static Move[] Moves(String[] movefile) {

        Move[] moves = new Move[movefile.length];

        for (int i = 0; i < movefile.length; i++) {

            moves[i] = new Move(movefile[i]);
        }
        return moves;
    }

    public String getPromote() {
        return Promote;
    }

    public String getMove() {
        return Move;
    }

    public String getAffect() {
        return Affect;
    }
    public boolean getCheck(){
        return Check;
    }

    public static boolean Check(String line) {
        if (line.contains("+")) {
            return true;
        } else {
            return false;
        }
    }

    public static String typeOfMove(String line) {
        String typeOfMove = "";
        if (line.contains("0-0")) {
            if (line.contains("0-0-0")) {
                typeOfMove = "CASTLING QUEENSIDE";
            } else {
                typeOfMove = "CASTLING KINGSIDE";

            }
        } else if (line.contains("-") && !line.contains("0-0")) {
            typeOfMove = "MOVE";
        } else if (line.contains("x")) {
            typeOfMove = "CAPTURE";
        }

        return typeOfMove;
    }

    public static String typeOfAffect(String line) {
        String typeOfAffect = "";
        if (line.contains("=")) {
            typeOfAffect = "PROMOTION";
        }
        return typeOfAffect;
    }

    public static String typeOfPromote(String line) {
        String typeOfPromote = "none";
        if (line.contains("=")) {
            if (line.contains("+")) {
                typeOfPromote = line.charAt(line.length() - 2) + "";
            } else {
                typeOfPromote = line.charAt(line.length() - 1) + "";
            }
        }
        return typeOfPromote;

    }

    public static int sourceFile(String line) {
        if (!line.contains("0-0")) {
            int sfile = 0;
            char x = line.charAt(0);

            switch (x) {
            case 'a':
                sfile = 0;
                break;
            case 'b':
                sfile = 1;
                break;
            case 'c':
                sfile = 2;
                break;
            case 'd':
                sfile = 3;
                break;
            case 'e':
                sfile = 4;
                break;
            case 'f':
                sfile = 5;
                break;
            case 'g':
                sfile = 6;
                break;
            case 'h':
                sfile = 7;
                break;
            case 'i':
                sfile = 8;
                break;
            case 'j':
                sfile = 9;
                break;

            }

            return sfile;
        } else
            return 0;

    }

    public static int sourceRank(String line) {
        if (!line.contains("0-0")) {
            int srank = 0;
            String x = "";
            if (line.contains("-")) {
                x = line.substring(1, line.indexOf('-'));
            } else if (line.contains("x")) {
                x = line.substring(1, line.indexOf('x'));
            }

            switch (x) {
            case "10":
                srank = 0;
                break;
            case "9":
                srank = 1;
                break;
            case "8":
                srank = 2;
                break;
            case "7":
                srank = 3;
                break;
            case "6":
                srank = 4;
                break;
            case "5":
                srank = 5;
                break;
            case "4":
                srank = 6;
                break;
            case "3":
                srank = 7;
                break;
            case "2":
                srank = 8;
                break;
            case "1":
                srank = 9;
                break;

            }

            return srank;
        } else {
            return 0;
        }
    }

    public static int destinationFile(String line) {
        if (!line.contains("0-0")) {
            int dfile = 0;
            int index = 0;
            if (line.contains("-")) {
                index = line.indexOf("-");
            } else if (line.contains("x")) {
                index = line.indexOf("x");
            }
            char x = line.charAt(index + 1);
            switch (x) {
            case 'a':
                dfile = 0;
                break;
            case 'b':
                dfile = 1;
                break;
            case 'c':
                dfile = 2;
                break;
            case 'd':
                dfile = 3;
                break;
            case 'e':
                dfile = 4;
                break;
            case 'f':
                dfile = 5;
                break;
            case 'g':
                dfile = 6;
                break;
            case 'h':
                dfile = 7;
                break;
            case 'i':
                dfile = 8;
                break;
            case 'j':
                dfile = 9;
                break;

            }
            return dfile;
        } else {
            return 0;
        }

    }

    public static int destinationRank(String line) {
        if (!line.contains("0-0")) {
            int drank = 0;
            int upper = 0;
            int lower = 0;
            String x = "";
            if (line.charAt(line.length() - 1) == '+') {
                upper = line.length() - 1;
            } else if (line.charAt(line.length() - 2) == '=') {
                upper = line.length() - 2;
            } else {
                upper = line.length();
            }
            if (line.contains("x")) {
                lower = line.indexOf("x") + 2;
            } else if (line.contains("-")) {
                lower = line.indexOf("-") + 2;
            }
            x = line.substring(lower, upper);
            switch (x) {
            case "10":
                drank = 0;
                break;
            case "9":
                drank = 1;
                break;
            case "8":
                drank = 2;
                break;
            case "7":
                drank = 3;
                break;
            case "6":
                drank = 4;
                break;
            case "5":
                drank = 5;
                break;
            case "4":
                drank = 6;
                break;
            case "3":
                drank = 7;
                break;
            case "2":
                drank = 8;
                break;
            case "1":
                drank = 9;
                break;

            }

            return drank;
        } else {
            return 0;
        }
    }
}
