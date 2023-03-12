import java.util.Scanner;
import java.util.Stack;

public class Game{

    private static int[][] readBoard(Scanner in){
        int[][] b = new int[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                b[i][j] = in.nextInt();
            }
        }
        return b;
    }

    // function to check if a given board can reach the Final Standard Configuration 
    private static boolean isSolvableToFSC(Board b){
        int[][] matrix = b.getBoard();
        // convert the matrix to a line
        int[] line = new int[16];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                line[4*i + j] = matrix[i][j];
            }
        }
        // get the sum of inversions for each number
        /* 
            for each number in the list, its number of inversions is the amount of numbers
            that are lower than itself and that lay after itself on the list (excluding 0)
        */ 
        int inv = 0;
        for (int i = 0; i < 16; i++){
            for (int j = i+1; j < 16; j++){
                if (line[i] != 0 && line[j] != 0 && line[j] < line[i]) inv++;
            }
        }
        /*
            for an initial (square) board with even size, that board can reach the FSC
            if (total_inv%2 == 0) == (blank_row%2 == 1)
        */
        int[] blank = b.getPos();
        return ((inv%2 == 0) == (blank[0]%2 == 1));
    }

    private static boolean isSolvable(Board i, Board f){
        return (isSolvableToFSC(i) == isSolvableToFSC(f));
    }


    // -------------------------------- MAIN --------------------------------

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // read boards
        int[][] cfg_i = readBoard(in);
        int[][] cfg_f = readBoard(in);

        Board b_i = new Board(cfg_i);
        Board b_f = new Board(cfg_f);

        // check if it's possible to go from b_i to b_f
        if (!isSolvable(b_i, b_f)){
            System.out.println("It's impossible to go from\n");
            System.out.println(b_i);
            System.out.println("to\n");
            System.out.println(b_f);
            return;
        }

        long startTime = 0;
        Stack<Board> play = null;
        switch (args[0]) {
            case "DFS":
                DFS a_DFS = new DFS(b_i, b_f);
                startTime = System.nanoTime();
                play = a_DFS.solveDFS(15);
                break;
                
            case "BFS":
                BFS a_BFS = new BFS(b_i, b_f);
                startTime = System.nanoTime();
                play = a_BFS.solveBFS();
                break;
                
            case "IDFS":
                IDFS a_IDFS = new IDFS(b_i, b_f);
                startTime = System.nanoTime();
                play = a_IDFS.solveIDFS(50);
                break;
                
            case "A*-misplaced":
                AStar a_AStar1 = new AStar(b_i, b_f);
                startTime = System.nanoTime();
                play = a_AStar1.solveAStar(1);
                break;
                
            case "A*-Manhattan":
                AStar a_AStar2 = new AStar(b_i, b_f);
                startTime = System.nanoTime();
                play = a_AStar2.solveAStar(2);
                break;
                
            case "Greedy-misplaced":
                Greedy a_Greedy1 = new Greedy(b_i, b_f);
                startTime = System.nanoTime();
                play = a_Greedy1.solveGreedy(1);
                break;
                
            case "Greedy-Manhattan":
                Greedy a_Greedy2 = new Greedy(b_i, b_f);
                startTime = System.nanoTime();
                play = a_Greedy2.solveGreedy(2);
                break;
                
            default:
                break;
        }

        if (play == null)
            System.out.println("A solution was not found!");
        else{
            long endTime = System.nanoTime();
            System.out.println("The solution was found in " + ((endTime-startTime)/1000000) + " miliseconds!");

            while (play.size() > 0){
                System.out.println(play.pop());
            }
        }

        in.close();
    }
}