package physicsEngine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ACInput.Listener;
import physicsEngine.collisions.CircleCircle;
import physicsEngine.collisions.CircleLine;

public class ShapeEnvironment {

	private BufferedImage buffi;
	private Graphics2D g2d;

	private int width;
	private int height;

	static Shape[] shapes = new Shape[0];

	int cursorBindedShape = -1;

	public ShapeEnvironment(int width, int height) {
		this.width = width;
		this.height = height;
		buffi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d = buffi.createGraphics();
	}

	public void setStroke(BasicStroke bs) {
		g2d.setStroke(bs);
	}

	public void tick(EditMenu edit) {
		render();
		calculate(edit);
	}

	private void calculate(EditMenu edit) {

		search: if (Listener.getMouse.leftButton().isPressed()) {
			for (int i = 0; i < shapes.length; i++) {
				if (shapes[i] instanceof Circle) {
					Circle c = (Circle) shapes[i];
					if (distance(c.posX, c.posY, Simulation.mousePosition.x, Simulation.mousePosition.y) < c.r) {
						cursorBindedShape = i;
						break search;
					}
				} else if (shapes[i] instanceof Line) {
					Line l = (Line) shapes[i];
					if (distance(l.posX, l.posY, Simulation.mousePosition.x, Simulation.mousePosition.y) < l.r) {
						cursorBindedShape = i;
						l.boundTo = 1;
						break search;
					}
					if (distance(l.x2, l.y2, Simulation.mousePosition.x, Simulation.mousePosition.y) < l.r) {
						cursorBindedShape = i;
						l.boundTo = 2;
						break search;
					}
					shapes[i] = l;
				}
			}
			if (!edit.mouseOnMenu())
				edit.unbind();
		}
		if (Listener.getMouse.leftButton().isHeld() && !Listener.getKey(Listener.VK_SHIFT).isHeld()) {
			if (cursorBindedShape != -1) {
				if (shapes[cursorBindedShape] instanceof Circle) {
					shapes[cursorBindedShape].posX = Simulation.mousePosition.x;
					shapes[cursorBindedShape].posY = Simulation.mousePosition.y;
					shapes[cursorBindedShape].velocityX = 0;
					shapes[cursorBindedShape].velocityY = 0;
				} else if (shapes[cursorBindedShape] instanceof Line) {
					Line l = (Line) shapes[cursorBindedShape];
					if (l.boundTo == 1) {
						l.posX = Simulation.mousePosition.x;
						l.posY = Simulation.mousePosition.y;
					} else if (l.boundTo == 2) {
						l.x2 = Simulation.mousePosition.x;
						l.y2 = Simulation.mousePosition.y;
					}
					l.velocityX = 0;
					l.velocityY = 0;
					shapes[cursorBindedShape] = l;
				}
			}
		}

		if (Listener.getMouse.leftButton().isHeld() && Listener.getKey(Listener.VK_SHIFT).isHeld()) {
			if (cursorBindedShape != -1) {
				g2d.setColor(Color.BLUE);
				g2d.drawLine((int) shapes[cursorBindedShape].posX, (int) shapes[cursorBindedShape].posY,
						Simulation.mousePosition.x, Simulation.mousePosition.y);
			}
		}
		if (Listener.getMouse.leftButton().isReleased() && !Listener.getKey(Listener.VK_SHIFT).isHeld()) {
			cursorBindedShape = -1;
		}
		if (Listener.getMouse.leftButton().isReleased() && Listener.getKey(Listener.VK_SHIFT).isHeld()) {
			if (cursorBindedShape != -1) {
				Shape s = shapes[cursorBindedShape];
				if (!s.isFixed()) {
					s.velocityX = 0.05f * (s.posX - (float) Simulation.mousePosition.getX());
					s.velocityY = 0.05f * (s.posY - (float) Simulation.mousePosition.getY());
				}
				cursorBindedShape = -1;
			}
		}

		menu: if (Listener.getMouse.rightButton().isPressed() && Simulation.editable) {
			for (int i = 0; i < shapes.length; i++) {
				if (shapes[i] instanceof Circle) {
					Circle c = (Circle) shapes[i];
					if (distance(c.posX, c.posY, Simulation.mousePosition.x, Simulation.mousePosition.y) < c.r) {
						if (shapes[i].isEditable()) edit.bindShape(shapes[i]);
						break menu;
					}
				} else if (shapes[i] instanceof Line) {
					Line l = (Line) shapes[i];
					if (distance(l.posX, l.posY, Simulation.mousePosition.x, Simulation.mousePosition.y) < l.r) {
						if (shapes[i].isEditable()) edit.bindShape(shapes[i]);
						break menu;
					}
				}
			}
			edit.activateSimulationEdit();
		}
		if (Listener.getKey(Listener.VK_DELETE).isPressed() && Simulation.editable) {
			Shape nearestShape = null;
			for (Shape s : shapes) {
				if (s != null) {
					if (nearestShape == null) {
						nearestShape = s;
						continue;
					}
					if (Math.abs(s.posX - Simulation.mousePosition.x)
							+ Math.abs(s.posY - Simulation.mousePosition.y) < Math
									.abs(nearestShape.posX - Simulation.mousePosition.x)
									+ Math.abs(nearestShape.posY - Simulation.mousePosition.y))
						nearestShape = s;
				}
			}
			if (nearestShape != null)
				removeShapes(nearestShape.getID());
		}

		for (int i = 0; i < Simulation.simulationUpdates; i++) {
			for (Shape shape : shapes) {
				if (shape != null) {
					shape.calculateVelocity(Simulation.simulationUpdates);
					shape.tick();
				}
			}

			for (Shape shape : shapes) {

				for (Shape target : shapes) {

					if (shape.id != target.id) {

						if (shape.isCollidable() && target.isCollidable()) {

							if (shape instanceof Circle && target instanceof Circle) {

								Circle c1 = (Circle) shape;
								Circle c2 = (Circle) target;

								CircleCircle cc = new CircleCircle(c1, c2);
								shape = cc.getCollidedCircle1();
								target = cc.getCollidedCircle2();
								if (Simulation.debugMode)
									cc.renderDebug(g2d);

							} else if (shape instanceof Circle && target instanceof Line) {

								Circle c = (Circle) shape;
								Line l = (Line) target;

								CircleLine cl = new CircleLine(c, l);
								shape = cl.getCollidedCircle();
								target = cl.getCollidedLine();
								if (Simulation.debugMode)
									cl.renderDebug(g2d);
							}
						}
					}
				}
			}
		}
	}

