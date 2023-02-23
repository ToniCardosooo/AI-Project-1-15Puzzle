public class Board{
    private int[][] b;
    private int x0, y0;
    private Board parent;

    Board(int[] in){
        b = new int[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                b[i][j] = in[4*i + j];
                if (b[i][j] == 0){
                    x0=i; y0=j;
                }
            }
        }
        parent = null;
    }

    Board(int[][] in){
        b = new int[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                b[i][j] = in[i][j];
                if (b[i][j] == 0){
                    x0=i; y0=j;
                }
            }
        }
        parent = null;
    }

    private boolean verify(int x, int y){
        return ((0 <= x && x <= 3) && (0 <= y && y <= 3));
    }

    //getters
    public int[][] getBoard(){
        int[][] bb = new int[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                bb[i][j] == b[i][j];
            }
        }
        return bb;
    }
    public int[] getPos(){
        int[] p = new int[2];
        p[0] = x0; p[1] = y0;
        return p;
    }

    public Board getParent(){return parent;}

    // setters
    public void setParent(Board p){parent = p;}

    public Board setPos(int x, int y){
        if (!verify(x, y)) return null;
        int[][] bb = getBoard();
        int n = bb[x][y];
        bb[x][y] = 0;
        bb[x0][y0] = n;
        x0 = x;
        y0 = y;
        Board child = new Board(bb);
        child.setParent(this);
        return child;
    }

    // print
    public String toString(){
        String s = "";
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                s += b[i][j]+" ";
            }
            s += "\n";
        }
        return s;
    }


}