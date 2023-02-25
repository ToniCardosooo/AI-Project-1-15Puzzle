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

    // ----------------------------------------------------------------------

    // -------------------------------- MAIN --------------------------------
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // ler boards do input
        int[][] cfg_i = readBoard(in);
        int[][] cfg_f = readBoard(in);

        Board b_i = new Board(cfg_i);
        Board b_f = new Board(cfg_f);

        // pre-verificar se é possivel chegar de b_i até b_f
        if (!isSolvable(b_i, b_f)){
            System.out.println("It's impossible to go from");
            System.out.println(b_i);
            System.out.println("to");
            System.out.println(b_f);
            return;
        }

        AgentIDFS a_idfs = new AgentIDFS(b_i, b_f);
        Stack<Board> play = a_idfs.solveIDFS(15);

        if (play == null)
            System.out.println("Não foi encontrada a solução");
        else{
            while (play.size() > 0){
                System.out.println(play.pop());
            }
        }
    
    }
}