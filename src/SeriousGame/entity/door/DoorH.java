package SeriousGame.entity.door;

import SeriousGame.graphics.AnimatedSprite;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;
import SeriousGame.graphics.SpriteSheet;

public class DoorH extends Doors {

	public DoorH(int x, int y) {
		super(x, y);
		sprite1 = animSprite1.getSprite();
		sprite2 = animSprite2.getSprite();
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	public void update() {
		if(open){
			animSprite1.updateOnce();
			animSprite2.updateOnce();
		}
	}
	public boolean solid(){
		return solid;
	}
	
	
	public void render(Screen screen) {
		sprite1 = animSprite1.getSprite();
		sprite2 = animSprite2.getSprite();
		screen.renderMob(x, y, sprite1, 2);
		screen.renderMob(x + 32, y, sprite2, 2);
	}
}
