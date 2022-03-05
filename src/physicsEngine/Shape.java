package physicsEngine;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape {
	
	public static class Defaults {
		static boolean collidable = true;
		static boolean gravity = true;
		static boolean fixed = false;
		static boolean wallCollision = true;
		static boolean editable = true;
		static Color color = Color.WHITE;
	}

	public float posX = Simulation.default_positions.width;
	public float posY = Simulation.default_positions.height;

	public float velocityX = 0f;
	public float velocityY = 0f;

	public float mass = 1f;

	String id;

	private boolean collidable = Defaults.collidable;
	private boolean gravity = Defaults.gravity;
	private boolean fixed = Defaults.fixed;
	private boolean wallCollision = Defaults.wallCollision;
	private boolean editable = Defaults.editable;

	Color shapeColor = Defaults.color;

	void setID(String id) {
		this.id = id;
	}

	/**
	 * Returns the ID of this shape.
	 */
	public String getID() {
		return id;
	}

	/**
	 * Returns true when the edit-menu can be opened on this shape. Otherwise it returns false.
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Sets whether the shape can be modified. If this is set to true, the edit-menu can be opened on this shape,
	 * when the simulation is editable.
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * When this is set to true, this shape can collide with the window borders. Otherwise the shape goes through 
	 * the window borders.
	 */
	public void setWallCollision(boolean b) {
		wallCollision = b;
	}

	/**
	 * Returns whether this shape can collide with the window borders.
	 */
	public boolean isWallCollision() {
		return wallCollision;
	}

	/**
	 * Sets the color of this shape.
	 */
	public void setColor(Color c) {
		shapeColor = c;
	}

	/**
	 * Returns the color of this shape.
	 */
	public Color getColor() {
		return shapeColor;
	}

	/**
	 * Sets the simulation position of this shape. This position relies on the pixel size.
	 */
	public void setLocation(float x, float y) {
		posX = x;
		posY = y;
	}

	/**
	 * Returns the X-coordinate in the simulation.
	 */
	public float getLocationX() {
		return posX;
	}

	/**
	 * Returns the Y-coordinate in the simulation.
	 */
	public float getLocationY() {
		return posY;
	}

	/**
	 * If this option is set to true, the shape can collide with other shapes. Otherwise it can't.
	 */
	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	/**
	 * Returns whether this shape can collide with other shapes.
	 */
	public boolean isCollidable() {
		return collidable;
	}

	/**
	 * Sets whether this shape should be affected by gravity.
	 */
	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}

	/**
	 * Returns whether this shape is affected by gravity.
	 */
	public boolean isGravity() {
		return gravity;
	}

	/**
	 * When this option is set to true, this shape cannot move except by the mouse. This shapes velocity can't be 
	 * increased by other shapes which are colliding.
	 */
	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	/**
	 * Returns whether this shape is fixed.
	 */
	public boolean isFixed() {
		return fixed;
	}

	void calculateVelocity(int updates) {

		if (gravity && !fixed) {
			velocityX -= (velocityX * Simulation.friction) / updates;
			velocityY -= ((velocityY * Simulation.friction) - Simulation.gravityFactor) / updates;
		} else {
			velocityX -= (velocityX * Simulation.friction) / updates;
			velocityY -= (velocityY * Simulation.friction) / updates;
		}

		mass = mass();

		posX += velocityX / updates;
		posY += velocityY / updates;

		if (velocityX > 300f || velocityY > 300f) {
			velocityX = 0;
			velocityY = 0;
			posX = (float) (Math.random() * Simulation.width);
			posY = (float) (Math.random() * Simulation.height);
		}
	}

	/**
	 * This option changes the weight of the shape.
	 */
	public void setMass(float mass) {
		this.mass = mass;
	}

	/**
	 * Returns the weight of this shape.
	 */
	public float getMass() {
		return mass;
	}

	abstract void tick();

	abstract void render(Graphics2D g2d);

	abstract float mass();
}
