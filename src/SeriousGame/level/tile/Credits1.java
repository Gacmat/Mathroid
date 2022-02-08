package SeriousGame.level.tile;

import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;

public class Credits1 extends Tile {

	public Credits1(Sprite sprite) {
		super(sprite);
	}
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 5, y << 5, this.sprite);
	}

}
