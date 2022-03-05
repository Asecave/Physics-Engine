package physicsEngine;

import java.awt.Color;
import java.awt.Graphics2D;

import ACInput.Listener.Button;

public class NumberPicker {

	TextMouseClickBox[] boxes;
	
	Button button;

	public NumberPicker(Button button, int posX, int posY, int from, int to) {

		this.button = button;
		
		if (from >= to)
			new RuntimeException(
					"Illegal 'from to' values: " + from + "  " + to + "\n 'from' has to be smaller than 'to'")
							.printStackTrace();
		
		boxes = new TextMouseClickBox[to + 1 - from];
		
		int x = 0;
		
		for (int i = 0; i < boxes.length; i++) {
			boxes[i] = new TextMouseClickBox(button, posX, posY);
			boxes[i].setTextContent("" + (i + from));
			boxes[i].posX += i + x;
			x += boxes[i].getBoxWidth();
		}
	}
	
	public void render(Graphics2D g2d) {
		for (TextMouseClickBox box : boxes) {
			box.render(g2d);
		}
	}
	
	public int getPickerWidth() {
		int width = 0;
		for (TextMouseClickBox box : boxes) {
			width += box.getBoxWidth() + 1;
		}
		return width;
	}
	
	public void setSelection(int selection) {
		for (TextMouseClickBox box : boxes) {
			if (Integer.parseInt(box.textContent) == selection) {
				box.setColor(Color.GREEN);
			}else {
				box.setColor(Color.WHITE);
			}
		}
	}
	
	public String getSelectedNumber() {
		if (button.isPressed()) {
			for (TextMouseClickBox box : boxes) {
				if (box.isClicked()) {
					return box.lastString;
				}
			}
		}
		return null;
	}
}
