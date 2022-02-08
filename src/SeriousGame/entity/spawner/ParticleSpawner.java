package SeriousGame.entity.spawner;

import SeriousGame.entity.particle.Particle;
import SeriousGame.entity.spawner.Spawner.Type;
import SeriousGame.level.Level;

public class ParticleSpawner extends Spawner {

	private int life;
	private double nx, ny;

	public ParticleSpawner(int x, int y, int life, int amount, Level level, boolean blood) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;

		for (int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life, blood));
		}
	}

	public ParticleSpawner(int x, int y, int life, int amount, Level level, boolean terminal, boolean death) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;

		for (int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life, terminal, death));
		}
	}

	public ParticleSpawner(int x, int y, int life, int amount, Level level, double nx, double ny, double angle) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		this.nx = nx;
		this.ny = ny;

		for (int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life, nx, ny, angle));
		}
	}
}
