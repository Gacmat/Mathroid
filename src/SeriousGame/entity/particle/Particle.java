package SeriousGame.entity.particle;

import java.util.ArrayList;
import java.util.List;

import SeriousGame.entity.Entity;
import SeriousGame.entity.door.DoorV;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;

public class Particle extends Entity {

	private Sprite sprite;
	private boolean death = false;

	private int life;
	private int time = 0;
	protected double xx, yy, zz, ny, nx;
	protected double xa, ya, za;

	public Particle(int x, int y, int life, boolean blood) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(30) - 10);
		if(blood)sprite = Sprite.particleGetDamage;
		else sprite = Sprite.particle_normal;

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}
	public Particle(int x, int y, int life, boolean terminal, boolean death) {
		this.death = death;
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(30) - 10);
		if(terminal) sprite = Sprite.particleSolved;
		else if(death)sprite = Sprite.particle_terminal;
		
		else sprite = Sprite.particle_normal;

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}

	public Particle(int x, int y, int life, double nx, double ny, double angle) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.ny = ny;
		this.nx = nx;

		nx = Math.cos(angle) - 0.5;
		ny = Math.sin(angle) - 0.5;

		this.life = life + (random.nextInt(10) - 10);
		sprite = Sprite.particle_normal;

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}

	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) remove();
		if(death){
			za -= 0.1;
			if (zz < 0) {
				zz = 0;
				za *= -0.4;
				xa *= 0.9;
				ya *= 0.9;
						
			}
		}
		else{
			za -= 0.1;
			xa += nx / 10;
			ya += ny / 10;
			if (time > 20 && time < 60) {
				if (xa > 0) xa -= nx / 5;
				if (ya > 0) ya -= ny / 5;
				nx *= 0.5;
				ny *= 0.5;
			}
	
			if (zz < 0) {
				zz = 0;
				za *= -0.3;
				xa *= 0.1;
				ya *= 0.1;
				
			}
		}
		move((xx + xa), (yy + ya) + (zz - za));
	}

	private void move(double x, double y) {
		if (collision(x, y)) {
			this.xa *= -0.1;
			this.ya *= -0.1;
			this.za *= -0.05;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}

	public boolean collision(double x, double y) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 32) / 32;
			double yt = (y - c / 2 * 32) / 32;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}
	

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy - (int) zz, sprite, true);
	}
}
