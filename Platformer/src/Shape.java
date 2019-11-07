import java.awt.Graphics;
import java.util.ArrayList;

public class Shape {
ArrayList<Rect> bounds = new ArrayList<Rect>();
Point pos = new Point(0,0);

public Shape(ArrayList<Rect> bounds) {
	super();
	this.bounds = bounds;
	this.pos = new Point(0,0);
}
public void move(Vec2 vel) {
	pos.add(vel);
	for(Rect r: bounds) {
		r.pos.add(vel);
	}
	
}
public void draw(Graphics g) {
	for(Rect r : bounds) {
		r.draw(g);
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
