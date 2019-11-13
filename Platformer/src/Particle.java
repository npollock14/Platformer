import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Particle {
Point pos;
BufferedImage image;
Vec2 vel;
int w, h;
double angle;
double omega;
public Particle(Point pos, Vec2 vel, double angle,double omega, int w, int h, String path) {
	super();
	this.pos = pos;
	this.image = Misc.loadImage(path);
	this.vel = vel;
	this.w = w;
	this.h = h;
	this.angle = angle;
	this.omega = omega;
}
public Particle(Point pos, Vec2 vel, double angle,double omega, int w, int h, BufferedImage image) {
	super();
	this.pos = pos;
	this.image = image;
	this.vel = vel;
	this.w = w;
	this.h = h;
	this.angle = angle;
	this.omega = omega;
}
public void draw(Graphics g) {
	Graphics2D g2d = (Graphics2D)g;
	AffineTransform backup = g2d.getTransform();
    //rx is the x coordinate for rotation, ry is the y coordinate for rotation, and angle
    //is the angle to rotate the image. If you want to rotate around the center of an image,
    //use the image's center x and y coordinates for rx and ry.
    AffineTransform a = AffineTransform.getRotateInstance(angle, pos.x + w/2, pos.y + h/2);
    //Set our Graphics2D object to the transform
    g2d.setTransform(a);
    //Draw our image like normal
    g2d.drawImage(image, (int)pos.x, (int)pos.y,w,h, null);
    //Reset our graphics object so we can draw with it again.
    g2d.setTransform(backup);
}
public void update(double g) {
	vel.y += g;
	pos.add(vel);
	angle += omega;
}


}
