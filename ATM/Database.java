import java.util.ArrayList;

/**
 * 은행 고객의 계좌정보를 담고있는 전산시스템 역할을 하는 클래스이다.  
 */

public class Database {

	private String accountNumber;
	private long password;
	private String name;
	private ArrayList<String> record = new ArrayList<String>();
	private long balance;

	protected static Database[] init_db = new Database[9];

	public Database() {
		this.accountNumber = "temp";
		this.password = 0;
		this.name = "temp";
		this.balance = 0;
		this.record = null;
	}
	
	public Database(String accountNumber, long password, String name, long balance, ArrayList<String> record) {
		this.accountNumber = accountNumber;
		this.password = password;
		this.name = name;
		this.balance = balance;
		this.record = record;
	}

	
	/* 은행 초기 데이터베이스 세팅용 */
	public static void initDatabase() {

		ArrayList<String> record0 = new ArrayList<String>();
		ArrayList<String> record1 = new ArrayList<String>();
		ArrayList<String> record2 = new ArrayList<String>();
		ArrayList<String> record3 = new ArrayList<String>();
		ArrayList<String> record4 = new ArrayList<String>();
		ArrayList<String> record5 = new ArrayList<String>();
		ArrayList<String> record6 = new ArrayList<String>();
		ArrayList<String> record7 = new ArrayList<String>();
		ArrayList<String> record8 = new ArrayList<String>();
		
		init_db[0] = new AdminDatabase("999-999-999", 1234, "admin", 1500000, 100, 5, record0);
		init_db[1] = new CADatabase("111-111-111", 1111, "한규민", 1000000, record1);
		init_db[2] = new CADatabase("111-111-112", 1111, "허윤석", 1000000, record2);
		init_db[3] = new CADatabase("111-111-113", 1111, "허태영", 1000000, record3);
		init_db[4] = new CADatabase("111-111-114", 1111, "황재웅", 10000000, record4);
		init_db[5] = new TADatabase("211-111-111", 1111, "한규민", 1000000, 0.95, 6, "20190426", record5);
		init_db[6] = new TADatabase("211-111-112", 1111, "허윤석", 2000000, 1.02, 12, "20191230", record6);
		init_db[7] = new TADatabase("211-111-113", 1111, "허태영", 10000000, 1.02, 12, "20200111", record7);
		init_db[8] = new TADatabase("211-111-114", 1111, "황재웅", 1000000, 1.06, 15, "20200223", record8);

	}

	
	/* Tool */
	public Database checkAccount(String accountNumber) {

		for (Database temp : init_db) {
			if (temp.accountNumber.equals(accountNumber)) return temp;
		}
		return null;

	}

	
	/* Get information */
	public String getAccountNumber() {
		return this.accountNumber;
	}

	public long getPassword() {
		return this.password;
	}

	public String getName() {
		return this.name;
	}

	public long getBalance() {
		return this.balance;
	}

	public ArrayList<String> getRecord() {
		return this.record;
	}

	
	/* Modifier */
	public void setAccount(String accountNumber) {
		for (int i = 0; i < init_db.length; i++) {
			if (init_db[i].accountNumber.equals(accountNumber))
				init_db[i].accountNumber = accountNumber;
		}
	}

	public void setPassword(String accountNumber, long password) {
		for (int i = 0; i < init_db.length; i++) {
			if (init_db[i].accountNumber.equals(accountNumber))
				init_db[i].password = password;
		}
	}

	public void setName(String accountNumber, String name) {
		for (int i = 0; i < init_db.length; i++) {
			if (init_db[i].accountNumber.equals(accountNumber))
				init_db[i].name = name;
		}
	}

	public void setBalance(String accountNumber, long balance) {
		for (int i = 0; i < init_db.length; i++) {
			if (init_db[i].accountNumber.equals(accountNumber))
				init_db[i].balance = balance;
		}
	}

	public void setRecord(String accountNumber, ArrayList<String> record) {
		for (int i = 0; i < init_db.length; i++) {
			if (init_db[i].accountNumber.equals(accountNumber))
				init_db[i].record = record;
		}
	}

}