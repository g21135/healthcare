package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Date;

public class MembershipVO implements Serializable{
	private int membership_no;
	private String mem_id;
	private Date membership_startdate;
	private Date membership_enddate;
	public MembershipVO(int membership_no, String mem_id, Date membership_startdate, Date membership_enddate) {
		super();
		this.membership_no = membership_no;
		this.mem_id = mem_id;
		this.membership_startdate = membership_startdate;
		this.membership_enddate = membership_enddate;
	}
	
	public MembershipVO() {
		
	}
	public int getMembership_no() {
		return membership_no;
	}
	public void setMembership_no(int membership_no) {
		this.membership_no = membership_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public Date getMembership_startdate() {
		return membership_startdate;
	}
	public void setMembership_startdate(Date membership_startdate) {
		this.membership_startdate = membership_startdate;
	}
	public Date getMembership_enddate() {
		return membership_enddate;
	}
	public void setMembership_enddate(Date membership_enddate) {
		this.membership_enddate = membership_enddate;
	}
	
	

}
