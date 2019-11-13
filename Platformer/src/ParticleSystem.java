import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ParticleSystem {
	BufferedImage[] textures;
	ArrayList<Particle> particles = new ArrayList<Particle>();

	public ParticleSystem(BufferedImage[] textures, Shape s) {
		super();
		this.textures = textures;
		for (int i = 0; i < s.bounds.size(); i++) {
			int dim = (int) (Math.random() * 20);
			particles.add(new Particle(s.bounds.get(i).pos, new Vec2(Math.random() * 2, Math.random() * 2),
					Math.random() * Math.PI * 2, Math.toRadians(Math.random() * 15), dim, dim,
					textures[(int) (Math.random() * textures.length)]));
		}
	}
	public void update(float g) {
		for(Particle p : particles) {
			p.update(g);
		}
	}
	public void draw(Graphics g) {
		for(Particle p : particles) {
			p.draw(g);
		}
	}

}
