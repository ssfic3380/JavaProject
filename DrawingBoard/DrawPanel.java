import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

/**
 * 이름 : DrawPanel
 * 역할 : 실제로 도형을 그리는 역할을 담당하는 클래스로, JPanel을 상속했다.
 *     : 주로 MouseEvent를 통해서 작동한다.
 *     : Object를 Selecting, Dragging, Resizing, Moving, Erasing, Copy/Paste, Changing line color, Changing fill color, Changing thickness 하는 역할을 담당한다.
 */

public class DrawPanel extends JPanel {

	public static ArrayList<Shape> shapes = new ArrayList<Shape>();
	public static ArrayList<Shape> tempShapes = new ArrayList<Shape>();
	public static ArrayList<Shape> copyShapes = new ArrayList<Shape>();
	private int startX;
	private int startY;
	private int currentX;
	private int currentY;
	private int pointX;
	private int pointY;
	private int lineStartX;
	private int lineStartY;
	private int lineCurrentX;
	private int lineCurrentY;
	
	private Boolean isSelected;
	private Boolean isResizing = false;
	private static int LOCATION = 5;
	private int pasteCount;

	public DrawPanel() {

		this.setBackground(Color.WHITE);
		isSelected = false;

		addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent mouseEvent) {
				
				tempShapes.clear();
				isSelected = false;
				isResizing = false;

				currentX = mouseEvent.getPoint().x;
				currentY = mouseEvent.getPoint().y;

				for (Shape temp : shapes) {
					temp.setSelected(false);
					
					if (temp instanceof BoundedShape) {
						if (((BoundedShape) temp).getFillShape()) {
							if ((temp.getX1() <= currentX) && (temp.getY1() <= currentY)
									&& ((((BoundedShape) temp).getWidth() + temp.getX1()) >= currentX)
									&& ((((BoundedShape) temp).getHeight() + temp.getY1()) >= currentY))
								tempShapes.add(temp);
						} else {
							if ((temp.getX1()-LOCATION <= currentX && temp.getX1()+((BoundedShape) temp).getWidth()+LOCATION >= currentX
									&& temp.getY1()-LOCATION <= currentY && temp.getY1() >= currentY)
									|| (temp.getX1()-LOCATION <= currentX && temp.getX1()+((BoundedShape) temp).getWidth()+LOCATION >= currentX
									&& temp.getY1()+((BoundedShape) temp).getHeight() <= currentY && temp.getY1()+((BoundedShape) temp).getHeight()+LOCATION >= currentY)
									|| temp.getX1()-LOCATION <= currentX && temp.getX1() >= currentX
									&& temp.getY1()-LOCATION <= currentY && temp.getY1()+((BoundedShape) temp).getHeight()+LOCATION >= currentY
									|| temp.getX1()+((BoundedShape) temp).getWidth() <= currentX && temp.getX1()+((BoundedShape) temp).getWidth()+LOCATION >= currentX
									&& temp.getY1()-LOCATION <= currentY && temp.getY1()+((BoundedShape) temp).getHeight()+LOCATION >= currentY)
								tempShapes.add(temp);
						}
					} else if (temp instanceof Line) {
						if (temp.getX1() <= ((Line) temp).getX2()) {
							if ((temp.getX1() <= currentX) && (temp.getY1() <= currentY)
									&& (((Line) temp).getX2() >= currentX) && (((Line) temp).getY2() >= currentY))
								tempShapes.add(temp);
						} else {
							if ((temp.getX1() >= currentX) && (temp.getY1() <= currentY)
									&& (((Line) temp).getX2() <= currentX) && (((Line) temp).getY2() >= currentY))
								tempShapes.add(temp);
						}
					}
				}

				if (tempShapes.size() != 0)	{ 
					tempShapes.get(tempShapes.size() - 1).setSelected(true);
					tempShapes.get(tempShapes.size() - 1).setStartX1(tempShapes.get(tempShapes.size() - 1).getX1());
					tempShapes.get(tempShapes.size() - 1).setStartY1(tempShapes.get(tempShapes.size() - 1).getY1());
					if (tempShapes.get(tempShapes.size() -1) instanceof BoundedShape) {
						((BoundedShape) tempShapes.get(tempShapes.size() - 1)).setStartWidth(((BoundedShape) tempShapes.get(tempShapes.size() - 1)).getWidth());
						((BoundedShape) tempShapes.get(tempShapes.size() - 1)).setStartHeight(((BoundedShape) tempShapes.get(tempShapes.size() - 1)).getHeight());
					}
					if (tempShapes.get(tempShapes.size() - 1) instanceof Line) {
						((Line) tempShapes.get(tempShapes.size() - 1)).setStartX2(((Line) tempShapes.get(tempShapes.size() - 1)).getX2());
						((Line) tempShapes.get(tempShapes.size() - 1)).setStartY2(((Line) tempShapes.get(tempShapes.size() - 1)).getY2());
					}
					isSelected = true;
				}
				
				tempShapes.clear();
				repaint();

			}

			public void mousePressed(MouseEvent mouseEvent) {
				
				startX = mouseEvent.getPoint().x;
				startY = mouseEvent.getPoint().y;
				currentX = startX;
				currentY = startY;
				pointX = startX;
				pointY = startY;
				lineStartX = startX;
				lineStartY = startY;
				lineCurrentX = startX;
				lineCurrentY = startY;

				repaint();

			}

