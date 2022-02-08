package SeriousGame.level.tile;

import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;

public class TerminalBigTile extends Tile {

	public TerminalBigTile(Sprite sprite) {
		super(sprite);
	}
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 5, y << 5, this.sprite);
	}
	
}
