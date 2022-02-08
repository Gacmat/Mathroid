package SeriousGame.entity.mob;

import SeriousGame.entity.spawner.ParticleSpawner;
import SeriousGame.graphics.AnimatedSprite;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;
import SeriousGame.graphics.SpriteSheet;

public class Enemy extends Mob {

	private boolean walking = false;

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.Enemy_down, 32, 32, 4);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.Enemy_up, 32, 32, 4);
	private AnimatedSprite side = new AnimatedSprite(SpriteSheet.Enemy_side, 32, 32, 7);

	private AnimatedSprite animSprite = down;

	private int time = 0;
	private int xa = 0;
	private int ya = 0;

	public Enemy(int x, int y) {
		this.x = x << 5;
		this.y = y << 5;
		sprite = animSprite.getSprite();
		animSprite.setFramerate(6);
		health = 5;
	}

	
	public void update() {
		if(getDamage){
			health--;
		}
		PlayerCollision(xa, ya);
		if(health<0) remove();
		time++;
		if (time % (random.nextInt(50) + 30) == 0) {
			if (time > 60 * 120) time = 0;

			xa = random.nextInt(5) - 3;
			ya = random.nextInt(5) - 3;
			if (random.nextInt(4) == 0) {
				xa = 0;
				ya = 0;
			}
		}
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

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else
			walking = false;
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
		}
		else if(sprite == Sprite.EnemyScared){
			if(time % 60==0) sprite = animSprite.getSprite();
		}else sprite = animSprite.getSprite();
		
		screen.renderMob(x, y, this, flip);
		if (health < 1){
			level.add(new ParticleSpawner((int) x + 15, (int) y + 15, 60, 300, level, false, true));
			remove();
		}
	}
}
