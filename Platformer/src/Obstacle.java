import java.awt.Graphics;
import java.util.ArrayList;

public class Obstacle {
	Point pos;
	Vec2 vel;
	Shape s;
	int shapeID;
	boolean destroyed;
	public Obstacle(Point pos, Vec2 vel, Shape shape, int shapeID) {
		super();
		this.pos = pos;
		this.vel = vel;
		this.s = shape;
		this.shapeID = shapeID;
		s.setPosition(pos);
	}
	public void update(Player p) {
		if(!destroyed) {
			pos.add(vel);
			int newX = ((int)(pos.x / 50)) * 50;
			int newY = ((int)(pos.y / 50)) * 50;
			s.setPosition(new Point(newX,newY));
			if(p.shapes.get(p.shape)[p.rotation].intersects(s, 1.0f)) {
				p.dead = true;
			}
			if(p.shapes.get(p.shape)[p.rotation].getBottom() <= this.s.getBottom() && !p.dead) {
				destroyed = true;
			}
		}
			
	}
	public void draw(Graphics g) {
		s.draw(g);
	}
	
}
