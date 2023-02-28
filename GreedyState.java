public class GreedyState implements Comparable<GreedyState>{
    private Board cur;
    private int score;

    GreedyState(Board b) {
        cur = b;
        score = 0;
    }

    //getters
    public Board getBoardObject(){return cur;}
    public int getScore(){return score;}

    //setter
    public void setScore(int x){score = x;}

    //comparator
    public int compareTo(GreedyState other){
        int other_s = other.getScore();
        int cur_s = getScore();        
        if (cur_s != other_s) {
            return Integer.compare(cur_s, other_s);
        }

        int[][] other_board = other.getBoardObject().getBoard();
        int[][] cur_board = cur.getBoard();
        //if(other_board != cur_board)
        return cur_board.toString().compareTo(other_board.toString());
    }

}