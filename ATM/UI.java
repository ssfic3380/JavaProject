import java.util.ArrayList;
import java.util.Scanner;

/**
 * ATM의 입력을 받아서 전달해주고, 해당하는 메뉴를 출력해주는 클래스
 */

public class UI {

	private GUI gui = new GUI();

	/* Tool */
	// 로그인 - 계좌번호
	public String get_id() {
		gui.reset(); 
		gui.repaint( gui.center_input("계좌번호를 입력해주세요.", true, false) );
		gui.lock();
		return gui.inputMsg;
	}

	// 로그인 - 비밀번호
	public long get_pass() {
		gui.reset();
		gui.repaint( gui.center_input("비밀번호를 입력해주세요.", false, false) );
		gui.lock();
		
		long pass = 0;
		if (!gui.inputMsg.equals("")) pass = Long.parseLong(gui.inputMsg);
		else pass = 0;
		
		return pass;
	}

	// 로그인 - 실패
	public boolean login_fail_msg() {
		gui.reset();
		gui.repaint( gui.center_alart("아이디 또는 비밀번호가 틀렸습니다", "다시 로그인하기", "메인 메뉴로 돌아가기", true) );
		gui.lock();
		int num = gui.choice;
		if (num == 1) {
			return false;
		} else {
			return true;
		}
	}

	// 로그아웃
	public void logout_success() {
		gui.reset();
		gui.repaint( gui.center_alart("로그아웃 되었습니다. 이용해주셔서 감사합니다.", "확       인", "", false) );
		gui.lock();
	}

	// 금액을 입력받는 함수
	public long get_price(String text) {
		gui.reset();
		gui.repaint( gui.center_input(text + "하실 금액을 입력해주세요.", false, true) );
		gui.lock();
		long price = Long.parseLong(gui.inputMsg);
		return price;
	}

	/* 0. Main Menu */
	public int main_menu(boolean isLogin, boolean isTerm, boolean isAdmin, String name) {
		
		gui.reset();

		if (isTerm) // 정기예금계좌로 로그인
			return 4;
		if (isAdmin) // 관리자 계정으로 로그인
			return 99;
		if (isLogin) { // 입출금계좌로 로그인
			String msg = String.format("<html><center>%s님, 환영합니다!<br/>메뉴를 선택해주세요.</center></html>", name);
			gui.repaint( gui.center_menu("잔고 조회", 1, "입       금", 2, "출       금", 3, "", 0, "", 0, "로그아웃", 10, msg) );
		} else {// 로그인 안되어있을시
			String msg = "<html><center>이용해주셔서 감사합니다!<br/>메뉴를 선택해주세요.</center></html>";
			gui.repaint( gui.center_menu("잔고 조회", 1, "입       금", 2, "출       금", 3, "정기예금계좌 조회", 4, "", 0, "관리자 메뉴", 99, msg) );
		}

		gui.lock();
		return gui.choice;
		
	}

	/* 1. CheckingAccount Menu */
	// 입출금 메뉴 접근 오류
	public void not_CA() {
		gui.reset();
		gui.repaint( gui.center_alart("입출금 계좌가 아닙니다. 해당 메뉴를 이용하실 수 없습니다.", "확       인", "", false) );
		gui.lock();
	}

	// 입출금 잔고조회
	public void ca_menu_balance(String accountNumber, long balance) {
		gui.reset();
		String msg1 = "<html>계좌번호:&emsp;" + accountNumber + "<br/>";
		String msg2 = "잔&emsp;&emsp;고:&emsp;" + balance + " 원</html>";
		gui.repaint( gui.center_alart(msg1 + msg2, "확       인", "", false));
		gui.lock();
	}

	// 입출금 입금
	public long ca_menu_add() {
		return get_price("입금 ");
	}

