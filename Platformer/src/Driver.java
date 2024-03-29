import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel
		implements ActionListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	private static final long serialVersionUID = 1L;
	int screenWidth = 1000;
	int screenHeight = 1000;
	boolean[] keys = new boolean[300];
	boolean[] keysToggled = new boolean[300];
	boolean[] keysHeld = new boolean[300];
	boolean[] mouse = new boolean[200];
	int frame = 0;
	double startTime = 0;
	Point mPos;
	boolean paused = false;
	Player p;
	float shapeWidth = 50.0f;
	float shapeHeight = 50.0f;
	float obstacleWidth = 50.0f;
	float feather = 1f;
	
	Shape background; 

	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	ArrayList<Obstacle> activeObstacles = new ArrayList<Obstacle>();
	
	ArrayList<ParticleSystem> pss = new ArrayList<ParticleSystem>();

	int enemysFaced = 0;
	

	// ============== end of settings ==================

	public void paint(Graphics g) {
		g.drawRect(0, 0, screenWidth, screenHeight);
		super.paintComponent(g);
		background.draw(g);
		
		p.draw(g);
		for (Obstacle o : activeObstacles) {
			o.draw(g);
		}
		for(ParticleSystem ps : pss) {
			ps.draw(g);
		}
		g.drawString("Player is Alive? : " + !p.dead, 0, 20);
	}

	public void update() throws InterruptedException {
		p.update(getMousePos(), keys, keysHeld);
		if (activeObstacles.size() < 1) {
			enemysFaced++;
			Obstacle o = getNewObstacle(obstacles);

			activeObstacles.add(o);
			p.shape = activeObstacles.get(0).shapeID;
			p.rotation = 0;
			p.changeShape();
		}
		
		for(ParticleSystem ps : pss) {
			ps.update(.1f);
		}

		for (int i = 0; i < activeObstacles.size(); i++) {
			if (activeObstacles.get(i).destroyed) {
				activeObstacles.remove(i);
				continue;
			}
			activeObstacles.get(i).update(p, pss);

		}

		updateKeysHeld();
	}

	private Obstacle getNewObstacle(ArrayList<Obstacle> templates) {
		Obstacle o = templates.get((int) (Math.random() * templates.size()));
		return new Obstacle(new Point(0, -5 * 50), new Vec2(0, 2 + (enemysFaced * .2)), o.s, o.shapeID);

	}

	private void updateKeysHeld() {
		for (int i = 0; i < keysHeld.length; i++) {
			if (keys[i]) {
				keysHeld[i] = true;
			} else {
				keysHeld[i] = false;
			}

		}
	}

	private void init() {
		BufferedImage[] redTiles = {Misc.loadImage("/RedTile1.png"), Misc.loadImage("/RedTile2.png"), Misc.loadImage("/RedTile3.png")};
		BufferedImage[] backgroundTexture = {Misc.loadImage("/BasicBackgroundBlock.png")};//, Misc.loadImage("/BasicBackgroundBlock2.png"), Misc.loadImage("/BasicBackgroundBlock3.png")};
		background = new Shape(25, 25, (float) obstacleWidth,backgroundTexture);
		
		Shape[] squares = { new Shape(shapeWidth, shapeHeight,feather,redTiles, 0, 0, 1, 0, 1, 1, 0, 1) };
		Shape[] lines = { new Shape(shapeWidth, shapeHeight,feather,redTiles, 0, 0, 0, 1, 0, 2, 0, 3),
				new Shape(shapeWidth, shapeHeight,feather,redTiles, 0, 0, 1, 0, 2, 0, 3, 0) };
		Shape[] lShapes = { new Shape(shapeWidth, shapeHeight,feather,redTiles, 0, 0, 1, 0, 2, 0, 2, 1),
				new Shape(shapeWidth, shapeHeight,feather,redTiles, -1, -1, -1, -2, -1, -3, 0, -3),
				new Shape(shapeWidth, shapeHeight,feather,redTiles, -1, 0, -2, 0, -3, 0, -3, -1),
				new Shape(shapeWidth, shapeHeight,feather,redTiles, 0, 0, 0, 1, 0, 2, -1, 2) };
		Shape[] tShapes = { new Shape(shapeWidth, shapeHeight,feather,redTiles, 0, 0, 1, 0, 2, 0, 1, -1),
				new Shape(shapeWidth, shapeHeight,feather,redTiles, 0, 0, 1, -1, 1, 0, 1, 1),
				new Shape(shapeWidth, shapeHeight,feather,redTiles, 0, 0, 1, 0, 2, 0, 1, 1),
				new Shape(shapeWidth, shapeHeight,feather,redTiles, 1, 0, 0, -1, 0, 0, 0, 1) };
		
		Shape lineCutOut = new Shape(25, 5, (float) obstacleWidth,redTiles, 13, 4, 13, 3, 13, 2, 13, 1);
		Shape lineCutOut2 = new Shape(25, 5, (float) obstacleWidth,redTiles, 2, 4, 3, 4, 4, 4, 5, 4);
		
		Shape squareCutOut = new Shape(25, 5, (float) obstacleWidth,redTiles, 5, 4, 6, 4, 5, 3, 6, 3);
		
		Shape lCutOut = new Shape(25, 5, (float) obstacleWidth,redTiles, 3, 4, 3, 3, 3, 2, 2, 4);
		Shape lCutOut2 = new Shape(25, 5, (float) obstacleWidth,redTiles, 6, 4, 6, 3, 6, 2, 5, 4);
		
		Shape tCutOut = new Shape(25, 5, (float) obstacleWidth,redTiles, 16, 4, 15, 4, 17, 4, 16, 3);
		Shape tCutOut2 = new Shape(25, 5, (float) obstacleWidth,redTiles, 10, 4, 9, 4, 11, 4, 10, 3);
		
		p = new Player(new Point(500, 600), 0, squares, lines, lShapes, tShapes);

		
		
		obstacles.add(new Obstacle(new Point(0, 0), new Vec2(0, 3), lineCutOut, 1));
		obstacles.add(new Obstacle(new Point(0, 0), new Vec2(0, 3), lineCutOut2, 1));
		
		obstacles.add(new Obstacle(new Point(0, 0), new Vec2(0, 3), squareCutOut, 0));
		
		obstacles.add(new Obstacle(new Point(0, 0), new Vec2(0, 0), lCutOut, 2));
		obstacles.add(new Obstacle(new Point(0, 0), new Vec2(0, 0), lCutOut2, 2));
		
		obstacles.add(new Obstacle(new Point(0, 0), new Vec2(0, 0), tCutOut, 3));
		obstacles.add(new Obstacle(new Point(0, 0), new Vec2(0, 0), tCutOut2, 3));

	}

	private int randSign() {
		return (Math.random() > .5 ? 1 : -1);
	}

	public Point getMousePos() {
		try {
			return new Point(this.getMousePosition().x, this.getMousePosition().y);
		} catch (Exception e) {
			return mPos;
		}
	}

	// ==================code above ===========================

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			update();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}

	public static void main(String[] arg) {
		@SuppressWarnings("unused")
		Driver d = new Driver();
	}

	public Driver() {

		init();

		JFrame f = new JFrame();
		f.setTitle("Pong... Again");
		f.setSize(screenWidth, screenHeight);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		f.addMouseWheelListener(this);
		f.addMouseListener(this);

		f.add(this);

		t = new Timer(15, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {

		keys[e.getKeyCode()] = false;

		if (keysToggled[e.getKeyCode()]) {
			keysToggled[e.getKeyCode()] = false;
		} else {
			keysToggled[e.getKeyCode()] = true;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouse[e.getButton()] = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouse[e.getButton()] = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse[e.getButton()] = true;

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}

class Point {
	double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distanceTo(Point p2) {
		return Math.sqrt((this.x - p2.x) * (this.x - p2.x) + (this.y - p2.y) * (this.y - p2.y));
	}

	public double angleTo(Point p2) {
		try {
			return Math.atan2(this.y - p2.y, this.x - p2.x);
		} catch (Exception e) {

		}
		return 0;
	}

	public boolean inside(Rect r, float... feather) {
		if (feather.length > 1) {
			throw new IllegalArgumentException();
		} else if (feather.length == 1) {
			return (x > r.pos.x + feather[0] && x + feather[0] < r.pos.x + r.w && y > r.pos.y + feather[0]
					&& y + feather[0] < r.pos.y + r.h);

		} else {
			return (x >= r.pos.x && x <= r.pos.x + r.w && y >= r.pos.y && y <= r.pos.y + r.h);
		}

	}

	public void add(Vec2 v) {
		this.x += v.x;
		this.y += v.y;
	}

	public boolean isSame(Point p) {
		return p.x == this.x && p.y == this.y;
	}
}

class Vec2 {
	double x, y;

	public Vec2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vec2() {
		this.x = Math.random() * 2 - 1;
		this.y = Math.random() * 2 - 1;
		double mag = this.getMagnitude();
		this.x /= mag;
		this.y /= mag;

	}

	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
	}

	public double getAngle() {
		try {
			return Math.atan2(this.y, this.x);
		} catch (Exception e) {

		}
		return 0;
	}

}
