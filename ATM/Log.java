import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 은행의 전체 transaction을 기록하고 동시에 Database를 갱신하는 클래스
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
		String log = get_Date() + "[입금]&emsp;&emsp;+" + balance + " 원";
		add_log(acc, log);

		String admin_log = get_Date() + "[입금] " + acc.getAccountNumber() + "&emsp;&emsp;&emsp;+" + balance + " 원";
		add_log(admin, admin_log);
	}

	public void log_subtractBalance(Account acc, long balance) {
		String log = get_Date() + "[출금]&emsp;&emsp;-" + balance + " 원";
		add_log(acc, log);

		String admin_log = get_Date() + "[출금] " + acc.getAccountNumber() + "&emsp;&emsp;&emsp;-" + balance + " 원";
		add_log(admin, admin_log);
	}

	public void log_viewCA(Account acc) {
		String log = get_Date() + "[입출금계좌 조회]";
		add_log(acc, log);

		String admin_log = get_Date() + "[입출금계좌 조회] " + acc.getAccountNumber();
		add_log(admin, admin_log);
	}
	
	public void log_viewTA(Account acc) {
		String log = get_Date() + "[정기예금계좌 조회]";
		add_log(acc, log);

		String admin_log = get_Date() + "[정기예금계좌 조회] " + acc.getAccountNumber();
		add_log(admin, admin_log);
	}

	public void log_viewPaper(Account acc) {
		String admin_log = get_Date() + "[Admin: 지폐 현황 조회]";
		add_log(acc, admin_log);
	}

	public void log_addOne(Account acc, long amount) {
		String admin_log = get_Date() + "[Admin: 1만원권 추가]&emsp;&ensp;" + amount + "매";
		add_log(acc, admin_log);
	}

	public void log_addFive(Account acc, long amount) {
		String admin_log = get_Date() + "[Admin: 5만원권 추가]&emsp;&ensp;" + amount + "매";
		add_log(acc, admin_log);
	}

	public void log_subtractOne(Account acc, long amount) {
		String admin_log = get_Date() + "[Admin: 1만원권 사용]&emsp;&ensp;" + amount + "매";
		add_log(acc, admin_log);
	}

	public void log_subtractFive(Account acc, long amount) {
		String admin_log = get_Date() + "[Admin: 5만원권 사용]&emsp;&ensp;" + amount + "매";
		add_log(acc, admin_log);
	}

	public void log_viewRecord(Account acc) {
		String admin_log = get_Date() + "[Admin: 은행 기록 조회]";
		add_log(acc, admin_log);
	}

}