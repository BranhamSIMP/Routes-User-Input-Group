package Pathfinder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
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
		BufferedImage b = ImageIO.read(new File("40133654-422f3c42-58f5-11e8-92ff-88f9042b3984.png"));


		//The Rock:(b.getWidth()/3+80, 80)
		//Turner:672, 840
		PixelTraverse pt = new PixelTraverse(b,1,672,840,468,80);
		//16777216
		//OR: 1
		ArrayList<Point> sol =pt.startpixeltraverse();
	//	BufferedImage img= deepCopy(b);
		for(Point pp:sol) {
			System.out.println(pp.getX()+", "+pp.getY());
		
			
			
			b.setRGB((int)pp.getX(),(int)pp.getY(),Color.RED.getRGB());
			
			}
		
		JFrame j = new JFrame();
		j.setSize(b.getWidth(), b.getHeight());
		JPanel jp = new JPanel();
	//	JLabel jl = new JLabel(new ImageIcon(img));
		JLabel jorigin=new JLabel(new ImageIcon(b));
		j.add(jp);
		jp.add(jorigin);
	//	jp.add(jl);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		}
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
}
}
