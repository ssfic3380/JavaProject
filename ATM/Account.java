import java.util.ArrayList;

/**
 * 계좌를 상속시키기 위한 추상 클래스 
 * 계좌의 잔고, 계좌번호, 가입자의 이름, 로그를 저장하고 불러옮
 * 하위 클래스 : CheckingAccount, TermAccount, Admin  
 */

public abstract class Account {

	// Instance Variable
	protected Log log = new Log();

	private long balance = 0;
	private String accountNumber = null;
	private String name = null;
	private ArrayList<String> record = null;
	
	// Constructor
	public Account(String accountNumber, String name, long balance, ArrayList<String> record) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.record = record;
	}

	
	// Set Method
	public void setBalance(long d) {
		this.balance = d;
	}

	public void setAccountNumber(String accountNum) {
		this.accountNumber = accountNum;
	}

	public void setRecord(ArrayList<String> record) {
		this.record = record;
	}

	
	// Get Method
	public long getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getRecord() {
		return record;
	}

	
	// Show Method
	public long showBalance() {
		log.log_viewCA(this);
		return getBalance();
	}

} // end Account class