package SeriousGame.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import SeriousGame.entity.door.DoorH;
import SeriousGame.entity.door.DoorV;
import SeriousGame.entity.mob.Enemy;
import SeriousGame.entity.mob.EnemyRed;
import SeriousGame.entity.mob.Player;
import SeriousGame.entity.terminal.BigTerminal;
import SeriousGame.entity.terminal.MainTerminal;
import SeriousGame.entity.terminal.SmallTerminal;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}
	

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
		Random r = new Random();
		
		add(new BigTerminal(4, 4, "Aby wejœæ musisz powiedzieæ has³o", "2xP4WEweRescue"));
		add(new DoorV(4,5));
		
		add(new SmallTerminal(16, 4, "Pi¹tka podniesiona do trzeciej potêgi to:", "125"));
		add(new DoorV(16,5));
		
		add(new SmallTerminal(17, 19, "Wynikiem tego dzia³ania 12 - 3 * 9 + 6 / 3 jest: ", "-13"));
		add(new DoorV(34,13));
		
		add(new SmallTerminal(34, 16, "Dodatnim wynikiem równania 4x^2 + 2x - 2 jest: ", "0.5"));
		add(new SmallTerminal(32,33, "Jeœli potwór zadaje 59 obra¿eñ, a ty masz 93 pkt. zdrowia, to ile zdrowia Ci zostanie?", "34"));
		add(new DoorV(29,15));
		
		add(new BigTerminal(39, 51, "Jeœli a + b = 6 oraz a - b = 8 to a jest równe?", "7"));
		add(new DoorV(32,46));
		
		add(new BigTerminal(33, 11, "Prawo Ohma mówi, ¿e: ", "R=U/I"));
		add(new DoorH(31,11));
		
		add(new SmallTerminal(31, 17, "Pierwiastek z 144 to", "12"));
		add(new DoorH(32,18));
		
		add(new BigTerminal(45, 20, "Ile kolumn by³o w 2 pomieszczeniu?", "16"));
		add(new DoorH(43,20));
		
		add(new BigTerminal(46, 36, "16*16 to", "256"));
		add(new DoorH(47,36));
		
		add(new BigTerminal(48, 37, "Dwójka podniesiona do dziesi¹tej potêgi to:", "1024"));
		add(new DoorH(46,37));
		
		add(new SmallTerminal(42, 12, "W którym roku powsta³ pierwszy film z serii Gwiezdnych Wojen?", "1977"));
		add(new DoorH(40,12));
		
		
		add(new Enemy(13,5));
		add(new EnemyRed(24,4));
		add(new EnemyRed(24,7));
		add(new EnemyRed(34,28));
		add(new EnemyRed(31,28));
		for(int i = 0; i < 10 ; i++){
			add(new Enemy(32,14));
			add(new Enemy(19,19));
			add(new Enemy(33,28));
		}
		
		
		for(int i = 0; i < 100 ; i++){
			add(new EnemyRed(r.nextInt(6) + 47, r.nextInt(6) + 46 ));
			add(new Enemy(r.nextInt(4) + 48, r.nextInt(4) + 29) );
		}
		
		add(new MainTerminal(24,41,"Czy na pewno chcesz przywróciæ stan sprzed inwazji?",""));
	}
	
}
		


