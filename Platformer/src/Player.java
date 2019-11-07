import java.awt.Graphics;
import java.util.ArrayList;

public class Player {
	Vec2 vel = new Vec2(0,0);
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	int health;
	Point pos;
	int shape;
	boolean dead;

	public Player(Point pos, int shape, Shape... shapes) {
		super();
		for(int i = 0; i < shapes.length; i++) {
		this.shapes.add(shapes[i]);
		}
		this.shape = shape;
		changeShape();
		this.pos = pos;
		this.vel = new Vec2(0, 0);
	}

	public void draw(Graphics g) {
		shapes.get(shape).draw(g);
	}

	public void update(Point mPos, boolean[] keys) {
		try {
			if (pos.distanceTo(mPos) > 1) {
				vel.x = (mPos.x - pos.x) / 10;
				vel.y = (mPos.y - pos.y) / 10;
			} else {
				vel = new Vec2(0, 0);
			}
		} catch (Exception e) {

		}
		move();
		
		if(keys[37]) {
			if(shape == 0) {
				shape = 6;
			}
			else {
				shape --;
				changeShape();
			}
		}
		if(keys[39]) {
			if(shape == 6) {
				shape = 0;
			}
			else {
				shape ++;
				changeShape();
			}
		}
		
		
		
	}

	private void changeShape() {
		shapes.get(shape).pos = pos;
	}

	private void move() {
		pos.add(vel);
		shapes.get(shape).move(vel);

	}
}
