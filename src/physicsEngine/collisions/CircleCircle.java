package physicsEngine.collisions;

import java.awt.Color;
import java.awt.Graphics2D;

import physicsEngine.Circle;

public class CircleCircle extends Collision {

	private Circle c1;
	private Circle c2;

	private boolean collided = false;

	public CircleCircle(Circle c1, Circle c2) {

		if (doCirclesOverlap(c1, c2)) {

			collided = true;

			float distance = distance(c1, c2);

			float overlap;

			if (c1.isFixed() || c2.isFixed()) {
				overlap = (distance - c1.getRadius() - c2.getRadius());
			} else {
				overlap = 0.5f * (distance - c1.getRadius() - c2.getRadius());
			}

			if (!c1.isFixed()) {
				c1.posX -= overlap * (c1.posX - c2.posX) / distance;
				c1.posY -= overlap * (c1.posY - c2.posY) / distance;
			}

			if (!c2.isFixed()) {
				c2.posX += overlap * (c1.posX - c2.posX) / distance;
				c2.posY += overlap * (c1.posY - c2.posY) / distance;
			}

			if (c1.isFixed() && !c2.isFixed()) {

				c1.mass = c2.mass;

				c1.velocityX = -c2.velocityX * 0.8f;
				c1.velocityY = -c2.velocityY * 0.8f;

			} else if (c2.isFixed() && !c1.isFixed()) {

				c2.mass = c1.mass;

				c2.velocityX = -c1.velocityX * 0.8f;
				c2.velocityY = -c1.velocityY * 0.8f;

			}
			
			if (!(c1.isFixed() && c2.isFixed())) {
				
				float nx = (c2.posX - c1.posX) / distance;
				float ny = (c2.posY - c1.posY) / distance;

				float tx = -ny;
				float ty = nx;

				float dpTan1 = c1.velocityX * tx + c1.velocityY * ty;
				float dpTan2 = c2.velocityX * tx + c2.velocityY * ty;

				float dpNorm1 = c1.velocityX * nx + c1.velocityY * ny;
				float dpNorm2 = c2.velocityX * nx + c2.velocityY * ny;

				float m1 = (dpNorm1 * (c1.mass - c2.mass) + c2.mass * dpNorm2) / (c1.mass + c2.mass);
				float m2 = (dpNorm2 * (c2.mass - c1.mass) + c1.mass * dpNorm1) / (c1.mass + c2.mass);

				c1.velocityX = tx * dpTan1 + nx * m1;
				c1.velocityY = ty * dpTan1 + ny * m1;
				c2.velocityX = tx * dpTan2 + nx * m2;
				c2.velocityY = ty * dpTan2 + ny * m2;
				
				if (c1.isFixed()) {
					c1.velocityX = 0f;
					c1.velocityY = 0f;
				}
				if (c2.isFixed()) {
					c2.velocityX = 0f;
					c2.velocityY = 0f;
				}
			}
		}

		this.c1 = c1;
		this.c2 = c2;

	}

	public Circle getCollidedCircle1() {
		return c1;
	}

	public Circle getCollidedCircle2() {
		return c2;
	}

	private boolean doCirclesOverlap(Circle c1, Circle c2) {
		return Math.abs((c1.posX - c2.posX) * (c1.posX - c2.posX)
				+ (c1.posY - c2.posY) * (c1.posY - c2.posY)) <= (c1.getRadius() + c2.getRadius())
						* (c1.getRadius() + c2.getRadius());
	}

	@Override
	public void renderDebug(Graphics2D g2d) {
		if (collided) {
			g2d.setColor(Color.RED);
			g2d.drawLine((int) c1.posX, (int) c1.posY, (int) c2.posX, (int) c2.posY);
		}
	}

}
