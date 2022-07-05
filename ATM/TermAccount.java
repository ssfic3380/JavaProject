import java.util.ArrayList;
import java.util.Calendar;

/**
 * Account 클래스를 상속받은 하위 클래스
 * 정기예금계좌의 정보(이율, 가입기간, 가입일, 만기일, 이자)를 저장하고 불러옮  
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

		// 만기일(dueDate)은 시작일(subscriptionDate)에서 가입기간을 더한 값
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

	public long getBalance() { // 정기예금계좌의 balance는 일반계좌의 balance에 interest를 더한 값
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
	public boolean isMatured() { // 예금 약정기간이 지났으면 true 반환
		if (today.after(dueDate)) { // 만약 오늘(today)이 만기일(dueDate) 이후(after) 이면
			return true; // true
		}
		return false;
	}

} // end TermAccount method