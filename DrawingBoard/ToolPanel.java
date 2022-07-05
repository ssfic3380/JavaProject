import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * 이름 : ToolPanel
 * 역할 : EditorFrame의 상단에 위치하여 그릴 도형을 선택하는 툴을 담당하는 클래스로, JPanel을 상속했다.
 *     : EditorFrame의 static 변수에 정보를 전달하면, DrawPanel이 그것을 사용하는 방식이다.
 *     : 사용자가 원하는 도형이 무엇인지 알리는 역할을 담당한다.
 */

public class ToolPanel extends JPanel {
	
	private int buttonXSize = 70;
	private int buttonYSize = 30;
	
	/* File Buttons */
	private JButton saveButton;
	private JButton openButton;
	private ButtonGroup fileButtons;
	
	/* Drawing Buttons */
	private JToggleButton selectButton;
	private JToggleButton ovalButton;
	private JToggleButton rectButton;
	private JToggleButton lineButton;
	private JToggleButton imageButton;
	private JToggleButton textButton;
	private ButtonGroup drawingButtons;
	
	/* Function Buttons */
	private JButton copyShapeButton;
	private JButton pasteShapeButton;
	private JButton deleteShapeButton;
	private ButtonGroup functionButtons;

	
	public ToolPanel() {

		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(new Color(196, 222, 255));
		
		
		/* File Buttons */
		fileButtons = new ButtonGroup();
		fileButtons.add(saveButton);
		fileButtons.add(openButton);
		
		// openButton
		openButton = new JButton("열기");
		openButton.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		openButton.setBackground(new Color(255, 255, 255));
		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				
				JFileChooser fileChooser;
				if (EditorFrame.currentPath != null) fileChooser = new JFileChooser(EditorFrame.currentPath);
				else fileChooser = new JFileChooser("C:");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Term Project 3 파일(.tpt)", "tpt");
				fileChooser.addChoosableFileFilter(filter);

				int result = fileChooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					File fileName = fileChooser.getSelectedFile();
					
					if ((fileName == null) || (fileName.getName().equals(""))) {
						JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid Name", JOptionPane.ERROR_MESSAGE);
						EditorFrame.currentPath = null;
					}
					
					EditorFrame.currentPath = fileName.getAbsolutePath();
					
					SaveShape.openFile();
					SaveShape.readRecords();
					SaveShape.closeReadFile();
					EditorFrame.drawPanel.repaint();
				}	
				
			}
		});
		add(openButton);
		
		// saveButton
		saveButton = new JButton("저장");
		saveButton.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		saveButton.setBackground(new Color(255, 255, 255));
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				
				JFileChooser fileChooser;
				if (EditorFrame.currentPath != null) fileChooser = new JFileChooser(EditorFrame.currentPath);
				else fileChooser = new JFileChooser("C:");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Term Project 3 파일(.tpt)", "tpt");
				fileChooser.addChoosableFileFilter(filter);

				int result = fileChooser.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					File fileName = fileChooser.getSelectedFile();
					
					if ((fileName == null) || (fileName.getName().equals(""))) {
						JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid Name", JOptionPane.ERROR_MESSAGE);
						EditorFrame.currentPath = null;
					}
					
					EditorFrame.currentPath = fileName.getAbsolutePath();
					
					SaveShape.makeFile();
					SaveShape.addRecords();
					SaveShape.closeCreateFile();
				}

			}
		});
		add(saveButton);
		
		// 빈 공간용
		JPanel temp1 = new JPanel();
		temp1.setPreferredSize(new Dimension(20, buttonYSize));
		temp1.setBackground(new Color(196, 222, 255));
		add(temp1);
		
		/* Drawing Buttons */
		drawingButtons = new ButtonGroup();
		drawingButtons.add(selectButton);
		drawingButtons.add(ovalButton);
		drawingButtons.add(rectButton);
		drawingButtons.add(lineButton);
		drawingButtons.add(imageButton);
		drawingButtons.add(textButton);
		
		// selectButton
		selectButton = new JToggleButton("선택");
		selectButton.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		selectButton.setBackground(new Color(255, 255, 255));
		selectButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ovalButton.setSelected(false);
					rectButton.setSelected(false);
					lineButton.setSelected(false);
					imageButton.setSelected(false);
					textButton.setSelected(false);
					EditorFrame.currentShape = "SELECT";
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentShape = null;
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
				}
			}
		});
		add(selectButton);
		
		// ovalButton
		ovalButton = new JToggleButton("원");
		ovalButton.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		ovalButton.setBackground(new Color(255, 255, 255));
		ovalButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectButton.setSelected(false);
					rectButton.setSelected(false);
					lineButton.setSelected(false);
					imageButton.setSelected(false);
					textButton.setSelected(false);
					EditorFrame.currentShape = "OVAL";
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentShape = null;
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
				}
			}
		});
		add(ovalButton);
		
		// rectButton
		rectButton = new JToggleButton("사각형");
		rectButton.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		rectButton.setBackground(new Color(255, 255, 255));
		rectButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectButton.setSelected(false);
					ovalButton.setSelected(false);
					lineButton.setSelected(false);
					imageButton.setSelected(false);
					textButton.setSelected(false);
					EditorFrame.currentShape = "RECT";
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentShape = null;
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
				}
			}
		});
		add(rectButton);
		
		// lineButton
		lineButton = new JToggleButton("선");
		lineButton.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		lineButton.setBackground(new Color(255, 255, 255));
		lineButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectButton.setSelected(false);
					ovalButton.setSelected(false);
					rectButton.setSelected(false);
					imageButton.setSelected(false);
					textButton.setSelected(false);
					EditorFrame.currentShape = "LINE";
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					EditorFrame.currentShape = null;
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
				}
			}
		});
		add(lineButton);
		
		// openImageButton
		imageButton = new JToggleButton("이미지");
		imageButton.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		imageButton.setBackground(new Color(255, 255, 255));
		imageButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectButton.setSelected(false);
					ovalButton.setSelected(false);
					rectButton.setSelected(false);
					lineButton.setSelected(false);
					textButton.setSelected(false);
					EditorFrame.currentShape = "IMAGE";
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
					
					JFileChooser fileChooser;
					if (EditorFrame.currentPath != null) fileChooser = new JFileChooser(EditorFrame.currentPath);
					else fileChooser = new JFileChooser("C:");
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

					int result = fileChooser.showOpenDialog(null);

					if (result == JFileChooser.APPROVE_OPTION) {
						File fileName = fileChooser.getSelectedFile();
						
						if ((fileName == null) || (fileName.getName().equals(""))) {
							JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid Name", JOptionPane.ERROR_MESSAGE);
							EditorFrame.currentPath = null;
						}
						
						EditorFrame.currentPath = fileName.getAbsolutePath();
					}
					
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
				}
				
			}
		});
		add(imageButton);
		
		// openImageButton
		textButton = new JToggleButton("텍스트");
		textButton.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		textButton.setBackground(new Color(255, 255, 255));
		textButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectButton.setSelected(false);
					ovalButton.setSelected(false);
					rectButton.setSelected(false);
					lineButton.setSelected(false);
					imageButton.setSelected(false);
					EditorFrame.currentShape = "TEXT";
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
					
					EditorFrame.currentText = JOptionPane.showInputDialog("텍스트를 입력해주세요.");
					
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					//EditorFrame.currentShape = null;
					String text = String.format("currentShape = %s", EditorFrame.currentShape);
					AxisPanel.setText(text);
				}
			}
		});
		add(textButton);
		
		// 빈 공간용
		JPanel temp2 = new JPanel();
		temp2.setPreferredSize(new Dimension(20, buttonYSize));
		temp2.setBackground(new Color(196, 222, 255));
		add(temp2);
		
		/* Function Buttons */
		functionButtons = new ButtonGroup();
		functionButtons.add(copyShapeButton);
		functionButtons.add(pasteShapeButton);
		functionButtons.add(deleteShapeButton);
		
		// copyShapeButton
		copyShapeButton = new JButton("복사");
		copyShapeButton.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		copyShapeButton.setBackground(new Color(255, 255, 255));
		copyShapeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				EditorFrame.drawPanel.copy();
			}
		});
		add(copyShapeButton);
		
		// pasteShapeButton
		pasteShapeButton = new JButton("붙여넣기");
		pasteShapeButton.setPreferredSize(new Dimension(90, buttonYSize));
		pasteShapeButton.setBackground(new Color(255, 255, 255));
		pasteShapeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				EditorFrame.drawPanel.paste();
			}
		});
		add(pasteShapeButton);
		
		
		// deleteShapeButton
		deleteShapeButton = new JButton("지우기");
		deleteShapeButton.setPreferredSize(new Dimension(buttonXSize, buttonYSize));
		deleteShapeButton.setBackground(new Color(255, 255, 255));
		deleteShapeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				EditorFrame.drawPanel.remove();
			}
		});
		add(deleteShapeButton);
		

	}
	
	public void initialize() {
		selectButton.setSelected(true);
		ovalButton.setSelected(false);
		rectButton.setSelected(false);
		lineButton.setSelected(false);
		imageButton.setSelected(false);
		textButton.setSelected(false);
	}

}
