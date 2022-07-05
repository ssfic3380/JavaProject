import java.util.ArrayList;
import java.util.Scanner;

/**
 * ATM�� �Է��� �޾Ƽ� �������ְ�, �ش��ϴ� �޴��� ������ִ� Ŭ����
 */

public class UI {

	private GUI gui = new GUI();

	/* Tool */
	// �α��� - ���¹�ȣ
	public String get_id() {
		gui.reset(); 
		gui.repaint( gui.center_input("���¹�ȣ�� �Է����ּ���.", true, false) );
		gui.lock();
		return gui.inputMsg;
	}

	// �α��� - ��й�ȣ
	public long get_pass() {
		gui.reset();
		gui.repaint( gui.center_input("��й�ȣ�� �Է����ּ���.", false, false) );
		gui.lock();
		
		long pass = 0;
		if (!gui.inputMsg.equals("")) pass = Long.parseLong(gui.inputMsg);
		else pass = 0;
		
		return pass;
	}

	// �α��� - ����
	public boolean login_fail_msg() {
		gui.reset();
		gui.repaint( gui.center_alart("���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�", "�ٽ� �α����ϱ�", "���� �޴��� ���ư���", true) );
		gui.lock();
		int num = gui.choice;
		if (num == 1) {
			return false;
		} else {
			return true;
		}
	}

	// �α׾ƿ�
	public void logout_success() {
		gui.reset();
		gui.repaint( gui.center_alart("�α׾ƿ� �Ǿ����ϴ�. �̿����ּż� �����մϴ�.", "Ȯ       ��", "", false) );
		gui.lock();
	}

	// �ݾ��� �Է¹޴� �Լ�
	public long get_price(String text) {
		gui.reset();
		gui.repaint( gui.center_input(text + "�Ͻ� �ݾ��� �Է����ּ���.", false, true) );
		gui.lock();
		long price = Long.parseLong(gui.inputMsg);
		return price;
	}

	/* 0. Main Menu */
	public int main_menu(boolean isLogin, boolean isTerm, boolean isAdmin, String name) {
		
		gui.reset();

		if (isTerm) // ���⿹�ݰ��·� �α���
			return 4;
		if (isAdmin) // ������ �������� �α���
			return 99;
		if (isLogin) { // ����ݰ��·� �α���
			String msg = String.format("<html><center>%s��, ȯ���մϴ�!<br/>�޴��� �������ּ���.</center></html>", name);
			gui.repaint( gui.center_menu("�ܰ� ��ȸ", 1, "��       ��", 2, "��       ��", 3, "", 0, "", 0, "�α׾ƿ�", 10, msg) );
		} else {// �α��� �ȵǾ�������
			String msg = "<html><center>�̿����ּż� �����մϴ�!<br/>�޴��� �������ּ���.</center></html>";
			gui.repaint( gui.center_menu("�ܰ� ��ȸ", 1, "��       ��", 2, "��       ��", 3, "���⿹�ݰ��� ��ȸ", 4, "", 0, "������ �޴�", 99, msg) );
		}

		gui.lock();
		return gui.choice;
		
	}

	/* 1. CheckingAccount Menu */
	// ����� �޴� ���� ����
	public void not_CA() {
		gui.reset();
		gui.repaint( gui.center_alart("����� ���°� �ƴմϴ�. �ش� �޴��� �̿��Ͻ� �� �����ϴ�.", "Ȯ       ��", "", false) );
		gui.lock();
	}

	// ����� �ܰ���ȸ
	public void ca_menu_balance(String accountNumber, long balance) {
		gui.reset();
		String msg1 = "<html>���¹�ȣ:&emsp;" + accountNumber + "<br/>";
		String msg2 = "��&emsp;&emsp;��:&emsp;" + balance + " ��</html>";
		gui.repaint( gui.center_alart(msg1 + msg2, "Ȯ       ��", "", false));
		gui.lock();
	}

	// ����� �Ա�
	public long ca_menu_add() {
		return get_price("�Ա� ");
	}

