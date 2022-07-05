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

					if (!isAccountNumber && !isBalance) { // ��й�ȣ�� ���

						if (temp.length() == 4) { // ��й�ȣ 4�� �̻� �Է� �Ұ���
							
							if (buttonName.equals("��")) {
								temp = temp.substring(0, temp.length() - 1);
							} else if (buttonName.equals("����")) {
								temp = "";
							}
							
						} else {
							
							if (buttonName.equals("��")) {
								temp = temp.substring(0, temp.length() - 1);
							} else if (buttonName.equals("����")) {
								temp = "";
							} else if (buttonName.equals("Ȯ       ��")) {

							} else {
								temp = temp + buttonName;
							}
						}

					} else if (isAccountNumber && !isBalance) { // ���¹�ȣ�� ���

						if (temp.length() == 11) { // ���¹�ȣ 11�� �̻� �Է� �Ұ���

							if (buttonName.equals("��")) {
								temp = temp.substring(0, temp.length() - 1);
							} else if (buttonName.equals("����")) {
								temp = "";
							}
							
						} else {

							if (buttonName.equals("��")) {
								temp = temp.substring(0, temp.length() - 1);
							} else if (buttonName.equals("����")) {
								temp = "";
							} else if (buttonName.equals("Ȯ       ��")) {

							} else {
								temp = temp + buttonName;
								if ((temp.length() == 3) || (temp.length() == 7))
									temp = temp + "-";
							}

						}

					} else if (!isAccountNumber && isBalance) { // �ݾ� �Է��� ���

						if (buttonName.equals("��")) {
							temp = temp.substring(0, temp.length() - 1);
						} else if (buttonName.equals("����")) {
							temp = "";
						} else if (buttonName.equals("Ȯ       ��")) {

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