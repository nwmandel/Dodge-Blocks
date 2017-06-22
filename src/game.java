import java.awt.Image;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * Created by Nick on 6/22/2017.
 */
public class game implements ActionListener, KeyListener {

    public static final int FPS = 60, HEIGHT = 640, WIDTH = 480;
    private ArrayList<Rectangle> rects;
    private JFrame frame;
    private JPanel panel;
    private player player;
    private int time, scroll;
    private Timer t;
    private boolean paused;

    private void begin() {
        frame = new JFrame("simple game");
        player = new player();
        rects = new ArrayList<Rectangle>();
        panel = new board(this, player, rects);
        frame.add(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);
        paused = true;
        t = new Timer(1000/FPS, this);
        t.start();
    }

    public static void main(String[] args) {
        new game().begin();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        panel.repaint();
        if(!paused) {
            //player.physics();
            if(scroll % 90 == 0) {
                /*Rectangle r = new Rectangle(WIDTH, (int) (Math.random()*HEIGHT/3f), GamePanel.PIPE_W, (int) ((Math.random()*HEIGHT)/5f + (0.3f)*HEIGHT));
                int h2 = (int) ((Math.random()*HEIGHT)/5f + (0.2f)*HEIGHT);
                Rectangle r2 = new Rectangle(WIDTH, HEIGHT - h2, GamePanel.PIPE_W, h2);*/
                int h1 = (int) ((Math.random()*WIDTH)/5f + (0.2f)*WIDTH);
                Rectangle r = new Rectangle((int) (Math.random()*WIDTH/3f), 50, h1, board.PIPE_H);
                int h2 = (int) ((Math.random()*WIDTH)/5f + (0.2f)*WIDTH);
                Rectangle r2 = new Rectangle((int) (WIDTH-Math.random()*WIDTH/3f), 50,  h2, board.PIPE_H);
                int h3 = (int) ((Math.random()*WIDTH)/6f + (0.1f)*WIDTH);
                Rectangle r3 = new Rectangle((int) (Math.random()*WIDTH/2), 50,  h3, board.PIPE_H);
                rects.add(r);
                rects.add(r2);
                rects.add(r3);
                /*
                int h1 = (int) ((Math.random()*HEIGHT)/5f + (0.2f)*HEIGHT);
                Rectangle r = new Rectangle(WIDTH, (int) (Math.random()*HEIGHT/3f), board.PIPE_W, h1);
                int h2 = (int) ((Math.random()*HEIGHT)/5f + (0.2f)*HEIGHT);
                Rectangle r2 = new Rectangle(WIDTH, (int) (HEIGHT-Math.random()*HEIGHT/3f), board.PIPE_W, h2);
                int h3 = (int) ((Math.random()*HEIGHT)/6f + (0.1f)*HEIGHT);
                Rectangle r3 = new Rectangle(WIDTH, (int) (Math.random()*HEIGHT/2*0.9+1), board.PIPE_W, h1);
                rects.add(r);
                rects.add(r2);
                rects.add(r3);*/
            }
            boolean game = true;
            ArrayList<Rectangle> toRemove = new ArrayList<Rectangle>();

            for(Rectangle r : rects) {
                r.y+=3;
                if(r.y - r.height <= 0) {
                    toRemove.add(r);
                }
                if(r.contains(player.x, player.y)) {
                    JOptionPane.showMessageDialog(frame, "You lose!\n"+"Your score was: "+time/8+".");
                    game = false;
                }
            }
            rects.removeAll(toRemove);
            time++;
            scroll++;

            if (player.y > HEIGHT || player.y + player.RAD < 0) {
                game = false;
            }

            if (player.x > WIDTH || player.x + player.RAD < 0) {
                game = false;
            }

            if(!game) {
                rects.clear();
                player.reset();
                time = 0;
                scroll = 0;
                paused = true;
            }
        }
    }

    public int getScore() {
        return time/8;
    }

    public void keyPressed(KeyEvent e) {
        if (paused) {
            player.x = WIDTH/2;
            player.y = HEIGHT/2;
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                paused = false;
            }
        }else if (!paused) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                player.jump();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                player.mov_up();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                player.mov_down();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                player.mov_left();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                player.mov_right();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                paused = false;
            }
        }
    }


    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {

    }

    public boolean paused() {
        return paused;
    }
}