import java.util.ArrayList;

/**
 * Database 클래스를 상속받은 하위 클래스
 * 입출금 계좌의 데이터베이스 클래스
 */

public class CADatabase extends Database {
	
	public CADatabase(String accountNumber, long password, String name, long balance, ArrayList<String> record) {
		super(accountNumber, password, name, balance, record);
	}
	
}