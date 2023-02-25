import java.util.Stack;
import java.util.Set;
import java.util.TreeSet;

public class AgentIDFS{
    private Board initial_b; // para guardar uma copia do tabuleiro inicial
    private Board final_b; // para guardar uma copia do tabuleiro final

    AgentIDFS(Board i, Board f){
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

    // Iterative-Depth-First-Search algorithm that returns the playthrough to finish the game in a stack
    public Stack<Board> solveIDFS(int max_level){
        IDFSState cur_state;
        Set<IDFSState> visited;

        int[][] vec = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // up, down, left, right (respectively)

        // for loop that iterates the max_level for the DFS search
        for (int l = 1; l <= max_level; l++){

            //System.out.println("Max Level = " + l);

            // set current state to initial state, and clear visited states set
            cur_state = new IDFSState(initial_b, 0);
            visited = new TreeSet<IDFSState>();

            // stack for DFS algorithm
            Stack<IDFSState> s = new Stack<>();
            s.push(cur_state);

            // main loop of DFS search
            while (s.size() > 0){
                // gather current state's board info and level (for code simplification purposes)
                Board cur_board = cur_state.getBoardObject();
                int[] cur_blank_pos = cur_board.getPos();
                int x0 = cur_blank_pos[0], y0 = cur_blank_pos[1];
                int cur_level = cur_state.getLevel();


                //System.out.println("Current Board = \n" + cur_board);

                    
                // create state in all directions (child states of current state)
                for (int[] v : vec){  
                    // check if current state is over limit level
                    if (cur_level > l) break;

                    // create child state
                    Board child_board = cur_board.setPos(x0+v[0], y0+v[1]);
                    IDFSState child_state = new IDFSState(child_board, cur_level+1);

                    //System.out.println("Child Board = \n" + child_board);

                    // check if child is invalid (== null)
                    if (child_board == null) continue;

                    // check if child is the final state
                    if (isFinished(child_board.getBoard())){
                        System.out.println("Final state found at level " + child_state.getLevel() + "\n");
                        return playthrough(child_board);
                    }

                    // else
                    // check if child state is an already existing state in the DFS tree
                    if (!visited.contains(child_state)){
                        visited.add(child_state);
                        // push child state to the DFS stack
                        s.push(child_state);
                    }

                } // end of for loop

                cur_state = s.pop();                
            } // end of while loop
            
        } // end of iterative DFS loop

        // if code reaches this point, then there is no solution for the given initial state
        return null;
    }
}