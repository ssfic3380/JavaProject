import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class GUIButton extends JButton {

	public int choice;

	GUIButton(String buttonName, int num) {

		super(buttonName);
		choice = num;

		Font font = new Font("SansSerif", Font.BOLD, 30);
		setFont(font);
		// setBackground(new Color(183, 240, 177));

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (GUI.lock) {
					GUI.choice = choice;
					GUI.lock.notify();
				}
			}

		});

	}

	GUIButton(String buttonName, JTextField jtext, boolean isAccountNumber, boolean isBalance, boolean isNotify) {

		super(buttonName);
		Font font = new Font("SansSerif", Font.BOLD, 30);
		setFont(font);

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				synchronized (GUI.lock) {

					String temp = jtext.getText();

					if (!isAccountNumber && !isBalance) { // 비밀번호일 경우

						if (temp.length() == 4) { // 비밀번호 4자 이상 입력 불가능
							
							if (buttonName.equals("←")) {
								temp = temp.substring(0, temp.length() - 1);
							} else if (buttonName.equals("정정")) {
								temp = "";
							}
							
						} else {
							
							if (buttonName.equals("←")) {
								temp = temp.substring(0, temp.length() - 1);
							} else if (buttonName.equals("정정")) {
								temp = "";
							} else if (buttonName.equals("확       인")) {

							} else {
								temp = temp + buttonName;
							}
						}

					} else if (isAccountNumber && !isBalance) { // 계좌번호일 경우

						if (temp.length() == 11) { // 계좌번호 11자 이상 입력 불가능

							if (buttonName.equals("←")) {
								temp = temp.substring(0, temp.length() - 1);
							} else if (buttonName.equals("정정")) {
								temp = "";
							}
							
						} else {

							if (buttonName.equals("←")) {
								temp = temp.substring(0, temp.length() - 1);
							} else if (buttonName.equals("정정")) {
								temp = "";
							} else if (buttonName.equals("확       인")) {

							} else {
								temp = temp + buttonName;
								if ((temp.length() == 3) || (temp.length() == 7))
									temp = temp + "-";
							}

						}

					} else if (!isAccountNumber && isBalance) { // 금액 입력일 경우

						if (buttonName.equals("←")) {
							temp = temp.substring(0, temp.length() - 1);
						} else if (buttonName.equals("정정")) {
							temp = "";
						} else if (buttonName.equals("확       인")) {

						} else {
							temp = temp + buttonName;
						}

					}
					
					jtext.setText(temp);
					if (isNotify) {
						GUI.inputMsg = jtext.getText();
						GUI.lock.notify();
					}
				}
			}

		});

	}

}