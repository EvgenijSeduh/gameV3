import javax.swing.*;
import java.awt.*;

public class Scene extends JFrame {

    public Scene() throws Exception {
        setTitle("Shooter");
        setDefaultCloseOperation(Scene.EXIT_ON_CLOSE);
        add(new Logic());
        setBackground(Color.black);
        setSize(800, 450);
        //setExtendedState(MAXIMIZED_BOTH);
        setLocation(40,40);
        setVisible(true);
    }
}