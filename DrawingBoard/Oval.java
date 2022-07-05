import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * �̸� : Oval
 * ���� : Graphic Editor�� �׷����� ���� �� Oval�� ������ �����ϴ� Ŭ������, BoundedShape�� ����ߴ�.
 */

public class Oval extends BoundedShape {

	public Oval(int x1, int y1, int width, int height, int thickness, Color lineColor, Color fillColor, Boolean isFillShape, Boolean isSelected) {
		super(x1, y1, width, height, thickness, lineColor, fillColor, isFillShape, isSelected);
	}
	

	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke thickShape = new BasicStroke(getThickness());
		Stroke dotLine = new BasicStroke(getThickness(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{7}, 0);
		
		if (getFillShape()) {
			if (!getSelected()) {
				g2d.setStroke(thickShape);
				g2d.setColor(getLineColor());
				g2d.drawOval(getX1() - 1, getY1() - 1, getWidth() + 2, getHeight() + 2);
				g2d.setColor(getFillColor());
				g2d.fillOval(getX1(), getY1(), getWidth(), getHeight());
			} else {
		        g2d.setStroke(dotLine);
		        g2d.setColor(getLineColor());
		        g2d.drawOval(getX1() - 1, getY1() - 1, getWidth() + 2, getHeight() + 2);
		        
		        g2d.setStroke(thickShape);
				g2d.setColor(getFillColor());
				g2d.fillOval(getX1(), getY1(), getWidth(), getHeight());

				/* ���ý� ����ǥ�ÿ� �׵θ� */
				g.setColor(new Color(189, 189, 189));
				g.drawRect(getX1(), getY1(), getWidth(), getHeight());
			}
		} else {
			if (!getSelected()) {
				g2d.setStroke(thickShape);
				g2d.setColor(getLineColor());
				g2d.drawOval(getX1(), getY1(), getWidth(), getHeight());
			} else {
		        g2d.setStroke(dotLine);
		        g2d.setColor(getLineColor());
		        g2d.drawOval(getX1(), getY1(), getWidth(), getHeight());
		        g2d.dispose();

		        /* ���ý� ����ǥ�ÿ� �׵θ� */
				g.setColor(new Color(189, 189, 189));
				g.drawRect(getX1(), getY1(), getWidth(), getHeight());
			}
		}
	}
}