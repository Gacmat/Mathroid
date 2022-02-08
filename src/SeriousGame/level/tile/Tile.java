package SeriousGame.level.tile;

import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	
	//Pod³o¿a
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile metal = new MetalTile(Sprite.metal);
	public static Tile metalFloor = new metalFloor(Sprite.metalFloor);
	public static Tile wood = new WoodTile(Sprite.wood);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile brick = new BrickTile(Sprite.brick);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile RockFloor = new RockFloorTile(Sprite.RockFloor);
	public static Tile fence = new fence(Sprite.fence);
	public static Tile Health = new Health(Sprite.Health);
	//Drzwi


	//Ikony
	public static Tile Options	= new Option(Sprite.Options);
	public static Tile Start 	= new Start(Sprite.Start);
	public static Tile Exit 	= new Exit(Sprite.Exit);
	public static Tile Credits1 = new Credits1(Sprite.Credits1);
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public boolean solid(){
		return false;
	}	
}
