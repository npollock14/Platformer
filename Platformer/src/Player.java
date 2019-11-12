import java.awt.Graphics;
import java.util.ArrayList;

public class Player {
	ArrayList<Shape[]> shapes = new ArrayList<Shape[]>();
	Point pos;
	int shape = 0;
	int rotation = 0;
	boolean dead;
	Shape current;

	public Player(Point playerPos, int shape, Shape[]... shapes) {
		super();
		for(int i = 0; i < shapes.length; i++) {
				this.shapes.add(shapes[i]);
		}
		this.shape = shape;
		this.pos = playerPos;
		changeShape();
	}

	public void draw(Graphics g) {
		shapes.get(shape)[rotation].draw(g);
	}

	public void update(Point mPos, boolean[] keys, boolean[] keysHeld) {
		if(!dead) {
		
		if(keys[37] && !keysHeld[37]) {
			if(shape == 0) {
				shape = shapes.size()-1;
			}
			else {
				shape --;
			}
			rotation = 0;
			changeShape();
		}
		if(keys[39] && !keysHeld[39]) {
			if(shape == shapes.size() - 1) {
				shape = 0;
			}
			else {
				shape ++;
			}
			rotation = 0;
			changeShape();
		}
		if(keys[32] && !keysHeld[32]) {
			if(rotation < shapes.get(shape).length-1) {
				rotation ++;
			}else {
				rotation = 0;
			}
			changeShape();
		}
		}
		if(dead) {
			
			//do dead stuff
			
		}else {
			move(mPos);
		}
		
		
	}

	public void changeShape() {
		
		shapes.get(shape)[rotation].setPosition(pos);
		
	}

	private void move(Point mPos) {
		try {
		pos = mPos;
		pos.x = ((int)(pos.x / 50) * 50);
		pos.y = ((int)(pos.y / 50) * 50);
		System.out.println(pos.x + " " + pos.y);
		shapes.get(shape)[rotation].setPosition(mPos);
		int newX = ((int)(shapes.get(shape)[rotation].pos.x / 50) * 50);
		int newY = ((int)(shapes.get(shape)[rotation].pos.y / 50) * 50);
		shapes.get(shape)[rotation].setPosition(new Point(newX,newY));
		}catch (Exception e) {
			
		}
		
		
	}
	
}
