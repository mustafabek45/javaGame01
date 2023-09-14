package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class Fire{
    private int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int y;

}
public class GameScreen extends JPanel implements KeyListener, ActionListener {
    Timer timer = new Timer(5, this);

    private int passingTime=0;
    private int wastedFire=0;
    private BufferedImage image;

    private ArrayList<Fire> fires=new ArrayList<Fire>();

    private int fireDirY=1;
    private int ballX=0;

    private int ballDirX=2;
    private int rocketX=0;
    private int dirRocketX=20;

    public boolean checkIt() {
        for (Fire fire:fires) {
            if (new Rectangle(fire.getX(), fire.getY(), 10, 20).intersects(new Rectangle(ballX, 0,20, 20))) {
                return true;
            }

        }
        return false;
    }


    public GameScreen() {
        try {
            image = ImageIO.read(new FileInputStream(new File("src/main/java/game/rocket.png")));
        } catch (IOException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE,null,ex);
        }
        setBackground(Color.BLACK);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        passingTime+=5;

        g.setColor(Color.red);
        g.fillOval(ballX,0,20,20);
        g.drawImage(image, rocketX, 490, getWidth() / 15, getHeight() / 10,this);

        for (Fire fire:fires) {
            if (fire.getY() < 0) {
                fires.remove(fire);
            }

        }
        g.setColor(Color.blue);
        for (Fire fire:fires) {
            g.fillRect(fire.getX(),fire.getY(),10,20);

        }
        if (checkIt()) {
            timer.stop();
            String message = "You won....\n"+
                    "wasted fire : " +wastedFire+
                    "\nThe passing time :" +passingTime/1000.0+" second";
            JOptionPane.showMessageDialog(this,message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

        int c=e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {
            if (rocketX <= 0) {
                rocketX=0;
            }else rocketX-=dirRocketX;

        } else if (c==KeyEvent.VK_RIGHT) {
            if (rocketX >= 720) {

            }else rocketX+=dirRocketX;
        } else if (c==KeyEvent.VK_ALT) {
            fires.add(new Fire(rocketX+15, 470));
            wastedFire++;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Fire fire:fires) {
            fire.setY(fire.getY()-fireDirY);


        }

        ballX+=ballDirX;
        if (ballX >= 750) {
            ballDirX=-ballDirX;
        }
        if (ballX <= 0) {
            ballDirX=-ballDirX;
        }
        repaint();

    }
}