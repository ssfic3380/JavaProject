import java.util.ArrayList;

/**
 * Account 클래스를 상속받은 하위 클래스
 * 입출금계좌의 정보를 저장하고 불러옮 
 * 계좌의 입금과 출금관련 메소드를 포함 
 */

public class CheckingAccount extends Account{
	
	// Constructor
	public CheckingAccount(String accountNumber, String name, long balance, ArrayList<String> record) {
		super(accountNumber, name, balance, record);
	}
	
	
	// Method
	public CheckingAccount addBalance(long value) {
		
		setBalance( getBalance() + value );	// 입력받는 지폐수를 세는 것은 구현하지 않았다 -> 은행 총 지폐수에 추가하지 않음
		log.log_addBalance(this, value);
		
		return this;
		
	}
	
	public CheckingAccount subtractBalance(long value, long fiftyThousandWon) {	
		
		long tenThousandWon = (value - (fiftyThousandWon*50000)) / 10000;
		
		if (getBalance() >= value) {
			
			// 본인 잔고에서 빼기
			setBalance( getBalance() - value );
			
			// 은행 지폐 수 줄이기
			if (tenThousandWon != 0) ((Admin)Main.admin).subtractOnes(tenThousandWon);
			if (fiftyThousandWon != 0) ((Admin)Main.admin).subtractFives(fiftyThousandWon);
			
			log.log_subtractBalance(this, value);
			
		}
		else {	// 잔고가 부족하면 null을 리턴
			return null;
		}
		return this;
	} 
	
}	// end CheckingAccount class