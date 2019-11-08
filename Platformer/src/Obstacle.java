import java.awt.Graphics;
import java.util.ArrayList;

public class Obstacle {
	Point pos;
	Vec2 vel;
	Shape s;
	public Obstacle(Point pos, Vec2 vel, ArrayList<Shape> shapes) {
		super();
		this.pos = pos;
		this.vel = vel;
		int shape = (int) (Math.random() * shapes.size());
		this.s = shapes.get(shape);
	}
	public void update(Player p) {
		if(!s.getDestroyed()) {
			s.move(vel);
			if(p.shapes.get(p.shape)[p.rotation].intersects(s)) {
				p.dead = true;
			}

			//
			
		}
	}
	public void draw(Graphics g) {
		s.draw(g);
	}
	
	
}
