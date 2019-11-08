import java.awt.Graphics;
import java.util.ArrayList;

public class Shape {
ArrayList<Rect> bounds = new ArrayList<Rect>();
Point pos;

public Shape(int boundWidth, int... positions) {
	super();
	if(positions.length % 2 != 0) {
		throw new IllegalArgumentException("Boi, give points");
	}
	for(int i = 0; i < positions.length-1; i+=2) {
		bounds.add(new Rect(positions[i]*boundWidth, positions[i+1]*boundWidth, boundWidth, boundWidth));
	}
	this.pos = new Point(0,0);
}
public void move(Vec2 vel) {
	this.pos.add(vel);
	for(Rect r: bounds) {
		r.pos.add(vel);
	}
	
}
public void rotate() {
	for(Rect r : bounds) {
		double x = r.pos.x;
		r.pos.x = r.pos.y;
		r.pos.y = x;
	}
	setPosition(pos);
//	double x = pos.x;
//	pos.x = pos.y;
//	pos.y = pos.x;
}
public void setPosition(Point pos) {
	double deltaX = pos.x - this.pos.x;
	double deltaY = pos.y - this.pos.y;
	
	move(new Vec2(deltaX,deltaY));
	
	//System.out.println("Moved " + deltaX + " " + deltaY);
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
