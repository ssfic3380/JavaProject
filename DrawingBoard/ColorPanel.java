import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;
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
 * 이름 : ColorPanel
 * 역할 : EditorFrame의 하단에 위치하여 색 변경 관련 툴을 담당하는 클래스로, JPanel을 상속했다.
 *     : EditorFrame의 static 변수에 정보를 전달하면, DrawPanel이 그것을 사용하는 방식이다.
 *     : 사용자가 원하는 색깔이 무엇인지 알리는 역할을 담당한다.
 */

public class ColorPanel extends JPanel {
	
	private int buttonXSize = 20;
	private int buttonYSize = 20;
	
	private JToggleButton red;
	private JToggleButton orange;
	private JToggleButton yellow;
	private JToggleButton light_green;
	private JToggleButton green;
	private JToggleButton sky;
	private JToggleButton blue;
	private JToggleButton indigo;
	private JToggleButton purple;
	private JToggleButton pink;
	private JToggleButton hot_pink;
	private JToggleButton black;
	private JToggleButton white;
	private JToggleButton noColor;
	private ButtonGroup colors;
	
	private JButton changeLine;
	private JButton changeFill;
	private JButton changeTextColor;
	
	public ColorPanel() {
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(new Color(196, 222, 255));
		this.setBorder(new TitledBorder(new LineBorder(new Color(189, 189, 189), 1), "Color"));
				
		colors = new ButtonGroup();
		colors.add(red);
		colors.add(orange);
		colors.add(yellow);
		colors.add(light_green);
		colors.add(green);
		colors.add(sky);
		colors.add(blue);
		colors.add(indigo);
		colors.add(purple);
		colors.add(pink);
		colors.add(hot_pink);
		colors.add(black);
		colors.add(white);	
		colors.add(noColor);
		
		// red
		red = new JToggleButton("");
		red.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		red.setBackground(new Color(255, 0, 0));
		red.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					orange.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(255, 0, 0);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(red);
		
		// orange
		orange = new JToggleButton("");
		orange.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		orange.setBackground(new Color(255, 187, 0));
		orange.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(255, 187, 0);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(orange);

		// yellow
		yellow = new JToggleButton("");
		yellow.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		yellow.setBackground(new Color(255, 228, 0));
		yellow.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(255, 228, 0);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(yellow);
		
		// light_green
		light_green = new JToggleButton("");
		light_green.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		light_green.setBackground(new Color(171, 242, 0));
		light_green.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					yellow.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(171, 242, 0);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(light_green);
		
		// green
		green = new JToggleButton("");
		green.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		green.setBackground(new Color(29, 219, 22));
		green.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(29, 219, 22);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(green);
		
		// sky
		sky = new JToggleButton("");
		sky.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		sky.setBackground(new Color(0, 216, 255));
		sky.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(0, 216, 255);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(sky);
		
		// blue
		blue = new JToggleButton("");
		blue.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		blue.setBackground(new Color(0, 84, 255));
		blue.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(0, 84, 255);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(blue);
		
		// indigo
		indigo = new JToggleButton("");
		indigo.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		indigo.setBackground(new Color(1, 0, 255));
		indigo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(1, 0, 255);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(indigo);
		
		// purple
		purple = new JToggleButton("");
		purple.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		purple.setBackground(new Color(95, 0, 255));
		purple.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(95, 0, 255);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(purple);
		
		// pink
		pink = new JToggleButton("");
		pink.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		pink.setBackground(new Color(255, 0, 221));
		pink.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(255, 0, 221);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(pink);
		
		// hot_pink
		hot_pink = new JToggleButton("");
		hot_pink.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		hot_pink.setBackground(new Color(255, 0, 127));
		hot_pink.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(255, 0, 127);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(hot_pink);
		
		// black
		black = new JToggleButton("");
		black.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		black.setBackground(new Color(0, 0, 0));
		black.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					white.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(0, 0, 0);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(black);
		
		// white
		white = new JToggleButton("");
		white.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		white.setBackground(new Color(255, 255, 255));
		white.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					noColor.setSelected(false);
					EditorFrame.currentColor = new Color(255, 255, 255);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
					noColor.setSelected(true);
				}
			}
		});
		add(white);
		
		// noColor
		noColor = new JToggleButton("Remove");
		noColor.setPreferredSize(new Dimension(buttonXSize*4, buttonYSize));
		noColor.setBackground(SystemColor.menu);
		noColor.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					red.setSelected(false);
					orange.setSelected(false);
					yellow.setSelected(false);
					light_green.setSelected(false);
					green.setSelected(false);
					sky.setSelected(false);
					blue.setSelected(false);
					indigo.setSelected(false);
					purple.setSelected(false);
					pink.setSelected(false);
					hot_pink.setSelected(false);
					black.setSelected(false);
					white.setSelected(false);
					EditorFrame.currentColor = null;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentColor = null;
				}
			}
		});
		add(noColor);
		
		// 빈 공간용
		JPanel temp1 = new JPanel();
		temp1.setPreferredSize(new Dimension(100, buttonYSize));
		temp1.setBackground(new Color(196, 222, 255));
		add(temp1);		
		
		// changeLine
		changeLine = new JButton("Line");
		changeLine.setPreferredSize(new Dimension(100, buttonYSize));
		changeLine.setBackground(new Color(255, 255, 255));
		changeLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				EditorFrame.drawPanel.changeLineColor();
			}
		});
		add(changeLine);
		
		// changeFill
		changeFill = new JButton("Fill");
		changeFill.setPreferredSize(new Dimension(100, buttonYSize));
		changeFill.setBackground(new Color(255, 255, 255));
		changeFill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				EditorFrame.drawPanel.changeFillColor();
			}
		});
		add(changeFill);
		
		// changeTextColor
		changeTextColor = new JButton("Text");
		changeTextColor.setPreferredSize(new Dimension(100, buttonYSize));
		changeTextColor.setBackground(new Color(255, 255, 255));
		changeTextColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				EditorFrame.drawPanel.changeTextColor();
			}
		});
		add(changeTextColor);
	}
	
	public void initialize() {
		red.setSelected(false);
		orange.setSelected(false);
		yellow.setSelected(false);
		light_green.setSelected(false);
		green.setSelected(false);
		sky.setSelected(false);
		blue.setSelected(false);
		indigo.setSelected(false);
		purple.setSelected(false);
		pink.setSelected(false);
		hot_pink.setSelected(false);
		black.setSelected(false);
		white.setSelected(false);
		noColor.setSelected(true);
	}
	
}
