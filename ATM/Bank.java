import java.util.Calendar;

/**
 * 은행의 데이터베이스에서 불러온 정보를 Account 객체에 담아서 전달하고 바뀐 정보를 Database에 업데이트를 하는 클래스 
 */

public class Bank {
	
	private Database db = new Database();
	private static Calendar cal = Calendar.getInstance();
	
	/* Tool */
	// 데이터베이스를 통해 계좌번호와 비밀번호가 일치하는지 확인 하는 함수
	public Account login(String accountNumber, long password) {
		
		if (db == null) db = new Database();
		db = db.checkAccount(accountNumber);
		
		if ((db != null) && (db.getPassword() == password)) {
			if (db instanceof CADatabase) { // 입출금계좌면 CheckingAccount 객체 리턴
			CheckingAccount acc = new CheckingAccount(accountNumber, db.getName(), db.getBalance(), db.getRecord());
			return acc;
			} else if (db instanceof TADatabase) { // 정기예금계좌면 TermAccount 객체 리턴
				cal.set(Calendar.YEAR, Integer.parseInt(((TADatabase) db).getSubscriptionDate().substring(0, 4)));
				cal.set(Calendar.MONTH, Integer.parseInt(((TADatabase) db).getSubscriptionDate().substring(4, 6)) - 1);
				cal.set(Calendar.DATE, Integer.parseInt(((TADatabase) db).getSubscriptionDate().substring(6, 8)));
				TermAccount acc = new TermAccount(accountNumber, db.getName(), db.getBalance(), ((TADatabase)db).getRate(), ((TADatabase)db).getTerm(), cal, db.getRecord());
				return acc;
			} else if (db instanceof AdminDatabase) { // Admin이면 Admin 객체 리턴
				Admin acc = new Admin(accountNumber, db.getName(), db.getBalance(), ((AdminDatabase)db).getOne(), ((AdminDatabase)db).getFive(), db.getRecord());
				return acc;
			}
		}

		return null;	
	}
	
	
	// 데이터베이스를 업데이트 하는 함수
	public void updateDB(Account acc) {
		
		if (db == null) db = new Database();
		db = db.checkAccount(acc.getAccountNumber());
		
		if (acc instanceof CheckingAccount) {
			db.setBalance(acc.getAccountNumber(), acc.getBalance());	// Balance 수정
			db.setRecord(acc.getAccountNumber(), acc.getRecord());		// Record 수정
		} else if (acc instanceof TermAccount) {
			db.setRecord(acc.getAccountNumber(), acc.getRecord());		// Record 수정
		} else if (acc instanceof Admin) {
			((AdminDatabase)db).setOne( acc.getAccountNumber(), ((Admin)acc).getOne() );	// 1만원권 수량 수정
			((AdminDatabase)db).setFive( acc.getAccountNumber(), ((Admin)acc).getFive() );	// 5만원권 수량 수정
			db.setBalance(acc.getAccountNumber(), acc.getBalance());						// Balance 수정
			db.setRecord(acc.getAccountNumber(), acc.getRecord());							// Record 수정
		}

	}
	
}