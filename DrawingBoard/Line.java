import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * 이름 : Line
 * 역할 : Graphic Editor에 그려지는 도형 중 Line의 정보를 저장하는 클래스로, Shape을 상속했다.
 *     : 끝나는 지점의 X좌표, Y좌표를 지장해야 하는 도형의 정보를 담당한다.
 */

public class Line extends Shape {

	private int x2;
	private int y2;
	private int startX2;
	private int startY2;

	public Line(int x1, int y1, int x2, int y2, int thickness, Color lineColor, Boolean isSelected) {
		super(x1, y1, thickness, lineColor, isSelected);
		this.x2 = x2;
		this.y2 = y2;
	}
	

	public int getX2() {
		return this.x2;
	}

	public int getY2() {
		return this.y2;
	}
	
	public int getStartX2() {
		return this.startX2;
	}
	
	public int getStartY2() {
		return this.startY2;
	}
	
	public void setX2(int x2) {
		this.x2 = x2;
	}
	
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public void setStartX2(int startX2) {
		this.startX2 = startX2;
	}
	
	public void setStartY2(int startY2) {
		this.startY2 = startY2;
	}

	@Override
	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		Stroke thickShape = new BasicStroke(getThickness());
		Stroke dotLine = new BasicStroke(getThickness(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		
		if (!getSelected()) {
			g2d.setStroke(thickShape);
			g2d.setColor(getLineColor());
			g2d.drawLine(getX1(), getY1(), getX2(), getY2());
		} else {
	        g2d.setStroke(dotLine);
	        g2d.setColor(getLineColor());
	        g2d.drawLine(getX1(), getY1(), getX2(), getY2());

	        /* 선택시 영역표시용 테두리 */
			if (getX1() > getX2()) {
				g.setColor(new Color(189, 189, 189));
				if (getY1() > getY2()) {
					g.drawRect(getX2(), getY2(), Math.abs(getX1() - getX2()), Math.abs(getY1() - getY2()));
				} else {
					g.drawRect(getX2(), getY1(), Math.abs(getX1() - getX2()), Math.abs(getY1() - getY2()));
				}
			}
			else {
				g.setColor(new Color(189, 189, 189));
				if (getY1() > getY2()) {
					g.drawRect(getX1(), getY2(), Math.abs(getX1() - getX2()), Math.abs(getY1() - getY2()));
				} else {
					g.drawRect(getX1(), getY1(), Math.abs(getX1() - getX2()), Math.abs(getY1() - getY2()));
				}
			}
		}
	}
}