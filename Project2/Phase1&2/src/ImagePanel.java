

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * this class will add an image
 *
 * @author Mohammadreza hassanzadeh
 * @since 17 Dec 200
 * @version 1.0
 */
public class ImagePanel extends JPanel {
    //image that we want to add
    private BufferedImage image;
    //x position
    private int x;
    //y position
    private int y;
    //width of the picture
    private int width;
    //height of the picture
    private int height;

    /**
     * constructor for making an image
     *
     * @param filePath address of the image
     * @param x x position
     * @param y y position
     * @param width width of the image
     * @param height height of the image
     */
    public ImagePanel (String filePath,int x , int y,int width,int height){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        try{
            image = ImageIO.read(new File(filePath));
        }catch(Exception e){/*invalid address*/}
        this.setSize(width,height);
        this.setOpaque(true);
    }

    /**
     * override method for adding the image
     *
     * @param graphics
     */
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(image,x,y,width,height,this);

    }
}
