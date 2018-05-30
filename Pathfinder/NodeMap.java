package Pathfinder;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
//NOTE: y=height, x=width, but arrays are in [y][x].
public class NodeMap {
	public static int width=0;
	public static int height=0;
	private int[][] pixels;
	private int roadcolor;
	public Point[][] Points;
	public NodeMap(BufferedImage bi, int roadcolorvalue) throws IOException {
		
		this.roadcolor=roadcolorvalue;
		this.pixels = PixelProcessor.convertTo2D(bi);
		this.width=bi.getWidth();
		this.height=bi.getHeight();
		Points=new Point[this.height][this.width];
		for(int y=0;y<bi.getHeight();y++) {
			
			for(int x =0;x<bi.getWidth();x++) {
				if(pixels[y][x] == roadcolorvalue) {
				Points[y][x]=new Point(x,y);
				}
				else {
					Points[y][x]=null;
				}
			}
			
		}
		
	}
	
	public static void main(String args[]) throws IOException {
		NodeMap m = new NodeMap(ImageIO.read(new File("40133654-422f3c42-58f5-11e8-92ff-88f9042b3984.png")),1);
		
//		for(int r=0;r<height;r++) {
//			for(int c=0;c<width;c++) {
//				System.out.println(m.Points[r][c]);
//				System.out.println(m.pixels[r][c]);
//			}
//		}
		
			for(int c=0;c<width;c++) {
				System.out.println(m.Points[449][c]);
				System.out.println(m.pixels[449][c]);
			}
		System.out.println(m.pixels[840][672]);
		
	}
	
	
	
	
}
