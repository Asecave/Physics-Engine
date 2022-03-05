package physicsEngine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import ACInput.Listener;
import ACWindow.Frame;


public class Simulation {

	private ShapeEnvironment se;

	private EditMenu edit;

	static int width, height;

	static Dimension default_positions;

	static boolean debugMode = false;

	static float gravityFactor = 0.1f;

	static float friction = 0.015f;

	static int simulationUpdates = 10;

	static Point mousePosition = new Point();

	static boolean editable = false;

	static Color backgroundColor = Color.BLACK;

	static Color menuColor = Color.WHITE;

	static int pixelSize = 1;

	static int thickness = 1;

	/**
	 * Returns the current size (width and height) of the simulated pixels.
	 */
	public int getPixelSize() {
		return pixelSize;
	}

	/**
	 * Sets a new pixel size of the simulated pixels. Increasing makes the play-area smaller.
	 */
	public void setPixelSize(int pixelSize) {
		width = Simulation.pixelSize * width / pixelSize;
		height = Simulation.pixelSize * height / pixelSize;
		Simulation.pixelSize = pixelSize;
		se.changeImage(width, height);
		edit.changeImage();
	}

	/**
	 * Returns the current mouse cursor position on the window. 
	 */
	public Point getMousePosition() {
		return mousePosition;
	}

	/**
	 * Returns the simulation width. This depends on the pixel size.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the simulation height. This depends on the pixel size.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the edit menu color. If the edit-menu is disabled, this method won't affect anything.
	 */
	public Color getMenuColor() {
		return menuColor;
	}

	/**
	 * Sets a new edit menu color. If the edit-menu is disabled, this method won't affect anything.
	 */
	public void setMenuColor(Color menuColor) {
		Simulation.menuColor = menuColor;
	}

	/**
	 * Returns true, if the edit-menu is enabled. Otherwise it returns false.
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Setting this option to false disables the edit-menu.
	 */
	public void setEditable(boolean editable) {
		Simulation.editable = editable;
	}

	/**
	 * Returns the simulation steps per frame. 
	 * If this option is set to 1, the Simulation will only test once per frame, 
	 * if there is a collision. Increasing this option makes the Simulation more accurate, but it will take more
	 * calculations, so this may slows the simulation down.
	 */
	public int getSimulationUpdates() {
		return simulationUpdates;
	}

	/**
	 * Sets the simulation steps per frame. 
	 * If this option is set to 1, the Simulation will only test once per frame, 
	 * if there is a collision. Increasing this option makes the Simulation more accurate, but it will take more
	 * calculations, so this may slows the simulation down.
	 */
	public void setSimulationUpdates(int simulationUpdates) {
		Simulation.simulationUpdates = simulationUpdates;
	}

	/**
	 * In debug mode you can see the collisions. Every collision is marked by a red line.
	 */
	public void setDebugMode(boolean b) {
		debugMode = b;
	}

	/**
	 * Returns true, if you are currently in debug mode. Otherwise it returns false.
	 */
	public boolean isDebugMode() {
		return debugMode;
	}

	/**
	 * Returns the current gravity factor. The attraction to the floor depends on this option.
	 */
	public float getGravityFactor() {
		return gravityFactor;
	}

	/**
	 * Sets the gravity factor. The attraction to the floor depends on this option.
	 * The gravity factor is set to 0.1 in default;
	 */
	public void setGravityFactor(float gravity) {
		Simulation.gravityFactor = gravity;
	}

	/**
	 * Sets the Background color to a new color.
	 */
	public void setBackgroundColor(Color c) {
		backgroundColor = c;
	}

	/**
	 * Returns the current background color.
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * Sets the line thickness.
	 * The thickness is just a display option. It will not affect the shapes.
	 */
	public void setThickness(int thickness) {
		Simulation.thickness = thickness;
		se.setStroke(new BasicStroke(thickness));
	}

	/**
	 * Returns the current display thickness.
	 */
	public int getThickness() {
		return thickness;
	}

	/**
	 * Sets whether the shapes can go through the window borders.
	 * This option overrides the wall collision option of every shape.
	 */
	public void setWallCollisionForAll(boolean b) {
		se.setWallCollisionForAll(b);
	}
	
	/**
	 * Sets whether the shapes can be affected by gravity.
	 * This option overrides the gravity option of every shape.
	 */
	public void setGravityForAll(boolean b) {
		se.setGravityForAll(b);
	}

	/**
	 * Adds one or more shapes to the simulation.
	 */
	public void addShapes(Shape... shapes) {
		se.addShapes(shapes);
	}

	/**
	 * Removes a shape by its id.
	 */
	public void removeShapes(String... ids) {
		se.removeShapes(ids);
	}

	/**
	 * Returns an array of all the shapes in the simulation.
	 */
	public Shape[] getShapeArray() {
		return ShapeEnvironment.shapes;
	}

	/**
	 * Returns the background-friction value.
	 */
	public float getFriction() {
		return friction;
	}

	/**
	 * Sets the background-friction.
	 * The background-friction is set to 0.015 in default.
	 */
	public void setFriction(float friction) {
		Simulation.friction = friction;
	}
	
	/**
	 * Returns the distance between two shapes.
	 */
	public float distance(Shape s1, Shape s2) {
		return se.distance(s1, s2);
	}

	/**
	 * Returns the distance between two points.
	 */
	public float distance(float x1, float y1, float x2, float y2) {
		return se.distance(x1, y1, x2, y2);
	}

	long oldTime = 0;

	public Simulation(int width, int height, int pixelSize, int fps) {

		Simulation.pixelSize = pixelSize;
		Simulation.width = width / pixelSize;
		Simulation.height = height / pixelSize;
		default_positions = new Dimension((width / pixelSize) / 2, (height / pixelSize) / 2);

		se = new ShapeEnvironment(width / pixelSize, height / pixelSize);

		edit = new EditMenu();

		Frame frame = new Frame(width, height, fps) {
			@Override
			public void frameLoop(Graphics2D g2d) {
				mousePosition.x = mouseOnWindow.x / Simulation.pixelSize;
				mousePosition.y = mouseOnWindow.y / Simulation.pixelSize;
				loop();
				loop(g2d);
				se.tick(edit);
				edit.repaint();
				g2d.drawImage(se.getImage(), 0, 0, width, height, null);
				g2d.drawImage(edit.render(getSimulation()), 0, 0, width, height, null);
				se.repaint();
			}

			@Override
			public void titleUpdate() {
				setAdditionalTitle(ShapeEnvironment.shapes.length + " Objects");
			}
		};

		frame.setShowInfoInTitle(true);

		Listener listener = new Listener(frame);

		frame.addListener(listener);

	}

	private Simulation getSimulation() {
		return this;
	}

	public void loop() {
	}

	public void loop(Graphics2D g2d) {
	}
}
