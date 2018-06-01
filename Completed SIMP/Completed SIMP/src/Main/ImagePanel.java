package Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

class ImagePanel extends JComponent{

private BufferedImage image;

public ImagePanel(String fileName) {
   try {                
      image = ImageIO.read(new File(fileName));
   } catch (IOException ex) {
        // handle exception...
   }
}

@Override
protected void paintComponent(Graphics g) {
    g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
}
}
