package physicsEngine;

import java.awt.Color;
import java.awt.Graphics2D;

public class MouseClickBox extends Letters {
	
	private Simulation simulation;
	
	int posX, posY, width, height;
	
	private Color color = Color.WHITE;
	
	private Button mouseButton;
	
	public boolean fill = false;

	public MouseClickBox(Button mouseButton, int posX, int posY, int width, int height, Simulation simulation) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.mouseButton = mouseButton;
		this.simulation = simulation;
	}
	
	public boolean isClicked() {
		if (mouseButton != null) {
			if (mouseButton.isPressed()) {
				if (simulation.getMousePosition().x * simulation.getPixelSize() / EditMenu.scale >= posX && simulation.getMousePosition().x * simulation.getPixelSize() / EditMenu.scale <= posX + width
						&& simulation.getMousePosition().y * simulation.getPixelSize() / EditMenu.scale >= posY && simulation.getMousePosition().y * simulation.getPixelSize() / EditMenu.scale <= posY + height)
					return true;
			}
		}
		return false;
	}
	
	public boolean isHeld() {
		if (mouseButton != null) {
			if (mouseButton.isHeld()) {
				if (simulation.getMousePosition().x * simulation.getPixelSize() / EditMenu.scale >= posX && simulation.getMousePosition().x * simulation.getPixelSize() / EditMenu.scale <= posX + width
						&& simulation.getMousePosition().y * simulation.getPixelSize() / EditMenu.scale >= posY && simulation.getMousePosition().y * simulation.getPixelSize() / EditMenu.scale <= posY + height)
					return true;
			}
		}
		return false;
	}
	
	public boolean isReleased() {
		if (mouseButton != null) {
			if (mouseButton.isReleased()) {
				if (simulation.getMousePosition().x * simulation.getPixelSize() / EditMenu.scale >= posX && simulation.getMousePosition().x * simulation.getPixelSize() / EditMenu.scale <= posX + width
						&& simulation.getMousePosition().y * simulation.getPixelSize() / EditMenu.scale >= posY
						&& simulation.getMousePosition().y * simulation.getPixelSize() / EditMenu.scale <= posY + height)
					return true;
			}
		}
		return false;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return color;
	}

	public void render(Graphics2D g2d) {
		Color prevColor = g2d.getColor();
		g2d.setColor(color);
		if (fill) {
			g2d.drawRoundRect(posX, posY, width, height, 2, 2);
			g2d.fillRect(posX + 1, posY + 1, width - 1, height - 1);
		}else
			g2d.drawRoundRect(posX, posY, width, height, 2, 2);
		g2d.setColor(prevColor);
	}
}
