import java.util.ArrayList;

/**
 * Database Ŭ������ ��ӹ��� ���� Ŭ����
 * ����� ������ �����ͺ��̽� Ŭ����
 */

public class CADatabase extends Database {
	
	public CADatabase(String accountNumber, long password, String name, long balance, ArrayList<String> record) {
		super(accountNumber, password, name, balance, record);
	}
	
}