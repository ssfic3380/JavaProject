import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * �̸� : Rectangle
 * ���� : Graphic Editor�� �׷����� ���� �� Rectangle�� ������ �����ϴ� Ŭ������, BoundedShape�� ����ߴ�.
 */

public class Rectangle extends BoundedShape {

	public Rectangle(int x1, int y1, int width, int height, int thickness, Color lineColor, Color fillColor, Boolean isFillShape, Boolean isSelected) {
		super(x1, y1, width, height, thickness, lineColor, fillColor, isFillShape, isSelected);
	}

	
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke thickShape = new BasicStroke(getThickness());
		Stroke dotLine = new BasicStroke(getThickness(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		
		if (getFillShape()) {
			if (!getSelected()) {
				g2d.setStroke(thickShape);
				g2d.setColor(getLineColor());
				g2d.drawRect(getX1() - 1, getY1() - 1, getWidth() + 1, getHeight() + 1);
				g2d.setColor(getFillColor());
				g2d.fillRect(getX1(), getY1(), getWidth(), getHeight());
			} else {
		        g2d.setStroke(dotLine);
		        g2d.setColor(getLineColor());
		        g2d.drawRect(getX1() - 1, getY1() - 1, getWidth() + 1, getHeight() + 1);
				
		        g2d.setStroke(thickShape);
				g2d.setColor(getFillColor());
				g2d.fillRect(getX1(), getY1(), getWidth(), getHeight());
				
				/* ���ý� ����ǥ�ÿ� �׵θ� */
				g.setColor(new Color(189, 189, 189));
				g.drawRect(getX1(), getY1(), getWidth(), getHeight());
			}
		} else {
			if (!getSelected()) {
				g2d.setStroke(thickShape);
				g2d.setColor(getLineColor());
				g2d.drawRect(getX1(), getY1(), getWidth(), getHeight());
			} else {
		        g2d.setStroke(dotLine);
		        g2d.setColor(getLineColor());
		        g2d.drawRect(getX1(), getY1(), getWidth(), getHeight());
		        g2d.dispose();
		        
		        /* ���ý� ����ǥ�ÿ� �׵θ� */
				g.setColor(new Color(189, 189, 189));
				g.drawRect(getX1(), getY1(), getWidth(), getHeight());
			}
		}
	}
}