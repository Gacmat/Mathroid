package SeriousGame.entity.spawner;

import SeriousGame.entity.Entity;
import SeriousGame.entity.particle.Particle;
import SeriousGame.level.Level;

public class Spawner extends Entity {

	public enum Type {
		MOB, PARTICLE;
	}

	private Type type;

	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
