package kr.or.ddit.vo;

import java.util.Date;

public class MsgHistoryVO {
	private int history_num;
	private String mem_id;
	private String history_content;
	private Date history_time;
	
	public MsgHistoryVO(int history_num, String mem_id, String history_content, Date history_time) {
		super();
		this.history_num = history_num;
		this.mem_id = mem_id;
		this.history_content = history_content;
		this.history_time = history_time;
	}
	
	public MsgHistoryVO() {
		
	}
	
	public int getHistory_num() {
		return history_num;
	}
	public void setHistory_num(int history_num) {
		this.history_num = history_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getHistory_content() {
		return history_content;
	}
	public void setHistory_content(String history_content) {
		this.history_content = history_content;
	}
	public Date getHistory_time() {
		return history_time;
	}
	public void setHistory_time(Date history_time) {
		this.history_time = history_time;
	}
	
	
	

	
}
