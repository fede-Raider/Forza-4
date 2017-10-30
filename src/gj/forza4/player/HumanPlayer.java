package gj.forza4.player;

import gj.forza4.gui.Arrow;
import gj.forza4.gui.Disk;
import gj.forza4.gui.Grid;
import gj.forza4.gui.SoundManager;
import gj.forza4.tween.JComponentTween;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.equations.Elastic;
import aurelienribon.tweenengine.equations.Expo;

public class HumanPlayer extends AbstractHumanPlayer implements MouseListener {

	private int nr = 0;
	private int nc = 0;
	private static int[][] tab;

	private Timer timer;
	private TweenManager tManager;
	private SoundManager sManager;

	private long lastUpdate = 0;
	private long delta = 0;

	private String turn = "Giocatore";

	public static BufferedImage green;
	public static BufferedImage red;
	public final static int cube = 100;
	private int upperSpace = 80;

	private Arrow freccia;
	private JComponent write;

	public HumanPlayer() {
		tManager = new TweenManager();
		Tween.registerAccessor(JComponent.class, new JComponentTween());
		sManager = new SoundManager();
		sManager.play("assets/sounds/soundtrack.wav", true);

		setTitle("Forza 4 - Ciardi Federico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(new Color(0, 255, 255, 25));
		setLayout(null);

		try {
			green = ImageIO.read(new File("assets/disk_green2.png"));
			red = ImageIO.read(new File("assets/disk_red2.png"));
		} catch (IOException ex) {
			System.out.println("ERRORE");
		}

		freccia = new Arrow();
		freccia.setSize(freccia.getImage().getWidth(), freccia.getImage()
				.getHeight());
		freccia.setLocation(0, 20);

		Timeline.createSequence()
				.push(Tween.to(freccia, JComponentTween.POSITION_Y, 1f)
						.targetRelative(10).ease(Elastic.OUT))
				.repeatYoyo(Tween.INFINITY, 0f).start(tManager);

		write = new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setFont(new Font("TimesRoman", Font.BOLD, 20));
				g.setColor(Color.black);
				g.drawString("Turno: " + turn, 4, 19);
				g.drawString("Turno: " + turn, 4, 21);
				g.drawString("Turno: " + turn, 6, 21);
				g.drawString("Turno: " + turn, 6, 19);
				if (turn.equalsIgnoreCase("Giocatore")) {
					g.setColor(Color.green);
					g.drawString("Turno: " + turn, 5, 20);
				} else {
					g.setColor(Color.red);
					g.drawString("Turno: " + turn, 5, 20);
				}
			}
		};
		write.setBounds(0, 0, 500, 30);

		timer = new Timer(16, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				update();
				repaint();
				lastUpdate = delta;
				delta = System.nanoTime();
				float deltaTime = ((float) (delta - lastUpdate)) / 1000000000f;
				tManager.update(deltaTime);
			}

			private void update() {

				int x = (int) MouseInfo.getPointerInfo().getLocation().x
						- getX();

				x = x / cube;

				if (x >= nc) {
					x = nc - 1;
				} else if (x < 0) {
					x = 0;
				}

				if (x != freccia.getPosition()) {
					freccia.setPosition(x);
					tManager.killTarget(freccia, 1);
					Tween.to(freccia, JComponentTween.POSITION_X, .5f)
							.target(cube * x).ease(Expo.OUT).start(tManager);
				}
			}

		});

		addMouseListener(this);
		timer.start();
	}

	@Override
	public void start(int nr, int nc) {
		this.nr = nr;
		this.nc = nc;

		tab = new int[nr][nc];

		getContentPane().removeAll();

		getContentPane().add(freccia);
		getContentPane().add(write);

		getContentPane().setPreferredSize(new Dimension(cube * nc, cube * nr));

		Grid gp = new Grid(nr, nc);
		gp.setSize(cube * nc, cube * nr);
		gp.setLocation(0, upperSpace);
		getContentPane().add(gp);

		pack();
		setSize(getWidth(), getHeight() + upperSpace);

		setLocation((int) Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth()
				/ 2 - getWidth() / 2, (int) Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight()
				/ 2 - getHeight() / 2);
		setVisible(true);
	}

	@Override
	public void tellMove(int c) {
		int r = insert(c, -1);
		addDisk(r, c, false);

		turn = "Giocatore";
	}

	private void addDisk(int r, int c, boolean b) {
		Disk d = new Disk(b);
		d.setSize(cube, cube);
		d.setLocation(c * cube, -cube);

		Tween.to(d, JComponentTween.POSITION_Y, 1.5f)
				.target(r * cube + upperSpace).ease(Bounce.OUT).start(tManager);

		sManager.play("assets/sounds/insert.wav", false);

		getContentPane().add(d);
	}

	private int insert(int c, int p) {
		int i = nr - 1;
		boolean possible = false;
		while (i >= 0 && !possible) {
			if (tab[i][c] == 0) {
				tab[i][c] = (byte) p;
				possible = true;
			}
			i--;
		}
		return ++i;
	}

	@Override
	protected void humanPlayerMove(int chosenCol) {

		int r = insert(chosenCol, 1);
		addDisk(r, chosenCol, true);

		turn = "Avversario";

	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public void setNc(int nc) {
		this.nc = nc;
	}

	public boolean possible(int c) {
		boolean possible = false;
		for (int i = nr - 1; i >= 0; i--) {
			if (tab[i][c] == 0) {
				possible = true;
			}

		}
		return possible;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x = arg0.getX();

		x = x / cube;

		if (x >= nc) {
			x = nc - 1;
		} else if (x < 0) {
			x = 0;
		}

		if (possible(x)) {
			setMove(x);
		}

		/*
		 * for (int i = 0; i < nc; i++) { if (x < 100 * (i + 1)) { if
		 * (possible(i)) { setMove(i); } i = nc; } }
		 */
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
