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
        Board other_board = other.getBoardObject();
        Board cur_board = cur_b;

        return cur_board.toString().compareTo(other_board.toString());
    }

    @Override
    public boolean equals(Object obj) {
        NIState other = (NIState) obj;
        if (this.compareTo(other) == 0) return true;
        return false;
    }

}
