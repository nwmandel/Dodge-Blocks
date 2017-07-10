
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Created by Nick on 6/22/2017.
 */
public class player {
    public float x, y;
    public static final int RAD = 25;
    private Image img;
    public player() {
        x = game.WIDTH;
        y = game.HEIGHT;
        try {
            img = ImageIO.read(new File("triangle.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Graphics g) {
        g.drawImage(img, Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD, null);
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
        y = 500;
    }
}