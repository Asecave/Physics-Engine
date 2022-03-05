package physicsEngine;

import java.awt.Graphics2D;

public class Letters {
	
	String lastString = "";
	
	public void drawString(String s, Graphics2D g2d, int posX, int posY) {
		lastString = s;
		int x = 0;
		for (int i = 0; i < s.length(); i ++) {
			switch (s.charAt(i)) {
			case 'a':
			case 'A':
				L_A(g2d, posX + x, posY);
				break;
			case 'b':
			case 'B':
				L_B(g2d, posX + x, posY);
				break;
			case 'c':
			case 'C':
				L_C(g2d, posX + x, posY);
				break;
			case 'd':
			case 'D':
				L_D(g2d, posX + x, posY);
				break;
			case 'e':
			case 'E':
				L_E(g2d, posX + x, posY);
				break;
			case 'f':
			case 'F':
				L_F(g2d, posX + x, posY);
				break;
			case 'g':
			case 'G':
				L_G(g2d, posX + x, posY);
				break;
			case 'h':
			case 'H':
				L_H(g2d, posX + x, posY);
				break;
			case 'i':
			case 'I':
				L_I(g2d, posX + x, posY);
				break;
			case 'j':
			case 'J':
				L_J(g2d, posX + x, posY);
				break;
			case 'k':
			case 'K':
				L_K(g2d, posX + x, posY);
				break;
			case 'l':
			case 'L':
				L_L(g2d, posX + x, posY);
				break;
			case 'm':
			case 'M':
				L_M(g2d, posX + x, posY);
				break;
			case 'n':
			case 'N':
				L_N(g2d, posX + x, posY);
				break;
			case 'o':
			case 'O':
				L_O(g2d, posX + x, posY);
				break;
			case 'p':
			case 'P':
				L_P(g2d, posX + x, posY);
				break;
			case 'q':
			case 'Q':
				L_Q(g2d, posX + x, posY);
				break;
			case 'r':
			case 'R':
				L_R(g2d, posX + x, posY);
				break;
			case 's':
			case 'S':
				L_S(g2d, posX + x, posY);
				break;
			case 't':
			case 'T':
				L_T(g2d, posX + x, posY);
				break;
			case 'u':
			case 'U':
				L_U(g2d, posX + x, posY);
				break;
			case 'v':
			case 'V':
				L_V(g2d, posX + x, posY);
				break;
			case 'w':
			case 'W':
				L_W(g2d, posX + x, posY);
				break;
			case 'x':
			case 'X':
				L_X(g2d, posX + x, posY);
				break;
			case 'y':
			case 'Y':
				L_Y(g2d, posX + x, posY);
				break;
			case 'z':
			case 'Z':
				L_Z(g2d, posX + x, posY);
				break;
			case ':':
				colon(g2d, posX + x, posY);
				break;
			case '1':
				N_1(g2d, posX + x, posY);
				break;
			case '2':
				N_2(g2d, posX + x, posY);
				break;
			case '3':
				N_3(g2d, posX + x, posY);
				break;
			case '4':
				N_4(g2d, posX + x, posY);
				break;
			case '5':
				N_5(g2d, posX + x, posY);
				break;
			case '6':
				N_6(g2d, posX + x, posY);
				break;
			case '7':
				N_7(g2d, posX + x, posY);
				break;
			case '8':
				N_8(g2d, posX + x, posY);
				break;
			case '9':
				N_9(g2d, posX + x, posY);
				break;
			case '0':
				N_0(g2d, posX + x, posY);
				break;
			case '-':
				minus(g2d, posX + x, posY);
				break;
			}
			x += 4;
		}
	}
	
	private String renderString;
	private int posX, posY;
	
	public void setString(String s, int posX, int posY) {
		renderString = s;
		this.posX = posX;
		this.posY = posY;
	}
	
	public void render(Graphics2D g2d) {
		if (renderString != null)
			drawString(renderString, g2d, posX, posY);
	}
	
	public int getLastStringLength() {
		return lastString.length() * 4;
	}
	
