import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 이름 : MenuBar
 * 역할 : EditorFrame의 메뉴를 담당하는 클래스로, JMenuBar를 상속했다.
 */

public class MenuBar extends JMenuBar {

	private JMenu file;
	private JMenuItem newFile;
	private JMenuItem openFile;
	private JMenuItem saveFile;
	private JMenuItem exitFile;
	
	private JMenu tool;
	private JMenu fontName;
	private JRadioButtonMenuItem fonts[];
	private String fontNames[] = { "굴림체", "Serif", "Monospaced", "SansSerif" };
	private ButtonGroup fontButtonGroup;
	private JMenu fontStyle;
	private JCheckBoxMenuItem styleItems[];
	private String styleNames[] = { "Bold", "Italic" };
	private int style;
	
	private JMenu help;
	private JMenuItem madeBy;
	

	public MenuBar() {
		
		/* 파일 */
		file = new JMenu("파일(F)");
		file.setMnemonic('F');
		this.add(file);

		// 새 작업
		newFile = new JMenuItem("새 작업(N)");
		newFile.setMnemonic('N');
		newFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				EditorFrame.drawPanel.initialize();
				EditorFrame.toolPanel.initialize();
				EditorFrame.colorPanel.initialize();
				EditorFrame.sizePanel.initialize();
				
				EditorFrame.currentShape = null;
				EditorFrame.currentColor = null;
				EditorFrame.currentSize = 3;
				EditorFrame.currentPath = null;
				EditorFrame.currentText = null;
			}
		});
		file.add(newFile);

		file.addSeparator();

		// 불러오기
		openFile = new JMenuItem("불러오기(O)");
		openFile.setMnemonic('O');
		openFile.addActionListener(new ActionListener() {
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
		file.add(openFile);

		// 저장
		saveFile = new JMenuItem("저장하기(S)");
		saveFile.setMnemonic('S');
		saveFile.addActionListener(new ActionListener() {
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
		file.add(saveFile);
		
		file.addSeparator();

		// 끝내기
		exitFile = new JMenuItem("끝내기(X)");
		exitFile.setMnemonic('X');
		exitFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
		file.add(exitFile);
		
		
		/* 도구 */
		tool = new JMenu("도구(T)");
		tool.setMnemonic('T');
		this.add(tool);
				
		// 폰트 종류 바꾸기
		fontName = new JMenu("폰트 종류(K)");
		fontName.setMnemonic('K');
		tool.add(fontName);
		
		fonts = new JRadioButtonMenuItem[fontNames.length];
		fontButtonGroup = new ButtonGroup();
		ItemHandler itemHandler = new ItemHandler();
		
		for (int i = 0; i < fonts.length; i++) {
			fonts[i] = new JRadioButtonMenuItem(fontNames[i]);
			fontName.add(fonts[i]);
			fontButtonGroup.add(fonts[i]);
			fonts[i].addActionListener(itemHandler);
		}
		fonts[0].setSelected(true);
		

		// 폰트 스타일 바꾸기
		fontStyle = new JMenu("폰트 스타일(Y)");
		fontStyle.setMnemonic('Y');
		tool.add(fontStyle);
		
		styleItems = new JCheckBoxMenuItem[styleNames.length];
		StyleHandler styleHandler = new StyleHandler();
		
		for (int i = 0; i < styleNames.length; i++) {
			styleItems[i] = new JCheckBoxMenuItem(styleNames[i]);
			fontStyle.add(styleItems[i]);
			styleItems[i].addItemListener(styleHandler);
		}
		
		
		/* 도움말 */
		help = new JMenu("도움말(H)");
		help.setMnemonic('H');
		this.add(help);
		
		// 만든이
		madeBy = new JMenuItem("만든이(Q)");
		madeBy.setMnemonic('Q');
		madeBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JOptionPane.showMessageDialog(null, "자바와 객체지향프로그래밍 목요일\n만든이 : 2016125080 허윤석");
			}
		});
		help.add(madeBy);
		
	}
	
	private class ItemHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			for (int i = 0; i < fonts.length; i++) {
				if (event.getSource() == fonts[i]) {
					EditorFrame.drawPanel.changeFontName(fonts[i].getText());
				}
			}

		}
	}

	private class StyleHandler implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			style = 0;

			if (styleItems[0].isSelected())
				style += Font.BOLD;
			
			if (styleItems[1].isSelected())
				style += Font.ITALIC;

			EditorFrame.drawPanel.changeFontStyle(style);
		}
	}
}