import java.awt.*;

public interface Figure {
    public void drawFigure(Graphics g);
    public boolean hit(double x, double y);
}
