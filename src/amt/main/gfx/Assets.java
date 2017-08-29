package amt.main.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author tsharman58
 */
public class Assets {
    
    //tilesheet
    public static int tileWidth=64, tileHeight=64;
    
    public static BufferedImage platform, background;
    public static BufferedImage player, bullet, turret, heart, rusher, explodingRusher;
    
    private static SpriteSheet sheet;
    
    //buttonsheet
    public static int buttonWidth=190,buttonHeight=48;
    
    public static BufferedImage buttonUp, buttonDown;
    public static BufferedImage buttonGrey;
    public static BufferedImage rightArrow, rightArrowDown, leftArrow, leftArrowDown;
    
    private static SpriteSheet buttonSheet;
    
    //testlevel
    public static int testWidth=394, testHeight = 426;
    
    public static BufferedImage testLevel, level2;
    
    public static SpriteSheet levelSheet;
    
    /**
     * initiates all assets
     */
    public static void init(){
        //tilesheet
        sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sokoban_tilesheet.png"));
        
        platform = easyLoad(1, 0);
        background = easyLoad(11, 6);
        
        player = easyLoad(0,4);
        turret = easyLoad(2, 0);
        bullet = easyLoad(12, 2);
        heart = easyLoad(12, 0);
        rusher = easyLoad(6, 5);
        explodingRusher = easyLoad(7, 5);
        
        //buttonsheet
        buttonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/blueSheet.png"));
        
        buttonUp = buttonSheet.crop(0, 0, buttonWidth, buttonHeight);
        buttonDown = buttonSheet.crop(buttonWidth, 0, buttonWidth, buttonHeight);
        buttonGrey = buttonSheet.crop(0, 3 * buttonHeight, buttonWidth, buttonHeight);
        
        rightArrow = buttonSheet.crop(339, 143, 39, 30);
        leftArrow = buttonSheet.crop(378, 143, 39, 30);
        
        rightArrowDown = buttonSheet.crop(417, 143, 39, 30);
        leftArrowDown = buttonSheet.crop(456, 143, 39, 30);
        
        //levelsheet
        levelSheet = new SpriteSheet(ImageLoader.loadImage("/textures/levelsheet.png"));
        
        testLevel = levelSheet.crop(testWidth, 0, testWidth, testHeight);
        level2 = levelSheet.crop(0, 0, testWidth, testHeight);
    }
    
    //Uses tiles instead of pixels
    private static BufferedImage easyLoad(int x, int y) {
        return sheet.crop(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }
    
    //spritesheet crop reference
    /* 0,0 1,0 2,0 ...
     * 0,1 1,1 2,1 ...
     * 0,2
     * 0,3
     */
}
