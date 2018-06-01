package Main;
import java.awt.AlphaComposite;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class ZoomButtons extends JButton implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int outwidth;
	private int outlocalx;
	private int outlocaly;
	private int inwidth;
	private int inlocalx;
	private int inlocaly;
	private int borderdim;
	private double scalefac=0.1;
	private JFrame parent;
	private JLabel labelref;
	private JButton zoomin = new JButton("+");
	private JButton zoomout = new JButton("-");
	private BufferedImage inImage;
	private BufferedImage zoomedImage;
	private int zoomx=0;
	private int zoomy=0;
	private int zoomwidth;
	private int zoomheight;
	private int zoomfactor=2;
	private boolean isZoomed=false;
	private static final int maxzooms=8;
	private int maxzoomclicks=maxzooms;
	public ZoomButtons(){
		
	}

	/*
	 * @Param frame JFrame that is in the front
	 */
	public void addZoomButtons(JFrame jf, JLabel label) {
//		zoomin.setFont(new Font("Arial", Font.BOLD,20));
//		zoomout.setFont(new Font("Arial", Font.BOLD,20));
		parent=jf;
		labelref=label;
		label=labelref;
		zoomin.setName("+");
		zoomout.setName("-");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		 
		 //Set flexible sizes
		 int useddim=0;
		 
		 borderdim=(int) (scalefac/4*useddim);
		 
		 boolean usingwidth;
		 if(screenSize.getWidth()>screenSize.getHeight()) {
			 useddim=(int) screenSize.getHeight();
			 usingwidth=false;
		 }
		 else {
			 useddim=(int) screenSize.getWidth();
			 usingwidth=true;
		 }
		 	
		 	borderdim=(int) (scalefac/3*useddim);
			outwidth= (int) (useddim*scalefac);
			
			if(usingwidth) {
			outlocalx= (int) ((int) screenSize.getWidth()-(useddim*scalefac)-borderdim);
			outlocaly= (int) (screenSize.getHeight()-useddim*scalefac-borderdim);
			}
			else {
				outlocalx= (int) ((int) screenSize.getHeight()-(useddim*scalefac)-borderdim);
				outlocaly= (int) (screenSize.getWidth()-useddim*scalefac-borderdim);	
			}
			
			//Setting zoomin button
			
			inwidth=outwidth;
			inlocaly=outlocaly;
			inlocalx=(int) (outlocalx-borderdim-inwidth);
			
//		zoomin.setBounds(inlocalx, inlocaly, inwidth, inwidth);
//		zoomout.setBounds(outlocalx, outlocaly, outwidth, outwidth);
		zoomin.setSize(inwidth, inwidth);
		zoomin.setLocation(inlocaly, inlocalx);
		zoomout.setSize(inwidth,inwidth);
		zoomout.setLocation(outlocaly, outlocalx);
		zoomin.addMouseListener(this);
		zoomout.addMouseListener(this);
		
	
		JPanel overlay = new JPanel();
		JPanel buttonlay = new JPanel();
		buttonlay.add(zoomin);
		buttonlay.add(zoomout);
	
		buttonlay.setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
		buttonlay.setLayout(null);
		buttonlay.setOpaque(false);
		overlay.setLayout(null);
		overlay.add(buttonlay);
		overlay.add(label);
		
		
		jf.add(overlay);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
	}
	
	public void changeButtonSize(double proportion) {
		inwidth*=proportion;
		zoomin.setSize(inwidth,inwidth);
		zoomout.setSize(inwidth,inwidth);
		repaint();
	}
	public void changeButtonSize(int newdim) {
		inwidth=newdim;
		zoomin.setSize(inwidth, inwidth);
		zoomout.setSize(inwidth,inwidth);
		
		repaint();
	}
	public void changeZoomInButtonLocation(int x, int y) {
		
		inlocaly=y;
		inlocalx=x;
		zoomin.setLocation(inlocalx, inlocaly);
		
		
		repaint();
	}
	public void changeZoomOutButtonLocation(int x, int y) {
		outlocalx=x;
		outlocaly=y;
		zoomout.setLocation(outlocalx, outlocaly);
		repaint();
		
	}
	private BufferedImage createImage() throws IOException {
		int w = labelref.getWidth();
	    int h = labelref.getHeight();
	   
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics g =bi.createGraphics();
	    labelref.getIcon().paintIcon(null,g,0,0);
	    g.dispose();
	    return bi;
			    }
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
//        int x = (getWidth() - inImage.getWidth()) / 2;
//        int y = (getHeight() - inImage.getHeight()) / 2;
        g2d.drawImage(inImage, 0, 0,this);
        g2d.dispose();
	}
	
	private static BufferedImage resize	(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
}
	
	/*
	 * Use this to change the dimensions and location of the zoomview
	 * @param r pass rectangle that is the new size and location of the
	 * 			zoomed-image.
	 */
	public void setZoomDims(Rectangle r) {
		zoomy=r.x;
		zoomx=r.y;
		zoomwidth=r.width;
		zoomheight=r.height;
		
	}
	
	/*
	 * Use this to get the dimensions and location of the zoomed-image.
	 * @return returns Rectangle containing dimensions and location.
	 */
	public Rectangle getZoomDims() {
		return new Rectangle(zoomy,zoomx,zoomwidth,zoomheight);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		//Update with new Imageicons
		try {
			
					if(inImage !=null) {
						
						
					}
					else {
						inImage=createImage();
						zoomedImage=inImage;
						zoomx=0;
						zoomy=0;
						zoomheight=inImage.getHeight();
						zoomwidth=inImage.getWidth();
					}
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		
		if(clicked.getName().equals("+") && maxzoomclicks>0) {
			
			zoomx+=zoomheight/(2*zoomfactor);
			zoomy+=zoomwidth/(2*zoomfactor);
			zoomheight=zoomheight/zoomfactor;
			zoomwidth=zoomwidth/zoomfactor;
			int realx=zoomedImage.getHeight()/(2*zoomfactor);
			int realy=zoomedImage.getWidth()/(2*zoomfactor);
			int realzheight=realx*2;
			int realzwidth=realy*2;
			
			zoomedImage = zoomedImage.getSubimage(realy, realx, realzwidth, realzheight);
			zoomedImage=resize(zoomedImage, labelref.getHeight(), labelref.getWidth());
			labelref.setIcon(new ImageIcon(zoomedImage));
			isZoomed=true;
			maxzoomclicks--;
			
		}
		else if(clicked.getName().equals("-") && isZoomed) {

			int ratio=(int)Math.round((double)(inImage.getWidth()/zoomwidth));
			//catch rounding errors pushing it below 1 or even 0.
			if(ratio<1) {
				ratio=1;
			}
			if(ratio==1) {
				zoomedImage=inImage;
			}
			else if(ratio>1) {
			
			zoomx=zoomx-zoomheight/2;
			zoomy=zoomy-zoomwidth/2;
			zoomheight=zoomheight*zoomfactor;
			zoomwidth=zoomwidth*zoomfactor;
			}
			//Catching error if someone zooms in, then tries to zoom out when there
			//is only about a few pixels difference between inImage and zoomedImage
			//due to rounding errors. Will set to maximum size if this happens.
//			try {
			zoomedImage=inImage.getSubimage(zoomy, zoomx, zoomwidth, zoomheight);
//
//			}
//			catch(Exception exc) {
//				zoomedImage=inImage;
//				
//			}
			zoomedImage=resize(zoomedImage, labelref.getHeight(),labelref.getWidth());
			labelref.setIcon(new ImageIcon(zoomedImage));
			
			if(maxzoomclicks==maxzooms) {
				isZoomed=false;
				maxzoomclicks=maxzooms;
				
				
			}
			else {
				maxzoomclicks++;
			}
			
		}
		repaint();

	}
//	public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
//		  // The images must be the same size.
//		  if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
//		    return false;
//		  }
//
//		  int width  = imgA.getWidth();
//		  int height = imgA.getHeight();
//
//		  // Loop over every pixel.
//		  for (int y = 0; y < height; y++) {
//		    for (int x = 0; x < width; x++) {
//		      // Compare the pixels for equality.
//		      if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
//		        return false;
//		      }
//		    }
//		  }
//
//		  return true;
//		}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
