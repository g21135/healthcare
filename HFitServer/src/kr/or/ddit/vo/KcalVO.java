package kr.or.ddit.vo;

import java.io.Serializable;

public class KcalVO implements Serializable{
	private int kcal_num; //사전 번호
	private int div_code; // 구분 코드
	private String kcal_name; // 이름
	private int kcal_calory; // 칼로리
		
	public int getKcal_num() {
		return kcal_num;
	}
	public void setKcal_num(int kcal_num) {
		this.kcal_num = kcal_num;
	}
	public int getDiv_code() {
		return div_code;
	}
	public void setDiv_code(int div_code) {
		this.div_code = div_code;
	}
	public String getKcal_name() {
		return kcal_name;
	}
	public void setKcal_name(String kcal_name) {
		this.kcal_name = kcal_name;
	}
	public int getKcal_calory() {
		return kcal_calory;
	}
	public void setKcal_calory(int kcal_calory) {
		this.kcal_calory = kcal_calory;
	}
	
	public KcalVO(int kcal_num, int div_code, String kcal_name, int kcal_calory) {
		super();
		this.kcal_num = kcal_num;
		this.div_code = div_code;
		this.kcal_name = kcal_name;
		this.kcal_calory = kcal_calory;
	}
	public KcalVO() {
		super();
	}
	
	
}
