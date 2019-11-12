import java.awt.Graphics;
import java.util.ArrayList;

public class Obstacle {
	Point pos;
	Vec2 vel;
	Shape s;
	Shape[] shapes;
	boolean destroyed;
	public Obstacle(Point pos, Vec2 vel, Shape shape, Shape[] shapes) {
		super();
		this.pos = pos;
		this.vel = vel;
		this.s = shape;
		this.shapes = shapes;
		s.setPosition(pos);
	}
	public void update(Player p) {
			s.move(vel);
			if(p.shapes.get(p.shape)[p.rotation].intersects(s)) {
				p.dead = true;
			}
			if(p.shapes.get(p.shape)[p.rotation].getBottom() < this.s.getBottom() && !p.dead) {
				destroyed = true;
			}
			
	}
	public void draw(Graphics g) {
		s.draw(g);
	}
	
}
