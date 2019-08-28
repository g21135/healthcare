package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Date;

public class sampleVO implements Serializable{
	private int mem_no;
	private String mem_id;
	private String mem_name;
	private Date membership_startdate;
	private String trainer_id;
	
	public sampleVO() {
		super();
	}
	public sampleVO(int mem_no, String mem_id, String mem_name, Date membership_startdate, String trainer_id) {
		super();
		this.mem_no = mem_no;
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.membership_startdate = membership_startdate;
		this.trainer_id = trainer_id;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public Date getMembership_startdate() {
		return membership_startdate;
	}
	public void setMembership_startdate(Date membership_startdate) {
		this.membership_startdate = membership_startdate;
	}
	public String getTrainer_id() {
		return trainer_id;
	}
	public void setTrainer_id(String trainer_id) {
		this.trainer_id = trainer_id;
	}
}
