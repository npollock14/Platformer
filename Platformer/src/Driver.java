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
	int pScore = 0, eScore = 0;
	double startTime = 0;
	Point mPos;
	boolean paused = false;
	Player p;
	Shape square;
	Shape line;

	// ============== end of settings ==================

	public void paint(Graphics g) {
		super.paintComponent(g);
		p.draw(g);
	}

	public void update() throws InterruptedException {
		p.update(getMousePos(), keys, keysHeld);
		
		
		for(int i = 0; i<keysHeld.length; i++) {
			if(keys[i]) {
				keysHeld[i] = true;
			}else {
				keysHeld[i] = false;
			}
			
		}
	}

	private void init() {
		int sW = 30;
		Rect s1 = new Rect(0, 0, sW, sW);
		Rect s2 = new Rect(sW, 0, sW, sW);
		Rect s3 = new Rect(sW, sW, sW, sW);
		Rect s4 = new Rect(0, sW, sW, sW);
		ArrayList<Rect> sq = new ArrayList<Rect>();
		sq.add(s1);
		sq.add(s2);
		sq.add(s3);
		sq.add(s4);
		square = new Shape(sq, new Point(sW,sW));
		
		int lW = 30;
		Rect l1 = new Rect(0, 0, lW, lW);
		Rect l2 = new Rect(0, lW, lW, lW);
		Rect l3 = new Rect(0, lW*2, lW, lW);
		Rect l4 = new Rect(0, lW*3, lW, lW);
		ArrayList<Rect> lineBounds = new ArrayList<Rect>();
		lineBounds.add(l1);
		lineBounds.add(l2);
		lineBounds.add(l3);
		lineBounds.add(l4);
		line = new Shape(lineBounds, new Point(15,60));
		
		
		
		

		p = new Player(new Point(500, 600), 0, square, line);
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

	public boolean inside(Rect r) {
		return (x > r.pos.x && x < r.pos.x + r.w && y > r.pos.y && y < r.pos.y + r.h);
	}

	public void add(Vec2 v) {
		this.x += v.x;
		this.y += v.y;
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
