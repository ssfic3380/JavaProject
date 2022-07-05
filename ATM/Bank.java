import java.util.Calendar;

/**
 * ������ �����ͺ��̽����� �ҷ��� ������ Account ��ü�� ��Ƽ� �����ϰ� �ٲ� ������ Database�� ������Ʈ�� �ϴ� Ŭ���� 
 */

public class Bank {
	
	private Database db = new Database();
	private static Calendar cal = Calendar.getInstance();
	
	/* Tool */
	// �����ͺ��̽��� ���� ���¹�ȣ�� ��й�ȣ�� ��ġ�ϴ��� Ȯ�� �ϴ� �Լ�
	public Account login(String accountNumber, long password) {
		
		if (db == null) db = new Database();
		db = db.checkAccount(accountNumber);
		
		if ((db != null) && (db.getPassword() == password)) {
			if (db instanceof CADatabase) { // ����ݰ��¸� CheckingAccount ��ü ����
			CheckingAccount acc = new CheckingAccount(accountNumber, db.getName(), db.getBalance(), db.getRecord());
			return acc;
			} else if (db instanceof TADatabase) { // ���⿹�ݰ��¸� TermAccount ��ü ����
				cal.set(Calendar.YEAR, Integer.parseInt(((TADatabase) db).getSubscriptionDate().substring(0, 4)));
				cal.set(Calendar.MONTH, Integer.parseInt(((TADatabase) db).getSubscriptionDate().substring(4, 6)) - 1);
				cal.set(Calendar.DATE, Integer.parseInt(((TADatabase) db).getSubscriptionDate().substring(6, 8)));
				TermAccount acc = new TermAccount(accountNumber, db.getName(), db.getBalance(), ((TADatabase)db).getRate(), ((TADatabase)db).getTerm(), cal, db.getRecord());
				return acc;
			} else if (db instanceof AdminDatabase) { // Admin�̸� Admin ��ü ����
				Admin acc = new Admin(accountNumber, db.getName(), db.getBalance(), ((AdminDatabase)db).getOne(), ((AdminDatabase)db).getFive(), db.getRecord());
				return acc;
			}
		}

		return null;	
	}
	
	
	// �����ͺ��̽��� ������Ʈ �ϴ� �Լ�
	public void updateDB(Account acc) {
		
		if (db == null) db = new Database();
		db = db.checkAccount(acc.getAccountNumber());
		
		if (acc instanceof CheckingAccount) {
			db.setBalance(acc.getAccountNumber(), acc.getBalance());	// Balance ����
			db.setRecord(acc.getAccountNumber(), acc.getRecord());		// Record ����
		} else if (acc instanceof TermAccount) {
			db.setRecord(acc.getAccountNumber(), acc.getRecord());		// Record ����
		} else if (acc instanceof Admin) {
			((AdminDatabase)db).setOne( acc.getAccountNumber(), ((Admin)acc).getOne() );	// 1������ ���� ����
			((AdminDatabase)db).setFive( acc.getAccountNumber(), ((Admin)acc).getFive() );	// 5������ ���� ����
			db.setBalance(acc.getAccountNumber(), acc.getBalance());						// Balance ����
			db.setRecord(acc.getAccountNumber(), acc.getRecord());							// Record ����
		}

	}
	
}