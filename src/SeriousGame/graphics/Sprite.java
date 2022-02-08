package SeriousGame.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	// Pod³o¿a
	public static Sprite grass 	= new Sprite(32, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(32, 1, 0, SpriteSheet.tiles);
	public static Sprite metal 	= new Sprite(32, 6, 0, SpriteSheet.tiles);
	public static Sprite metalFloor	= new Sprite(32, 2, 0, SpriteSheet.tiles);
	public static Sprite wood 	= new Sprite(32, 3, 0, SpriteSheet.tiles);
	public static Sprite rock 	= new Sprite(32, 4, 0, SpriteSheet.tiles);
	public static Sprite brick 	= new Sprite(32, 5, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(32, 7, 0, SpriteSheet.tiles);
	public static Sprite RockFloor = new Sprite(32, 4, 4, SpriteSheet.tiles);
	public static Sprite fence	 	= new Sprite(32, 3, 4, SpriteSheet.tiles);
	public static Sprite Health 	= new Sprite(32, 2, 4, SpriteSheet.tiles);
	
	public static Sprite MainTerminal1 	= new Sprite(128, 0, 0, SpriteSheet.SkillComp);
	public static Sprite MainTerminal2 	= new Sprite(128, 1, 0, SpriteSheet.SkillComp);
	
	public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);
	
	//particle
	public static Sprite getDamage = new Sprite (32,0xFFff0000);
	public static Sprite particleGetDamage = new Sprite(2, 0xFF0000FF);
	public static Sprite particle_normal = new Sprite(3, 0xFF222222);
	public static Sprite particleBlood = new Sprite (2,0xFFFF0000);
	public static Sprite particle_terminal = new Sprite(3, 0xFF22FFFF);
	public static Sprite particleSolved = new Sprite(3, 0xFF00FF00);
	
	//Drzwi
	public static Sprite Door1Closed = new Sprite(32, 0, 2, SpriteSheet.tiles);
	public static Sprite Door2Closed = new Sprite(32, 0, 3, SpriteSheet.tiles);
	
	//Terminale
	public static Sprite TerminalSmall 			= new Sprite(32, 0, 4, SpriteSheet.tiles);
	public static Sprite TerminalSmallSolved 	= new Sprite(32, 0, 5, SpriteSheet.tiles);
	
	public static Sprite TerminalBig 		= new Sprite(32, 1, 4, SpriteSheet.tiles);
	public static Sprite TerminalBigSolved 	= new Sprite(32, 1, 5, SpriteSheet.tiles);
	
	//Ikony
	public static Sprite Options 	= new Sprite(32, 0, 1, SpriteSheet.tiles);
	public static Sprite OptionsClic= new Sprite(32, 1, 1, SpriteSheet.tiles);
	public static Sprite Exit 		= new Sprite(32, 2, 1, SpriteSheet.tiles);
	public static Sprite ExitClic 	= new Sprite(32, 3, 1, SpriteSheet.tiles);
	public static Sprite Start 		= new Sprite(32, 4, 1, SpriteSheet.tiles);
	public static Sprite StartClic 	= new Sprite(32, 5, 1, SpriteSheet.tiles);
	public static Sprite Credits1 	= new Sprite(32, 6, 1, SpriteSheet.tiles);
	public static Sprite Credits2 	= new Sprite(32, 7, 1, SpriteSheet.tiles);

	//Potworek
	public static Sprite EnemyScared 	= new Sprite(32, 7, 0, SpriteSheet.Enemy);
	
	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int colour) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColour(colour);
	}

	public Sprite(int size, int colour) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}

	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	private void setColour(int colour) {
		for (int i = 0; i < width * height; i++)
			pixels[i] = colour;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < SIZE; y++)
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
	}
}
