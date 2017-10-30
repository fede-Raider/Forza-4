package gj.forza4.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Arrow extends JComponent {

	private BufferedImage image;
	private int position = 0;

	public BufferedImage getImage() {
		return image;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Arrow() {
		
		try {
				image = ImageIO.read(new File("assets/arrow.png"));
		} catch (IOException ex) {
			System.out.println("ERRORE");
		}
	}
	
	public int getDimension(){
		return image.getHeight();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}