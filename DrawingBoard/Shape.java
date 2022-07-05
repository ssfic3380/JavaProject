import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * �̸� : Shape
 * ���� : Graphic Editor�� �׷����� ������ ������ �����ϴ� �ֻ��� Ŭ�����̴�.
 *     : ��ǥ ����, ���� ���� ����, ���� �β� ���� ���� ������ �ִ�.
 */

public abstract class Shape implements Serializable {
	
	private int x1;
	private int y1;
	private int startX1;
	private int startY1;
	private int thickness = 3;
	private Color lineColor;
	private Boolean isSelected;

	public Shape(int x1, int y1, int thickness, Color lineColor, Boolean isSelected) {
		this.x1 = x1;
		this.y1 = y1;
		this.thickness = thickness;
		this.lineColor = lineColor;
		this.isSelected = isSelected;
	}
	
	
	/* Get Method */
	public int getX1() {
		return this.x1;
	}
	
	public int getY1() {
		return this.y1;
	}
	
	public int getStartX1() {
		return this.startX1;
	}
	
	public int getStartY1() {
		return this.startY1;
	}
	
	public int getThickness() {
		return this.thickness;
	}
	
	public Color getLineColor() {
		return this.lineColor;
	}
	
	public Boolean getSelected() {
		return this.isSelected;
	}
	
	/* Set Method */
	public void setX1(int x1) {
		this.x1 = x1;
	}
	
	public void setY1(int y1) {
		this.y1 = y1;
	}
	
	public void setStartX1(int startX1) {
		this.startX1 = startX1;
	}
	
	public void setStartY1(int startY1) {
		this.startY1 = startY1;
	}
	
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	
	public void setSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	/* Tool */
	public abstract void draw(Graphics g);
}