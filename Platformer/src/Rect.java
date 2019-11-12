import java.awt.Graphics;

public class Rect {
	Point pos;
	double h, w;

	public Rect(double x, double y, double w, double h) {

		this.pos = new Point(x, y);
		this.h = h;
		this.w = w;

	}

	public boolean intersects(Rect r, float feather) {
		return (pos.inside(r, feather) || new Point(pos.x + w, pos.y).inside(r, feather)
				|| new Point(pos.x + w, pos.y + h).inside(r, feather)
				|| new Point(pos.x, pos.y + h).inside(r, feather));
	}

	public void draw(Graphics g) {
		g.drawRect((int) pos.x, (int) pos.y, (int) w, (int) h);
	}
}