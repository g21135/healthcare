package kr.or.ddit.vo;

import java.io.Serializable;

public class MatchVO implements Serializable{
	private String trainer_id;
	private String mem_id;
	private int membership_no;
	private String membership_startdate;
	private String mem_name;
	
	public MatchVO() {
		
	}

	public MatchVO(String trainer_id, String mem_id, int membership_no, String membership_startdate, String mem_name) {
		super();
		this.trainer_id = trainer_id;
		this.mem_id = mem_id;
		this.membership_no = membership_no;
		this.membership_startdate = membership_startdate;
		this.mem_name = mem_name;
	}

	public String getTrainer_id() {
		return trainer_id;
	}

	public void setTrainer_id(String trainer_id) {
		this.trainer_id = trainer_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getMembership_no() {
		return membership_no;
	}

	public void setMembership_no(int membership_no) {
		this.membership_no = membership_no;
	}

	public String getMembership_startdate() {
		return membership_startdate;
	}

	public void setMembership_startdate(String membership_startdate) {
		this.membership_startdate = membership_startdate;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}


}
