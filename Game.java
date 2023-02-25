import java.util.Scanner;
import java.util.Stack;

public class Game{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[][] cfg_i = new int[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                cfg_i[i][j] = in.nextInt();
            }
        }

        int[][] cfg_f = new int[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                cfg_f[i][j] = in.nextInt();
            }
        }

        Board b_i = new Board(cfg_i);
        Board b_f = new Board(cfg_f);

        AgentIDFS a_idfs = new AgentIDFS(b_i, b_f);

        Stack<Board> play = a_idfs.solveIDFS(5);

        if (play == null)
            System.out.println("Não foi encontrada a solução");
        else{
            while (play.size() > 0){
                System.out.println(play.pop());
            }
        }
    
    }
}