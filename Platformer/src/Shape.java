import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Shape {
	ArrayList<Rect> bounds = new ArrayList<Rect>();
	Point pos;

	public Shape(float boundWidth, float boundHeight, float feather, BufferedImage[] images, int... positions) {
		super();
		if (positions.length % 2 != 0) {
			throw new IllegalArgumentException("Sets of 2 points needed");
		}
		for (int i = 0; i < positions.length - 1; i += 2) {
			bounds.add(new Rect(positions[i] * boundWidth + feather,
					positions[i + 1] * boundHeight + feather,
					boundWidth - feather - feather, boundHeight - feather - feather));
		}
		this.pos = new Point(0, 0);
		this.setPosition(pos);
	}

	public Shape(int w, int h, float boundWidth, BufferedImage[] images, int... positions) {
		super();
		ArrayList<Point> positionsToSubtract = new ArrayList<Point>();
		if (positions.length % 2 != 0) {
			throw new IllegalArgumentException("Sets of 2 points needed");
		} else {

			for (int i = 0; i < positions.length - 1; i += 2) {
				positionsToSubtract.add(new Point(positions[i], positions[i + 1]));
			}
		}
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				boolean valid = true;
				for (Point p : positionsToSubtract) {
					if (p.isSame(new Point(i, j))) {
						valid = false;
						break;
					}
				}
				if (valid)
					bounds.add(new Rect(i * boundWidth, j * boundWidth, boundWidth, boundWidth));
			}
		}
		this.pos = new Point(0, 0);
		this.setPosition(pos);
	}

	public void move(Vec2 vel) {
		this.pos.add(vel);
		for (Rect r : bounds) {
			r.pos.add(vel);
		}

	}

	public void setPosition(Point pos) {
		try {
			double deltaX = pos.x - this.pos.x;
			double deltaY = pos.y - this.pos.y;

			move(new Vec2(deltaX, deltaY));
		} catch (Exception e) {

		}

	}

	public void draw(Graphics g) {
		for (Rect r : bounds) {
			r.draw(g);
			// System.out.println("Drawing " + r.pos.x + " " + r.pos.y );
		}
	}

	public boolean intersects(Rect r) {
		for (Rect re : bounds) {
			if (re.intersects(r, 0)) {
				return true;
			}
		}
		return false;
	}

	public boolean intersects(Shape s, float feather) {
		for (Rect re : bounds) {
			for (Rect r : s.bounds) {
				if (re.intersects(r, feather)) {
					return true;
				}
			}

		}
		return false;
	}

	public double getBottom() {
		double low = this.bounds.get(0).pos.y + this.bounds.get(0).h;
		for (int i = 1; i < this.bounds.size(); i++) {
			if (this.bounds.get(i).pos.y + this.bounds.get(i).h > low) {
				low = this.bounds.get(i).pos.y + this.bounds.get(i).h;
			}
		}
		return low;
	}

}
