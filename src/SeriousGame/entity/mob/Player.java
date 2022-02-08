package SeriousGame.entity.mob;

import java.util.List;

import SeriousGame.Game;
import SeriousGame.entity.Entity;
import SeriousGame.entity.projectile.Projectile;
import SeriousGame.entity.projectile.WizardProjectile;
import SeriousGame.graphics.AnimatedSprite;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;
import SeriousGame.graphics.SpriteSheet;
import SeriousGame.input.Keyboard;
import SeriousGame.input.Mouse;
import SeriousGame.level.Level;

public class Player extends Mob {

	public Keyboard input;
	private Sprite sprite;
	private boolean action;

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 4);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 4);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 4);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 4);
	private AnimatedSprite animSprite = down;
	private int fireRate = 0;

	private int time = 0;
	public int health = 10;
	private boolean getDamage=false;
	private boolean MobGiveDamage = false;
	public Player(Keyboard input) {
		this.input = input;
		sprite = animSprite.getSprite();
		animSprite = down;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = animSprite.getSprite();
		fireRate = WizardProjectile.FIRE_RATE;
	}

	public void getDamage(){
		health--;
		getDamage = true;
		MobGiveDamage = false;
	}
	public void MobGiveDamage(){
		MobGiveDamage = true;
	}
	
	public void update() {
		time++;
		if(MobGiveDamage && time % 30 == 1){
			getDamage();
			time = 2;
		}
		if(time == 7200) time = 0;
		
		if(input.action){
			if(!action){
				action = true;
			}
		}else action = false;

		if (walking) animSprite.update();
		else
			animSprite.setFrame(0);
		if (WizardProjectile.FIRE_RATE > 0) {
			fireRate--;
		}
		int xa = 0, ya = 0, boost = 3;
		if (input.shift) {
			boost+=2;
		}

		if (!input.shift) {
			boost--;
		}

		if(input.esc){
			System.exit(0);
		}
		if (input.left) {
			xa = xa - boost;
			animSprite = left;
		} else if (input.right) {
			xa = xa + boost;
			animSprite = right;
		}

		if (input.up) {
			ya = ya - boost;
			animSprite = up;
		} else if (input.down) {
			ya = ya + boost;
			animSprite = down;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else
			walking = false;

		clear();
		updateShooting();
	}
	
	public boolean getAction(){
		return action;
	}
	
	
	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	private void updateShooting() {
		if (Mouse.getB() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		if(getDamage){
			for(int i = 0; i < 20;i++){
				screen.renderSprite(i*32, 0, Sprite.getDamage, false);
				screen.renderSprite(i*32, 15*32, Sprite.getDamage, false);
			}
			for(int i = 0; i < 15;i++){
				screen.renderSprite(0, i*32, Sprite.getDamage, false);
				screen.renderSprite(19*32, i*32, Sprite.getDamage, false);
			}
			
			if(time % 90 == 0){
				getDamage = false;
				time = 0;
			}
		}
		for(int i = 0 ; i < health ; i++ )
			screen.renderTile(i*32, 0, Sprite.Health, false);
		screen.renderMob(x, y, sprite, 0);
	}
}
