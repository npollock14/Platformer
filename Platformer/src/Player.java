import java.awt.Graphics;
import java.util.ArrayList;

public class Player {
	Vec2 vel = new Vec2(0,0);
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	int health;
	Point pos;
	int shape;
	boolean dead;

	public Player(Point playerPos, int shape, Shape... shapes) {
		super();
		for(int i = 0; i < shapes.length; i++) {
		this.shapes.add(shapes[i]);
		}
		this.shape = shape;
		this.pos = playerPos;
		changeShape();
		this.vel = new Vec2(0, 0);
	}

	public void draw(Graphics g) {
		shapes.get(shape).draw(g);
	}

	public void update(Point mPos, boolean[] keys, boolean[] keysHeld) {
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
		if(keys[37] && !keysHeld[37]) {
			if(shape == 0) {
				shape = shapes.size()-1;
			}
			else {
				shape --;
			}
			changeShape();
		}
		if(keys[39] && !keysHeld[39]) {
			if(shape == shapes.size() - 1) {
				shape = 0;
			}
			else {
				shape ++;
			}
			changeShape();
		}
		if(keys[32] && !keysHeld[32]) {
			shapes.get(shape).rotate();
			changeShape();
		}
		
		
	}

	private void changeShape() {
		shapes.get(shape).setPosition(pos);
	}

	private void move() {
		pos.add(vel);
		shapes.get(shape).move(vel);

	}
}
