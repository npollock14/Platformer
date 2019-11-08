import java.awt.Graphics;
import java.util.ArrayList;

public class Shape {
ArrayList<Rect> bounds = new ArrayList<Rect>();
Point pos;

public Shape(ArrayList<Rect> bounds, Point pos) {
	super();
	this.bounds = bounds;
	this.pos = pos;
}
public void move(Vec2 vel) {
	this.pos.add(vel);
	for(Rect r: bounds) {
		r.pos.add(vel);
	}
	
}
public void setPosition(Point pos) {
	double deltaX = pos.x - this.pos.x;
	double deltaY = pos.y - this.pos.y;
	
	move(new Vec2(deltaX,deltaY));
}
public void draw(Graphics g) {
	for(Rect r : bounds) {
		r.draw(g);
		//System.out.println("Drawing " + r.pos.x + " " + r.pos.y );
	}
}
public boolean intersects(Rect r) {
	for(Rect re : bounds) {
		if(re.intersects(r)) {
		return true;
		}
	}
	return false;
}



}