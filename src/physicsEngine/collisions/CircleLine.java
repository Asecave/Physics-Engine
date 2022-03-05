package physicsEngine.collisions;

import java.awt.Color;
import java.awt.Graphics2D;

import physicsEngine.Circle;
import physicsEngine.Line;

public class CircleLine extends Collision {

	Circle c;
	Line l;

	Circle fake;
	
	CircleCircle cc;

	public CircleLine(Circle c, Line l) {

		float fLineX1 = l.x2 - l.posX;
		float fLineY1 = l.y2 - l.posY;

		float fLineX2 = c.posX - l.posX;
		float fLineY2 = c.posY - l.posY;

		float flLength = fLineX1 * fLineX1 + fLineY1 * fLineY1;

		float t = Math.max(0, Math.min(flLength, (fLineX1 * fLineX2 + fLineY1 * fLineY2))) / flLength;

		float fClosestPointX = l.posX + t * fLineX1;
		float fClosestPointY = l.posY + t * fLineY1;

		float distance = (float) Math.sqrt((c.posX - fClosestPointX) * (c.posX - fClosestPointX)
				+ (c.posY - fClosestPointY) * (c.posY - fClosestPointY));

		if (distance <= (c.getRadius() + l.getRadius())) {

			Circle fake = new Circle();
			fake.setRadius(l.getRadius());
			fake.mass = c.mass * 0.8f;
			fake.posX = fClosestPointX;
			fake.posY = fClosestPointY;
			fake.setEditable(false);
			fake.setCollidable(true);
			fake.setFixed(true);
			
			this.fake = fake;
			
			cc = new CircleCircle(c, fake);

			c = cc.getCollidedCircle1();

		}

		this.c = c;
		this.l = l;
		
	}
	
	public Circle getCollidedCircle() {
		return c;
	}

	public Line getCollidedLine() {
		return l;
	}

	@Override
	public void renderDebug(Graphics2D g2d) {
		if (cc != null)
			cc.renderDebug(g2d);
		if (fake != null) {
			g2d.setColor(Color.RED);
			g2d.drawOval((int) fake.posX - fake.getRadius(), (int) fake.posY - fake.getRadius(), fake.getRadius() * 2,
					fake.getRadius() * 2);
		}
	}
}
