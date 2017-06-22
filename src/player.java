import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Created by Nick on 6/22/2017.
 */
public class player {
    public float x, y, vx, vy;
    public static final int RAD = 25;
    private Image img;
    public player() {
        x = game.WIDTH/2;
        y = game.HEIGHT/2;
        try {
            img = ImageIO.read(new File("triangle.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void physics() {
        x = vx;
        y = vy;
        vy += 0.5f;
    }
    public void update(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(img, Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD, null);
    }
    public void jump() {
        vy = -8;
    }

    public void mov_up (){
        y -= 20;
    }

    public void mov_down () {
        y += 20;
    }

    public void mov_left (){
        x -= 20;
    }

    public void mov_right () {
        x += 20;
    }

    public void reset() {
        x = 640/2;
        y = 640/2;
        vx = vy = 0;
    }
}