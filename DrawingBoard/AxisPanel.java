import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * �̸� : AxisPanel
 * ���� : EditorFrame�� ��ܿ� ��ġ�Ͽ� ���� ���콺�� ��ġ�� ��ǥ�� �˸��� Ŭ������, JPanel�� ����ߴ�.
 */

public class AxisPanel extends JPanel {

	private int x = 0;
	private int y = 0;
	public static JTextField textField = null;

	public AxisPanel() {
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(new Color(250, 224, 212));
		
		String text = String.format("  ���콺 ��ǥ(x, y) = (%d, %d)", x, y);

		textField = new JTextField();
		textField.setText(text);
		textField.setFont(new Font("����ü", Font.BOLD, 15));
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
