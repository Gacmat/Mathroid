package SeriousGame.level;



import java.util.ArrayList;
import java.util.List;

import SeriousGame.entity.Entity;
import SeriousGame.entity.terminal.MainTerminal;
import SeriousGame.entity.terminal.Terminal;
import SeriousGame.entity.door.DoorH;
import SeriousGame.entity.door.DoorV;
import SeriousGame.entity.mob.Enemy;
import SeriousGame.entity.mob.EnemyRed;
import SeriousGame.entity.mob.Player;
import SeriousGame.entity.particle.Particle;
import SeriousGame.entity.projectile.Projectile;
import SeriousGame.graphics.Screen;
import SeriousGame.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;

	public List<Entity> entities = new ArrayList<Entity>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<Particle> particles = new ArrayList<Particle>();
	public List<Player> players = new ArrayList<Player>();
	public List<Terminal> terminals = new ArrayList<Terminal>();
	public List<DoorH> doorsH = new ArrayList<DoorH>();
	public List<DoorV> doorsV = new ArrayList<DoorV>();
	public List<Enemy> enemies = new ArrayList<Enemy>();
	public List<EnemyRed> enemiesRed = new ArrayList<EnemyRed>();
	public List<MainTerminal> mainTerminal = new ArrayList<MainTerminal>();

	public static Level spawn = new SpawnLevel("/levels/level.png");
	
	public Screen screen;
	
	public Level(int width, int height, Screen screen) {
		this.width = width;
		this.height = height;
		this.screen = screen;
		tilesInt = new int[width * height];
	}

	public Level(String path) {
		loadLevel(path);
	}
	
	protected void loadLevel(String path) {
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 5;
		int x1 = (xScroll + screen.width + 32) >> 5;
		int y0 = yScroll >> 5;
		int y1 = (yScroll + screen.height + 32) >> 5;
		for (int y = y0; y < y1; y++)
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		if(solving()!=0){
			int Beka = 0;
			do{
				
			for (int i = 0; i < entities.size(); i++) {
				entities.get(i).render(screen);
			}
			for (int i = 0; i < projectiles.size(); i++) {
				projectiles.get(i).render(screen);
			}
			for (int i = 0; i < particles.size(); i++) {
				particles.get(i).render(screen);
			}
			for (int i = 0; i < players.size(); i++) {
				players.get(i).render(screen);
			}
			for (int i = 0; i < terminals.size(); i++) {
				terminals.get(i).render(screen);
			}
			for (int i = 0; i < doorsV.size(); i++) {
				doorsV.get(i).render(screen);
			}
			for (int i = 0; i < doorsH.size(); i++) {
				doorsH.get(i).render(screen);
			}
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).render(screen);
			}
			for (int i = 0; i < enemiesRed.size(); i++) {
				enemiesRed.get(i).render(screen);
			}
			mainTerminal.get(0).render(screen);
			}while(Beka==1);
		}
		else if(solving()==0){
			
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
		for (int i = 0; i < terminals.size(); i++) {
			terminals.get(i).render(screen);
		}
		for (int i = 0; i < doorsV.size(); i++) {
			doorsV.get(i).render(screen);
		}
		for (int i = 0; i < doorsH.size(); i++) {
			doorsH.get(i).render(screen);
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(screen);
		}
		for (int i = 0; i < enemiesRed.size(); i++) {
			enemiesRed.get(i).render(screen);
		}
		mainTerminal.get(0).render(screen);
		}
		
	}
	
	public int solving(){
		short solvCount=0;
		if(mainTerminal.get(0).solving)solvCount++;
		for (int i = 0; i < terminals.size(); i++) {
			if(terminals.get(i).solving){
				solvCount++;
			}
		}
		return solvCount;
	}
	
	public void update() {
		if(solving()==0){
			int Beka =1;
			for (int i = 0; i < players.size(); i++) {
				players.get(i).update();
			}
			do{
				for (int i = 0; i < entities.size(); i++) {
					entities.get(i).update();
				}
				for (int i = 0; i < projectiles.size(); i++) {
					projectiles.get(i).update();
				}
				for (int i = 0; i < terminals.size(); i++) {
					terminals.get(i).update();
				}
				for (int i = 0; i < particles.size(); i++) {
					particles.get(i).update();
				}

				for (int i = 0; i < doorsV.size(); i++) {
					doorsV.get(i).update();
				}
				for (int i = 0; i < doorsH.size(); i++) {
					doorsH.get(i).update();
				}
				for (int i = 0; i < enemies.size(); i++) {
					enemies.get(i).update();
				}
				for (int i = 0; i < enemiesRed.size(); i++) {
					enemiesRed.get(i).update();
				}
				mainTerminal.get(0).update();
				OpenTheDoor();
				remove();
			}while(Beka==0);
		}else if(solving()==0){
			for (int i = 0; i < entities.size(); i++) {
				entities.get(i).update();
			}
			for (int i = 0; i < projectiles.size(); i++) {
				projectiles.get(i).update();
			}
			for (int i = 0; i < terminals.size(); i++) {
				terminals.get(i).update();
			}
			for (int i = 0; i < particles.size(); i++) {
				particles.get(i).update();
			}

			for (int i = 0; i < doorsV.size(); i++) {
				doorsV.get(i).update();
			}
			for (int i = 0; i < doorsH.size(); i++) {
				doorsH.get(i).update();
			}
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).update();
			}
			for (int i = 0; i < enemiesRed.size(); i++) {
				enemiesRed.get(i).update();
			}
			mainTerminal.get(0).update();
			OpenTheDoor();
			remove();
		}
	}
	
	private void OpenTheDoor(){
		if(terminals.get(0).isSolved()) doorsV.get(0).setOpen();
		if(terminals.get(1).isSolved()) doorsV.get(1).setOpen();
		if(terminals.get(2).isSolved()) doorsV.get(2).setOpen();
		if(terminals.get(3).isSolved() && terminals.get(3).isSolved()) doorsV.get(3).setOpen();
		if(terminals.get(5).isSolved()) doorsV.get(4).setOpen();
		if(terminals.get(6).isSolved()) doorsH.get(0).setOpen();
		if(terminals.get(7).isSolved()) doorsH.get(1).setOpen();
		if(terminals.get(8).isSolved()) doorsH.get(2).setOpen();
		if(terminals.get(9).isSolved()) doorsH.get(3).setOpen();
		if(terminals.get(10).isSolved()) doorsH.get(4).setOpen();
		if(terminals.get(11).isSolved()) doorsH.get(5).setOpen();	
	}
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved()) players.remove(i);
		}
		for (int i = 0; i < terminals.size(); i++) {
			if (terminals.get(i).isRemoved()) terminals.remove(i);
		}
		for (int i = 0; i < doorsV.size(); i++) {
			if (doorsV.get(i).isRemoved()) doorsV.remove(i);
		}
		for (int i = 0; i < doorsH.size(); i++) {
			if (doorsH.get(i).isRemoved()) doorsH.remove(i);
		}
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).isRemoved()) enemies.remove(i);
		}
		for (int i = 0; i < enemiesRed.size(); i++) {
			if (enemiesRed.get(i).isRemoved()) enemiesRed.remove(i);
		}
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 5;
			int yt = (y - c / 2 * size + yOffset) >> 5;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public boolean ObjectCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset)+10;
			int yt = (y - c / 2 * size + yOffset);
			for (int i = 0; i < doorsV.size(); i++) {
				if(doorsV.get(i).solid()){
					if (Math.abs(doorsV.get(i).getX() - xt) < 10 && Math.abs(doorsV.get(i).getY() - yt) < 64) solid = true;
				}
			}
			xt = (x - c % 2 * size + xOffset);
			yt = (y - c / 2 * size + yOffset);
			for (int i = 0; i < doorsH.size(); i++) {
				if(doorsH.get(i).solid()){
					if (Math.abs(doorsH.get(i).getX() - xt) < 64 && Math.abs(doorsH.get(i).getY() - yt) < 10) solid = true;
				}
			}
		}
		return solid;
	}
	

	public boolean MobCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset);
			int yt = (y - c / 2 * size + yOffset);
			for (int i = 0; i < enemies.size(); i++) {
				if (Math.abs(enemies.get(i).getX() - xt) < 10 && Math.abs(enemies.get(i).getY() - yt) < 10){
					solid = true;
					enemies.get(i).getDamage=true;
				}
			}
			for (int i = 0; i < enemiesRed.size(); i++) {
				if (Math.abs(enemiesRed.get(i).getX() - xt) < 10 && Math.abs(enemiesRed.get(i).getY() - yt) < 10){
					solid = true;
					enemiesRed.get(i).getDamage=true;
				}
			}
		}
		return solid;
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else if (e instanceof Terminal){
			terminals.add((Terminal) e);
		} else if (e instanceof DoorV){
			doorsV.add((DoorV)e);
		}else if(e instanceof DoorH){
			doorsH.add((DoorH)e);
		}else if(e instanceof Enemy){
			enemies.add((Enemy)e);
		}else if(e instanceof EnemyRed){
			enemiesRed.add((EnemyRed)e);
		}else if(e instanceof MainTerminal){
			mainTerminal.add((MainTerminal)e);
		}else
			entities.add(e);
	}
	
	public int getW(){
		return width;
	}
	
	public int getH(){
		return height;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Player getPlayerAt(int index) {
		return players.get(index);
	}
	
	public Player getClientPlayer() {
		return players.get(0);
	}
	
	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = e.getX();
		int ey = e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = entity.getX();
			int y = entity.getY();

			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(entity);
		}
		return result;
	}

	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		int ex = e.getX();
		int ey = e.getY();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = player.getX();
			int y = player.getY();

			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(player);
		}
		return result;
	}
	// Grass 	= 0xFF00FF00	(green)
	// Flower 	= 0xFFFFFF00	(yellow)
	// Metal	= 0xFFBFBFBF  	(light grey)
	// MetalFloor = 0xFFBFBFFF	
	// Wood 	= 0xFFA52A2A  	(Brown)	
	// Rock 	= 0xFF7F7F7F	(grey)
	// Brick 	= 0xFFFF0000	(red)
	// RockFloor= 0x7F7F0F
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == 0xFF00FF00) return Tile.grass;
		if (tiles[x + y * width] == 0xFFFFFF00) return Tile.flower;
		if (tiles[x + y * width] == 0xFFBFBFBF) return Tile.metal;
		if (tiles[x + y * width] == 0xFFA52A2A) return Tile.wood;
		if (tiles[x + y * width] == 0xFF7F7F0F) return Tile.RockFloor;
		if (tiles[x + y * width] == 0xFF00FF00) return Tile.grass;
		if (tiles[x + y * width] == 0xFFFFFF00) return Tile.flower;
		if (tiles[x + y * width] == 0xFFBFBFBF) return Tile.metal;
		if (tiles[x + y * width] == 0xFFA52A2A) return Tile.wood;
		if (tiles[x + y * width] == 0xFF7F7F7F) return Tile.rock;
		if (tiles[x + y * width] == 0xFFFF0000) return Tile.brick;
		if (tiles[x + y * width] == 0xFFBFBFFF) return Tile.metalFloor;
		if (tiles[x + y * width] == 0xFF5B809E) return Tile.fence;
		return Tile.voidTile;
	}
}
