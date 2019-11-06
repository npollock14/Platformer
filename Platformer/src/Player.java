import java.awt.Graphics;

public class Player {
	Vec2 vel;
	Rect bounds;
	int health;

	public Player(Point pos, Vec2 vel, Rect bounds, int health) {
		super();
		this.vel = vel;
		this.bounds = bounds;
		this.health = health;
	}

	public void draw(Graphics g) {
		bounds.draw(g);
	}
}
