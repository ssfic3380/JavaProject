import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.ImageIcon;

/**
 * 이름 : Image
 * 역할 : Graphic Editor에 그려지는 이미지의 정보를 저장하는 클래스로, BoundedShape을 상속했다.
 */

public class Image extends BoundedShape {
	
	private String path;
	private ImageIcon icon;
	//private java.awt.Image img;

	public Image(int x1, int y1, int width, int height, String path, Boolean isSelected) {
		super(x1, y1, width, height, 3, Color.WHITE, Color.WHITE, true, isSelected);
		this.path = path;
		this.icon = new ImageIcon(path);
	}
	
	
	/* Get Method */
	public String getPath() {
		return this.path;
	}
	
	public ImageIcon getImageIcon() {
		return this.icon;
	}
	
	/* Set Method */
	public void setPath(String path) {
		this.path = path;
	}
	
	public void setImageIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
	/* Tool */
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke thickShape = new BasicStroke(getThickness());
		Stroke dotLine = new BasicStroke(getThickness(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);

		if (!getSelected()) {
			g2d.setStroke(thickShape);
			g2d.setColor(getLineColor());
			g2d.drawRect(getX1() - 1, getY1() - 1, getWidth() + 1, getHeight() + 1);
			java.awt.Image img = getImageIcon().getImage();
			g.drawImage(img, getX1(), getY1(), getWidth(), getHeight(), null);
		} else {
			g2d.setStroke(dotLine);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(getX1() - 1, getY1() - 1, getWidth() + 1, getHeight() + 1);

			java.awt.Image img = getImageIcon().getImage();
			g.drawImage(img, getX1(), getY1(), getWidth(), getHeight(), null);
			
			/* 선택시 영역표시용 테두리 */
			/*
			g.setColor(new Color(189, 189, 189));
			g.drawRect(getX1(), getY1(), getWidth(), getHeight());
			*/
		}

	}
}