import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * 이름 : EditorFrame
 * 역할 : 각종 Panel을 담는 Frame이며 main 함수를 가지고 있다.
 *     : 각종 Panel에서 액션리스너를 통해 전달 받은 정보를 static 변수에 담아놓으면, 이를 DrawPanel이 사용한다.
 * 
 * @author 자바와 객체지향프로그래밍 목요일 2016125080 허윤석
 */

public class EditorFrame extends JFrame{
	
	public static String currentShape;
	public static Color currentColor;
	public static int currentSize = 3;
	public static String currentPath = null;
	public static String currentText = null;	
	
	private MenuBar menuBar = new MenuBar();
	private AxisPanel axisPanel = new AxisPanel();
	public static DrawPanel drawPanel = new DrawPanel();
	public static ToolPanel toolPanel = new ToolPanel();
	public static ColorPanel colorPanel = new ColorPanel();
	public static SizePanel sizePanel = new SizePanel();
	
	public EditorFrame() {
		
		super("Graphic Editor");
		this.setSize(1700, 1000);
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		JPanel functionPanel = new JPanel();
		functionPanel.setLayout(new BorderLayout());
		functionPanel.setBackground(new Color(196, 222, 255));
		functionPanel.add(toolPanel, BorderLayout.CENTER);
		functionPanel.add(axisPanel, BorderLayout.EAST);
		
		JPanel paintingPanel = new JPanel();
		paintingPanel.setLayout(new GridLayout(1, 2));
		paintingPanel.setBackground(new Color(196, 222, 255));
		paintingPanel.add(colorPanel);
		paintingPanel.add(sizePanel);
		
		JPanel temp1 = new JPanel();
		temp1.setBackground(new Color(196, 222, 255));
		JPanel temp2 = new JPanel();
		temp2.setBackground(new Color(196, 222, 255));
		
		setJMenuBar(menuBar);
		this.add(drawPanel, BorderLayout.CENTER);
		this.add(functionPanel, BorderLayout.NORTH);
		this.add(paintingPanel, BorderLayout.SOUTH);
		this.add(temp1, BorderLayout.EAST);
		this.add(temp2, BorderLayout.WEST);
		
		currentShape = null;
		toolPanel.initialize();
		colorPanel.initialize();
		sizePanel.initialize();
		
	}
	
	public static void main(String[] args) {
		EditorFrame editorFrame = new EditorFrame();
	}
	
}
