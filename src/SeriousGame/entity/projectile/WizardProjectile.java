package SeriousGame.entity.projectile;

import SeriousGame.entity.spawner.ParticleSpawner;
import SeriousGame.entity.spawner.Spawner;
import SeriousGame.entity.particle.Particle;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 3; // higher = slower

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 300;
		speed = 10;
		damage = 1;
		sprite = Sprite.projectile_wizard;
		nx = speed * Math.cos(angle) - 0.5;
		ny = speed * Math.sin(angle) - 0.5;
	}

	public void update() {
		if (level.tileCollision((int) (x + nx + 16), (int) (y + ny + 16), 7, +5, +5) || level.ObjectCollision((int) (x + nx-12), (int) (y + ny), 7, +5, +5)) {
			level.add(new ParticleSpawner((int) x + 15, (int) y + 15, 20, 10, level, false));
			remove();
		}
		if(level.MobCollision((int) (x + nx), (int) (y + ny), 5, 0, 0)){
			level.add(new ParticleSpawner((int) x + 15, (int) y + 15, 40, 20, level, true));
			remove();
		}
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) {
			level.add(new ParticleSpawner((int) x + 15, (int) y + 15, 40, 100, level, nx, ny, angle));
			remove();
		}
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x + 8, (int) y + 8, this);
	}
	public int getDamage(){
		return (int) damage;
	}
}
