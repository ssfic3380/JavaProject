import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GUI extends JFrame {

	public static Object lock = new Object();	// 스레드를 위한 지표
	public static int choice = 10;				// 선택된 메뉴
	public static String inputMsg = "";			// 입력받은 메시지 반환용

	private int width;
	private int height;

	public GUI() {
		
		super("K-Bank ATM");

		width = 1024;
		height = 768;
		setSize(width, height);
		this.setUndecorated(true);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(255, 255, 255));

	}

	
	/* Thread Method */
	public void lock() {
		synchronized (lock){
			try {
				lock.wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	
	/* GUI Method */
	public void reset() {
		this.getContentPane().removeAll();
	}

	
	public void repaint(JPanel centerPanel) {
		
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();

		
		/* north */
		JLabel title = new JLabel("    K-Bank ATM");
		title.setFont(new Font("SansSerif", Font.BOLD, 35));
		
		north.setLayout(new GridLayout(0, 1));
		north.setBackground(new Color(214, 240, 255));
		north.add( title );
		north.setBounds(0, 0, width, height/10);
		this.add( north );
		
		
		/* center */
		center = centerPanel;
		center.setBackground(new Color(255, 255, 255));
		center.setBounds(10, height/10 + 10, width - 20, 8*height/10 - 20);
		this.add( center );
		
		
		/* south */
		JLabel info = new JLabel("<html>&emsp;&emsp;&emsp;&emsp;● 입금/출금은 지폐만 가능합니다&emsp;&emsp;&emsp;&emsp;&emsp;● 24시간 운영 ATM입니다&emsp;&emsp;&emsp;&emsp;&emsp;● 기타 문의사항: 02-111-1111</html>");
		info.setFont(new Font("SansSerif", Font.BOLD, 17));
		
		south.setLayout(new GridLayout(0, 1));
		south.setBackground(new Color(214, 240, 255));
		south.add( info );
		south.setBounds(0, 9*height/10, width, height/10);
		this.add( south );


		super.repaint();
		this.setVisible(true);

	}
	

	/* Menu */
	public JPanel center_menu(String leftButton1, int leftNum1, String leftButton2, int leftNum2, String leftButton3,
			int leftNum3, String rightButton1, int rightNum1, String rightButton2, int rightNum2, String rightButton3,
			int rightNum3, String msg) {

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1, 3, 30, 30));

		JPanel center1 = new JPanel();
		center1.setBackground(new Color(255, 255, 255));
		JPanel center2 = new JPanel();
		center2.setBackground(new Color(255, 255, 255));
		JPanel center3 = new JPanel();
		center3.setBackground(new Color(255, 255, 255));
		JPanel center2_1 = new JPanel();
		JPanel center2_2 = new JPanel();

		
		/* center1 */
		center1.setLayout(new GridLayout(3, 1, 30, 30));
		if (leftButton1.equals("")) {
			JPanel temp = new JPanel();
			temp.setBackground(new Color(255, 255, 255));
			center1.add( temp );
		}
		else
			center1.add( makeButton(leftButton1, leftNum1) );
		if (leftButton2.equals("")) {
			JPanel temp = new JPanel();
			temp.setBackground(new Color(255, 255, 255));
			center1.add( temp );
		}
		else
			center1.add( makeButton(leftButton2, leftNum2) );
		if (leftButton3.equals("")) {
			JPanel temp = new JPanel();
			temp.setBackground(new Color(255, 255, 255));
			center1.add( temp );
		}
		else
			center1.add( makeButton(leftButton3, leftNum3) );

		
		
		/* notice_msg */
		JLabel notice_msg = new JLabel(msg, JLabel.CENTER);
		notice_msg.setOpaque(true);
		notice_msg.setBackground(new Color(214, 240, 255));
		notice_msg.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		/* center2_1 */
		center2_1.setLayout(new GridLayout(0, 1));
		center2_1.setBackground(new Color(214, 240, 255));
		center2_1.setBorder(new LineBorder(Color.BLACK));
		center2_1.add( notice_msg );
		
		
		/* team_name */
		JLabel team_name = new JLabel("Java J조 (목)", JLabel.CENTER);
		team_name.setBorder(new LineBorder(Color.BLACK));
		team_name.setOpaque(true);
		team_name.setBackground(new Color(214, 240, 255));
		team_name.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		/* member_name */
		JLabel member_name = new JLabel(
				"<html>2017125071 한규민<br/>2016125080 허윤석<br/>2017121261 허태영<br/>2016125084 황재웅</html>", JLabel.CENTER);
		member_name.setBorder(new LineBorder(Color.BLACK));
		member_name.setOpaque(true);
		member_name.setBackground(new Color(214, 240, 255));
		member_name.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		/* center2_2 */
		center2_2.setLayout(new GridLayout(2, 1, 20, 20));
		center2_2.setBackground(new Color(255, 255, 255));
		center2_2.add( team_name );
		center2_2.add( member_name );

		/* center2 */
		center2.setLayout(new GridLayout(2, 1, 30, 30));
		center2.add( center2_1 );
		center2.add( center2_2 );

		
		
		/* center3 */
		center3.setLayout(new GridLayout(3, 1, 30, 30));
		if (rightButton1.equals("")) {
			JPanel temp = new JPanel();
			temp.setBackground(new Color(255, 255, 255));
			center3.add( temp );
		}
		else
			center3.add( makeButton(rightButton1, rightNum1) );
		if (rightButton2.equals("")) {
			JPanel temp = new JPanel();
			temp.setBackground(new Color(255, 255, 255));
			center3.add( temp );
		}
		else
			center3.add( makeButton(rightButton2, rightNum2) );
		if (rightButton3.equals("")) {
			JPanel temp = new JPanel();
			temp.setBackground(new Color(255, 255, 255));
			center3.add( temp );
		}
		else
			center3.add( makeButton(rightButton3, rightNum3) );

		
		/* center */
		center.add( center1 );
		center.add( center2 );
		center.add( center3 );
		return center;

	}

	
	/* Alart */
	public JPanel center_alart(String msg, String button_msg1, String button_msg2, boolean isLogin) {
		
		JPanel center = new JPanel();
		center.setLayout(null);

		
		/* message */
		JLabel message = new JLabel(msg, JLabel.CENTER);
		//message.setBounds(20, 20, width*9/10 + 35, height*6/10 - 20);
		//message.setBorder(new LineBorder(Color.black));
		message.setFont(new Font("SansSerif", Font.BOLD, 20));
		JScrollPane scroller = new JScrollPane(message, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setBounds(20, 20, width*9/10 + 35, height*6/10 - 20);
		center.add(scroller);
		
		
		/* button */
		if (isLogin) { // 로그인 오류 화면이면 버튼이 2개
			
			JButton button1 = makeButton(button_msg1, 1);
			JButton button2 = makeButton(button_msg2, 2);
			
			button1.setBounds(20, height*6/10 + 25, 250, 100);
			button1.setFont(new Font("SansSerif", Font.BOLD, 20));
			button2.setBounds(width*7/10 + 10, height*6/10 + 25, 250, 100);
			button2.setFont(new Font("SansSerif", Font.BOLD, 20));
			
			center.add(button1);
			center.add(button2);
			
		} else { // 그 외 오류 화면이면 버튼이 1개
			
			JButton button = makeButton(button_msg1, -1);
			button.setBounds(width*7/10 + 10, height*6/10 + 25, 250, 100);
			button.setFont(new Font("SansSerif", Font.BOLD, 20));
			
			center.add(button);
			
		}
		
		
		return center;
		
	}
	
	
	/* Input */
	public JPanel center_input(String msg, boolean isAccountNumber, boolean isBalance) {

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1, 2));
		
		JPanel center1 = new JPanel();
		JPanel center2 = new JPanel();

		
		/* notice_msg */
		JLabel notice_msg = new JLabel(msg, JLabel.CENTER);
		notice_msg.setBorder(new LineBorder(Color.black));
		notice_msg.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		
		/* center1 */
		center1.setLayout(null);
		center1.setBackground(new Color(255, 255, 255));
		notice_msg.setBounds(20, 20, width/3 + 110, height*7/10 + 20);
		center1.add(notice_msg);
		
		
		/* inputField */
		JTextField inputField = null;
		if (isAccountNumber || isBalance) inputField = new JTextField();
		else if (!isAccountNumber && !isBalance) inputField = new JPasswordField();
		inputField.setBackground(new Color(255, 255, 255));
		inputField.setBorder(new LineBorder(Color.BLACK));
		inputField.setHorizontalAlignment(JTextField.CENTER);
		inputField.setFont(new Font("SansSerif", Font.BOLD, 20));
		inputField.setEditable(false);
		
		/* keypad */
		JPanel keypad = new JPanel();
		keypad.setBackground(new Color(255, 255, 255));
		keypad.setBorder(new LineBorder(Color.BLACK));
		keypad.setLayout(new GridLayout(4, 3, 15, 15));
	
		keypad.add( makeButton("1", inputField, isAccountNumber, isBalance, false) );
		keypad.add( makeButton("2", inputField, isAccountNumber, isBalance, false) );
		keypad.add( makeButton("3", inputField, isAccountNumber, isBalance, false) );
		keypad.add( makeButton("4", inputField, isAccountNumber, isBalance, false) );
		keypad.add( makeButton("5", inputField, isAccountNumber, isBalance, false) );
		keypad.add( makeButton("6", inputField, isAccountNumber, isBalance, false) );
		keypad.add( makeButton("7", inputField, isAccountNumber, isBalance, false) );
		keypad.add( makeButton("8", inputField, isAccountNumber, isBalance, false) );
		keypad.add( makeButton("9", inputField, isAccountNumber, isBalance, false) );
		keypad.add( makeButton("←", inputField, isAccountNumber, isBalance, false) );
		keypad.add( makeButton("0", inputField, isAccountNumber, isBalance, false) );
		keypad.add( makeButton("정정", inputField, isAccountNumber, isBalance, false) );

		/* center2 */
		center2.setLayout(null);
		center2.setBackground(new Color(255, 255, 255));
		
		inputField.setBounds(width/9, 50, width/3, 70);
		center2.add(inputField);
		
		keypad.setBounds(width/9, 140, width/3, height*4/10);
		center2.add(keypad);
		
		JButton button = makeButton("확       인", inputField, true, true, true);
		button.setBounds(width/9, height*4/10+175, width/3, 70);
		center2.add(button);
		
		//center2.setBorder(new LineBorder(Color.BLACK));
		
		
		/* center */
		center.add(center1);
		center.add(center2);
		
		return center;

	}

	public JButton makeButton(String buttonName, JTextField jtext, boolean isAccountNumber, boolean isBalance, boolean isNotify) {
		GUIButton button = new GUIButton(buttonName, jtext, isAccountNumber, isBalance, isNotify);
		return button;
	}

	public JButton makeButton(String buttonName, int num) {
		GUIButton button = new GUIButton(buttonName, num);
		return button;
	}

}