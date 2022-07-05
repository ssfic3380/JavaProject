import java.util.ArrayList;

/**
 * Account Ŭ������ ��ӹ��� ���� Ŭ����
 * ����ݰ����� ������ �����ϰ� �ҷ��� 
 * ������ �Աݰ� ��ݰ��� �޼ҵ带 ���� 
 */

public class CheckingAccount extends Account{
	
	// Constructor
	public CheckingAccount(String accountNumber, String name, long balance, ArrayList<String> record) {
		super(accountNumber, name, balance, record);
	}
	
	
	// Method
	public CheckingAccount addBalance(long value) {
		
		setBalance( getBalance() + value );	// �Է¹޴� ������� ���� ���� �������� �ʾҴ� -> ���� �� ������� �߰����� ����
		log.log_addBalance(this, value);
		
		return this;
		
	}
	
	public CheckingAccount subtractBalance(long value, long fiftyThousandWon) {	
		
		long tenThousandWon = (value - (fiftyThousandWon*50000)) / 10000;
		
		if (getBalance() >= value) {
			
			// ���� �ܰ��� ����
			setBalance( getBalance() - value );
			
			// ���� ���� �� ���̱�
			if (tenThousandWon != 0) ((Admin)Main.admin).subtractOnes(tenThousandWon);
			if (fiftyThousandWon != 0) ((Admin)Main.admin).subtractFives(fiftyThousandWon);
			
			log.log_subtractBalance(this, value);
			
		}
		else {	// �ܰ� �����ϸ� null�� ����
			return null;
		}
		return this;
	} 
	
}	// end CheckingAccount class