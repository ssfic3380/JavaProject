import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 이름 : AxisPanel
 * 역할 : EditorFrame의 상단에 위치하여 현재 마우스가 위치한 좌표를 알리는 클래스로, JPanel을 상속했다.
 */

public class AxisPanel extends JPanel {

	private int x = 0;
	private int y = 0;
	public static JTextField textField = null;

	public AxisPanel() {
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(new Color(250, 224, 212));
		
		String text = String.format("  마우스 좌표(x, y) = (%d, %d)", x, y);

		textField = new JTextField();
		textField.setText(text);
		textField.setFont(new Font("굴림체", Font.BOLD, 15));
		textField.setDragEnabled(false);
		textField.setEditable(false);
		textField.setBackground(new Color(232, 217, 255));
		textField.setPreferredSize(new Dimension(770, 30));
		add(textField);

	}
	
	
	/* Get Method */
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	/* Set Method */
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public static void setText(String text) {
		textField.setText(text);
	}
	
}