	// 입출금 입금 성공
	public void ca_menu_add_success(String accountNumber, long money, long balance) {
		
		String msg1 = "<html>계좌번호:&emsp;" + accountNumber + "<br/>";
		String msg2 = "입금금액:&emsp;" + money + " 원<br/>";
		String msg3 = "잔&emsp;&emsp;고:&emsp;" + balance + " 원</html>";
		gui.reset();
		gui.repaint( gui.center_alart(msg1 + msg2 + msg3, "확       인", "", false) );
		gui.lock();
		
	}

	// 입출금 입금 실패
	public void ca_menu_add_fail() {
		gui.reset();
		gui.repaint( gui.center_alart("입금은 1000원 혹은 10000원 단위만 가능합니다.", "확       인", "", false) );
		gui.lock();
	}

	// 입출금 출금
	public long ca_menu_withdraw() {
		return get_price("출금 ");
	}

	// 입출금 출금 성공
	public void ca_menu_withdraw_success(String accountNumber, long money, long one, long five, long balance) {

		String msg = String.format("<html>계좌번호:&emsp;%s<br/>", accountNumber);
		String msg1 = String.format("인출금액:&emsp;%d 원<br/>", money);
		String msg2 = String.format("오만원권:&emsp;%d 매<br/>", one);
		String msg3 = String.format("만&ensp;원&ensp;권:&emsp;%d 매<br/>", five);
		String msg4 = String.format("잔&emsp;&emsp;고:&emsp;%d 원</html>", balance);

		gui.reset();
		gui.repaint( gui.center_alart(msg + msg1 + msg2 + msg3 + msg4, "확       인", "", false));
		gui.lock();
	}

	// 입출금 출금 실패
	public void ca_menu_withdraw_fail() {
		gui.reset();
		gui.repaint( gui.center_alart("출금을 위한 잔액이 부족합니다.", "확       인", "", false));
		gui.lock();
	}

	// 입출금 출금 실패2
	public void ca_menu_withdraw_fail2() {
		gui.reset();
		gui.repaint( gui.center_alart("출금은 1000원 혹은 10000원 단위만 가능합니다. 다시 입력해 주세요.", "확       인", "", false));
		gui.lock();
	}

	// 입출금 잔액 부족
	public void ca_menu_subtract_fail() {
		gui.reset();
		gui.repaint( gui.center_alart("출금을 위한 잔액이 부족합니다. 다시 입력해 주세요.", "확       인", "", false));
		gui.lock();
	}

	// 입출금 잔액 부족2
	public void ca_menu_subtract_fail2() {
		gui.reset();
		gui.repaint( gui.center_alart("출금할 수 있는 5만원권 수를 초과했습니다. 다시 입력해 주세요.", "확       인", "", false));
		gui.lock();
	}

	// 입출금 잔액 부족3
	public void ca_menu_subtract_fail3() {
		gui.reset();
		gui.repaint( gui.center_alart("ATM 기기가 보유중인 지폐수가 부족합니다. 관리자에게 문의해주세요.", "확       인", "", false));
		gui.lock();
	}

	// 입출금 오만원권 개수 결정
	public long ca_menu_subtract_five(String accountNumber, long l) {
		
		String msg = String.format("<html><center>현재 %s계좌의<br/>오만원권 출금 가능 개수는 %d장 입니다.<br/>원하는 5만원권 출금 개수를 입력하세요.</center></html>", accountNumber, l);
		gui.reset();
		gui.repaint( gui.center_input(msg, false, true) );
		gui.lock();
		
		long num = Long.parseLong(gui.inputMsg);
		return num;
	}

	/* 2. TermAccount Menu */
	// 정기예금 메뉴 접근 오류
	public void not_TA() {
		gui.reset();
		gui.repaint( gui.center_alart("정기예금계좌가 아닙니다. 해당 메뉴를 이용하실 수 없습니다.\n", "확       인", "", false));
		gui.lock();
	}

	// 정기예금 메뉴조회
	public int get_ta_menu(String name) {
		String msg = String.format("<html><center>%s님, 환영합니다!<br/>메뉴를 선택해주세요.</center></html>", name);
		gui.reset();
		gui.repaint( gui.center_menu("정기예금계좌 조회", 1, "", 0, "", 0, "", 0, "", 0, "로그아웃", 10, msg) );
		gui.lock();
		
		int choice = gui.choice;
		return choice;
	}

