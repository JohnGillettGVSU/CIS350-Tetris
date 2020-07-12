public class TetrisPiece {
    private int[][] position;
    private String name;

    public TetrisPiece(String name) {
        this.name = name;
        position = new int[4][2];

        //line piece's starting position on the board will have each square
        //at position {(4, 19), (4, 18), (4, 17), (4, 16)}
        if (name.equals("linePiece")) {
            position[0][0] = 4;
            position[0][1] = 19;
            position[1][0] = 4;
            position[1][1] = 18;
            position[2][0] = 4;
            position[2][1] = 17;
            position[3][0] = 4;
            position[3][1] = 16;
        }
        //square piece's starting position on the board will have each square
        //at position {(4, 19), (5, 19), (4, 18), (5, 18)}
        else if (name.equals("squarePiece")) {
            position[0][0] = 4;
            position[0][1] = 19;
            position[1][0] = 5;
            position[1][1] = 19;
            position[2][0] = 4;
            position[2][1] = 18;
            position[3][0] = 5;
            position[3][1] = 18;
        }
        //T-shape piece's starting position on the board will have each square
        //at position {(4, 19), (3, 18), (4, 18), (5, 18)}
        else if (name.equals("T_Piece")) {
            position[0][0] = 4;
            position[0][1] = 19;
            position[1][0] = 3;
            position[1][1] = 18;
            position[2][0] = 4;
            position[2][1] = 18;
            position[3][0] = 5;
            position[3][1] = 18;
        }
        //Z-shape piece's starting position on the board will have each square
        //at position {(5, 19), (4, 18), (5, 18), (4, 17)}
        else if (name.equals("Z_Piece")) {
            position[0][0] = 5;
            position[0][1] = 19;
            position[1][0] = 4;
            position[1][1] = 18;
            position[2][0] = 5;
            position[2][1] = 18;
            position[3][0] = 4;
            position[3][1] = 17;
        }
        //S-shape piece's starting position on the board will have each square
        //at position {(4, 19), (4, 18), (5, 18), (5, 17)}
        else if (name.equals("S_Piece")) {
            position[0][0] = 4;
            position[0][1] = 19;
            position[1][0] = 4;
            position[1][1] = 18;
            position[2][0] = 5;
            position[2][1] = 18;
            position[3][0] = 5;
            position[3][1] = 17;
        }
        //L-shape piece's starting position on the board will have each square
        //at position {(4, 19), (5, 19), (5, 18), (5, 17)}
        else if (name.equals("L_Piece")) {
            position[0][0] = 4;
            position[0][1] = 19;
            position[1][0] = 5;
            position[1][1] = 19;
            position[2][0] = 5;
            position[2][1] = 18;
            position[3][0] = 5;
            position[3][1] = 17;
        }
        //mirrored L-shape piece's starting position on the board will have each square
        //at position {(4, 19), (5, 19), (4, 18), (4, 17)}
        else if (name.equals("mirroredL_Piece")) {
            position[0][0] = 4;
            position[0][1] = 19;
            position[1][0] = 5;
            position[1][1] = 19;
            position[2][0] = 4;
            position[2][1] = 18;
            position[3][0] = 4;
            position[3][1] = 17;
        }
    }

    public String getName() {
        return name;
    }
    
    public int getPosition(int x, int y){
        return position[x][y];
    }

    public void setPosition(int x, int y, int pos){
        position[x][y] = pos;
    }

    public void downOneLine(){
        for(int i = 0; i <= 3; ++i){
            --position[i][1];
        }
    }

}
