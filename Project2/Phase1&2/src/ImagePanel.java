

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImagePanel extends JPanel {

    private BufferedImage image;
    private int x;
    private int y;
    private int width;
    private int height;
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
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(image,x,y,width,height,this);

    }
}
