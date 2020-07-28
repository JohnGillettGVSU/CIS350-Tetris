import java.awt.*;

public class Square {
    private boolean isFilled;
    private Color color;

    public Square(){
        isFilled = false;
        color = Color.CYAN;
    }

    public void setFilled(boolean fill){
        isFilled = fill;
    }

    public boolean isTrue(){
        return isFilled;
    }

    public void setColor(Color c){
        color = c;
    }

    public Color getColor(){
        return color;
    }
}
