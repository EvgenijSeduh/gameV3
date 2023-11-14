public class Player {
    private double x,y;
    private double speed;
    private double rotationDegree;
    final static double  DISTANCE_VIEW = 300,VIEWING_ANGEL = 20, TURNING_SPEED = 3;

    public Player(double x, double y, double speed, double rotationDegree) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.rotationDegree = rotationDegree;

    }

    public void setRotationDegree(double rotationDegree) {
        this.rotationDegree = rotationDegree;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void moveUp() {
        x+=(Math.sin(Math.toRadians(rotationDegree))* speed);
        y+=(Math.cos(Math.toRadians(rotationDegree))* speed);
    }

    public void moveDown() {
        x-=(Math.sin(Math.toRadians(rotationDegree))* speed);
        y-=(Math.cos(Math.toRadians(rotationDegree))* speed);
    }

    public void turnLeft(){
        rotationDegree-=TURNING_SPEED;
        if(Math.abs(rotationDegree)>360)
            rotationDegree %= 361;
    }

    public void turnRight(){
        rotationDegree+=TURNING_SPEED;
        if(Math.abs(rotationDegree)>360)
            rotationDegree %= 361;
    }


    public String  outInfo(){
        return ("degree: " + rotationDegree +" x: " + x + " y: " + y);
    }

    public double getRotationDegree() {
        return rotationDegree;
    }
}
