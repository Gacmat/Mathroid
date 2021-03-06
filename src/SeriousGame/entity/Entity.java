package SeriousGame.entity;

import java.util.Random;

import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;
import SeriousGame.level.Level;

public abstract class Entity {

	protected int x, y;
	protected Sprite sprite;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public Entity() {

	}

	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void update() {
	}

	public void render(Screen screen) {
	}

	public void remove() {
		//Remove from level
		removed = true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}
}
