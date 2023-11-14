import java.awt.*;

public class Border implements Figure{
    double width, height;

    public Border(double width, double height){
        this.height = height;
        this.width = width;
    }

    @Override
    public void drawFigure(Graphics g) {
        g.drawRect( 0, 0, (int) width, (int) height);
    }

    @Override
    public boolean hit(double x, double y) {
        if ((x>=0 && x<=width)&&(y>=0 && y<=height))
            return false;
        else
            return true;
    }
}
