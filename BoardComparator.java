import java.util.Comparator;

public class BoardComparator implements Comparator<Board>{
    @Override
    public int compare(Board b1, Board b2) {
        return b1.toString().compareTo(b2.toString());
    }
}
