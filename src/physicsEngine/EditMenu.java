package physicsEngine;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ACInput.Listener;

public class EditMenu {

	private Shape s;

	private boolean editSimulation = false;

	private int posX = 0, posY = 0;

	private int width = 50, height = 25;

	private int cursorPosX, cursorPosY;

	private BufferedImage img;

	private Graphics2D g2d;

	static int scale = 4;

	public EditMenu() {
		changeImage();
	}

	public void changeImage() {
		img = new BufferedImage(Simulation.width * Simulation.pixelSize / scale,
				Simulation.height * Simulation.pixelSize / scale, BufferedImage.TYPE_INT_ARGB);
		g2d = img.createGraphics();
	}

	public void bindShape(Shape s) {
		this.s = s;
		editSimulation = false;
		setPositionForShape();
	}

	public void activateSimulationEdit() {
		s = null;
		editSimulation = true;
		setPositionForSimulation();
	}

	public void unbind() {
		s = null;
		editSimulation = false;
	}

	public Shape getShapeChanges() {
		return s;
	}

	public BufferedImage render(Simulation sim) {
		if (s != null) {
			shapeMenu();
		} else if (editSimulation) {
			simulationMenu(sim);
		}
		return img;
	}

	public void repaint() {
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		g2d.fillRect(0, 0, Simulation.width * Simulation.pixelSize / scale,
				Simulation.height * Simulation.pixelSize / scale);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
	}

	public boolean mouseOnMenu() {
		if (Simulation.mousePosition.x * Simulation.pixelSize / scale >= posX
				&& Simulation.mousePosition.x * Simulation.pixelSize / scale <= posX + width
				&& Simulation.mousePosition.y * Simulation.pixelSize / scale >= posY
				&& Simulation.mousePosition.y * Simulation.pixelSize / scale <= posY + height)
			return true;
		return false;
	}

	private TextMouseClickBox collidableSBox;
	private boolean collidableSBoxState = Shape.Defaults.collidable;
	private TextMouseClickBox gravitySBox;
	private boolean gravitySBoxState = Shape.Defaults.gravity;
	private TextMouseClickBox fixedSBox;
	private boolean fixedSBoxState = Shape.Defaults.fixed;
	private TextMouseClickBox delete;
	private ColorPicker sColorPicker;

	private void setPositionForShape() {
		if (Simulation.mousePosition.x < Simulation.width / 2) {
			posX = (int) s.posX * Simulation.pixelSize / scale + 20;
		} else {
			posX = (int) s.posX * Simulation.pixelSize / scale - 20 - width;
		}
		if (Simulation.mousePosition.y < Simulation.height / 2) {
			posY = (int) s.posY * Simulation.pixelSize / scale + 20;
		} else {
			posY = (int) s.posY * Simulation.pixelSize / scale - 20 - height;
		}

		int inLine = 2;

		collidableSBoxState = s.isCollidable();
		collidableSBox = new TextMouseClickBox(Listener.getMouse.leftButton(), posX + 2, posY + inLine);
		collidableSBox.setTextContent("Collidable:     " + (s.isCollidable() ? " on" : "off"));
		collidableSBox.setColor(s.isCollidable() ? Color.GREEN : Color.LIGHT_GRAY);
		collidableSBox.setTextColor(s.isCollidable() ? Color.WHITE : Color.LIGHT_GRAY);

		gravityBoxState = s.isGravity();
		gravitySBox = new TextMouseClickBox(Listener.getMouse.leftButton(), posX + 2, posY + (inLine += 10));
		gravitySBox.setTextContent("Gravity:        " + (s.isGravity() ? " on" : "off"));
		gravitySBox.setColor(s.isGravity() ? Color.GREEN : Color.LIGHT_GRAY);
		gravitySBox.setTextColor(s.isGravity() ? Color.WHITE : Color.LIGHT_GRAY);

		fixedSBoxState = s.isFixed();
		fixedSBox = new TextMouseClickBox(Listener.getMouse.leftButton(), posX + 2, posY + (inLine += 10));
		fixedSBox.setTextContent("Fixed:          " + (s.isFixed() ? " on" : "off"));
		fixedSBox.setColor(s.isFixed() ? Color.GREEN : Color.LIGHT_GRAY);
		fixedSBox.setTextColor(s.isFixed() ? Color.WHITE : Color.LIGHT_GRAY);

		delete = new TextMouseClickBox(Listener.getMouse.leftButton(), posX + 2, posY + (inLine += 10));
		delete.setTextContent("Delete       ");
		delete.setColor(Color.RED);
		delete.setTextColor(Color.RED);

		sColorPicker = new ColorPicker(Listener.getMouse.leftButton(), posX + 4, posY + (inLine += 10));

		width = 82;
		height = 38;
	}

