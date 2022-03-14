package simulation;

import java.awt.Color;
import java.awt.event.KeyEvent;

import physicsEngine.Circle;
import physicsEngine.Line;
import physicsEngine.Simulation;

public class Launcher extends Simulation {

	private static final long serialVersionUID = 1L;

	public Launcher() {

		super(1600, 900, 4, 90);

		this.setThickness(1);
		this.setWallCollisionForAll(true);
		this.setDebugMode(false);
		this.setEditable(true);

		for (int i = 0; i < 30; i++) {
			Circle c = new Circle(this);
			c.setRadius((int)(Math.random() * 10) + 2);
			c.setGravity(true);
			c.setCollidable(true);
			c.setFixed(false);
			c.setColor(Color.WHITE);
			c.setLocation((int)(Math.random() * (getWidth())), (int)(Math.random() * getHeight()));
			
			addShapes(c);
		}
		
		Circle c = new Circle(this);
		c.setRadius((int)(Math.random() * 10) + 2);
		c.setGravity(false);
		c.setCollidable(true);
		c.setFixed(true);
		c.setColor(Color.GRAY);
		c.setLocation((int)(Math.random() * (getWidth())), (int)(Math.random() * getHeight()));
		
		addShapes(c);
	}

	@Override
	public void loop() {
		
	}
	
	@Override
	public void onKeyEvent(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Line l = new Line();
			l.setGravity(false);
			l.setFixed(true);
			l.setRadius(5);
			l.setColor(Color.GRAY);
			
			addShapes(l);
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Circle c = new Circle(this);
			c.setRadius(10);
			c.setFixed(true);
			c.setEditable(false);
			
			addShapes(c);
		}
	}

	public static void main(String[] args) {
		new Launcher();
	}
}
