import java.awt.*;
import java.util.Vector;

public class Place {
    private Vector<Figure> place;
    public Place(){
        place = new Vector<Figure>();
    }

    public void add(Figure newFigure){
        place.add(newFigure);
    }
    public void paintMap(Graphics g) {
        for(Figure i: place){
            i.drawFigure(g);
        }
    }

    public Vector<Figure> getPlace() {
        return place;
    }
}