	private TextMouseClickBox createCircle;
	private TextMouseClickBox createLine;
	private TextMouseClickBox pageButton;

	private TextMouseClickBox wallCollisionBox;
	private boolean wallCollisionBoxState = Shape.Defaults.wallCollision;
	private TextMouseClickBox gravityBox;
	private boolean gravityBoxState = Shape.Defaults.gravity;
	private Letters thicknessPickerText = new Letters();
	private NumberPicker thicknessPicker;
	private Letters backgroundColorPickerText = new Letters();
	private ColorPicker backgroundColorPicker;
	private int page = 0;
	private final int pageCount = 2;

	private void setPositionForSimulation() {
		if (Simulation.mousePosition.x < Simulation.width / 2) {
			posX = (int) Simulation.mousePosition.x * Simulation.pixelSize / scale + 20;
		} else {
			posX = (int) Simulation.mousePosition.x * Simulation.pixelSize / scale - 20 - width;
		}
		if (Simulation.mousePosition.y < Simulation.height / 2) {
			posY = (int) Simulation.mousePosition.y * Simulation.pixelSize / scale + 20;
		} else {
			posY = (int) Simulation.mousePosition.y * Simulation.pixelSize / scale - 20 - height;
		}

		cursorPosX = Simulation.mousePosition.x * Simulation.pixelSize / scale;
		cursorPosY = Simulation.mousePosition.y * Simulation.pixelSize / scale;

		int inLine = 2;

		// 1st page

		createCircle = new TextMouseClickBox(Listener.getMouse.leftButton(), posX + 2, posY + inLine);
		createCircle.setTextContent("create new circle");
		createCircle.setColor(Color.CYAN);
		createCircle.setTextColor(Color.CYAN);

		createLine = new TextMouseClickBox(Listener.getMouse.leftButton(), posX + 2, posY + (inLine += 10));
		createLine.setTextContent("create new line");
		createLine.setColor(Color.CYAN);
		createLine.setColor(Color.CYAN);

		// 2nd page

		inLine = 2;

		wallCollisionBox = new TextMouseClickBox(Listener.getMouse.leftButton(), posX + 2, posY + inLine);
		wallCollisionBox.setTextContent("Wall collision: " + (wallCollisionBoxState ? " on" : "off"));
		wallCollisionBox.setColor(wallCollisionBoxState ? Color.GREEN : Color.LIGHT_GRAY);
		wallCollisionBox.setTextColor(wallCollisionBoxState ? Color.WHITE : Color.LIGHT_GRAY);

		gravityBox = new TextMouseClickBox(Listener.getMouse.leftButton(), posX + 2, posY + (inLine += 10));
		gravityBox.setTextContent("Gravity:        " + (gravityBoxState ? " on" : "off"));
		gravityBox.setColor(gravityBoxState ? Color.GREEN : Color.LIGHT_GRAY);
		gravityBox.setTextColor(gravityBoxState ? Color.WHITE : Color.LIGHT_GRAY);

		thicknessPickerText.setString("Display-thickness:", posX + 2, posY + (inLine += 11));

		thicknessPicker = new NumberPicker(Listener.getMouse.leftButton(), posX + 2, posY + (inLine += 6), 1, 5);
		thicknessPicker.setSelection(Simulation.thickness);

		backgroundColorPickerText.setString("backgroundColor:", posX + 2, posY + (inLine += 11));

		backgroundColorPicker = new ColorPicker(Listener.getMouse.leftButton(), posX + 4, posY + (inLine += 6));

		width = wallCollisionBox.getBoxWidth() + 3;
		height = 62;

		pageButton = new TextMouseClickBox(Listener.getMouse.leftButton(), 0, 0);
		pageButton.setTextContent("Next page");
		pageButton.posX = posX + width / 2 - pageButton.getBoxWidth() / 2;
		pageButton.posY = posY + height - 10;
		pageButton.setColor(Color.LIGHT_GRAY);
	}

