package kr.or.ddit.vo;

import java.io.Serializable;

public class CardVO implements Serializable{
	private long card_number; // 카드번호
	private String card_name; // 카드번호
	private int card_cvc; // 카드번호
	private int card_pass; // 비밀번호
	private int card_balance; // 잔액
	private int card_alidity; // 유효기간
	private String mem_bir; // 주민등록번호
	
	public CardVO() {
	}
	
	

	public CardVO(long card_number, String card_name, int card_cvc, int card_pass, int card_balance, int card_alidity,
			String mem_bir) {
		super();
		this.card_number = card_number;
		this.card_name = card_name;
		this.card_cvc = card_cvc;
		this.card_pass = card_pass;
		this.card_balance = card_balance;
		this.card_alidity = card_alidity;
		this.mem_bir = mem_bir;
	}



	public long getCard_number() {
		return card_number;
	}

	public void setCard_number(long card_number) {
		this.card_number = card_number;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public int getCard_cvc() {
		return card_cvc;
	}

	public void setCard_cvc(int card_cvc) {
		this.card_cvc = card_cvc;
	}

	public int getCard_pass() {
		return card_pass;
	}

	public void setCard_pass(int card_pass) {
		this.card_pass = card_pass;
	}

	public int getCard_balance() {
		return card_balance;
	}

	public void setCard_balance(int card_balance) {
		this.card_balance = card_balance;
	}

	public int getCard_alidity() {
		return card_alidity;
	}

	public void setCard_alidity(int card_alidity) {
		this.card_alidity = card_alidity;
	}

	public String getMem_bir() {
		return mem_bir;
	}

	public void setMem_bir(String mem_bir) {
		this.mem_bir = mem_bir;
	}

	
	
		
	

}
