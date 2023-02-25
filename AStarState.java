public class AStarState implements Comparable<AStarState>{
    private Board cur;
    private int score;

    AStarState(Board b) {
        cur = b;
        score = 0;
    }

    // getters
    public Board getBoardObject(){return cur;}
    public int getScore(){return score;}

    // comparator
    public int compareTo(AStarState other){
        int[][] other_board = other.getBoardObject().getBoard();
        int[][] cur_board = cur.getBoard();

        return cur_board.toString().compareTo(other_board.toString());
    }

}