	// 정기예금계좌 조회
	public void ta_menu_account(String accountNumber, long balance, long term, double rate, String date,
			double interest) {
		String msg = String.format("<html>계좌번호:&emsp;%s<br/>", accountNumber);
		String msg1 = String.format("예&ensp;치&ensp;금:&emsp;%d 원<br/>", balance);
		String msg2 = String.format("가입기간:&emsp;%d 개월<br/>", term);
		String msg3 = String.format("예금이율:&emsp;%.2f%%<br/>", rate);
		String msg4 = String.format("만&ensp;기&ensp;일:&emsp;%s<br/>", date);
		String msg5 = String.format("이&emsp;&emsp;자:&emsp;%d 원</html>", (int) interest);
		gui.reset();
		gui.repaint( gui.center_alart(msg + msg1 + msg2 + msg3 + msg4 + msg5, "확       인", "", false));
		gui.lock();
	}

	/* 3. Admin Menu */
	// 관리자 메뉴 접근 오류
	public void not_Admin() {
		gui.reset();
		gui.repaint( gui.center_alart("접근이 제한된 메뉴입니다.\n", "확       인", "", false));
		gui.lock();
	}

	// 관리자 메뉴 조회
	public int get_ad_menu() {
		Scanner scan = new Scanner(System.in);
		String msg = String.format("<html><center>관리자 메뉴에 접근하셨습니다.<br/>업무를 선택해주세요.<br/>");
		String msg1 = String.format("<span style=\"color:red\">[! 모든 내역은 전산시스템에 기록됩니다 !]</span></center></html>");

		gui.reset();
		gui.repaint( gui.center_menu("지폐 현황 조회", 1, "만원권 추가", 2, "오만원권 추가", 3, "은행 기록 조회", 4, "ATM 기기 종료", 5, "로그아웃", 10, msg + msg1) );
		gui.lock();
		
		int choice = gui.choice;
		return choice;
	}

	// 관리자 지페 현황 조회
	public void ad_menu_show(long one, long five) {
		String msg = String.format("<html>만&ensp;원&ensp;권:&emsp;%d 매<br/>", one);
		String msg1 = String.format("오만원권:&emsp;%d 매</html>", five);
		gui.reset();
		gui.repaint( gui.center_alart(msg + msg1, "확       인", "", false));
		gui.lock();
	}

	// 관리자 만원권 추가
	public long ad_menu_addOne() {
		gui.reset();
		gui.repaint( gui.center_input("추가할 1만원권 수를 입력해주세요.", false, true) );
		gui.lock();

		long num = Long.parseLong(gui.inputMsg);
		return num;

	}

	// 관리자 오만원권 추가
	public long ad_menu_addFive() {

		gui.reset();
		gui.repaint( gui.center_input("추가할 5만원권 수를 입력해주세요.", false, true) );
		gui.lock();

		long num = Long.parseLong(gui.inputMsg);
		return num;
	}

	// 은행 기록 조회
	public void ad_menu_bankLog(ArrayList<String> log) {

		gui.reset();
		String logs = "<html>";
		for (String temp : log) {
			if (log.isEmpty())
				logs = "[내역 없음]";
			else
				logs = logs + "<br/>" + temp;
		}
		logs = logs + "</html>";
		
		gui.repaint( gui.center_alart(logs, "확       인", "", false));
		gui.lock();

	}

	// 관리자 ATM 종료
	public int ad_menu_atmOff() {
		String msg = String.format("기기를 종료하시겠습니까?");
		String msg1 = String.format("1: 종료");
		String msg2 = String.format("2: 취소");

		gui.reset();
		gui.repaint( gui.center_alart("기기를 종료하시겠습니까?", "종       료", "취       소", true));
		gui.lock();
		int choice = gui.choice;
		return choice;
	}

}