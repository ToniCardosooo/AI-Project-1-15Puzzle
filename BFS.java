import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class BFS{
    private Board initial_b; // saves a copy of the initial board
    private Board final_b; // saves a copy of the final board

    BFS(Board i, Board f){
        initial_b = new Board(i.getBoard());
        final_b = new Board(f.getBoard());
    }

    private boolean isFinished(int[][] cur){
        int[][] f = final_b.getBoard();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (cur[i][j] != f[i][j]) return false;
            }
        }
        return true;
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

    // Breadth-First-Search algorithm that returns the playthrough to finish the game in a stack
    public Stack<Board> solveBFS(){
      
        NIState cur_state = new NIState(initial_b, 0);
        Set<NIState> visited = new TreeSet<NIState>();
        visited.add(cur_state);
        int[][] vec = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // up, down, left, right (respectively)

        // queue for BFS algorithm
        Queue<NIState> q = new LinkedList<NIState>();
        q.add(cur_state);

        int maxInQ = 0;
        
        while (q.size() > 0){
            cur_state = q.poll();

            for (int[] v : vec){  
                Board child = cur_state.getBoardObject().setPos(v[0] + cur_state.getBoardObject().getPos()[0], v[1] + cur_state.getBoardObject().getPos()[1]);
                if (child == null) continue;
                
                if (isFinished(child.getBoard())) {
                    System.out.println("Maximum number of states in queue: " + maxInQ);
                    System.out.println("Final state found at a depth of " + (cur_state.getLevel() + 1));
                    return playthrough(child);
                }

                NIState c = new NIState(child, cur_state.getLevel() + 1);
                if (!visited.contains(c)){
                    visited.add(c);
                    q.add(c);
                }

            }

            if (q.size() > maxInQ) maxInQ = q.size();
        }

        // if code reaches this point, then there is no solution for the given initial state
        return null;
    }
}