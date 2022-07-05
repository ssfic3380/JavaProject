import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * 이름 : Text
 * 역할 : Graphic Editor에 그려지는 텍스트의 정보를 저장하는 클래스로, BoundedShape을 상속했다.
 */

public class Text extends BoundedShape {

	private Font font;
	private Color textColor;
	private String text;

	public Text(int x1, int y1, String text, Font font, Color textColor, Boolean isSelected) {
		super(x1, y1, 50, 50, 3, Color.WHITE, Color.WHITE, true, isSelected);
		this.text = text;
		this.font = font;
		this.textColor = textColor;
	}
	
	/* Get Method */	
	public Font getFont() {
		return this.font;
	}
	
	public Color getTextColor() {
		return this.textColor;
	}
	
	public String getText() {
		return this.text;
	}
	
	/* Set Method */
	public void setFont(Font font) {
		this.font = font;
	}
	
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void draw(Graphics g) {

		if (!getSelected()) {
			g.setColor(getTextColor());
			g.setFont(getFont());
			g.drawString(text, getX1(), getY1());
		} else {			
			g.setColor(new Color(189, 189, 189));
			g.fillOval(getX1(), getY1(), 5, 5);
			
			g.setColor(getTextColor());
			g.setFont(getFont());
			g.drawString(text, getX1(), getY1());
		}
	}
}