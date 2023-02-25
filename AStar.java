import java.util.PriorityQueue;
import java.util.Stack;

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
        int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
        for (int n = 0; n < 16; n++) {
            second:
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    if (b1.getBoard()[i][j] == n) {i1 = i; j1 = j; break second;}
                    if (b2.getBoard()[i][j] == n) {i2 = i; j2 = j; break second;}
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

    private int evaluate(int n, Board b1, Board b2) {
        if (n == 2) return manhattan(b1, b2);
        return outOfPlace(b1, b2);
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

    public Stack<Board> solveAStar(int n) {

        int[][] vec = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        AStarState cur_state = new AStarState(initial_b, 0, 0);
        PriorityQueue<AStarState> q = new PriorityQueue<>();
        q.add(cur_state);

        while (q.size() > 0){
            if (isFinished(cur_state.getBoardObject().getBoard())) {
                System.out.println("Final state found");
                return playthrough(cur_state.getBoardObject());
            }

            for (int[] v : vec){  
               Board child = cur_state.getBoardObject().setPos(v[0] + cur_state.getBoardObject().getPos()[0], v[1] + cur_state.getBoardObject().getPos()[1]);
               if (child == null) continue;

               AStarState c = new AStarState(child, evaluate(n, child, final_b), cur_state.getLevel() + 1);
               q.add(c);
            }
            q.poll();
            cur_state = q.peek();
        }

        return null;
    }


}