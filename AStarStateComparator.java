import java.util.Comparator;

public class AStarStateComparator implements Comparator<AStarState>{
    @Override
    public int compare(AStarState o1, AStarState o2) {
        return o1.getBoardObject().toString().compareTo(o2.getBoardObject().toString());
    }
}
