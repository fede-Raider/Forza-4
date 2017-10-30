package gj.forza4.gui;

import gj.forza4.player.HumanPlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Disk extends JComponent {

	private BufferedImage image;

	public Disk(boolean b) {

		if (b) {
			image = HumanPlayer.green;
		} else {
			image = HumanPlayer.red;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, null);
	}
}