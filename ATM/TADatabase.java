import java.util.ArrayList;

/**
 * Database 클래스를 상속받은 하위 클래스
 * 정기예금 계좌의 데이터베이스 클래스
 */

public class TADatabase extends Database  {
	
	private double rate;
	private String subscriptionDate;
	private long term;
	
	public TADatabase(String accountNumber, long password, String name, long balance, double rate, long term, String subscriptionDate, ArrayList<String> record) {
		super(accountNumber, password, name, balance, record);
		this.rate = rate;
		this.subscriptionDate = subscriptionDate;
		this.term = term;
	}
	
	
	/* Get information */
	public double getRate() {
		return this.rate;
	}
	
	public long getTerm() {
		return this.term;
	}
	
	public String getSubscriptionDate() {
		return this.subscriptionDate;
	}
	
	
	/* Modifier */
	public void setRate(String accountNumber, double rate) {
		for (int i = 0; i < init_db.length; i++) {
			if(init_db[i].getAccountNumber().equals(accountNumber)) ((TADatabase)init_db[i]).rate = rate;
		}
	}
	
	public void setTerm(String accountNumber, long term) {
		for (int i = 0; i < init_db.length; i++) {
			if(init_db[i].getAccountNumber().equals(accountNumber)) ((TADatabase)init_db[i]).term = term;
		}
	}
	
	public void setSubscriptionDate(String accountNumber, String subscriptionDate) {
		for (int i = 0; i < init_db.length; i++) {
			if(init_db[i].getAccountNumber().equals(accountNumber)) ((TADatabase)init_db[i]).subscriptionDate = subscriptionDate;
		}
	}
	
}