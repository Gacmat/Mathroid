package SeriousGame.entity.mob;

import java.util.ArrayList;
import java.util.List;

import SeriousGame.entity.Entity;
import SeriousGame.entity.door.DoorH;
import SeriousGame.entity.door.DoorV;
import SeriousGame.entity.projectile.Projectile;
import SeriousGame.entity.projectile.WizardProjectile;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;

public abstract class Mob extends Entity {

	protected boolean moving = false;
	protected boolean walking = false;
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private List<EnemyRed> enemiesRed = new ArrayList<EnemyRed>();
	private List<DoorV> doorsV = new ArrayList<DoorV>();
	private List<DoorH> doorsH = new ArrayList<DoorH>();
	public int health;
	public boolean getDamage = false;
	
	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction dir;

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = Direction.RIGHT; // Right 	
		if (xa < 0) dir = Direction.LEFT; // Left 	
		if (ya > 0) dir = Direction.DOWN; // Down	
		if (ya < 0) dir = Direction.UP; // Up 		

		if (!collision(xa, ya) && !ObjectCollision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	public abstract void update();

	public abstract void render(Screen screen);

	protected void shoot(int x, int y, double dir) {
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}
	
	private boolean ObjectCollision(int xa, int ya) {
		doorsV = level.doorsV;
		doorsH = level.doorsH;
		
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2);
			int yt = ((y + ya) + c / 2 - 16);
			for (int i = 0; i < doorsV.size(); i++) {
				if(doorsV.get(i).solid()){
					if (Math.abs(doorsV.get(i).getX() - xt) < 20 && Math.abs(doorsV.get(i).getY() - yt) < 64) solid = true;
				}
			}
			
			xt = ((x + xa) + c % 2 - 16);
			yt = ((y + ya) + c / 2 );
			for (int i = 0; i < doorsH.size(); i++) {
				if(doorsH.get(i).solid()){
					if (Math.abs(doorsH.get(i).getX() - xt) < 32 && Math.abs(doorsH.get(i).getY() - yt) < 25) solid = true;
				}
			}
		}
		return solid;
	}
	
	protected boolean MobCollision(int xa, int ya, int xOffset, int yOffset) {
		enemies = level.enemies;
		enemiesRed = level.enemiesRed;
		
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2+1);
			int yt = ((y + ya) + c / 2-8);
			for (int i = 0; i < enemies.size(); i++) {
					
				if (Math.abs(enemies.get(i).getX() - xt) < 15+xOffset && Math.abs(enemies.get(i).getY() - yt) < 23+yOffset){
					solid = true;
						
				}
			}
			for (int i = 0; i < enemiesRed.size(); i++) {
				if (Math.abs(enemiesRed.get(i).getX() - xt) < 15+xOffset && Math.abs(enemiesRed.get(i).getY() - yt) < 23+yOffset){
					solid = true;
				}
			}
		}
		return solid;
	}
	
	public boolean PlayerCollision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2);
			int yt = ((y + ya) + c / 2);
			if (Math.abs(level.getClientPlayer().getX() - xt) < 10 && Math.abs(level.getClientPlayer().getY() - yt) < 10){
				level.getClientPlayer().MobGiveDamage();
			}
		}
		return solid;
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 20 + 5) / 32;
			int yt = ((y + ya) + c / 2 * 25 + 5) / 32;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public int getHealth(){
		return health;
	}
}
