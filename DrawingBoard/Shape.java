import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * 이름 : Shape
 * 역할 : Graphic Editor에 그려지는 도형의 정보를 저장하는 최상위 클래스이다.
 *     : 좌표 정보, 선의 색깔 정보, 선의 두께 정보 등을 가지고 있다.
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