	float distance(float x1, float y1, float x2, float y2) {
		float dist = (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		if (dist == 0)
			return 0.1f;
		return dist;
	}
	
	float distance(Shape s1, Shape s2) {
		float dist = (float) Math.sqrt((s1.posX - s2.posX) * (s1.posX - s2.posX) + (s1.posY - s2.posY) * (s1.posY - s2.posY));
		if (dist == 0)
			return 0.1f;
		return dist;
	}

	private void render() {
		for (Shape s : shapes) {
			if (s != null)
				s.render(g2d);
		}
	}

	public void addShapes(Shape... newShapes) {
		Shape[] array = new Shape[shapes.length + newShapes.length];
		int i = 0;
		for (; i < shapes.length; i++) {
			array[i] = shapes[i];
		}
		for (; i < array.length; i++) {
			array[i] = newShapes[i - shapes.length];
			array[i].setID(array[i].toString());
		}
		shapes = array;
	}

	public void changeImage(int width, int height) {
		this.width = width;
		this.height = height;
		buffi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d = buffi.createGraphics();
	}

	public void setWallCollisionForAll(boolean b) {
		for (Shape s : shapes) {
			if (s != null)
				s.setWallCollision(b);
		}
	}

	public void setGravityForAll(boolean b) {
		for (Shape s : shapes) {
			s.setGravity(b);
		}
	}

	public void removeShapes(String... ids) {

		Shape[] oldShapes = shapes;
		int removedShapes = 0;

		for (int i = 0; i < oldShapes.length; i++) {
			for (String id : ids) {
				if (oldShapes[i].id.equals(id)) {
					oldShapes[i] = null;
					removedShapes++;
					break;
				}
			}
		}
		Shape[] newShapes = new Shape[oldShapes.length - removedShapes];
		int p = 0;

		for (int i = 0; i < oldShapes.length; i++) {
			if (oldShapes[i] != null) {
				newShapes[p] = oldShapes[i];
				p++;
			}
		}

		shapes = newShapes;
	}

	public void repaint() {
		g2d.setColor(Simulation.backgroundColor);
		g2d.fillRect(0, 0, width, height);
	}

	public BufferedImage getImage() {
		return buffi;
	}
}
