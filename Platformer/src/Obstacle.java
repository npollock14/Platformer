import java.awt.Graphics;
import java.util.ArrayList;

public class Obstacle {
	Point pos;
	Vec2 vel;
	Shape s;
	Shape[] shapes;
	int deadTicks = 0;
	public Obstacle(Vec2 vel, Shape shape, Shape[] shapes) {
		super();
		this.pos = new Point(0,0);
		this.vel = vel;
		this.s = shape;
		this.shapes = shapes;
	}
	public void update(Player p) {
		if(!s.getDestroyed()) {
			s.move(vel);
			if(p.shapes.get(p.shape)[p.rotation].intersects(s)) {
				p.dead = true;
			}
			if(p.shapes.get(p.shape)[p.rotation].getBottom() < this.s.getBottom()) {
				s.setDestroyed(true, vel);
			}
			
		}else {
			deadTicks++;
		}
	}
	public void draw(Graphics g) {
		s.draw(g);
	}
	public Obstacle getCopy() {
		return new Obstacle(this.vel, this.s, this.shapes);
	}
	
}
