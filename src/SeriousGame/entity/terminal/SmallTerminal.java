package SeriousGame.entity.terminal;

import java.util.List;

import SeriousGame.entity.mob.Player;
import SeriousGame.graphics.Screen;
import SeriousGame.graphics.Sprite;

public class SmallTerminal extends Terminal{
	
	public SmallTerminal(int x, int y, String Zagadka, String rightAnswer){
		super(x, y, Zagadka, rightAnswer);
		sprite = Sprite.TerminalSmall;
	}
	
	public void update() {
		List<Player> players = level.getPlayers();
		action = players.get(0).getAction();
		playerX = players.get(0).getX();
		playerY = players.get(0).getY();
		PlayerAction();
	}
	
	public void render(Screen screen) {
		if(solved) sprite = Sprite.TerminalSmallSolved;
		else	sprite = Sprite.TerminalSmall;
		screen.renderMob(x, y, sprite, 0);
	}

}
