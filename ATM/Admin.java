import java.util.ArrayList;

/**
 * Account Ŭ������ ��ӹ��� ���� Ŭ����
 * ������ �����ǰ� ���������� ������ �����ϰ� ������ ���շα׸� ����ϴ� �޼ҵ带 ����
 */

public class Admin extends Account {

	// Instance Variables
	private long one;
	private long five;
	
	// Constructor
	public Admin(String accountNumber, String name, long balance, long one, long five, ArrayList<String> record) {
		super(accountNumber, name, balance, record);
		this.one = one;
		this.five = five;
	}

	
	// Set Method
	public void setOne(long o) {
		one = o;
	}

	public void setFive(long f) {
		five = f;
	}

	
	// Get Method
	public long getOne() {
		return one;
	}

	public long getFive() {
		return five;
	}

	public long getBalance() {
		return (one * 10000) + (five * 50000);
	}
	
	
	// Show Method
	public long showOne() {
		log.log_viewPaper(this);
		return getOne();
	}
	
	public long showFive() {
		return getFive();
	}
	
	
	// Method
	public Admin addOnes(long o) {
		setOne(getOne() + o);
		log.log_addOne(this, o);

		return this;
	}

	public Admin addFives(long f) {
		setFive(getFive() + f);
		log.log_addFive(this, f);

		return this;
	}

	public Admin subtractOnes(long o) {
		setOne(getOne() - o);
		log.log_subtractOne(this, o);

		return this;
	}

	public Admin subtractFives(long f) {
		setFive(getFive() - f);
		log.log_subtractFive(this, f);

		return this;
	}
	
	public ArrayList<String> getBankLog() {
		
		log.log_viewRecord(this);
		
		return this.getRecord();
		
	}

} // end Admin class