	// ����� �Ա� ����
	public void ca_menu_add_success(String accountNumber, long money, long balance) {
		
		String msg1 = "<html>���¹�ȣ:&emsp;" + accountNumber + "<br/>";
		String msg2 = "�Աݱݾ�:&emsp;" + money + " ��<br/>";
		String msg3 = "��&emsp;&emsp;��:&emsp;" + balance + " ��</html>";
		gui.reset();
		gui.repaint( gui.center_alart(msg1 + msg2 + msg3, "Ȯ       ��", "", false) );
		gui.lock();
		
	}

	// ����� �Ա� ����
	public void ca_menu_add_fail() {
		gui.reset();
		gui.repaint( gui.center_alart("�Ա��� 1000�� Ȥ�� 10000�� ������ �����մϴ�.", "Ȯ       ��", "", false) );
		gui.lock();
	}

	// ����� ���
	public long ca_menu_withdraw() {
		return get_price("��� ");
	}

	// ����� ��� ����
	public void ca_menu_withdraw_success(String accountNumber, long money, long one, long five, long balance) {

		String msg = String.format("<html>���¹�ȣ:&emsp;%s<br/>", accountNumber);
		String msg1 = String.format("����ݾ�:&emsp;%d ��<br/>", money);
		String msg2 = String.format("��������:&emsp;%d ��<br/>", one);
		String msg3 = String.format("��&ensp;��&ensp;��:&emsp;%d ��<br/>", five);
		String msg4 = String.format("��&emsp;&emsp;��:&emsp;%d ��</html>", balance);

		gui.reset();
		gui.repaint( gui.center_alart(msg + msg1 + msg2 + msg3 + msg4, "Ȯ       ��", "", false));
		gui.lock();
	}

	// ����� ��� ����
	public void ca_menu_withdraw_fail() {
		gui.reset();
		gui.repaint( gui.center_alart("����� ���� �ܾ��� �����մϴ�.", "Ȯ       ��", "", false));
		gui.lock();
	}

	// ����� ��� ����2
	public void ca_menu_withdraw_fail2() {
		gui.reset();
		gui.repaint( gui.center_alart("����� 1000�� Ȥ�� 10000�� ������ �����մϴ�. �ٽ� �Է��� �ּ���.", "Ȯ       ��", "", false));
		gui.lock();
	}

	// ����� �ܾ� ����
	public void ca_menu_subtract_fail() {
		gui.reset();
		gui.repaint( gui.center_alart("����� ���� �ܾ��� �����մϴ�. �ٽ� �Է��� �ּ���.", "Ȯ       ��", "", false));
		gui.lock();
	}

	// ����� �ܾ� ����2
	public void ca_menu_subtract_fail2() {
		gui.reset();
		gui.repaint( gui.center_alart("����� �� �ִ� 5������ ���� �ʰ��߽��ϴ�. �ٽ� �Է��� �ּ���.", "Ȯ       ��", "", false));
		gui.lock();
	}

	// ����� �ܾ� ����3
	public void ca_menu_subtract_fail3() {
		gui.reset();
		gui.repaint( gui.center_alart("ATM ��Ⱑ �������� ������� �����մϴ�. �����ڿ��� �������ּ���.", "Ȯ       ��", "", false));
		gui.lock();
	}

	// ����� �������� ���� ����
	public long ca_menu_subtract_five(String accountNumber, long l) {
		
		String msg = String.format("<html><center>���� %s������<br/>�������� ��� ���� ������ %d�� �Դϴ�.<br/>���ϴ� 5������ ��� ������ �Է��ϼ���.</center></html>", accountNumber, l);
		gui.reset();
		gui.repaint( gui.center_input(msg, false, true) );
		gui.lock();
		
		long num = Long.parseLong(gui.inputMsg);
		return num;
	}

	/* 2. TermAccount Menu */
	// ���⿹�� �޴� ���� ����
	public void not_TA() {
		gui.reset();
		gui.repaint( gui.center_alart("���⿹�ݰ��°� �ƴմϴ�. �ش� �޴��� �̿��Ͻ� �� �����ϴ�.\n", "Ȯ       ��", "", false));
		gui.lock();
	}

	// ���⿹�� �޴���ȸ
	public int get_ta_menu(String name) {
		String msg = String.format("<html><center>%s��, ȯ���մϴ�!<br/>�޴��� �������ּ���.</center></html>", name);
		gui.reset();
		gui.repaint( gui.center_menu("���⿹�ݰ��� ��ȸ", 1, "", 0, "", 0, "", 0, "", 0, "�α׾ƿ�", 10, msg) );
		gui.lock();
		
		int choice = gui.choice;
		return choice;
	}

