package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Date;

public class ChallengeVO implements Serializable{
	
	private int chall_no;
	private String chall_name;
	private String chall_content;
	private String chall_photo;
	private Date chall_start;
	private Date chall_end;
	
	public int getChall_no() {
		return chall_no;
	}
	public void setChall_no(int chall_no) {
		this.chall_no = chall_no;
	}
	public String getChall_name() {
		return chall_name;
	}
	public void setChall_name(String chall_name) {
		this.chall_name = chall_name;
	}
	public String getChall_content() {
		return chall_content;
	}
	public void setChall_content(String chall_content) {
		this.chall_content = chall_content;
	}
	public String getChall_photo() {
		return chall_photo;
	}
	public void setChall_photo(String chall_photo) {
		this.chall_photo = chall_photo;
	}
	public Date getChall_start() {
		return chall_start;
	}
	public void setChall_start(Date chall_start) {
		this.chall_start = chall_start;
	}
	public Date getChall_end() {
		return chall_end;
	}
	public void setChall_end(Date chall_end) {
		this.chall_end = chall_end;
	}
	public ChallengeVO(int chall_no, String chall_name, String chall_content, String chall_photo, Date chall_start,
			Date chall_end) {
		super();
		this.chall_no = chall_no;
		this.chall_name = chall_name;
		this.chall_content = chall_content;
		this.chall_photo = chall_photo;
		this.chall_start = chall_start;
		this.chall_end = chall_end;
	}
	public ChallengeVO() {
		super();
	}

	
}