	private void shapeMenu() {

		g2d.setColor(Simulation.menuColor);
		int midX = posX + width / 2;
		int midY = posY + height / 2;
		g2d.drawLine(midX, midY, (int) s.posX * Simulation.pixelSize / scale, midY);
		g2d.drawLine((int) s.posX * Simulation.pixelSize / scale, midY, (int) s.posX * Simulation.pixelSize / scale,
				(int) s.posY * Simulation.pixelSize / scale);

		g2d.setColor(Simulation.backgroundColor);
		g2d.fillRoundRect(posX, posY, width, height, 5, 5);
		g2d.setColor(Simulation.menuColor);
		g2d.drawRoundRect(posX, posY, width, height, 5, 5);

		collidableSBox.render(g2d);
		if (collidableSBox.isClicked()) {
			collidableSBoxState = !collidableSBoxState;
			collidableSBox.setColor(collidableSBoxState ? Color.GREEN : Color.LIGHT_GRAY);
			collidableSBox.setTextColor(collidableSBoxState ? Color.WHITE : Color.LIGHT_GRAY);
			collidableSBox.setTextContent("Collidable:     " + (collidableSBoxState ? " on" : "off"));
			s.setCollidable(collidableSBoxState);
		}

		gravitySBox.render(g2d);
		if (gravitySBox.isClicked()) {
			gravitySBoxState = !gravitySBoxState;
			gravitySBox.setColor(gravitySBoxState ? Color.GREEN : Color.LIGHT_GRAY);
			gravitySBox.setTextColor(gravitySBoxState ? Color.WHITE : Color.LIGHT_GRAY);
			gravitySBox.setTextContent("Gravity:        " + (gravitySBoxState ? " on" : "off"));
			s.setGravity(gravitySBoxState);
			updateShape();
		}

		fixedSBox.render(g2d);
		if (fixedSBox.isClicked()) {
			fixedSBoxState = !fixedSBoxState;
			fixedSBox.setColor(fixedSBoxState ? Color.GREEN : Color.LIGHT_GRAY);
			fixedSBox.setTextColor(fixedSBoxState ? Color.WHITE : Color.LIGHT_GRAY);
			fixedSBox.setTextContent("fixed:          " + (fixedSBoxState ? " on" : "off"));
			s.setFixed(fixedSBoxState);
			updateShape();
		}

		sColorPicker.render(g2d);
		if (sColorPicker.getSelectedColor() != null) {
			s.setColor(sColorPicker.getSelectedColor());
		}
	}

	private void simulationMenu(Simulation sim) {
		g2d.setColor(Simulation.menuColor);
		int midX = posX + width / 2;
		int midY = posY + height / 2;
		g2d.drawLine(midX, midY, cursorPosX, midY);
		g2d.drawLine(cursorPosX, midY, cursorPosX, cursorPosY);

		g2d.setColor(Simulation.backgroundColor);
		g2d.fillRoundRect(posX, posY, width, height, 5, 5);
		g2d.setColor(Simulation.menuColor);
		g2d.drawRoundRect(posX, posY, width, height, 5, 5);

		if (page == 0) {

			createCircle.render(g2d);
			if (createCircle.isClicked()) {
				Circle c = new Circle();
				c.setLocation(cursorPosX / 2, cursorPosY / 2);
				sim.addShapes(c);
			}

			createLine.render(g2d);
			if (createLine.isClicked()) {
				Line l = new Line();
				l.setLocation(cursorPosX / 2, cursorPosY / 2);
				sim.addShapes(l);
			}

		} else if (page == 1) {

			if (wallCollisionBox.isClicked()) {
				wallCollisionBoxState = !wallCollisionBoxState;
				sim.setWallCollisionForAll(wallCollisionBoxState);
				wallCollisionBox.setColor(wallCollisionBoxState ? Color.GREEN : Color.LIGHT_GRAY);
				wallCollisionBox.setTextColor(wallCollisionBoxState ? Color.WHITE : Color.GRAY);
				wallCollisionBox.setTextContent("Wall collision: " + (wallCollisionBoxState ? " on" : "off"));
			}
			wallCollisionBox.render(g2d);

			if (gravityBox.isClicked()) {
				gravityBoxState = !gravityBoxState;
				sim.setGravityForAll(gravityBoxState);
				gravityBox.setColor(gravityBoxState ? Color.GREEN : Color.LIGHT_GRAY);
				gravityBox.setTextColor(gravityBoxState ? Color.WHITE : Color.GRAY);
				gravityBox.setTextContent("Gravity:        " + (gravityBoxState ? " on" : "off"));
			}
			gravityBox.render(g2d);

			thicknessPickerText.render(g2d);

			thicknessPicker.render(g2d);
			if (thicknessPicker.getSelectedNumber() != null) {
				int selNum = Integer.parseInt(thicknessPicker.getSelectedNumber());
				sim.setThickness(selNum);
				thicknessPicker.setSelection(selNum);
			}

			backgroundColorPickerText.render(g2d);

			backgroundColorPicker.render(g2d);
			if (backgroundColorPicker.getSelectedColor() != null)
				Simulation.backgroundColor = backgroundColorPicker.getSelectedColor();
		}

		pageButton.render(g2d);
		if (pageButton.isClicked()) {
			page++;
			if (page == pageCount)
				page = 0;
		}
	}

	private void updateShape() {
		for (int i = 0; i < ShapeEnvironment.shapes.length; i++) {
			if (ShapeEnvironment.shapes[i].getID().equals(s.getID())) {
				ShapeEnvironment.shapes[i] = s;
				break;
			}
		}
	}
}
