import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class AStar {
    private Board initial_b;
    private Board final_b;

    //constructor
    AStar(Board b, Board f) {
        initial_b = new Board(b.getBoard());
        final_b = new Board(f.getBoard());
    }

    //checks if we achieved the final state of the board
    private boolean isFinished(int[][] cur){
        int[][] f = final_b.getBoard();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (cur[i][j] != f[i][j]) return false;
            }
        }
        return true;
    }

    //evaluation functions

    private int manhattan(Board b1, Board b2) {
        int x = 0;
        int i1, j1, i2, j2;
        primary:
        for (int n = 0; n < 16; n++) {
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

    private int evaluate(Board b1, Board b2) {
        return manhattan(initial_b, final_b) + outOfPlace(initial_b, final_b)
    }

    // function to call once the final state is found
    // returns a stack with all the boards from the initial state (top) to the final one
    private Stack<Board> playthrough(Board f){
        Stack<Board> s = new Stack<>();
        while (f != null){
            s.push(f);
            f = f.getParent();
        }
        return s;
    }

}