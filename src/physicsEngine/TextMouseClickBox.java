package physicsEngine;

import java.awt.Color;
import java.awt.Graphics2D;

public class TextMouseClickBox extends MouseClickBox {
	
	String textContent = "";
	
	private Color textColor = Color.WHITE;

	public TextMouseClickBox(Button mouseButton, int posX, int posY, Simulation simulation) {
		super(mouseButton, posX, posY, 3, 8, simulation);
	}
	
	public void setTextContent(String text) {
		textContent = text;
		this.lastString = text;
		this.width = getLastStringLength() + 2;
	}
	
	public int getBoxWidth() {
		return this.getLastStringLength() + 3;
	}
	
	public void setTextColor(Color c) {
		textColor = c;
	}
	
	public Color getTextColor() {
		return textColor;
	}

	@Override
	public void render(Graphics2D g2d) {
		super.render(g2d);
		Color prevColor = g2d.getColor();
		g2d.setColor(textColor);
		drawString(textContent, g2d, posX + 2, posY + 2);
		g2d.setColor(prevColor);
	}
}
