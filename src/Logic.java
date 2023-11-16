import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Logic extends JPanel implements ActionListener, KeyListener, MouseMotionListener {
    private Timer timer;
    private Player player;
    private Place map;
    int HEIGHT = 450;
    int WIDTH = 800;
    private final double RENDER_STEP = 0.2,RAY_STEP = 0.2;
    int DOT_SIZE = (int) (WIDTH/(Player.VIEWING_ANGLE/RENDER_STEP));
    private final double DISTANCE_TO_MONITOR = 15;
    private final double SENSITIVITY = 0.5;

    public Logic() {
        player = new Player(WIDTH / 2, HEIGHT / 2, 2, -90);
        timer = new Timer(60, this);
        map = new Place();
        createMap();
        addKeyListener(this);
        setFocusable(true);
        timer.start();
        addMouseMotionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        player.outInfo();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 87)
            player.moveUp();
        if (keyCode == 83)
            player.moveDown();
        if (keyCode == 65)
            player.turnLeft();
        if (keyCode == 68)
            player.turnRight();
        if(keyCode==119){

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        WIDTH = getSize().width;
        HEIGHT = getSize().height;
        DOT_SIZE = (int)(Math.round(WIDTH/(Player.VIEWING_ANGLE/RENDER_STEP)));
        //System.out.println(DOT_SIZE + " " + WIDTH + " " + DOT_SIZE*Player.VIEWING_ANGLE/RENDER_STEP);
        //System.out.println(WIDTH+" "+HEIGHT);
        paintSky(g);
        paintEarth(g);
        paint3dGraphics(g);
        //paint2dGraphics(g);
        g.setColor(Color.YELLOW);
        g.drawString(player.outInfo(), 0, 10);
    }

    private void createMap(){
        map.add(new Border(WIDTH,HEIGHT));
        map.add(new Rect(600, 300, 550, 400));
        map.add(new Rect(100, 60, 70, 300));
    }

    private void paint3dGraphics(Graphics g) {
        int indPixel = 0;
        for (double i = player.getRotationDegree() - (player.VIEWING_ANGLE / 2); i <= player.getRotationDegree() + (player.VIEWING_ANGLE / 2); i += RENDER_STEP) {
            double ray = RAY_STEP;
            boolean flag = false;
            while (ray <= player.DISTANCE_VIEW) {
                if (flag)
                    break;
                for (Figure figure : map.getPlace()) {
                    if (figure.hit(player.getX() + Math.sin(Math.toRadians(i)) * ray, player.getY() + Math.cos(Math.toRadians(i)) * ray)) {
                        flag = true;
                        break;
                    }
                }
                ray += RAY_STEP;
            }

            double relativeHeight = (700*DISTANCE_TO_MONITOR)/ray;

            if (relativeHeight >= HEIGHT)
                relativeHeight = HEIGHT;

            int percent = (int) (relativeHeight * 255 / Player.DISTANCE_VIEW);

            if (percent > 255)
                percent = 255;
            g.setColor(new Color(percent, percent, percent));

            if (ray < Player.DISTANCE_VIEW)
                g.fillRect(indPixel * DOT_SIZE, (int) ((HEIGHT / 2) - (relativeHeight / 2)), DOT_SIZE, (int) relativeHeight);
            indPixel++;
        }
    }

    private void paint2dGraphics(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval((int) (player.getX() - DOT_SIZE / 2), (int) (player.getY() - DOT_SIZE / 2), DOT_SIZE, DOT_SIZE);
        g.drawLine((int) (player.getX()), (int) (player.getY()), (int) ((player.getX()) + (Math.sin(Math.toRadians(player.getRotationDegree())) * player.DISTANCE_VIEW)), (int) ((player.getY()) + (Math.cos(Math.toRadians(player.getRotationDegree())) * player.DISTANCE_VIEW)));
        g.setColor(Color.gray);
        double[] screen = null;
        double cof = 0;
        for (double i : screen) {
            g.drawLine((int) (player.getX()), (int) (player.getY()), (int) ((player.getX()) + (Math.sin(Math.toRadians(player.getRotationDegree() - (player.VIEWING_ANGLE) / 2 + cof)) * i)), (int) ((player.getY()) + (Math.cos(Math.toRadians(player.getRotationDegree() - (player.VIEWING_ANGLE) / 2 + cof)) * i)));
            cof += RENDER_STEP;
        }
        g.setColor(Color.BLUE);
        map.paintMap(g);
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
       //player.setRotationDegree((e.getX()*SENSITIVITY)%361);
    }

    private void paintSky(Graphics g){
        double stepColor = ((double)(HEIGHT)/2)/255;
        int tint = 0;
        g.setColor(new Color(0,0,tint));
        for(int i = (HEIGHT/2);i>0;i--){
            if((double)(i)%stepColor < 0.5)
                tint++;
            g.setColor(new Color(255-tint,255-tint,255));
            g.fillRect(0,i,WIDTH,1);
        }
        g.setColor(Color.WHITE);
    }

    private void paintEarth(Graphics g){
        double stepColor = ((double)(HEIGHT)/2)/255;
        int tint = 0;
        g.setColor(new Color(0,255,0));
        for(int i = HEIGHT;i>(HEIGHT/2);i--){
            System.out.println(i + " " + stepColor);
            if((double)(i)%stepColor < 0.5)
                tint++;
            g.setColor(new Color(0,255-tint,0));
            g.fillRect(0,i,WIDTH,1);
        }
    }
}
