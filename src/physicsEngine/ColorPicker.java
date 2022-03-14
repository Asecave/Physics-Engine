package physicsEngine;

import java.awt.Color;
import java.awt.Graphics2D;

public class ColorPicker {
	
	MouseClickBox boxes[] = new MouseClickBox[13];
	
	Button button;
	
	public ColorPicker(Button button, int posX, int posY, Simulation simulation) {
		this.button = button;
		
		for (int i = 0; i < boxes.length; i++) {
			boxes[i] = 
					new MouseClickBox(button, posX, posY, 4, 4, simulation);
			boxes[i].fill = true;
			boxes[i].posX += i * 6;
			switch (i) {
			case 0: boxes[i].setColor(Color.BLACK); break;
			case 1: boxes[i].setColor(Color.DARK_GRAY); break;
			case 2: boxes[i].setColor(Color.GRAY); break;
			case 3: boxes[i].setColor(Color.LIGHT_GRAY); break;
			case 4: boxes[i].setColor(Color.CYAN); break;
			case 5: boxes[i].setColor(Color.BLUE); break;
			case 6: boxes[i].setColor(Color.GREEN); break;
			case 7: boxes[i].setColor(Color.YELLOW); break;
			case 8: boxes[i].setColor(Color.ORANGE); break;
			case 9: boxes[i].setColor(Color.RED); break;
			case 10: boxes[i].setColor(Color.MAGENTA); break;
			case 11: boxes[i].setColor(Color.PINK); break;
			case 12: boxes[i].setColor(Color.WHITE); break;
			}
		}
	}
	
	public void render(Graphics2D g2d) {
		for (MouseClickBox box : boxes) {
			box.render(g2d);
		}
	}
	
	public Color getSelectedColor() {
		if (button.isPressed()) {
			for (MouseClickBox box : boxes) {
				if (box.isClicked()) {
					return box.getColor();
				}
			}
		}
		return null;
	}
}
