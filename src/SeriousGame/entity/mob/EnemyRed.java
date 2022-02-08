package SeriousGame.entity.mob;

import java.util.List;

import SeriousGame.entity.spawner.ParticleSpawner;
import SeriousGame.graphics.AnimatedSprite;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;
import SeriousGame.graphics.SpriteSheet;

public class EnemyRed extends Mob {

	private boolean walking = false;
	private int playerX, playerY;

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.Enemy_down, 32, 32, 4);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.Enemy_up, 32, 32, 4);
	private AnimatedSprite side = new AnimatedSprite(SpriteSheet.Enemy_side, 32, 32, 7);

	private AnimatedSprite animSprite = down;
	private int time=0;
	private int xa = 0;
	private int ya = 0;

	public EnemyRed(int x, int y) {
		this.x = x << 5;
		this.y = y << 5;
		sprite = animSprite.getSprite();
		animSprite.setFramerate(6);
		health = 8;
	}
	
	public void move(List<Player> players) {
		xa = 0;
		ya = 0;
		if (players.size() > 0) {
			Player player = level.getClientPlayer();
				if (x + 10 < player.getX()) xa+=3;
				if (x - 10 > player.getX()) xa-=3;
				if (y + 10 < player.getY()) ya+=3;
				if (y - 10 > player.getY()) ya-=3;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else
			walking = false;
	}

	public void update() {
		if(getDamage){
			health--;
		}
		PlayerCollision(xa, ya);
		List<Player> players = level.getPlayers(this,300);
		playerX = level.players.get(0).getX();
		playerY = level.players.get(0).getY();
		
		move(players);
		if (walking) animSprite.update();
		else
			animSprite.setFrame(0);

		if (xa > 0) {
			animSprite.setFramerate(3);
			animSprite = side;
			dir = Direction.RIGHT;
		} else if (xa < 0) {
			animSprite.setFramerate(3);
			animSprite = side;
			dir = Direction.LEFT;
		}

		if (ya < 0) {
			animSprite.setFramerate(6);
			animSprite = up;
			dir = Direction.UP;
		} else if (ya > 0) {
			animSprite.setFramerate(6);
			animSprite = down;
			dir = Direction.DOWN;
		}
	}

	public void render(Screen screen) {
		time++;
		if(time == 7200) time = 0;
		
		int flip = 0;
		if (dir == Direction.LEFT || dir == Direction.DOWN) flip = 1;
		if(getDamage){
			sprite = Sprite.EnemyScared;
			getDamage = false;
			time = 0;
			screen.renderSprite(0, 0, Sprite.grass, true);
		}
		else if(sprite == Sprite.EnemyScared){
			if(time % 60==0) sprite = animSprite.getSprite();
		}else sprite = animSprite.getSprite();
		screen.renderMob(x, y, this, flip);
		screen.renderMob(x, y, this, flip);
		if (health < 1){
			level.add(new ParticleSpawner((int) x + 15, (int) y + 15, 60, 300, level, false, true));
			remove();
		}
	}
}
