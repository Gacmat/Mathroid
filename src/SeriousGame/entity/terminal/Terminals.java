package SeriousGame.entity.terminal;

import SeriousGame.entity.Entity;
import SeriousGame.graphics.Screen;

public abstract class Terminals extends Entity{	
	
	public abstract void update();

	public abstract void render(Screen screen);
}
