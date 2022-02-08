package SeriousGame.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] pixels;

	public static SpriteSheet tiles 	= new SpriteSheet("/textures/spritesheet.png", 256);
	public static SpriteSheet SkillComp = new SpriteSheet("/textures/big_structure.png", 256);
	public static SpriteSheet Enemy 	= new SpriteSheet("/textures/Enemy.png", 256);
	public static SpriteSheet projectile_wizard = new SpriteSheet("/textures/projectiles/shoots.png", 48);
	public static SpriteSheet Player 	= new SpriteSheet("/textures/Player.png", 160);
	
	public static SpriteSheet BigTiles 	= new SpriteSheet("/levels/mysteryScenes/BigTiles.png", 256);

	public static SpriteSheet player_down 	= new SpriteSheet(Player, 0, 0, 4, 1, 32);
	public static SpriteSheet player_up 	= new SpriteSheet(Player, 0, 1, 4, 1, 32);
	public static SpriteSheet player_left 	= new SpriteSheet(Player, 0, 3, 4, 1, 32);
	public static SpriteSheet player_right 	= new SpriteSheet(Player, 0, 2, 4, 1, 32);
	public static SpriteSheet player_shoot  = new SpriteSheet(Player, 4, 0, 1, 4, 32);
	
	public static SpriteSheet Enemy_down  	= new SpriteSheet(Enemy, 0, 1, 4, 1, 32);
	public static SpriteSheet Enemy_up  	= new SpriteSheet(Enemy, 4, 1, 4, 1, 32);
	public static SpriteSheet Enemy_side  	= new SpriteSheet(Enemy, 0, 0, 7, 1, 32);
	public static SpriteSheet Door1  	= new SpriteSheet(tiles, 0, 2, 8, 1, 32);
	public static SpriteSheet Door2  	= new SpriteSheet(tiles, 0, 3, 8, 1, 32);
	
	//Icons
	public static SpriteSheet Option1  	= new SpriteSheet(tiles, 4, 1, 4, 1, 32);
	public static SpriteSheet Option2  	= new SpriteSheet(tiles, 0, 0, 7, 1, 32);
	public static SpriteSheet Start1  	= new SpriteSheet(tiles, 0, 2, 8, 1, 32);
	public static SpriteSheet Start2  	= new SpriteSheet(tiles, 0, 3, 8, 1, 32);
	public static SpriteSheet Credits  	= new SpriteSheet(tiles, 0, 0, 7, 1, 32);
	public static SpriteSheet Exit1  	= new SpriteSheet(tiles, 0, 2, 8, 1, 32);
	public static SpriteSheet Exit2  	= new SpriteSheet(tiles, 0, 3, 8, 1, 32);
	
	
	private Sprite[] sprites;

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = width;
		else
			SIZE = -1;
		WIDTH = w;
		HEIGHT = h;

		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[width * height];
		load();
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
