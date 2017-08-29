package amt.main.scene;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Alex
 */
public class SceneGenerator {
    
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;
    private static final double CaveSize = 4;
    public static final double hillSize = 25;
    
    private static int white = Color.WHITE.getRGB();
    private static int black = Color.BLACK.getRGB();
    
    private Color player = new Color(255, 0, 220);
    private int playerColor = player.getRGB();
    
    static Random r = new Random();
    
    public void generateMap() {
        
        int groundLevel = HEIGHT / 4;
        
        OpenSimplexNoise noise = new OpenSimplexNoise(r.nextLong());
        
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        
        for(int y = 0; y < groundLevel; y++){
            for(int x = 0; x < WIDTH; x++){
                image.setRGB(x, y, white);
            }
        }
        for (int y = groundLevel; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                double value = noise.eval(x / CaveSize, y / CaveSize);
                if (value > 0) {
                    image.setRGB(x, y, white);
                } else {
                    image.setRGB(x, y, black);
                }
            }
        }
        image.setRGB(0, 0, playerColor);
        //File world = new File("/res/levels/world.png");
        //System.out.println("Working Directory = " +
              //System.getProperty("user.dir"));
        File world = new File("C:\\Users\\Alex\\Documents\\NetBeansProjects\\Space\\res\\levels\\world.png");
        world.getParentFile().mkdirs();
        
        try {
            ImageIO.write(image, "png", world);
        } catch (IOException ex) {
            Logger.getLogger(SceneGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
