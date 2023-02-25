public class AStar {
    private Board initial;
    private Board final_b;

    AStar(Board b, Board f) {
        initial = b;
        final_b = f;
    }

//i need a priority queue
// also queremos o minimo de manhattan + outOfPlace


    private int manhattan(Board b1, Board b2) {
        int x = 0;
        int i1, j1, i2, j2;
        primary:
        for (int n = 0; n < 16; n++)
            secondary:
            for (int i = 0; i < 4; i++){
                third:
                for (int j = 0; j < 4; j++){
                    if (b1.getBoard()[i][j] == n) {i1 = i; j1 = j; break secondary;}
                    if (b2.getBoard()[i][j] == n) {i2 = i; j2 = j; break secondary;}
                }
            }
            x += Math.abs(i1 - i2) + Math.abs(j1 - j2);
        }
        return x;
    }

    private int outOfPlace(Board b1, Board b2) {
        int x = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (b1.getBoard()[i][j] != b2.getBoard()[i][j]) x++;
            }
        }
        return x;
    }







}