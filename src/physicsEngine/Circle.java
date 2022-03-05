package physicsEngine;

import java.awt.Graphics2D;

public class Circle extends Shape {

	int r = 10;
	
	private boolean fillCircle = false;
	
	/**
	 * Sets the radius of this circle.
	 */
	public void setRadius(int radius) {
		this.r = radius;
		this.mass = mass();
	}
	
	/**
	 * Returns the current radius of this circle.
	 */
	public int getRadius() {
		return r;
	}

	@Override
	void tick() {
		if (isWallCollision()) {
			if (posX + r > Simulation.width) {
				posX = Simulation.width - r;
				velocityX = velocityX * -0.8f;
			}
			if (posX - r < 0) {
				posX = r;
				velocityX = velocityX * -0.8f;
			}
			if (posY + r > Simulation.height) {
				posY = Simulation.height - r;
				velocityY = velocityY * -0.8f;
			}
			if (posY - r < 0) {
				posY = r;
				velocityY = velocityY * -0.8f;
			}
		}else {
			if (posX > Simulation.width) {
				posX = 0;
			}
			if (posX < 0) {
				posX = Simulation.width;
			}
			if (posY > Simulation.height) {
				posY = 0;
			}
			if (posY < 0) {
				posY = Simulation.height;
			}
		}
	}
	
	@Override
	void render(Graphics2D g2d) {
		g2d.setColor(shapeColor);
		if (fillCircle) {
			g2d.fillOval((int) posX - r, (int) posY - r, r * 2, r * 2);
			g2d.drawOval((int) posX - r, (int) posY - r, r * 2, r * 2);
		}else {
			g2d.drawOval((int) posX - r, (int) posY - r, r * 2, r * 2);
		}
	}

	/**
	 * Returns if this circle gets drawn as a filled circle.
	 */
	public boolean isFillCircle() {
		return fillCircle;
	}
	
	/**
	 * When this is set to true, this circle gets drawn as a filled circle.
	 */
	public void setFillCircle(boolean fillCircle) {
		this.fillCircle = fillCircle;
	}

	@Override
	float mass() {
		return (float) (r * r * Math.PI) * 0.05f;
	}
}
