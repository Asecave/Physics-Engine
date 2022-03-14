package physicsEngine;

import java.awt.event.MouseEvent;

public class Button {

	public static Button left = new Button(MouseEvent.BUTTON1);
	public static Button right = new Button(MouseEvent.BUTTON2);
	
	private int button;
	
	private boolean pressed;
	private boolean held;
	private boolean released;
	
	public Button(int button) {
		this.button = button;
	}
	
	void mousePressed(MouseEvent e) {
		if (e.getButton() == button) {
			pressed = true;
			held = true;
		}
	}
	
	void mouseReleased(MouseEvent e) {
		if (e.getButton() == button) {
			pressed = false;
			held = false;
			released = true;
		}
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
	public boolean isHeld() {
		return held;
	}
	
	public boolean isReleased() {
		return released;
	}
	
	void update() {
		if (pressed) {
			pressed = false;
		}
		if (released) {
			released = false;
		}
	}
}
