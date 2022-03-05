package physicsEngine;

import java.awt.Color;
import java.awt.Graphics2D;

import ACInput.Listener.Button;

public class MouseClickBox extends Letters {
	int posX, posY, width, height;
	
	private Color color = Color.WHITE;
	
	private Button mouseButton;
	
	public boolean fill = false;

	public MouseClickBox(Button mouseButton, int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.mouseButton = mouseButton;
		
	}
	
	public boolean isClicked() {
		if (mouseButton != null) {
			if (mouseButton.isPressed()) {
				if (Simulation.mousePosition.x * Simulation.pixelSize / EditMenu.scale >= posX && Simulation.mousePosition.x * Simulation.pixelSize / EditMenu.scale <= posX + width
						&& Simulation.mousePosition.y * Simulation.pixelSize / EditMenu.scale >= posY && Simulation.mousePosition.y * Simulation.pixelSize / EditMenu.scale <= posY + height)
					return true;
			}
		}
		return false;
	}
	
	public boolean isHeld() {
		if (mouseButton != null) {
			if (mouseButton.isHeld()) {
				if (Simulation.mousePosition.x * Simulation.pixelSize / EditMenu.scale >= posX && Simulation.mousePosition.x * Simulation.pixelSize / EditMenu.scale <= posX + width
						&& Simulation.mousePosition.y * Simulation.pixelSize / EditMenu.scale >= posY && Simulation.mousePosition.y * Simulation.pixelSize / EditMenu.scale <= posY + height)
					return true;
			}
		}
		return false;
	}
	
	public boolean isReleased() {
		if (mouseButton != null) {
			if (mouseButton.isReleased()) {
				if (Simulation.mousePosition.x * Simulation.pixelSize / EditMenu.scale >= posX && Simulation.mousePosition.x * Simulation.pixelSize / EditMenu.scale <= posX + width
						&& Simulation.mousePosition.y * Simulation.pixelSize / EditMenu.scale >= posY
						&& Simulation.mousePosition.y * Simulation.pixelSize / EditMenu.scale <= posY + height)
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
