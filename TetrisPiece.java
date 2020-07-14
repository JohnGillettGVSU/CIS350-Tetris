public class TetrisPiece {
    private int[][] position;
    private String name;
    private int[][][] rotation;
    private int rotatedState;

    public TetrisPiece(String name) {
        this.name = name;
        position = new int[4][2];
        rotation = new int[4][4][2];
        rotatedState = 0;

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

            //set instructions for how piece will be rotated
            rotation[0][0][0] = -2;
            rotation[0][0][1] = -2;
            rotation[0][1][0] = -1;
            rotation[0][1][1] = -1;
            rotation[0][2][0] = 0;
            rotation[0][2][1] = 0;
            rotation[0][3][0] = 1;
            rotation[0][3][1] = 1;

            rotation[1][0][0] = 2;
            rotation[1][0][1] = 2;
            rotation[1][1][0] = 1;
            rotation[1][1][1] = 1;
            rotation[1][2][0] = 0;
            rotation[1][2][1] = 0;
            rotation[1][3][0] = -1;
            rotation[1][3][1] = -1;

            rotation[2][0][0] = -2;
            rotation[2][0][1] = -2;
            rotation[2][1][0] = -1;
            rotation[2][1][1] = -1;
            rotation[2][2][0] = 0;
            rotation[2][2][1] = 0;
            rotation[2][3][0] = 1;
            rotation[2][3][1] = 1;

            rotation[3][0][0] = 2;
            rotation[3][0][1] = 2;
            rotation[3][1][0] = 1;
            rotation[3][1][1] = 1;
            rotation[3][2][0] = 0;
            rotation[3][2][1] = 0;
            rotation[3][3][0] = -1;
            rotation[3][3][1] = -1;
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

            //set instructions for how piece will be rotated
            rotation[0][0][0] = 1;
            rotation[0][0][1] = -1;
            rotation[0][1][0] = 1;
            rotation[0][1][1] = 1;
            rotation[0][2][0] = 0;
            rotation[0][2][1] = 0;
            rotation[0][3][0] = -1;
            rotation[0][3][1] = -1;

            rotation[1][0][0] = -1;
            rotation[1][0][1] = -1;
            rotation[1][1][0] = 1;
            rotation[1][1][1] = -1;
            rotation[1][2][0] = 0;
            rotation[1][2][1] = 0;
            rotation[1][3][0] = -1;
            rotation[1][3][1] = 1;

            rotation[2][0][0] = -1;
            rotation[2][0][1] = 1;
            rotation[2][1][0] = -1;
            rotation[2][1][1] = -1;
            rotation[2][2][0] = 0;
            rotation[2][2][1] = 0;
            rotation[2][3][0] = 1;
            rotation[2][3][1] = 1;

            rotation[3][0][0] = 1;
            rotation[3][0][1] = 1;
            rotation[3][1][0] = -1;
            rotation[3][1][1] = 1;
            rotation[3][2][0] = 0;
            rotation[3][2][1] = 0;
            rotation[3][3][0] = 1;
            rotation[3][3][1] = -1;
        }
        //Z-shape piece's starting position on the board will have each square
        //at position {(5, 19), (4, 18), (5, 18), (4, 17)}
        else if (name.equals("Z_Piece")) {
            position[0][0] = 5;
            position[0][1] = 19;
            position[1][0] = 5;
            position[1][1] = 18;
            position[2][0] = 4;
            position[2][1] = 18;
            position[3][0] = 4;
            position[3][1] = 17;

            //set instructions for how piece will be rotated
            rotation[0][0][0] = -2;
            rotation[0][0][1] = -1;
            rotation[0][1][0] = -1;
            rotation[0][1][1] = 0;
            rotation[0][2][0] = 0;
            rotation[0][2][1] = -1;
            rotation[0][3][0] = 1;
            rotation[0][3][1] = 0;

            rotation[1][0][0] = 2;
            rotation[1][0][1] = 1;
            rotation[1][1][0] = 1;
            rotation[1][1][1] = 0;
            rotation[1][2][0] = 0;
            rotation[1][2][1] = 1;
            rotation[1][3][0] = -1;
            rotation[1][3][1] = 0;

            rotation[2][0][0] = -2;
            rotation[2][0][1] = -1;
            rotation[2][1][0] = -1;
            rotation[2][1][1] = 0;
            rotation[2][2][0] = 0;
            rotation[2][2][1] = -1;
            rotation[2][3][0] = 1;
            rotation[2][3][1] = 0;

            rotation[3][0][0] = 2;
            rotation[3][0][1] = 1;
            rotation[3][1][0] = 1;
            rotation[3][1][1] = 0;
            rotation[3][2][0] = 0;
            rotation[3][2][1] = 1;
            rotation[3][3][0] = -1;
            rotation[3][3][1] = 0;
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

            //set instructions for how piece will be rotated
            rotation[0][0][0] = 1;
            rotation[0][0][1] = -1;
            rotation[0][1][0] = 0;
            rotation[0][1][1] = 0;
            rotation[0][2][0] = -1;
            rotation[0][2][1] = -1;
            rotation[0][3][0] = -2;
            rotation[0][3][1] = 0;

            rotation[1][0][0] = -1;
            rotation[1][0][1] = 1;
            rotation[1][1][0] = 0;
            rotation[1][1][1] = 0;
            rotation[1][2][0] = 1;
            rotation[1][2][1] = 1;
            rotation[1][3][0] = 2;
            rotation[1][3][1] = 0;

            rotation[2][0][0] = 1;
            rotation[2][0][1] = -1;
            rotation[2][1][0] = 0;
            rotation[2][1][1] = 0;
            rotation[2][2][0] = -1;
            rotation[2][2][1] = -1;
            rotation[2][3][0] = -2;
            rotation[2][3][1] = 0;

            rotation[3][0][0] = -1;
            rotation[3][0][1] = 1;
            rotation[3][1][0] = 0;
            rotation[3][1][1] = 0;
            rotation[3][2][0] = 1;
            rotation[3][2][1] = 1;
            rotation[3][3][0] = 2;
            rotation[3][3][1] = 0;
        }
        //L-shape piece's starting position on the board will have each square
        //at position {(4, 19), (5, 19), (5, 18), (5, 17)}
        else if (name.equals("L_Piece")) {
            position[0][0] = 4;
            position[0][1] = 19;
            position[1][0] = 4;
            position[1][1] = 18;
            position[2][0] = 4;
            position[2][1] = 17;
            position[3][0] = 5;
            position[3][1] = 17;

            //set instructions for how piece will be rotated
            rotation[0][0][0] = 1;
            rotation[0][0][1] = -1;
            rotation[0][1][0] = 0;
            rotation[0][1][1] = 0;
            rotation[0][2][0] = -1;
            rotation[0][2][1] = 1;
            rotation[0][3][0] = -2;
            rotation[0][3][1] = 0;

            rotation[1][0][0] = -1;
            rotation[1][0][1] = -1;
            rotation[1][1][0] = 0;
            rotation[1][1][1] = 0;
            rotation[1][2][0] = 1;
            rotation[1][2][1] = 1;
            rotation[1][3][0] = 0;
            rotation[1][3][1] = 2;

            rotation[2][0][0] = -1;
            rotation[2][0][1] = 1;
            rotation[2][1][0] = 0;
            rotation[2][1][1] = 0;
            rotation[2][2][0] = 1;
            rotation[2][2][1] = -1;
            rotation[2][3][0] = 2;
            rotation[2][3][1] = 0;

            rotation[3][0][0] = 1;
            rotation[3][0][1] = 1;
            rotation[3][1][0] = 0;
            rotation[3][1][1] = 0;
            rotation[3][2][0] = -1;
            rotation[3][2][1] = -1;
            rotation[3][3][0] = 0;
            rotation[3][3][1] = -2;
        }
        //mirrored L-shape piece's starting position on the board will have each square
        //at position {(4, 19), (5, 19), (4, 18), (4, 17)}
        else if (name.equals("mirroredL_Piece")) {
            position[0][0] = 5;
            position[0][1] = 19;
            position[1][0] = 5;
            position[1][1] = 18;
            position[2][0] = 5;
            position[2][1] = 17;
            position[3][0] = 4;
            position[3][1] = 17;

            //set instructions for how piece will be rotated
            rotation[0][0][0] = 1;
            rotation[0][0][1] = -1;
            rotation[0][1][0] = 0;
            rotation[0][1][1] = 0;
            rotation[0][2][0] = -1;
            rotation[0][2][1] = 1;
            rotation[0][3][0] = 0;
            rotation[0][3][1] = 2;

            rotation[1][0][0] = -1;
            rotation[1][0][1] = -1;
            rotation[1][1][0] = 0;
            rotation[1][1][1] = 0;
            rotation[1][2][0] = 1;
            rotation[1][2][1] = 1;
            rotation[1][3][0] = 2;
            rotation[1][3][1] = 0;

            rotation[2][0][0] = -1;
            rotation[2][0][1] = 1;
            rotation[2][1][0] = 0;
            rotation[2][1][1] = 0;
            rotation[2][2][0] = 1;
            rotation[2][2][1] = -1;
            rotation[2][3][0] = 0;
            rotation[2][3][1] = -2;

            rotation[3][0][0] = 1;
            rotation[3][0][1] = 1;
            rotation[3][1][0] = 0;
            rotation[3][1][1] = 0;
            rotation[3][2][0] = -1;
            rotation[3][2][1] = -1;
            rotation[3][3][0] = -2;
            rotation[3][3][1] = 0;
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

    public void rotateRight(boolean board[][]){
        if(name.equals("squarePiece")){
            return;
        }

        int[][] temp = new int[4][2];
        int curX;
        int curY;

        for(int i = 0; i <= 3; ++i) {
            for(int j = 0; j <= 1; ++j) {
                temp[i][j] = position[i][j];
            }
        }
        for(int i = 0; i <= 3; ++i) {
            curX = temp[i][0] + rotation[rotatedState][i][0];
            curY = temp[i][1] + rotation[rotatedState][i][1];
            if(curX < 0 || curX > 9 || curY < 0 || curY > 21){
                //stop trying to rotate piece if rotating it will place it out of the board
                return;
            }
            if(board[curX][curY]){
                //stop trying to rotate piece if it will intersect with another piece
                return;
            }
        }
        //rotate piece after checking to make sure it can be rotated
        for(int i = 0; i <= 3; ++i) {
            for(int j = 0; j <= 1; ++j) {
                position[i][j] += rotation[rotatedState][i][j];
            }
        }
        incrementRotatedState();
    }

    public void rotateLeft(boolean[][] board){
        if(name.equals("squarePiece")){
            return;
        }

        int[][] temp = new int[4][2];
        int curX;
        int curY;

        decrementRotatedState();
        for(int i = 0; i <= 3; ++i) {
            for(int j = 0; j <= 1; ++j) {
                temp[i][j] = position[i][j];
            }
        }
        for(int i = 0; i <= 3; ++i) {
            curX = temp[i][0] - rotation[rotatedState][i][0];
            curY = temp[i][1] - rotation[rotatedState][i][1];
            if(curX < 0 || curX > 9 || curY < 0 || curY > 21){
                //stop trying to rotate piece if rotating it will place it out of the board
                //also keep piece at same rotated state
                incrementRotatedState();
                return;
            }
            if(board[curX][curY]){
                //stop trying to rotate piece if it will intersect with another piece
                //also keep piece at same rotated state
                incrementRotatedState();
                return;
            }
        }
        //rotate piece after checking to make sure it can be rotated
        for(int i = 0; i <= 3; ++i) {
            for(int j = 0; j <= 1; ++j) {
                position[i][j] -= rotation[rotatedState][i][j];
            }
        }
    }

    public void incrementRotatedState(){
        rotatedState = (rotatedState + 1) % 4;
    }

    public void decrementRotatedState(){
        --rotatedState;
        if(rotatedState == -1){
            rotatedState = 3;
        }
    }
}