			public void mouseReleased(MouseEvent mouseEvent) {
				
				isResizing = false;
				
				if (isSelected) {
					for (Shape temp : shapes) {
						temp.setStartX1(temp.getX1());
						temp.setStartY1(temp.getY1());
						if (temp instanceof BoundedShape) {
							((BoundedShape) temp).setStartWidth(((BoundedShape) temp).getWidth());
							((BoundedShape) temp).setStartHeight(((BoundedShape) temp).getHeight());
						}
						if (temp instanceof Line) {
							((Line) temp).setStartX2(((Line) temp).getX2());
							((Line) temp).setStartY2(((Line) temp).getY2());
						}
					}
				} else {
					if (EditorFrame.currentShape == "OVAL") {
						if (startX - currentX != 0)
							shapes.add(new Oval(startX, startY, Math.abs(startX - currentX), Math.abs(startY - currentY), 3, Color.BLACK, Color.WHITE, false, false));
						repaint();
					} else if (EditorFrame.currentShape == "RECT") {
						if (startX - currentX != 0)
							shapes.add(new Rectangle(startX, startY, Math.abs(startX - currentX), Math.abs(startY - currentY), 3, Color.BLACK, Color.WHITE, false, false));
						repaint();
					} else if (EditorFrame.currentShape == "IMAGE") {
						if (startX - currentX != 0)
							shapes.add(new Image(startX, startY, Math.abs(startX - currentX), Math.abs(startY - currentY), EditorFrame.currentPath, false));
						repaint();
					} else if (EditorFrame.currentShape == "TEXT") {
						if (startX - currentX != 0) {
							if (EditorFrame.currentColor == null) shapes.add(new Text(startX, startY, EditorFrame.currentText, new Font("굴림체", Font.PLAIN, 21), Color.BLACK, false));
							else shapes.add(new Text(startX, startY, EditorFrame.currentText, new Font("굴림체", Font.PLAIN, 21), Color.BLACK, false));
							EditorFrame.currentText = null;
							EditorFrame.currentShape = "SELECT";
						}
						repaint();
					} else if (EditorFrame.currentShape == "LINE") {
						if (lineStartX != lineCurrentY) {
							if (lineStartY > lineCurrentY) {
								shapes.add(new Line(lineCurrentX, lineCurrentY, lineStartX, lineStartY, 3, Color.BLACK, false));
							} else if (lineStartY == lineCurrentY && lineStartX > lineCurrentX) {
								shapes.add(new Line(lineCurrentX, lineCurrentY, lineStartX, lineStartY, 3, Color.BLACK, false));
							} else {
								shapes.add(new Line(lineStartX, lineStartY, lineCurrentX, lineCurrentY, 3, Color.BLACK, false));
							}
						}
						repaint();
					} else if (EditorFrame.currentShape == "SELECT") {
						for (Shape temp : shapes) {
							if (temp instanceof BoundedShape) {
								if ((temp.getX1() >= startX) && (temp.getY1() >= startY)
										&& ((((BoundedShape) temp).getWidth() + temp.getX1()) <= (startX + Math.abs(startX - currentX)))
										&& ((((BoundedShape) temp).getHeight() + temp.getY1()) <= (startY + Math.abs(startY - currentY)))) {
									temp.setSelected(true);
									temp.setStartX1(temp.getX1());
									temp.setStartY1(temp.getY1());
									((BoundedShape) temp).setStartWidth(((BoundedShape) temp).getWidth());
									((BoundedShape) temp).setStartHeight(((BoundedShape) temp).getHeight());
									isSelected = true;
								}

							} else if (temp instanceof Line) {
								if ((temp.getX1() >= startX) && (temp.getY1() >= startY)
										&& (((Line) temp).getX2() <= (startX + Math.abs(startX - currentX)))
										&& (((Line) temp).getY2() <= (startY + Math.abs(startY - currentY)))) {
									temp.setSelected(true);									
									temp.setStartX1(temp.getX1());
									temp.setStartY1(temp.getY1());
									((Line) temp).setStartX2(((Line) temp).getX2());
									((Line) temp).setStartY2(((Line) temp).getY2());
									isSelected = true;
								}
							}
						}
						repaint();
					}
				}

				currentX = startX;
				currentY = startY;

			}

		});

		addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent mouseEvent) {
				
				String text = String.format("  마우스 좌표 = (%d, %d)", mouseEvent.getPoint().x, mouseEvent.getPoint().y);
				AxisPanel.setText(text);
				
				currentX = mouseEvent.getPoint().x;
				currentY = mouseEvent.getPoint().y;
				lineCurrentX = currentX;
				lineCurrentY = currentY;
				
				/* 도형 드래그 */
				if (isSelected) {
					if (tempShapes.size() == 0) {
						for (Shape temp : shapes) {
							if (temp.getSelected()) {
								tempShapes.add(temp);
							}
						}
					}
					
					/* Moving */
					for (Shape temp : tempShapes) {

						if (temp instanceof BoundedShape) {
							if ((temp.getX1() < currentX) && (temp.getY1() < currentY)
								&& ((((BoundedShape) temp).getWidth() + temp.getX1()) > currentX)
								&& ((((BoundedShape) temp).getHeight() + temp.getY1()) > currentY) && !isResizing) { // moving
								for (Shape allTemp : tempShapes) {
									allTemp.setX1(allTemp.getStartX1() + currentX - pointX);
									allTemp.setY1(allTemp.getStartY1() + currentY - pointY);
									if (allTemp instanceof Line) {
										((Line) allTemp).setX2(((Line) allTemp).getStartX2() + currentX - pointX);
										((Line) allTemp).setY2(((Line) allTemp).getStartY2() + currentY - pointY);
									}
								}
								break;

							} else { // resizing
								if (!(temp instanceof Text)) {	
									resizing(temp, currentX, currentY, pointX, pointY);
								}
								
							} 
						} else if (temp instanceof Line) {
							if (temp.getX1() < ((Line) temp).getX2()) { // 왼쪽에서 시작
								if ((temp.getX1() < currentX) && (temp.getY1() < currentY)
										&& (((Line) temp).getX2() > currentX) && (((Line) temp).getY2() > currentY) && !isResizing) { // 선 위의 좌표지만 끝점이 아니면 moving

									for (Shape allTemp : tempShapes) {
										allTemp.setX1(allTemp.getStartX1() + currentX - pointX);
										allTemp.setY1(allTemp.getStartY1() + currentY - pointY);
										if (allTemp instanceof Line) {
											((Line) allTemp).setX2(((Line) allTemp).getStartX2() + currentX - pointX);
											((Line) allTemp).setY2(((Line) allTemp).getStartY2() + currentY - pointY);
										}
									}
									break;

								} else { // 양 끝점이면 resizing
									resizing(temp, currentX, currentY, pointX, pointY);
								}
							} else { // 오른쪽에서 시작
								if ((temp.getX1() >= currentX) && (temp.getY1() <= currentY)
										&& (((Line) temp).getX2() <= currentX) && (((Line) temp).getY2() >= currentY) && !isResizing) { // 선 위의 좌표지만 끝점이 아니면 moving

									for (Shape allTemp : tempShapes) {
										allTemp.setX1(allTemp.getStartX1() + currentX - pointX);
										allTemp.setY1(allTemp.getStartY1() + currentY - pointY);
										if (allTemp instanceof Line) {
											((Line) allTemp).setX2(((Line) allTemp).getStartX2() + currentX - pointX);
											((Line) allTemp).setY2(((Line) allTemp).getStartY2() + currentY - pointY);
										}
									}
									break;

								} else { // 양 끝점이면 resizing
									resizing(temp, currentX, currentY, pointX, pointY);
								}
							} 
						}

					}					
				}
				
				/* 도형 그리기 좌표 설정 */
				if ((currentX < pointX) && (currentY > pointY)) {
					startX = currentX;
					currentX = pointX;
				} else if ((currentY < pointY) && (currentX > pointX)) {
					startY = currentY;
					currentY = pointY;
				} else if ((currentX < pointX) && (currentY < pointY)) {
					startX = currentX;
					startY = currentY;
					currentX = pointX;
					currentY = pointY;
				}

				repaint();				
				
			}

			public void mouseMoved(MouseEvent event) {
				String text = String.format("  마우스 좌표 = (%d, %d)", event.getPoint().x, event.getPoint().y);
				AxisPanel.setText(text);
			}

		});

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (EditorFrame.currentShape == "OVAL") {
			if (startX - currentX != 0 && !isSelected)
				g.drawOval(startX, startY, Math.abs(startX - currentX), Math.abs(startY - currentY));
		} else if (EditorFrame.currentShape == "RECT") {
			if (startX - currentX != 0 && !isSelected)
				g.drawRect(startX, startY, Math.abs(startX - currentX), Math.abs(startY - currentY));
		} else if (EditorFrame.currentShape == "IMAGE") {
			if (startX - currentX != 0 && !isSelected)
				g.drawRect(startX, startY, Math.abs(startX - currentX), Math.abs(startY - currentY));
		} else if (EditorFrame.currentShape == "TEXT") {
			if (startX - currentX != 0 && !isSelected)
				g.drawRect(startX, startY, Math.abs(startX - currentX), Math.abs(startY - currentY));
		} else if (EditorFrame.currentShape == "LINE") {
			if ( (lineStartX != lineCurrentY) && !isSelected ) {				
				if (lineStartY > lineCurrentY) {
					g.drawLine(lineCurrentX, lineCurrentY, lineStartX, lineStartY);
				} else if (lineStartY == lineCurrentY && lineStartX > lineCurrentX) {
					g.drawLine(lineCurrentX, lineCurrentY, lineStartX, lineStartY);
				} else {
					g.drawLine(lineStartX, lineStartY, lineCurrentX, lineCurrentY);
				}
			}
		} else if (EditorFrame.currentShape == "SELECT") {
			if (!isSelected) {
				g.setColor(new Color(0f, 0f, 0f, 0.5f));
				g.fillRect(startX, startY, Math.abs(startX - currentX), Math.abs(startY - currentY));
			}
		}

		for (Shape temp : shapes)
			temp.draw(g);
	}
	
	/* Change LineColor */
	public void changeLineColor() {
		
		Iterator<Shape> itr = shapes.iterator();
		
		while (itr.hasNext()) {
			Shape temp = (Shape) itr.next();
			if (temp.getSelected()) {
				if (EditorFrame.currentColor == null) temp.setLineColor(Color.BLACK);
				else temp.setLineColor(EditorFrame.currentColor);
			}
		}

		revalidate();
		repaint();
		
	}
	
	/* Change FillColor */
	public void changeFillColor() {
		
		Iterator<Shape> itr = shapes.iterator();
		
		while (itr.hasNext()) {
			Shape temp = (Shape) itr.next();
			if (temp.getSelected() && (temp instanceof BoundedShape)) {
				if (EditorFrame.currentColor == null) {
					((BoundedShape) temp).setFillShape(false);
				} else {
					((BoundedShape) temp).setFillColor(EditorFrame.currentColor);
					((BoundedShape) temp).setFillShape(true);
				}
			}
		}

		revalidate();
		repaint();
		
	}
	
	/* Change TextColor */
	public void changeTextColor() {
		
		Iterator<Shape> itr = shapes.iterator();
		
		while (itr.hasNext()) {
			Shape temp = (Shape) itr.next();
			if (temp.getSelected() && (temp instanceof Text)) {
				if (EditorFrame.currentColor == null) {
					((Text) temp).setTextColor(Color.BLACK);
				} else {
					((Text) temp).setTextColor(EditorFrame.currentColor);
				}
			}
		}

		revalidate();
		repaint();
		
	}
	
	/* Change TextSize */
	public void changeTextSize() {
		
		Iterator<Shape> itr = shapes.iterator();
		
		while (itr.hasNext()) {
			Shape temp = (Shape) itr.next();
			if (temp.getSelected() && (temp instanceof Text)) {
				Font currentFont = ((Text) temp).getFont();
				((Text) temp).setFont(new Font(currentFont.getName(), currentFont.getStyle(), EditorFrame.currentSize * 3));
			}
		}

		revalidate();
		repaint();
		
	}
	
	/* Change FontName */
	public void changeFontName(String fontName) {
		
		Iterator<Shape> itr = shapes.iterator();
		
		while (itr.hasNext()) {
			Shape temp = (Shape) itr.next();
			if (temp.getSelected() && (temp instanceof Text)) {
				Font currentFont = ((Text) temp).getFont();
				((Text) temp).setFont(new Font(fontName, currentFont.getStyle(), currentFont.getSize()));
			}
		}

		revalidate();
		repaint();
		
	}
	
	/* Change FontStyle */
	public void changeFontStyle(int fontStyle) {
		
		Iterator<Shape> itr = shapes.iterator();
		
		while (itr.hasNext()) {
			Shape temp = (Shape) itr.next();
			if (temp.getSelected() && (temp instanceof Text)) {
				Font currentFont = ((Text) temp).getFont();
				((Text) temp).setFont(new Font(currentFont.getName(), fontStyle, currentFont.getSize()));
			}
		}

		revalidate();
		repaint();
		
	}
	
	/* Change Thickness */
	public void changeThickness() {
		
		Iterator<Shape> itr = shapes.iterator();
		
		while (itr.hasNext()) {
			Shape temp = (Shape) itr.next();
			if (temp.getSelected()) temp.setThickness(EditorFrame.currentSize);
		}

		revalidate();
		repaint();
		
	}
	
	/* Copy */
	public void copy() {
		
		copyShapes.clear();
		pasteCount = 1;
		
		Iterator<Shape> itr = shapes.iterator();
		
		while (itr.hasNext()) {
			Shape temp = (Shape) itr.next();
			if (temp.getSelected()) copyShapes.add(temp);
		}
		
	}
	
	/* Paste */
	public void paste() {
		
		Iterator<Shape> itr = copyShapes.iterator();
		
		while (itr.hasNext()) {
			Shape temp = (Shape) itr.next();

			if (temp instanceof Oval) {
				shapes.add(new Oval(temp.getX1() + pasteCount*30, temp.getY1() + pasteCount*30, ((Oval) temp).getWidth(),
						((Oval) temp).getHeight(), temp.getThickness(), temp.getLineColor(), ((Oval) temp).getFillColor(),
						((Oval) temp).getFillShape(), temp.getSelected()));
				shapes.get(shapes.size() - 1).setStartX1(shapes.get(shapes.size() - 1).getX1());
				shapes.get(shapes.size() - 1).setStartY1(shapes.get(shapes.size() - 1).getY1());
				((BoundedShape) shapes.get(shapes.size() - 1)).setStartWidth(((BoundedShape) shapes.get(shapes.size() - 1)).getWidth());
				((BoundedShape) shapes.get(shapes.size() - 1)).setStartHeight(((BoundedShape) shapes.get(shapes.size() - 1)).getHeight());
			} else if (temp instanceof Rectangle) {
				shapes.add(new Rectangle(temp.getX1() + pasteCount*30, temp.getY1() + pasteCount*30, ((Rectangle) temp).getWidth(),
						((Rectangle) temp).getHeight(), temp.getThickness(), temp.getLineColor(), ((Rectangle) temp).getFillColor(),
						((Rectangle) temp).getFillShape(), temp.getSelected()));
				shapes.get(shapes.size() - 1).setStartX1(shapes.get(shapes.size() - 1).getX1());
				shapes.get(shapes.size() - 1).setStartY1(shapes.get(shapes.size() - 1).getY1());
				((BoundedShape) shapes.get(shapes.size() - 1)).setStartWidth(((BoundedShape) shapes.get(shapes.size() - 1)).getWidth());
				((BoundedShape) shapes.get(shapes.size() - 1)).setStartHeight(((BoundedShape) shapes.get(shapes.size() - 1)).getHeight());
			} else if (temp instanceof Image) {
				shapes.add(new Image(temp.getX1() + pasteCount*30, temp.getY1() + pasteCount*30, ((Image) temp).getWidth(),
						((Image) temp).getHeight(), ((Image) temp).getPath(), temp.getSelected()));
				shapes.get(shapes.size() - 1).setStartX1(shapes.get(shapes.size() - 1).getX1());
				shapes.get(shapes.size() - 1).setStartY1(shapes.get(shapes.size() - 1).getY1());
				((BoundedShape) shapes.get(shapes.size() - 1)).setStartWidth(((BoundedShape) shapes.get(shapes.size() - 1)).getWidth());
				((BoundedShape) shapes.get(shapes.size() - 1)).setStartHeight(((BoundedShape) shapes.get(shapes.size() - 1)).getHeight());
			} else if (temp instanceof Text) {
				shapes.add(new Text(temp.getX1() + pasteCount*30, temp.getY1() + pasteCount*30, ((Text) temp).getText(), ((Text) temp).getFont(),
						((Text) temp).getTextColor(), temp.getSelected()));
				shapes.get(shapes.size() - 1).setStartX1(shapes.get(shapes.size() - 1).getX1());
				shapes.get(shapes.size() - 1).setStartY1(shapes.get(shapes.size() - 1).getY1());
			} else if (temp instanceof Line) {
				shapes.add(new Line(temp.getX1() + pasteCount * 30, temp.getY1() + pasteCount * 30,
						((Line) temp).getX2() + pasteCount * 30, ((Line) temp).getY2() + pasteCount * 30,
						temp.getThickness(), temp.getLineColor(), temp.getSelected()));
				shapes.get(shapes.size() - 1).setStartX1(shapes.get(shapes.size() - 1).getX1());
				shapes.get(shapes.size() - 1).setStartY1(shapes.get(shapes.size() - 1).getY1());
				((Line) shapes.get(shapes.size() - 1)).setStartX2(((Line) shapes.get(shapes.size() - 1)).getX2());
				((Line) shapes.get(shapes.size() - 1)).setStartY2(((Line) shapes.get(shapes.size() - 1)).getY2());
			}

		}
		
		pasteCount++;
		
		revalidate();
		repaint();
		
	}
	
	/* Remove */
	public void remove() {
		
		Iterator<Shape> itr = shapes.iterator();
		
		while (itr.hasNext()) {
			Shape temp = (Shape) itr.next();
			if (temp.getSelected()) itr.remove();
		}

		revalidate();
		repaint();
		
	}
	
	/* Initialize */
	public void initialize() {
		
		shapes.clear();
		tempShapes.clear();
		copyShapes.clear();
		isSelected = false;
		isResizing = false;
		pasteCount = 1;
		
		revalidate();
		repaint();
		
	}
	
	/* Resizing */
	public void resizing(Shape target, int currentX, int currentY, int pointX, int pointY) {
		
		ArrayList<Shape> allTarget = tempShapes;
		int limit = LOCATION;
		
		if (target instanceof BoundedShape) {			
			
			if (target.getX1()+LOCATION < currentX && target.getX1() + ((BoundedShape) target).getWidth()-LOCATION > currentX 
					&& target.getY1()-LOCATION <= currentY && target.getY1() >= currentY) { // 위쪽 경계선을 누르면
				
				isResizing = true;
				
				for (Shape alltarget : allTarget) {
					if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
						if (((BoundedShape) alltarget).getStartHeight() - (currentY - pointY) < limit && (currentY - pointY) > 0) continue;
						((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() - (currentY - pointY));
						alltarget.setY1(alltarget.getStartY1() + currentY - pointY);
					}
					if (alltarget instanceof Line) { // ㅣ자 직선일 때만 리사이즈
						if ((alltarget.getX1() == ((Line) alltarget).getX2())) {
							if (alltarget.getY1() > ((Line) alltarget).getY2()) {
								if (((Line) alltarget).getStartY2() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
								((Line) alltarget).setY2(((Line) alltarget).getStartY2() + currentY - pointY);
							} else {
								if (alltarget.getStartY1() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
								alltarget.setY1(alltarget.getStartY1() + currentY - pointY);
							}
						}
					}
				}
				return;
				
			} else if (target.getX1()+LOCATION < currentX && target.getX1() + ((BoundedShape) target).getWidth()-LOCATION > currentX 
					&& target.getY1()+((BoundedShape) target).getHeight() <= currentY && target.getY1()+((BoundedShape) target).getHeight()+LOCATION >= currentY) { // 아래쪽 경계선을 누르면
				
				isResizing = true;
				
				for (Shape alltarget : allTarget) {
					if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
						if (((BoundedShape) alltarget).getStartHeight() + currentY - pointY < limit	&& (currentY - pointY) < 0) continue;
						((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() + currentY - pointY);
					}
					if (alltarget instanceof Line) { // ㅣ자 직선일 때만 리사이즈
						if ((alltarget.getX1() == ((Line) alltarget).getX2())) {
							if (alltarget.getY1() > ((Line) alltarget).getY2()) {
								if (alltarget.getStartY1() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
								alltarget.setY1(alltarget.getStartY1() + currentY - pointY);
							} else {
								if (((Line) alltarget).getStartY2() + currentY - pointY < limit && (currentY - pointY < 0)) continue;
								((Line) alltarget).setY2(((Line) alltarget).getStartY2() + currentY - pointY);
							}

						}
					}
				}
				return;
				
			} else if (target.getY1()+LOCATION < currentY && target.getY1() + ((BoundedShape) target).getHeight()-LOCATION > currentY 
					&& target.getX1()-LOCATION <= currentX && target.getX1() >= currentX) { // 왼쪽 경계선을 누르면
				
				isResizing = true;
				
				for (Shape alltarget : allTarget) {
					if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
						if (((BoundedShape) alltarget).getStartWidth() - (currentX - pointX) < limit && (currentX - pointX) > 0) continue;
						((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() - (currentX - pointX));
						alltarget.setX1(alltarget.getStartX1() + currentX - pointX);
					}
					if (alltarget instanceof Line) { // ㅡ자 직선일 때만 리사이즈
						if (alltarget.getY1() == ((Line) alltarget).getY2()) {
							if (alltarget.getX1() > ((Line) alltarget).getX2()) {
								if (((Line) alltarget).getStartX2() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
								((Line) alltarget).setX2(((Line) alltarget).getStartX2() + currentY - pointY);
							} else {
								if (alltarget.getStartX1() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
								alltarget.setX1(alltarget.getStartX1() + currentY - pointY);
							}
						}
					}
				}
				return;
				
			} else if (target.getY1()+LOCATION < currentY && target.getY1() + ((BoundedShape) target).getHeight()-LOCATION > currentY 
					&& target.getX1()+((BoundedShape) target).getWidth() <= currentX && target.getX1()+((BoundedShape) target).getWidth()+LOCATION >= currentX) { // 오른쪽 경계선을 누르면

				isResizing = true;
				
				for (Shape alltarget : allTarget) {
					if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
						if (((BoundedShape) alltarget).getStartWidth() + currentX - pointX < limit && (currentX - pointX) < 0) continue;
						((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() + currentX - pointX);
					}
					if (alltarget instanceof Line) { // ㅡ자 직선일 때만 리사이즈
						if (alltarget.getY1() == ((Line) alltarget).getY2()) {
							if (alltarget.getX1() > ((Line) alltarget).getX2()) {
								if (alltarget.getStartX1() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
								alltarget.setX1(alltarget.getStartX1() + currentY - pointY);
							} else {
								if (((Line) alltarget).getStartX2() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
								((Line) alltarget).setX2(((Line) alltarget).getStartX2() + currentY - pointY);
							}
						}
					}
				}
				return;
				
			} else if (target.getX1()+LOCATION >= currentX && target.getX1()-LOCATION <= currentX && target.getY1() >= currentY && target.getY1()-LOCATION <= currentY
					|| target.getX1() >= currentX && target.getX1()-LOCATION <= currentX && target.getY1() <= currentY && target.getY1()+LOCATION >= currentY) { // 왼쪽 위 끝점을 누르면

				isResizing = true;
				
				for (Shape alltarget : allTarget) {
					if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
						if (((BoundedShape) alltarget).getStartWidth() - (currentX - pointX) < limit && (currentX - pointX) > 0) continue;
						else if (((BoundedShape) alltarget).getStartHeight() - (currentY - pointY) < limit && (currentY - pointY) > 0) continue;
						alltarget.setX1(alltarget.getStartX1() + currentX - pointX);
						alltarget.setY1(alltarget.getStartY1() + currentY - pointY);
						((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() - (currentX - pointX));
						((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() - (currentY - pointY));
					}
					if (alltarget instanceof Line) { // 도형 따라서
						if (alltarget.getStartX1() + currentX - pointX < limit && (currentX - pointX) < 0) continue;
						else if (alltarget.getStartY1() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
						alltarget.setX1(alltarget.getStartX1() + currentX - pointX);
						alltarget.setY1(alltarget.getStartY1() + currentY - pointY);
					}
				}
				return;
				
			} else if (target.getX1()+((BoundedShape) target).getWidth()-LOCATION <= currentX && target.getX1()+((BoundedShape) target).getWidth()+LOCATION >= currentX
					&& target.getY1() >= currentY && target.getY1()-LOCATION <= currentY
					|| target.getX1()+((BoundedShape) target).getWidth() <= currentX && target.getX1()+((BoundedShape) target).getWidth()+LOCATION >= currentX
					&& target.getY1() <= currentY && target.getY1()+LOCATION >= currentY) { // 오른쪽 위 끝점을 누르면

				isResizing = true;
				
				for (Shape alltarget : allTarget) {
					if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
						if (((BoundedShape) alltarget).getStartWidth() + currentX - pointX < limit && (currentX - pointX) < 0) continue;
						else if (((BoundedShape) alltarget).getStartHeight() - (currentY - pointY) < limit && (currentY - pointY) > 0) continue;
						alltarget.setY1(alltarget.getStartY1() + currentY - pointY);
						((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() + currentX - pointX);
						((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() - (currentY - pointY));
					}
					if (alltarget instanceof Line) { // 도형 따라서
						if (alltarget.getStartX1() + currentX - pointX < limit) continue;
						else if (alltarget.getStartY1() + currentY - pointY < limit) continue;
						((Line) alltarget).setX1(alltarget.getStartX1() + currentX - pointX);
						((Line) alltarget).setY1(alltarget.getStartY1() + currentY - pointY);
					}
				}
				return;
				
			} else if (target.getX1()+LOCATION >= currentX && target.getX1()-LOCATION <= currentX 
					&& target.getY1()+((BoundedShape) target).getHeight()+LOCATION >= currentY && target.getY1()+((BoundedShape) target).getHeight() <= currentY
					|| target.getX1() >= currentX && target.getX1()-LOCATION <= currentX 
					&& target.getY1()+((BoundedShape) target).getHeight()-LOCATION >= currentY && target.getY1()+((BoundedShape) target).getHeight() <= currentY) { // 왼쪽 아래 끝점을 누르면

				isResizing = true;
				
				for (Shape alltarget : allTarget) {
					if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
						if (((BoundedShape) alltarget).getStartWidth() - (currentX - pointX) < limit && (currentX - pointX) > 0) continue;
						else if (((BoundedShape) alltarget).getStartHeight() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
						alltarget.setX1(alltarget.getStartX1() + currentX - pointX);
						((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() - (currentX - pointX));
						((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() + currentY - pointY);
					}
					if (alltarget instanceof Line) { // 도형 따라서
						if (((Line) alltarget).getStartX2() + currentX - pointX < limit) continue;
						else if (((Line) alltarget).getStartY2() + currentY - pointY < limit) continue;
						((Line) alltarget).setX2(((Line) alltarget).getStartX2() + currentX - pointX);
						((Line) alltarget).setY2(((Line) alltarget).getStartY2() + currentY - pointY);
					}
				}
				return;
			
			} else if (target.getX1()+((BoundedShape) target).getWidth()-LOCATION <= currentX && target.getX1()+((BoundedShape) target).getWidth()+LOCATION >= currentX
					&& target.getY1()+((BoundedShape) target).getHeight()+LOCATION >= currentY && target.getY1()+((BoundedShape) target).getHeight() <= currentY
					|| target.getX1()+((BoundedShape) target).getWidth() <= currentX && target.getX1()+((BoundedShape) target).getWidth()+LOCATION >= currentX
					&& target.getY1()+((BoundedShape) target).getHeight() >= currentY && target.getY1()+((BoundedShape) target).getHeight()-LOCATION <= currentY) { // 오른쪽 아래 끝점을 누르면

				isResizing = true;
				
				for (Shape alltarget : allTarget) {
					if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
						if (((BoundedShape) alltarget).getStartWidth() + currentX - pointX < limit && (currentX - pointX) < 0) continue;
						else if (((BoundedShape) alltarget).getStartHeight() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
						((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() + currentX - pointX);
						((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() + currentY - pointY);
					}
					if (alltarget instanceof Line) { // 나머지 : 도형 따라서
						if (((Line) alltarget).getStartX2() + currentX - pointX < limit) continue;
						else if (((Line) alltarget).getStartY2() + currentY - pointY < limit) continue;
						((Line) alltarget).setX2(((Line) alltarget).getStartX2() + currentX - pointX);
						((Line) alltarget).setY2(((Line) alltarget).getStartY2() + currentY - pointY);
					}
				}
				return;
					
			}
		} else if (target instanceof Line) {
			
			if (allTarget.size() == 1) { // 직선 하나만 선택했다면
				
				if (target.getX1()-LOCATION <= currentX && target.getX1()+LOCATION >= currentX && target.getY1()-LOCATION <= currentY && target.getY1()+LOCATION >= currentY) { // 왼쪽의 점을 누르면
					
					isResizing = true;
					
					target.setX1(currentX);
					target.setY1(currentY);
					
					return;
					
				} else if (((Line) target).getX2()-LOCATION <= currentX && ((Line) target).getX2()+LOCATION >= currentX 
						&& ((Line) target).getY2()-LOCATION <= currentY && ((Line) target).getY2()+LOCATION >= currentY) { // 오른쪽의 점을 누르면
					
					isResizing = true;
					
					((Line) target).setX2(currentX);
					((Line) target).setY2(currentY);
					
					return;
					
				}
			}
			
			/*
			if (target.getX1() == ((Line) target).getX2()) { // ㅣ자 직선일 때
				if (target.getX1()-LOCATION <= currentX && target.getX1()+LOCATION >= currentX && target.getY1() > currentY && target.getY1()-LOCATION <= currentY) { // 위의 점을 누르면
					
					for (Shape alltarget : allTarget) {
						if (alltarget instanceof BoundedShape) {
							if (((BoundedShape) alltarget).getStartHeight() - (currentY - pointY) < limit) return;
							((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() - (currentY - pointY));
							alltarget.setY1(alltarget.getStartY1() + currentY - pointY);
						}
						if (alltarget instanceof Line) {
							if (alltarget.getStartY1() + currentY - pointY < limit) return;
							alltarget.setY1(alltarget.getStartY1() + currentY - pointY);
						}
					}
					return;
					
				} else if (((Line) target).getX2()-LOCATION <= currentX && ((Line) target).getX2()+LOCATION >= currentX 
						&& ((Line) target).getY2() < currentY && ((Line) target).getY2()+LOCATION >= currentY) { // 아래의 점을 누르면
					
					for (Shape alltarget : allTarget) {
						if (alltarget instanceof BoundedShape) {
							if (((BoundedShape) alltarget).getStartHeight() + currentY - pointY < limit) return;
							((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() + currentY - pointY);
						}
						if (alltarget instanceof Line) {
							if (((Line) alltarget).getStartY2() + currentY - pointY < limit) return;
							((Line) alltarget).setY2(((Line) alltarget).getStartY2() + currentY - pointY);
						}
					}
					return;
					
				}
			} else if (target.getY1() == ((Line) target).getY2()) { // ㅡ자 직선일 때
				if (target.getX1()-LOCATION <= currentX && target.getX1() > currentX && target.getY1()-LOCATION < currentY && target.getY1()+LOCATION > currentY) { // 왼쪽의 점을 누르면
					
					for (Shape alltarget : allTarget) {
						if (alltarget instanceof BoundedShape) {
							if (((BoundedShape) alltarget).getStartWidth() - (currentX - pointX) < limit) return;
							((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() - (currentX - pointX));
							alltarget.setX1(alltarget.getStartX1() + currentX - pointX);
						}
						if (alltarget instanceof Line) {
							if (alltarget.getStartX1() + currentY - pointY < limit) return;
							alltarget.setX1(alltarget.getStartX1() + currentY - pointY);
						}
					}
					return;
					
				} else if (((Line) target).getX2()+LOCATION >= currentX && ((Line) target).getX2() < currentX 
						&& ((Line) target).getY2()-LOCATION <= currentY && ((Line) target).getY2()+LOCATION >= currentY) { // 오른쪽의 점을 누르면
					
					for (Shape alltarget : allTarget) {
						if (alltarget instanceof BoundedShape) {
							if (((BoundedShape) alltarget).getStartWidth() + currentX - pointX < limit) return;
							((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() + currentX - pointX);
						}
						if (alltarget instanceof Line) {
							if (((Line) alltarget).getStartX2() + currentY - pointY < limit) return;
							((Line) alltarget).setX2(((Line) alltarget).getStartX2() + currentY - pointY);
						}
					}
					return;
					
				}
			} 
			*/
			if (target.getX1() > ((Line) target).getX2()) { // 오른쪽 시작 직선일 때
				if (target.getX1()-LOCATION <= currentX && target.getX1()+LOCATION >= currentX && target.getY1()-LOCATION <= currentY && target.getY1()+LOCATION >= currentY) { // 오른쪽의 점을 누르면
					
					isResizing = true;

					for (Shape alltarget : allTarget) {
						if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
							if (((BoundedShape) alltarget).getStartWidth() + currentX - pointX < limit && (currentX - pointX) < 0) continue;
							else if (((BoundedShape) alltarget).getStartHeight() - (currentY - pointY) < limit && (currentY - pointY) > 0) continue;
							alltarget.setY1(alltarget.getStartY1() + currentY - pointY);
							((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() + currentX - pointX);
							((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() - (currentY - pointY));
						}
						if (alltarget instanceof Line) {
							if (alltarget.getStartX1() + currentX - pointX < limit && (currentX - pointX) < 0) continue;
							else if (alltarget.getStartY1() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
							((Line) alltarget).setX1(alltarget.getStartX1() + currentX - pointX);
							((Line) alltarget).setY1(alltarget.getStartY1() + currentY - pointY);
						}
					}
					return;
					
				} else if (((Line) target).getX2()-LOCATION <= currentX && ((Line) target).getX2()+LOCATION >= currentX 
						&& ((Line) target).getY2()-LOCATION <= currentY && ((Line) target).getY2()+LOCATION >= currentY) { // 왼쪽의 점을 누르면
					
					isResizing = true;

					for (Shape alltarget : allTarget) {
						if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
							if (((BoundedShape) alltarget).getStartWidth() - (currentX - pointX) < limit && (currentX - pointX) > 0) continue;
							else if (((BoundedShape) alltarget).getStartHeight() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
							alltarget.setX1(alltarget.getStartX1() + currentX - pointX);
							((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() - (currentX - pointX));
							((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() + currentY - pointY);
						}
						if (alltarget instanceof Line) {
							if (((Line) alltarget).getStartX2() + currentX - pointX < limit && (currentX - pointX) < 0) continue;
							else if (((Line) alltarget).getStartY2() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
							((Line) alltarget).setX2(((Line) alltarget).getStartX2() + currentX - pointX);
							((Line) alltarget).setY2(((Line) alltarget).getStartY2() + currentY - pointY);
						}
					}
					return;
				}
				
			} else { // 왼쪽 시작 직선일 때
				if (target.getX1()-LOCATION <= currentX && target.getX1()+LOCATION >= currentX && target.getY1()-LOCATION <= currentY && target.getY1()+LOCATION >= currentY) { // 왼쪽의 점을 누르면
					
					isResizing = true;

					for (Shape alltarget : allTarget) {
						if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
							if (((BoundedShape) alltarget).getStartWidth() - (currentX - pointX) < limit && (currentX - pointX) > 0) continue;
							else if (((BoundedShape) alltarget).getStartHeight() - (currentY - pointY) < limit && (currentY - pointY) > 0) continue;
							alltarget.setX1(alltarget.getStartX1() + currentX - pointX);
							alltarget.setY1(alltarget.getStartY1() + currentY - pointY);
							((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() - (currentX - pointX));
							((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() - (currentY - pointY));
						}
						if (alltarget instanceof Line) {
							if (alltarget.getStartX1() + currentX - pointX < limit && (currentX - pointX) < 0) continue;
							else if (alltarget.getStartY1() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
							alltarget.setX1(alltarget.getStartX1() + currentX - pointX);
							alltarget.setY1(alltarget.getStartY1() + currentY - pointY);
						}
					}
					return;
					
				} else if (((Line) target).getX2()-LOCATION <= currentX && ((Line) target).getX2()+LOCATION >= currentX 
						&& ((Line) target).getY2()-LOCATION <= currentY && ((Line) target).getY2()+LOCATION >= currentY) { // 오른쪽의 점을 누르면
					
					isResizing = true;

					for (Shape alltarget : allTarget) {
						if (alltarget instanceof BoundedShape && !(alltarget instanceof Text)) {
							if (((BoundedShape) alltarget).getStartWidth() + currentX - pointX < limit && (currentX - pointX) < 0) continue;
							else if (((BoundedShape) alltarget).getStartHeight() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
							((BoundedShape) alltarget).setWidth(((BoundedShape) alltarget).getStartWidth() + currentX - pointX);
							((BoundedShape) alltarget).setHeight(((BoundedShape) alltarget).getStartHeight() + currentY - pointY);
						}
						if (alltarget instanceof Line) {
							if (((Line) alltarget).getStartX2() + currentX - pointX < limit && (currentX - pointX) < 0) continue;
							else if (((Line) alltarget).getStartY2() + currentY - pointY < limit && (currentY - pointY) < 0) continue;
							((Line) alltarget).setX2(((Line) alltarget).getStartX2() + currentX - pointX);
							((Line) alltarget).setY2(((Line) alltarget).getStartY2() + currentY - pointY);
						}
					}
					return;
					
				}			
			}
			
		}

	}
}