	private void L_A(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 0, posY + 2, posX + 2, posY + 2);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 4);
	}
	private void L_B(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 0);
		g2d.drawLine(posX + 2, posY + 1, posX + 2, posY + 1);
		g2d.drawLine(posX + 1, posY + 2, posX + 1, posY + 2);
		g2d.drawLine(posX + 2, posY + 3, posX + 2, posY + 3);
		g2d.drawLine(posX + 1, posY + 4, posX + 1, posY + 4);
	}
	private void L_C(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 0, posY + 4, posX + 2, posY + 4);
	}
	private void L_D(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 0);
		g2d.drawLine(posX + 2, posY + 1, posX + 2, posY + 3);
		g2d.drawLine(posX + 1, posY + 4, posX + 1, posY + 4);
	}
	private void L_E(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 0, posY + 2, posX + 1, posY + 2);
		g2d.drawLine(posX + 0, posY + 4, posX + 2, posY + 4);
	}
	private void L_F(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 0, posY + 2, posX + 1, posY + 2);
	}
	private void L_G(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 1, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 0, posY + 1, posX + 0, posY + 3);
		g2d.drawLine(posX + 1, posY + 4, posX + 2, posY + 4);
		g2d.drawLine(posX + 2, posY + 2, posX + 2, posY + 3);
	}
	private void L_H(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 0, posY + 2, posX + 2, posY + 2);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 4);
	}
	private void L_I(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 4);
		g2d.drawLine(posX + 0, posY + 4, posX + 2, posY + 4);
	}
	private void L_J(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 4);
		g2d.drawLine(posX + 0, posY + 4, posX + 1, posY + 4);
		g2d.drawLine(posX + 0, posY + 3, posX + 0, posY + 3);
	}
	private void L_K(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 1, posY + 2, posX + 1, posY + 2);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 1);
		g2d.drawLine(posX + 2, posY + 3, posX + 2, posY + 4);
	}
	private void L_L(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 1, posY + 4, posX + 2, posY + 4);
	}
	private void L_M(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 1, posY + 1, posX + 1, posY + 1);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 4);
	}
	private void L_N(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 0);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 4);
	}
	private void L_O(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 0);
		g2d.drawLine(posX + 1, posY + 4, posX + 1, posY + 4);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 4);
	}
	private void L_P(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 1, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 1, posY + 2, posX + 2, posY + 2);
		g2d.drawLine(posX + 2, posY + 1, posX + 2, posY + 1);
	}
	private void L_Q(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 3);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 3);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 0);
		g2d.drawLine(posX + 1, posY + 3, posX + 1, posY + 4);
	}
	private void L_R(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 0);
		g2d.drawLine(posX + 2, posY + 1, posX + 2, posY + 1);
		g2d.drawLine(posX + 1, posY + 2, posX + 1, posY + 2);
		g2d.drawLine(posX + 2, posY + 3, posX + 2, posY + 4);
	}
	private void L_S(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 0, posY + 1, posX + 0, posY + 2);
		g2d.drawLine(posX + 1, posY + 2, posX + 2, posY + 2);
		g2d.drawLine(posX + 2, posY + 3, posX + 2, posY + 3);
		g2d.drawLine(posX + 0, posY + 4, posX + 2, posY + 4);
	}
	private void L_T(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 1, posY + 1, posX + 1, posY + 4);
	}
	private void L_U(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 1, posY + 4, posX + 1, posY + 4);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 4);
	}
	private void L_V(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 3);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 3);
		g2d.drawLine(posX + 1, posY + 4, posX + 1, posY + 4);
	}
	private void L_W(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 1, posY + 3, posX + 1, posY + 3);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 4);
	}
	private void L_X(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 1);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 1);
		g2d.drawLine(posX + 1, posY + 2, posX + 1, posY + 2);
		g2d.drawLine(posX + 0, posY + 3, posX + 0, posY + 4);
		g2d.drawLine(posX + 2, posY + 3, posX + 2, posY + 4);
	}
	private void L_Y(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 2);
		g2d.drawLine(posX + 1, posY + 3, posX + 1, posY + 4);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 2);
	}
	private void L_Z(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 2, posY + 1, posX + 2, posY + 1);
		g2d.drawLine(posX + 1, posY + 2, posX + 1, posY + 2);
		g2d.drawLine(posX + 0, posY + 3, posX + 0, posY + 3);
		g2d.drawLine(posX + 0, posY + 4, posX + 2, posY + 4);
	}
	private void colon(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 1, posY + 1, posX + 1, posY + 1);
		g2d.drawLine(posX + 1, posY + 3, posX + 1, posY + 3);
	}
	private void N_1(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 1, posX + 0, posY + 1);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 3);
		g2d.drawLine(posX + 0, posY + 4, posX + 2, posY + 4);
	}
	private void N_2(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 1, posX + 0, posY + 1);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 0);
		g2d.drawLine(posX + 2, posY + 1, posX + 2, posY + 2);
		g2d.drawLine(posX + 1, posY + 3, posX + 1, posY + 3);
		g2d.drawLine(posX + 0, posY + 4, posX + 2, posY + 4);
	}
	private void N_3(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 2, posY + 1, posX + 2, posY + 4);
		g2d.drawLine(posX + 1, posY + 2, posX + 1, posY + 2);
		g2d.drawLine(posX + 0, posY + 4, posX + 1, posY + 4);
	}
	private void N_4(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 2);
		g2d.drawLine(posX + 1, posY + 2, posX + 1, posY + 2);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 4);
	}
	private void N_5(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 0, posY + 1, posX + 0, posY + 1);
		g2d.drawLine(posX + 0, posY + 2, posX + 2, posY + 2);
		g2d.drawLine(posX + 2, posY + 3, posX + 2, posY + 3);
		g2d.drawLine(posX + 0, posY + 4, posX + 2, posY + 4);
	}
	private void N_6(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 0, posY + 1, posX + 0, posY + 1);
		g2d.drawLine(posX + 0, posY + 2, posX + 2, posY + 2);
		g2d.drawLine(posX + 2, posY + 3, posX + 2, posY + 3);
		g2d.drawLine(posX + 0, posY + 4, posX + 2, posY + 4);
		g2d.drawLine(posX + 0, posY + 3, posX + 0, posY + 3);
		
	}
	private void N_7(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 2, posY + 0);
		g2d.drawLine(posX + 2, posY + 1, posX + 2, posY + 4);
	}
	private void N_8(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 4);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 4);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 0);
		g2d.drawLine(posX + 1, posY + 2, posX + 1, posY + 2);
		g2d.drawLine(posX + 1, posY + 4, posX + 1, posY + 4);
	}
	private void N_9(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 0, posX + 0, posY + 2);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 4);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 0);
		g2d.drawLine(posX + 1, posY + 2, posX + 1, posY + 2);
		g2d.drawLine(posX + 0, posY + 4, posX + 1, posY + 4);
	}
	private void N_0(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 1, posX + 0, posY + 4);
		g2d.drawLine(posX + 2, posY + 0, posX + 2, posY + 3);
		g2d.drawLine(posX + 1, posY + 0, posX + 1, posY + 0);
		g2d.drawLine(posX + 1, posY + 4, posX + 1, posY + 4);
	}
	private void minus(Graphics2D g2d, int posX, int posY) {
		g2d.drawLine(posX + 0, posY + 2, posX + 2, posY + 2);
	}
}
