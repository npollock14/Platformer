public class Camera { // camera class
	double xOff, yOff, screenW, screenH;
	double scale;
	Point center;
	float scaleNotches = 0;
	float targetScale;

	public Camera(int xOff, int yOff, double scale, int screenW, int screenH) {
		super();
		this.xOff = xOff;
		this.yOff = yOff;
		this.scale = scale;
		this.screenW = screenW;
		this.screenH = screenH;
		center = new Point(screenW / 2, screenH / 2);
	}

	public void focus(Point p) { // focus the camera on a point (center screen on that point)
		xOff = screenW / 2 - p.x;
		yOff = screenH / 2 - p.y;
	}

	public void changeScale(float notches) { // zooming is exponential
		scaleNotches += notches;
		scale = Math.pow(2, scaleNotches);
	}

	// functions to convert screen coords to map coords and vice versa

	public int toXScreen(double x) {
		double dx = ((x + xOff - center.x) * scale);
		return (int) (center.x + dx);
	}

	public int toYScreen(int y) {
		double dy = ((y + yOff - center.y) * scale);
		return (int) (center.y + dy);
	}

	public double toXMap(int x) {
		return ((x - center.x) / scale) + center.x - xOff;
	}

	public double toYMap(int y) {
		return ((y - center.y) / scale) + center.y - yOff;
	}
}
