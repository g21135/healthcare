package kr.or.ddit.vo;

import java.io.Serializable;

public class HopeVO implements Serializable{
	private String mem_id;
	private String hope_purpose;
	private String hope_day;
	private String hope_time;
	private String hope_age;
	private String hope_gender;
	
	public HopeVO(String mem_id, String hope_purpose, String hope_day, String hope_time, String hope_age,
			String hope_gender) {
		super();
		this.mem_id = mem_id;
		this.hope_purpose = hope_purpose;
		this.hope_day = hope_day;
		this.hope_time = hope_time;
		this.hope_age = hope_age;
		this.hope_gender = hope_gender;
	}
	
	public HopeVO() {
		
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getHope_purpose() {
		return hope_purpose;
	}

	public void setHope_purpose(String hope_purpose) {
		this.hope_purpose = hope_purpose;
	}

	public String getHope_day() {
		return hope_day;
	}

	public void setHope_day(String hope_day) {
		this.hope_day = hope_day;
	}

	public String getHope_time() {
		return hope_time;
	}

	public void setHope_time(String hope_time) {
		this.hope_time = hope_time;
	}

	public String getHope_age() {
		return hope_age;
	}

	public void setHope_age(String hope_age) {
		this.hope_age = hope_age;
	}

	public String getHope_gender() {
		return hope_gender;
	}

	public void setHope_gender(String hope_gender) {
		this.hope_gender = hope_gender;
	}
	
	

}
