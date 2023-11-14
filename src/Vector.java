public class Vector {
    private double x,y;
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double length() {
        return Math.sqrt((x * x) + (y * y));
    }

    public void changeX(double ratio) {
        x += ratio;
    }

    public void changeY(double ratio) {
        y += ratio;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


}
