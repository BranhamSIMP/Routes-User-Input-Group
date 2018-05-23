package Pathfinder;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class testPixelTraverse {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedImage b = ImageIO.read(new File("thresh.jpg"));
		
		PixelTraverse pt = new PixelTraverse(b,16777216 ,50, 50, 100,100);
		ArrayList<Point> sol =pt.startpixeltraverse();
		BufferedImage img= deepCopy(b);
		for(Point pp:sol) {
			img.setRGB((int)pp.getY(), (int)pp.getX(),Color.YELLOW.getRGB());
		}
		
		JFrame j = new JFrame();
		j.setSize(b.getWidth(), b.getHeight());
		JPanel jp = new JPanel();
		JLabel jl = new JLabel(new ImageIcon(img));
		JLabel jorigin=new JLabel(new ImageIcon(b));
		j.add(jp);
		jp.add(jorigin);
		jp.add(jl);
		j.setVisible(true);
		
		
	}
	static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		}
}