	// ���⿹�ݰ��� ��ȸ
	public void ta_menu_account(String accountNumber, long balance, long term, double rate, String date,
			double interest) {
		String msg = String.format("<html>���¹�ȣ:&emsp;%s<br/>", accountNumber);
		String msg1 = String.format("��&ensp;ġ&ensp;��:&emsp;%d ��<br/>", balance);
		String msg2 = String.format("���ԱⰣ:&emsp;%d ����<br/>", term);
		String msg3 = String.format("��������:&emsp;%.2f%%<br/>", rate);
		String msg4 = String.format("��&ensp;��&ensp;��:&emsp;%s<br/>", date);
		String msg5 = String.format("��&emsp;&emsp;��:&emsp;%d ��</html>", (int) interest);
		gui.reset();
		gui.repaint( gui.center_alart(msg + msg1 + msg2 + msg3 + msg4 + msg5, "Ȯ       ��", "", false));
		gui.lock();
	}

	/* 3. Admin Menu */
	// ������ �޴� ���� ����
	public void not_Admin() {
		gui.reset();
		gui.repaint( gui.center_alart("������ ���ѵ� �޴��Դϴ�.\n", "Ȯ       ��", "", false));
		gui.lock();
	}

	// ������ �޴� ��ȸ
	public int get_ad_menu() {
		Scanner scan = new Scanner(System.in);
		String msg = String.format("<html><center>������ �޴��� �����ϼ̽��ϴ�.<br/>������ �������ּ���.<br/>");
		String msg1 = String.format("<span style=\"color:red\">[! ��� ������ ����ý��ۿ� ��ϵ˴ϴ� !]</span></center></html>");

		gui.reset();
		gui.repaint( gui.center_menu("���� ��Ȳ ��ȸ", 1, "������ �߰�", 2, "�������� �߰�", 3, "���� ��� ��ȸ", 4, "ATM ��� ����", 5, "�α׾ƿ�", 10, msg + msg1) );
		gui.lock();
		
		int choice = gui.choice;
		return choice;
	}

	// ������ ���� ��Ȳ ��ȸ
	public void ad_menu_show(long one, long five) {
		String msg = String.format("<html>��&ensp;��&ensp;��:&emsp;%d ��<br/>", one);
		String msg1 = String.format("��������:&emsp;%d ��</html>", five);
		gui.reset();
		gui.repaint( gui.center_alart(msg + msg1, "Ȯ       ��", "", false));
		gui.lock();
	}

	// ������ ������ �߰�
	public long ad_menu_addOne() {
		gui.reset();
		gui.repaint( gui.center_input("�߰��� 1������ ���� �Է����ּ���.", false, true) );
		gui.lock();

		long num = Long.parseLong(gui.inputMsg);
		return num;

	}

	// ������ �������� �߰�
	public long ad_menu_addFive() {

		gui.reset();
		gui.repaint( gui.center_input("�߰��� 5������ ���� �Է����ּ���.", false, true) );
		gui.lock();

		long num = Long.parseLong(gui.inputMsg);
		return num;
	}

	// ���� ��� ��ȸ
	public void ad_menu_bankLog(ArrayList<String> log) {

		gui.reset();
		String logs = "<html>";
		for (String temp : log) {
			if (log.isEmpty())
				logs = "[���� ����]";
			else
				logs = logs + "<br/>" + temp;
		}
		logs = logs + "</html>";
		
		gui.repaint( gui.center_alart(logs, "Ȯ       ��", "", false));
		gui.lock();

	}

	// ������ ATM ����
	public int ad_menu_atmOff() {
		String msg = String.format("��⸦ �����Ͻðڽ��ϱ�?");
		String msg1 = String.format("1: ����");
		String msg2 = String.format("2: ���");

		gui.reset();
		gui.repaint( gui.center_alart("��⸦ �����Ͻðڽ��ϱ�?", "��       ��", "��       ��", true));
		gui.lock();
		int choice = gui.choice;
		return choice;
	}

}