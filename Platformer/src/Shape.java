import java.awt.Graphics;
import java.util.ArrayList;

public class Shape {
	ArrayList<Rect> bounds = new ArrayList<Rect>();
	Point pos;
	boolean destroyed;
	boolean init;
	Vec2[] explosionVelocities;

	public Shape(int boundWidth, int... positions) {
		super();
		if (positions.length % 2 != 0) {
			throw new IllegalArgumentException("Boi, give points");
		}
		for (int i = 0; i < positions.length - 1; i += 2) {
			bounds.add(new Rect(positions[i] * boundWidth, positions[i + 1] * boundWidth, boundWidth, boundWidth));
		}
		this.pos = new Point(0, 0);
	}

	public void move(Vec2 vel) {
		this.pos.add(vel);
		for (Rect r : bounds) {
			r.pos.add(vel);
		}

	}

	public void setPosition(Point pos) {
		double deltaX = pos.x - this.pos.x;
		double deltaY = pos.y - this.pos.y;

		move(new Vec2(deltaX, deltaY));

		// System.out.println("Moved " + deltaX + " " + deltaY);
	}

	public void draw(Graphics g) {
		for (Rect r : bounds) {
			r.draw(g);
			// System.out.println("Drawing " + r.pos.x + " " + r.pos.y );
		}
		if (destroyed) {
			if (!init) {
				initDestroy();
			}
			for (int i = 0; i < bounds.size(); i++) {
				bounds.get(i).h *= .98;
				bounds.get(i).w *= .98;
				bounds.get(i).pos.add(explosionVelocities[i]);
				explosionVelocities[i].y += .1;
			}
		}
	}

	private void initDestroy() {

		explosionVelocities = new Vec2[bounds.size()];
		for (int i = 0; i < bounds.size(); i++) {
			explosionVelocities[i] = new Vec2(Math.random() * 10 - 5, Math.random() * 10 - 3);
		}

		init = true;

	}

	public boolean intersects(Rect r) {
		for (Rect re : bounds) {
			if (re.intersects(r)) {
				return true;
			}
		}
		return false;
	}

}
