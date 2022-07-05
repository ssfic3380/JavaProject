import java.awt.Color;
import java.awt.Graphics;

/**
 * �̸� : BoundedShape
 * ���� : Graphic Editor�� �׷����� ������ ������ �����ϴ� Ŭ������, Shape�� ����ߴ�.
 *     : Width�� Height�� �����ؾ� �ϴ� ������ ������ ����Ѵ�.
 */

public abstract class BoundedShape extends Shape {
	
	private int width;
	private int height;
	private int startWidth;
	private int startHeight;
	private Color fillColor;
	private Boolean isFillShape;

	public BoundedShape(int x1, int y1, int width, int height, int thickness, Color lineColor, Color fillColor, Boolean isFillShape, Boolean isSelected) {
		super(x1, y1, thickness, lineColor, isSelected);
		this.width = width;
		this.height = height;
		this.fillColor = fillColor;
		this.isFillShape = isFillShape;
	}

	
	/* Get Method */
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getStartWidth() {
		return this.startWidth;
	}
	
	public int getStartHeight() {
		return this.startHeight;
	}
	
	public Color getFillColor() {
		return this.fillColor;
	}
	
	public Boolean getFillShape() {
		return this.isFillShape;
	}
	
	/* Set Method */
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setStartWidth(int startWidth) {
		this.startWidth = startWidth;
	}
	
	public void setStartHeight(int startHeight) {
		this.startHeight = startHeight;
	}
	
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public void setFillShape(Boolean isFillShape) {
		this.isFillShape = isFillShape;
	}
	
	/* Tool */
	public abstract void draw(Graphics g);
}