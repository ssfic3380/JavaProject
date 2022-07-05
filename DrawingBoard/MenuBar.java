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
 * �̸� : MenuBar
 * ���� : EditorFrame�� �޴��� ����ϴ� Ŭ������, JMenuBar�� ����ߴ�.
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
	private String fontNames[] = { "����ü", "Serif", "Monospaced", "SansSerif" };
	private ButtonGroup fontButtonGroup;
	private JMenu fontStyle;
	private JCheckBoxMenuItem styleItems[];
	private String styleNames[] = { "Bold", "Italic" };
	private int style;
	
	private JMenu help;
	private JMenuItem madeBy;
	

	public MenuBar() {
		
		/* ���� */
		file = new JMenu("����(F)");
		file.setMnemonic('F');
		this.add(file);

		// �� �۾�
		newFile = new JMenuItem("�� �۾�(N)");
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

		// �ҷ�����
		openFile = new JMenuItem("�ҷ�����(O)");
		openFile.setMnemonic('O');
		openFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				
				JFileChooser fileChooser;
				if (EditorFrame.currentPath != null) fileChooser = new JFileChooser(EditorFrame.currentPath);
				else fileChooser = new JFileChooser("C:");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Term Project 3 ����(.tpt)", "tpt");
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

		// ����
		saveFile = new JMenuItem("�����ϱ�(S)");
		saveFile.setMnemonic('S');
		saveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				
				JFileChooser fileChooser;
				if (EditorFrame.currentPath != null) fileChooser = new JFileChooser(EditorFrame.currentPath);
				else fileChooser = new JFileChooser("C:");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Term Project 3 ����(.tpt)", "tpt");
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

		// ������
		exitFile = new JMenuItem("������(X)");
		exitFile.setMnemonic('X');
		exitFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
		file.add(exitFile);
		
		
		/* ���� */
		tool = new JMenu("����(T)");
		tool.setMnemonic('T');
		this.add(tool);
				
		// ��Ʈ ���� �ٲٱ�
		fontName = new JMenu("��Ʈ ����(K)");
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
		

		// ��Ʈ ��Ÿ�� �ٲٱ�
		fontStyle = new JMenu("��Ʈ ��Ÿ��(Y)");
		fontStyle.setMnemonic('Y');
		tool.add(fontStyle);
		
		styleItems = new JCheckBoxMenuItem[styleNames.length];
		StyleHandler styleHandler = new StyleHandler();
		
		for (int i = 0; i < styleNames.length; i++) {
			styleItems[i] = new JCheckBoxMenuItem(styleNames[i]);
			fontStyle.add(styleItems[i]);
			styleItems[i].addItemListener(styleHandler);
		}
		
		
		/* ���� */
		help = new JMenu("����(H)");
		help.setMnemonic('H');
		this.add(help);
		
		// ������
		madeBy = new JMenuItem("������(Q)");
		madeBy.setMnemonic('Q');
		madeBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JOptionPane.showMessageDialog(null, "�ڹٿ� ��ü�������α׷��� �����\n������ : 2016125080 ������");
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