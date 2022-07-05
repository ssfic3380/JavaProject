import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * ������ ��ü transaction�� ����ϰ� ���ÿ� Database�� �����ϴ� Ŭ����
 */


public class Log {

	private static Bank bank = new Bank();
	private static Account admin = bank.login("999-999-999", 1234);
	private final SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/* Class Tool */
	private void add_log(Account id, String record) {
		id.getRecord().add(record);
		bank.updateDB(id);
	}

	private String get_Date() {
		Calendar cal = Calendar.getInstance();
		return transFormat.format(cal.getTime()) + "&emsp;";
	}

	
	/* Tool */
	public void log_addBalance(Account acc, long balance) {
		String log = get_Date() + "[�Ա�]&emsp;&emsp;+" + balance + " ��";
		add_log(acc, log);

		String admin_log = get_Date() + "[�Ա�] " + acc.getAccountNumber() + "&emsp;&emsp;&emsp;+" + balance + " ��";
		add_log(admin, admin_log);
	}

	public void log_subtractBalance(Account acc, long balance) {
		String log = get_Date() + "[���]&emsp;&emsp;-" + balance + " ��";
		add_log(acc, log);

		String admin_log = get_Date() + "[���] " + acc.getAccountNumber() + "&emsp;&emsp;&emsp;-" + balance + " ��";
		add_log(admin, admin_log);
	}

	public void log_viewCA(Account acc) {
		String log = get_Date() + "[����ݰ��� ��ȸ]";
		add_log(acc, log);

		String admin_log = get_Date() + "[����ݰ��� ��ȸ] " + acc.getAccountNumber();
		add_log(admin, admin_log);
	}
	
	public void log_viewTA(Account acc) {
		String log = get_Date() + "[���⿹�ݰ��� ��ȸ]";
		add_log(acc, log);

		String admin_log = get_Date() + "[���⿹�ݰ��� ��ȸ] " + acc.getAccountNumber();
		add_log(admin, admin_log);
	}

	public void log_viewPaper(Account acc) {
		String admin_log = get_Date() + "[Admin: ���� ��Ȳ ��ȸ]";
		add_log(acc, admin_log);
	}

	public void log_addOne(Account acc, long amount) {
		String admin_log = get_Date() + "[Admin: 1������ �߰�]&emsp;&ensp;" + amount + "��";
		add_log(acc, admin_log);
	}

	public void log_addFive(Account acc, long amount) {
		String admin_log = get_Date() + "[Admin: 5������ �߰�]&emsp;&ensp;" + amount + "��";
		add_log(acc, admin_log);
	}

	public void log_subtractOne(Account acc, long amount) {
		String admin_log = get_Date() + "[Admin: 1������ ���]&emsp;&ensp;" + amount + "��";
		add_log(acc, admin_log);
	}

	public void log_subtractFive(Account acc, long amount) {
		String admin_log = get_Date() + "[Admin: 5������ ���]&emsp;&ensp;" + amount + "��";
		add_log(acc, admin_log);
	}

	public void log_viewRecord(Account acc) {
		String admin_log = get_Date() + "[Admin: ���� ��� ��ȸ]";
		add_log(acc, admin_log);
	}

}