package kr.or.ddit.vo;

import java.io.Serializable;
import java.sql.Date;

public class TalkAdminVO implements Serializable {
	private int talkadmin_no;
	private String mem_id;
	private int opentalk_no;
	private Date talkadmin_date;

	public TalkAdminVO(int talkadmin_no, String mem_id, int opentalk_no, Date talkadmin_date) {
		super();
		this.talkadmin_no = talkadmin_no;
		this.mem_id = mem_id;
		this.opentalk_no = opentalk_no;
		this.talkadmin_date = talkadmin_date;
	}

	public TalkAdminVO() {

	}

	public int getTalkadmin_no() {
		return talkadmin_no;
	}

	public void setTalkadmin_no(int talkadmin_no) {
		this.talkadmin_no = talkadmin_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getOpentalk_no() {
		return opentalk_no;
	}

	public void setOpentalk_no(int opentalk_no) {
		this.opentalk_no = opentalk_no;
	}

	public Date getTalkadmin_date() {
		return talkadmin_date;
	}

	public void setTalkadmin_date(Date talkadmin_date) {
		this.talkadmin_date = talkadmin_date;
	}

}
