package SeriousGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import SeriousGame.entity.mob.Player;
import SeriousGame.graphics.Screen;
import SeriousGame.input.Keyboard;
import SeriousGame.input.Mouse;
import SeriousGame.level.Level;
import SeriousGame.level.TileCoordinate;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int width = 640;
	private static int height = width / 5 * 4;
	private static int scale = 2;
	private double gameSpeed = 60.0;

	public static String title = "Mathroidvania";
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	public Level level;
	private Player player;
	//private Enemy enemy;
	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	// -------GAME----------------------------------------------GAME
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(1, 1);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		level.add(player);

		Mouse mouse = new Mouse();
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}

	// -------start----------------------------------------------start
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	// -------stop------------------------------------------------stop
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// -------run--------------------------------------------------run
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / gameSpeed;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	// -------update----------------------------------------------update
	public void update() {
		key.update();
		level.update();
		if(level.players.get(0).health < 1){
			gameOver();
		}
		if(level.mainTerminal.get(0).win == true){
			gameOver();
		}
	}

	// -------render----------------------------------------------render
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = player.getX() - screen.width / 2;
		int yScroll = player.getY() - screen.height / 2;
		level.render(xScroll, yScroll, screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
//		 Grafika start
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		//g.fillRect(Mouse.getX()-32, Mouse.getY()-32, 64, 64);
		//g.drawString("Button: "+ Mouse.getB() , 80, 80);
		// Grafika stop
		g.dispose();
		bs.show();
	}
	public void gameOver(){
		System.exit(0);
	}


	// -------main------------------------------------------------main
	public static void main(String[] args) {
			Game game = new Game();
			game.frame.setResizable(false);
			game.frame.setUndecorated(true);
			game.frame.setTitle(Game.title);
			game.frame.add(game);
			game.frame.pack();
			game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			game.frame.setLocationRelativeTo(null);
			game.frame.setVisible(true);
	
			game.start();
	}
}


