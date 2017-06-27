
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * Created by Nick on 6/22/2017.
 */
public class board extends JPanel {

    private player player;
    private ArrayList<Rectangle> rects;
    private game gm;
    private Font scoreFont, pauseFont;
    public static final Color bg = new Color(202, 74, 0);
    public static final int PIPE_W = 50, PIPE_H = 30;
    public Image blockSize;

    public board(game gm, player player, ArrayList<Rectangle> rects) {
        this.gm = gm;
        this.player = player;
        this.rects = rects;
        scoreFont = new Font("Comic Sans MS", Font.BOLD, 18);
        pauseFont = new Font("Arial", Font.BOLD, 48);

        try {
            blockSize = ImageIO.read(new File("block.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(bg);
        g.fillRect(0,0,game.WIDTH,game.HEIGHT); //background
        player.update(g);
        g.setColor(Color.RED);
        for(Rectangle r : rects) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.fillRect(r.x, r.y, r.width, r.height);
            AffineTransform old = g2d.getTransform();
            g2d.translate(r.x+PIPE_W/2, r.y+PIPE_H/2); //scroll direction
            g2d.rotate(Math.PI/2);
            //g2d.drawImage(blockSize,-r.width/2, r.height/2, board.PIPE_H,r.width , null);
            //g2d.drawImage(blockSize, -PIPE_W/2, PIPE_H/2, board.PIPE_W, r.height, null);

            g2d.setTransform(old);
        }
        g.setFont(scoreFont);
        g.setColor(Color.BLACK);
        g.drawString("Score: "+gm.getScore(), 10, 20);

        if(gm.paused()) {
            g.setFont(pauseFont);
            g.setColor(new Color(0,0,0,170));
            g.drawString("##PAUSED##", game.WIDTH/4-50, game.HEIGHT/2-100);
            g.drawString("press escape",game.WIDTH/2-200, game.HEIGHT/2+50);
            g.drawString("to start", game.WIDTH/2-200, game.HEIGHT/2+100);
        }
    }
}