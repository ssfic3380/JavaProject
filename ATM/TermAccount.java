import java.util.ArrayList;
import java.util.Calendar;

/**
 * Account Ŭ������ ��ӹ��� ���� Ŭ����
 * ���⿹�ݰ����� ����(����, ���ԱⰣ, ������, ������, ����)�� �����ϰ� �ҷ���  
 */

public class TermAccount extends Account {

	// Instance Variables
	private double rate = 0.0;
	private long term = 0;
	private Calendar today = Calendar.getInstance();
	private Calendar subscriptionDate = Calendar.getInstance();
	private Calendar dueDate = Calendar.getInstance();

	private double interest;

	// Constructor
	public TermAccount(String accountNumber, String name, long balance, double rate, long term, Calendar sDate,
			ArrayList<String> record) {
		super(accountNumber, name, balance, record);
		this.rate = rate;
		this.term = term;
		this.subscriptionDate = sDate;

		// ������(dueDate)�� ������(subscriptionDate)���� ���ԱⰣ�� ���� ��
		dueDate = subscriptionDate;
		dueDate.add(Calendar.MONTH, (int) term);
	}

	
	// Set Method
	public void setRate(double r) {
		rate = r;
	}

	public void setSubcriptionDate(Calendar sDate) {
		subscriptionDate = sDate;
	}

	public void setDueDate(Calendar dDate) {
		dueDate = dDate;
	}

	
	// Get Method
	public double getRate() {
		return rate;
	}

	public long getBalance() { // ���⿹�ݰ����� balance�� �Ϲݰ����� balance�� interest�� ���� ��
		interest = super.getBalance() * rate / 100;
		if (isMatured()) {
			return (long) ((long) (super.getBalance()) + interest);
		} else {
			return (long) (super.getBalance());
		}
	}

	public String getDueDate() {
		int year = dueDate.get(Calendar.YEAR);
		int month = dueDate.get(Calendar.MONTH) + 1;
		int day = dueDate.get(Calendar.DAY_OF_MONTH);
		String dd = year + "-" + month + "-" + day;
		return dd;
	}

	public double getInterest() {
		interest = super.getBalance() * rate / 100;
		if (isMatured()) {
			return interest;
		} else {
			return 0.0;
		}
	}
	
	public long getTerm() {
		return term;
	}
	
	// Show Method
	public long showBalance() {
		log.log_viewTA(this);
		return super.getBalance();
	}

	// Method
	public boolean isMatured() { // ���� �����Ⱓ�� �������� true ��ȯ
		if (today.after(dueDate)) { // ���� ����(today)�� ������(dueDate) ����(after) �̸�
			return true; // true
		}
		return false;
	}

} // end TermAccount method