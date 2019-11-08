import java.util.ArrayList;

public class Obstacle {
	Point pos;
	Vec2 vel;
	Shape s;
	public Obstacle(Point pos, Vec2 vel, ArrayList<Shape[]> shapes) {
		super();
		this.pos = pos;
		this.vel = vel;
		int shape = (int) (Math.random() * shapes.size());
		Shape design = shapes.get(shape)[(int) (Math.random() * shapes.get(shape).length)]; //makes it a new random shape
		s = getInverse(design, 10,10);
	}
	private Shape getInverse(Shape design, int w, int h) {
		ArrayList<Integer> invertedShape = new ArrayList<Integer>();
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j ++) {
				
			}
		}
	}
	
	
	
	
}
