import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * 이름 : SizePanel
 * 역할 : EditorFrame의 하단에 위치하여 크기 변경 관련 툴을 담당하는 클래스로, JPanel을 상속했다.
 *     : EditorFrame의 static 변수에 정보를 전달하면, DrawPanel이 그것을 사용하는 방식이다.
 *     : 사용자가 원하는 크기가 무엇인지 알리는 역할을 담당한다.
 */

public class SizePanel extends JPanel {
	
	private int buttonXSize = 50;
	private int buttonYSize = 20;
	
	private JToggleButton t1;
	private JToggleButton t3;
	private JToggleButton t5;
	private JToggleButton t7;
	private JToggleButton t9;
	private JToggleButton t11;
	private JToggleButton t13;
	private JToggleButton t15;
	private ButtonGroup thicknessButton;
	
	private JButton thickness;
	private JButton textSize;

	public SizePanel() {
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(new Color(196, 222, 255));
		this.setBorder(new TitledBorder(new LineBorder(new Color(189, 189, 189), 1), "Size"));
		
		thicknessButton = new ButtonGroup();
		thicknessButton.add(t1);
		thicknessButton.add(t3);
		thicknessButton.add(t5);
		thicknessButton.add(t7);
		thicknessButton.add(t9);
		thicknessButton.add(t11);
		thicknessButton.add(t13);
		thicknessButton.add(t15);
		
		// t1
		t1 = new JToggleButton("1");
		t1.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		t1.setBackground(new Color(255, 255, 255));
		t1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					t3.setSelected(false);
					t5.setSelected(false);
					t7.setSelected(false);
					t9.setSelected(false);
					t11.setSelected(false);
					t13.setSelected(false);
					t15.setSelected(false);
					EditorFrame.currentSize = 1;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentSize = 3;
					t3.setSelected(true);
				}
			}
		});
		add(t1);
		
		// t3
		t3 = new JToggleButton("3");
		t3.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		t3.setBackground(new Color(255, 255, 255));
		t3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					t1.setSelected(false);
					t5.setSelected(false);
					t7.setSelected(false);
					t9.setSelected(false);
					t11.setSelected(false);
					t13.setSelected(false);
					t15.setSelected(false);
					EditorFrame.currentSize = 3;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentSize = 3;
				}
			}
		});
		add(t3);
		
		// t5
		t5 = new JToggleButton("5");
		t5.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		t5.setBackground(new Color(255, 255, 255));
		t5.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					t1.setSelected(false);
					t3.setSelected(false);
					t7.setSelected(false);
					t9.setSelected(false);
					t11.setSelected(false);
					t13.setSelected(false);
					t15.setSelected(false);
					EditorFrame.currentSize = 5;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentSize = 3;
					t3.setSelected(true);
				}
			}
		});
		add(t5);
		
		// t7
		t7 = new JToggleButton("7");
		t7.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		t7.setBackground(new Color(255, 255, 255));
		t7.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					t1.setSelected(false);
					t3.setSelected(false);
					t5.setSelected(false);
					t9.setSelected(false);
					t11.setSelected(false);
					t13.setSelected(false);
					t15.setSelected(false);
					EditorFrame.currentSize = 7;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentSize = 3;
					t3.setSelected(true);
				}
			}
		});
		add(t7);
		
		// t9
		t9 = new JToggleButton("9");
		t9.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		t9.setBackground(new Color(255, 255, 255));
		t9.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					t1.setSelected(false);
					t3.setSelected(false);
					t5.setSelected(false);
					t7.setSelected(false);
					t11.setSelected(false);
					t13.setSelected(false);
					t15.setSelected(false);
					EditorFrame.currentSize = 9;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentSize = 3;
					t3.setSelected(true);
				}
			}
		});
		add(t9);
		
		// t11
		t11 = new JToggleButton("11");
		t11.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		t11.setBackground(new Color(255, 255, 255));
		t11.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					t1.setSelected(false);
					t3.setSelected(false);
					t5.setSelected(false);
					t7.setSelected(false);
					t9.setSelected(false);
					t13.setSelected(false);
					t15.setSelected(false);
					EditorFrame.currentSize = 11;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentSize = 3;
					t3.setSelected(true);
				}
			}
		});
		add(t11);

		// t13
		t13 = new JToggleButton("13");
		t13.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		t13.setBackground(new Color(255, 255, 255));
		t13.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					t1.setSelected(false);
					t3.setSelected(false);
					t5.setSelected(false);
					t7.setSelected(false);
					t9.setSelected(false);
					t11.setSelected(false);
					t15.setSelected(false);
					EditorFrame.currentSize = 13;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentSize = 3;
					t3.setSelected(true);
				}
			}
		});
		add(t13);
		
		// t15
		t15 = new JToggleButton("15");
		t15.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		t15.setBackground(new Color(255, 255, 255));
		t15.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					t1.setSelected(false);
					t3.setSelected(false);
					t5.setSelected(false);
					t7.setSelected(false);
					t9.setSelected(false);
					t11.setSelected(false);
					t13.setSelected(false);
					EditorFrame.currentSize = 15;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentSize = 3;
					t3.setSelected(true);
				}
			}
		});
		add(t15);
		
		// 빈 공간용
		JPanel temp1 = new JPanel();
		temp1.setPreferredSize(new Dimension(175, buttonYSize));
		temp1.setBackground(new Color(196, 222, 255));
		add(temp1);
		
		// Thickness
		thickness = new JButton("Thickness");
		thickness.setPreferredSize(new Dimension(100, buttonYSize));
		thickness.setBackground(new Color(255, 255, 255));
		thickness.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				EditorFrame.drawPanel.changeThickness();
			}
		});
		add(thickness);
		
		// textSize
		textSize = new JButton("Text");
		textSize.setPreferredSize(new Dimension(100, buttonYSize));
		textSize.setBackground(new Color(255, 255, 255));
		textSize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				EditorFrame.drawPanel.changeTextSize();
			}
		});
		add(textSize);
	}
	
	public void initialize() {
		t1.setSelected(false);
		t3.setSelected(true);
		t5.setSelected(false);
		t7.setSelected(false);
		t9.setSelected(false);
		t11.setSelected(false);
		t13.setSelected(false);
		t15.setSelected(false);
	}
	
}
