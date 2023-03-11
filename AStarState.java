public class AStarState implements Comparable<AStarState>{
    private Board cur;
    private int score;
    private int level;

    AStarState(Board b, int l) {
        cur = b;
        score = 0;
        level = l;
    }

    //getters
    public Board getBoardObject(){return cur;}
    public int getScore(){return score;}
    public int getLevel(){return level;}


    //setter
    public void setScore(int x){score = x + 2*level;}

    //comparator
    public int compareTo(AStarState other){
        int other_s = other.getScore();
        int cur_s = getScore();
        if (cur_s != other_s) {
            return Integer.compare(cur_s, other_s);
        }
        
        int other_lvl = other.getLevel();
        int cur_lvl = getLevel();
        if (cur_lvl != other_lvl) {
            return Integer.compare(cur_lvl, other_lvl);
        }

        Board other_board = other.getBoardObject();
        Board cur_board = cur;
        return cur_board.toString().compareTo(other_board.toString());
    }

}
