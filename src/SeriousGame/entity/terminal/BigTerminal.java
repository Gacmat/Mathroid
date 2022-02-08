package SeriousGame.entity.terminal;

import java.util.List;

import SeriousGame.entity.mob.Player;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;

public class BigTerminal extends Terminal{
	
	
	public BigTerminal(int x, int y, String Zagadka, String rightAnswer) {
		super(x, y, Zagadka, rightAnswer);
		sprite = Sprite.TerminalBig;
		
	}
	
	public void update() {
		List<Player> players = level.getPlayers();
		action = players.get(0).getAction();
		playerX = players.get(0).getX();
		playerY = players.get(0).getY();
		PlayerAction();
	}
	
	public void render(Screen screen) {
		if(solved) sprite = Sprite.TerminalBigSolved;
		else	sprite = Sprite.TerminalBig;
		screen.renderMob(x, y, sprite, 0);
	}

}
