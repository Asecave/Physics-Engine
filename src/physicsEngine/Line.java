package physicsEngine;

import java.awt.Graphics2D;

public class Line extends Shape {

	public float x2 = posX + 20;
	public float y2 = posY;
	
	public int r = 5;
	
	public int boundTo;
	
	public Line() {
		setGravity(false);
	}
	
	/**
	 * Sets the end position of the line on the simulation.
	 */
	public void setEndPosition(int x, int y) {
		x2 = x;
		y2 = y;
	}
	
	/**
	 * Sets the line radius.
	 * The radius of the line corresponds to half the diameter.  
	 */
	public void setRadius(int r) {
		this.r = r;
	}
	
	/**
	 * Returns the line radius.
	 */
	public int getRadius() {
		return r;
	}
	
	@Override
	void tick() {
		
	}

	@Override
	void render(Graphics2D g2d) {
		g2d.setColor(shapeColor);
		g2d.drawOval((int) posX - r, (int) posY - r, r * 2, r * 2);
		g2d.drawOval((int) x2 - r, (int) y2 - r, r * 2, r * 2);
		
		float nx = -(y2 - posY);
		float ny = (x2 - posX);
		float d = (float) Math.sqrt(nx * nx + ny * ny);
		nx /= d;
		ny /= d;
		g2d.drawLine((int)(posX + nx * r), (int)(posY + ny * r), (int)(x2 + nx * r), (int)(y2 + ny * r));
		g2d.drawLine((int)(posX - nx * r), (int)(posY - ny * r), (int)(x2 - nx * r), (int)(y2 - ny * r));
	}

	@Override
	float mass() {
		return 1;
	}
}
