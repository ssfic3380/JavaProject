import java.util.ArrayList;

/**
 * Database 클래스를 상속받은 하위 클래스
 * 관리자 계좌의 데이터베이스 클래스
 */

public class AdminDatabase extends Database  {
	
	private long one;
	private long five;
	
	public AdminDatabase(String accountNumber, long password, String name, long balance, long one, long five, ArrayList<String> record) {
		super(accountNumber, password, name, balance, record);
		this.one = one;
		this.five = five;
	}
	
	
	/* Get information */
	public long getOne() {
		return this.one;
	}
	
	public long getFive() {
		return this.five;
	}
	
	
	/* Modifier */
	public void setOne(String accountNumber, long one) {
		for (int i = 0; i < init_db.length; i++) {
			if(init_db[i].getAccountNumber().equals(accountNumber)) ((AdminDatabase)init_db[i]).one = one;
		}
	}
	
	public void setFive(String accountNumber, long five) {
		for (int i = 0; i < init_db.length; i++) {
			if(init_db[i].getAccountNumber().equals(accountNumber)) ((AdminDatabase)init_db[i]).five = five;
		}
	}
	
}