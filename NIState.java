public class NIState implements Comparable<NIState>{
    private Board cur_b;
    private int level;

    // construtor
    NIState(Board b, int l){
        cur_b = b;
        level = l;
    }

    // getters
    public Board getBoardObject(){return cur_b;}
    public int getLevel(){return level;}

    // comparator
    public int compareTo(NIState other){
        int[][] other_board = other.getBoardObject().getBoard();
        int[][] cur_board = cur_b.getBoard();

        return cur_board.toString().compareTo(other_board.toString());
    }

}
