package gj.forza4.gui;

import gj.forza4.player.HumanPlayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Grid extends JPanel {

	private int nr = 0;
	private int nc = 0;
	private BufferedImage image;

	public Grid(int nr, int nc) {

		this.nr = nr;
		this.nc = nc;

		this.setBackground(new Color(0, 0, 0, 0));
		try {
			image = ImageIO.read(new File("assets/grid_circle5.png"));
		} catch (IOException ex) {
			System.out.println("ERRORE");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < nc; i++) {
			for (int j = nr; j >= 0; j--) {
				g.drawImage(image, i * HumanPlayer.cube, j * HumanPlayer.cube,
						null);
			}
		}
	}
}
