package physicsEngine.collisions;

import java.awt.Graphics2D;

import physicsEngine.Shape;

public abstract class Collision {
	
	public float distance(Shape s1, Shape s2) {
		float dist = (float) Math.sqrt((s1.posX - s2.posX) * (s1.posX - s2.posX) + (s1.posY - s2.posY) * (s1.posY - s2.posY));
		if (dist == 0)
			return 0.1f;
		return dist;
	}
	
	public abstract void renderDebug(Graphics2D g2d);
}
