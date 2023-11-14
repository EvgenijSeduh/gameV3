import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Rect implements Figure{
    double startX, startY,  finishX,  finishY, height;

    public Rect(double startX, double startY, double finishX, double finishY){
        this.startX = Math.min(startX,finishX);
        this.startY = Math.min(startY,finishY);
        this.finishX = Math.max(startX,finishX);
        this.finishY = Math.max(startY,finishY);
        height = 50;
    }

    @Override
    public void drawFigure(Graphics g) {
        g.drawRect((int) startX, (int) startY, (int) (finishX-startX), (int) (finishY - startY));
    }

    @Override
    public boolean hit(double x, double y){
        if ((x >= startX && x <= finishX) && (y >= startY && y <= finishY))
            return true;
        else
            return false;
    }

    public double getHeight() {
        return height;
    }
}
