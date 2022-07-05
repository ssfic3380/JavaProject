/**
 * 
 * �ڹٿͰ�ü�������α׷��� (��) �̱��� ������
 * TermProject_1D
 * 2020.10.27
 * 
 * @author 2017121261 ���¿�
 * @author 2016125080 ������
 * @author 2017125071 �ѱԹ�
 * @author 2016125084 Ȳ��� 
 * 
 * MainŬ������ ATM�� ��ü ������ �ϴ� Ŭ������.
 * UIŬ������ ���� ���� �޴��� ����ϰ�  Account, Bank, UI Ŭ������ ��꿪���� �Ѵ�.
 */

public class Main {

	public static final int SHOW_BALANCE = 1;
	public static final int DEPOSIT = 2;
	public static final int WITHDRAW = 3;
	public static final int FIXED_DEPOSIT = 4;
	public static final int LOGOUT = 10;
	public static final int HIDDEN_ADMIN = 99;

	static Account admin;

	public static void main(String[] args) {

		UI ui = new UI();
		Bank bank = new Bank();
		Account acc = null;
		Database.initDatabase();
		admin = bank.login("999-999-999", 1234);

		String id;
		int password, user;

		while (true) {

			/* �޴� ���� */
			int menu = 0;
			while (!(menu > 0 && menu < 10)) {

				if (menu == 10 && (acc != null)) {
					break;
				}

				boolean isLogin = false;
				boolean isTerm = false;
				boolean isAdmin = false;

				if (acc != null) {
					isLogin = true;
					if (acc instanceof TermAccount)
						isTerm = true;
					if (acc instanceof Admin)
						isAdmin = true;
				}

				if (isLogin)
					menu = ui.main_menu(isLogin, isTerm, isAdmin, acc.getName());
				else
					menu = ui.main_menu(isLogin, isTerm, isAdmin, null);

				if (menu == 99)
					break;
			}

			/* �α��� */
			boolean doesUserWantBreak = false;
			while (acc == null) {
				id = ui.get_id();
				password = (int) ui.get_pass();
				acc = bank.login(id, password);

				if (acc == null) {
					doesUserWantBreak = ui.login_fail_msg();
					if (doesUserWantBreak) {
						id = "";
						password = 0;
						break;
					} else
						continue;
				}
			}
			
			if (doesUserWantBreak) {
				continue;
			}

			/* �������� �޴� ���� */
			switch (menu) {

			case SHOW_BALANCE:

				if (!(acc instanceof CheckingAccount)) {
					ui.not_CA();
					acc = null;
					continue;
				}

				long balance = acc.showBalance();
				ui.ca_menu_balance(acc.getAccountNumber(), balance);
				break;

			case DEPOSIT:

				if (!(acc instanceof CheckingAccount)) {
					ui.not_CA();
					acc = null;
					continue;
				}

				long money = 0;

				money = ui.ca_menu_add();

				while (money % 1000 != 0) { // �Ա� ������ Ʋ���� ��� ����
					ui.ca_menu_add_fail();
					money = ui.ca_menu_add();
				}

				((CheckingAccount) acc).addBalance(money);
				balance = acc.getBalance();
				ui.ca_menu_add_success(acc.getAccountNumber(), money, balance);

				break;

			case WITHDRAW:

				if (!(acc instanceof CheckingAccount)) {
					ui.not_CA();
					acc = null;
					continue;
				}

				balance = acc.getBalance();
				long omanWon = 0;

				if (balance == 0) { // ���忡 �ܾ��� ���� ���
					ui.ca_menu_withdraw_fail();
					balance = acc.getBalance();
					ui.ca_menu_balance(acc.getAccountNumber(), balance);
					continue;
				}
				money = ui.ca_menu_withdraw();

				while (money % 1000 != 0) { // ��� ������ Ʋ���� ��� ����
					ui.ca_menu_withdraw_fail2();
					money = ui.ca_menu_withdraw();
				}

				while (money > balance) { // ���ϴ� ��� �ݾ��� �ܱݺ��� ������ ���� ���
					ui.ca_menu_subtract_fail();
					money = ui.ca_menu_withdraw();

					while (money % 1000 != 0) { // ��� ������ Ʋ���� ��� ����
						ui.ca_menu_withdraw_fail2();
						money = ui.ca_menu_withdraw();
					}
				}
				// ��ݱݾ��� 5���� �̻��� �� �������ǰ��� ���
				if (money >= 50000) {
					omanWon = ui.ca_menu_subtract_five(acc.getAccountNumber(),
							Math.min(((Admin) admin).getFive(), (money / 50000)));

					while (omanWon > Math.min(((Admin) admin).getFive(), (money / 50000))) { // ��� ���� 5�������� ������ ����
						ui.ca_menu_subtract_fail2();
						omanWon = ui.ca_menu_subtract_five(acc.getAccountNumber(),
								Math.min(((Admin) admin).getFive(), (money / 50000)));
					}
				}

				long manWon = (money - (omanWon * 50000)) / 10000;
				// ������ ����
				if (((Admin) admin).getOne() < manWon) {
					ui.ca_menu_subtract_fail3();
					break;
				}

				((CheckingAccount) acc).subtractBalance(money, omanWon);

				balance = acc.getBalance();
				ui.ca_menu_withdraw_success(acc.getAccountNumber(), money, omanWon, manWon, balance);

				break;

			case FIXED_DEPOSIT:

				if (!(acc instanceof TermAccount)) {
					ui.not_TA();
					acc = null;
					continue;
				}

				/* �޴� ���� */
				user = ui.get_ta_menu(acc.getName());

				switch (user) {

				case 1: // ���⿹�ݰ��� ��ȸ

					balance = ((TermAccount) acc).getBalance(); // ��ġ��
					long term = ((TermAccount) acc).getTerm(); // ��ġ��					
					double rate = ((TermAccount) acc).getRate(); // ��������
					String date = String.format("%s", ((TermAccount) acc).getDueDate()); // ������
					ui.ta_menu_account(acc.getAccountNumber(), ((TermAccount) acc).showBalance(), term, rate, date, ((TermAccount) acc).getInterest());
					break;

				case 10:

					acc = null;
					ui.logout_success();
					break;

				}

				break;

			case HIDDEN_ADMIN:

				if (!(acc instanceof Admin)) {
					ui.not_Admin();
					acc = null;
					continue;
				}
				acc = admin;

				/* �޴� ���� */
				user = ui.get_ad_menu();

				long one = ((Admin) acc).getOne();
				long five = ((Admin) acc).getFive();

				switch (user) {
				case 1:

					one = ((Admin) acc).showOne();
					five = ((Admin) acc).showFive();

					ui.ad_menu_show(one, five);
					break;

				case 2:

					manWon = (int) ui.ad_menu_addOne();
					((Admin) acc).addOnes(manWon);

					one = ((Admin) acc).getOne();
					five = ((Admin) acc).getFive();

					ui.ad_menu_show(one, five);
					break;

				case 3:

					omanWon = (int) ui.ad_menu_addFive();
					((Admin) acc).addFives(omanWon);

					one = ((Admin) acc).getOne();
					five = ((Admin) acc).getFive();

					ui.ad_menu_show(one, five);
					break;

				case 4:

					ui.ad_menu_bankLog( ((Admin) acc).getBankLog() );
					//((Admin) acc).printBankLog();
					break;

				case 5:

					int choice = ui.ad_menu_atmOff();
					if (choice == 1)
						System.exit(0);
					else
						break;

				case 10:

					acc = null;
					ui.logout_success();
					break;

				}

				break;

			case LOGOUT:

				acc = null;
				ui.logout_success();
				break;

			}

		}

	}

}