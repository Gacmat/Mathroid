package SeriousGame.entity.door;

import SeriousGame.entity.Entity;
import SeriousGame.entity.terminal.Terminal;
import SeriousGame.graphics.AnimatedSprite;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;
import SeriousGame.graphics.SpriteSheet;

public abstract class Doors extends Entity{
	
	public boolean open = false;
	public boolean solid = true;
	protected Sprite sprite1;
	protected Sprite sprite2;
	protected Terminal terminal;
	
	public AnimatedSprite door1 = new AnimatedSprite(SpriteSheet.Door1, 32, 32, 8);
	public AnimatedSprite door2 = new AnimatedSprite(SpriteSheet.Door2, 32, 32, 8);
	
	public AnimatedSprite animSprite1 = door1;
	public AnimatedSprite animSprite2 = door2;
	
	public Doors(int x, int y){
		this.x = x << 5;
		this.y = y << 5;
		sprite1 = animSprite1.getSprite();
		sprite2 = animSprite2.getSprite();	
	}
	public void setOpen(){
		open = true;
		solid = false;
	}
	
	public abstract void update();

	public abstract void render(Screen screen);

}
