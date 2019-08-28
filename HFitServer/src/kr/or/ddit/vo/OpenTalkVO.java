package kr.or.ddit.vo;

import java.io.Serializable;

public class OpenTalkVO implements Serializable{
	private int opentalk_no;
	private String opentalk_name;
	public OpenTalkVO(int opentalk_no, String opentalk_name) {
		super();
		this.opentalk_no = opentalk_no;
		this.opentalk_name = opentalk_name;
	}
	
	public OpenTalkVO() {
		
	}
	public int getOpentalk_no() {
		return opentalk_no;
	}
	public void setOpentalk_no(int opentalk_no) {
		this.opentalk_no = opentalk_no;
	}
	public String getOpentalk_name() {
		return opentalk_name;
	}
	public void setOpentalk_name(String opentalk_name) {
		this.opentalk_name = opentalk_name;
	}
	
	

}
