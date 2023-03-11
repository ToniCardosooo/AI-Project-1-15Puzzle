import java.util.Stack;

public class IDFS{
    private Board initial_b; // saves a copy of the initial board
    private Board final_b; // saves a copy of the final board

    IDFS(Board i, Board f){
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
        NIState cur_state;
        Stack<NIState> cur_path;
        Stack<NIState> s;

        int[][] vec = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // up, down, left, right (respectively)

        // for loop that iterates the max_level for the DFS search
        for (int l = 1; l <= max_level; l++){

            // set current state to initial state, and clear visited states set
            cur_state = new NIState(initial_b, 0);
            cur_path = new Stack<NIState>();

            // stack for DFS algorithm
            s = new Stack<NIState>();
            s.push(cur_state);

            int maxInS = 0;

            // main loop of DFS search
            while (s.size() > 0){
                cur_state = s.pop();

                // gather current state's board info and level (for code simplification purposes)
                Board cur_board = cur_state.getBoardObject();
                int[] cur_blank_pos = cur_board.getPos();
                int x0 = cur_blank_pos[0], y0 = cur_blank_pos[1];
                int cur_level = cur_state.getLevel();

                // update current path
                if (cur_path.size() > 0){
                    Board path_latest_board = cur_path.peek().getBoardObject();
                    while (!path_latest_board.isEqual(cur_board.getParent())){
                        cur_path.pop();
                        path_latest_board = cur_path.peek().getBoardObject();
                    }
                }
                cur_path.push(cur_state);
                    
                // create state in all directions (child states of current state)
                for (int[] v : vec){  
                    // check if current state is over limit level
                    if (cur_level > l) break;

                    // create child state
                    Board child_board = cur_board.setPos(x0+v[0], y0+v[1]);
                    NIState child_state = new NIState(child_board, cur_level+1);

                    // check if child is invalid (== null)
                    if (child_board == null) continue;

                    // check if child is the final state
                    if (isFinished(child_board.getBoard())){
                        System.out.println("Maximum number of states in stack: " + maxInS);
                        System.out.println("Final state found at a depth of " + (cur_state.getLevel() + 1));
                        return playthrough(child_board);
                    }

                    // else
                    // check if child state is an already existing state in the current path (preventing cycles)
                    if (!cur_path.contains(child_state)){
                        // push child state to the DFS stack
                        s.push(child_state);
                    }

                } // end of for loop
                
                if (s.size() > maxInS) maxInS = s.size();
            } // end of while loop
            
        } // end of iterative DFS loop

        // if code reaches this point, then there is no solution for the given initial state
        return null;
    }
}
