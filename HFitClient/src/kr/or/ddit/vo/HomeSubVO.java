package kr.or.ddit.vo;

import java.io.Serializable;

import javafx.fxml.Initializable;

public class HomeSubVO implements Serializable{
	private int sub_no;
	private String mem_id;
	private int home_no;
	
	public HomeSubVO(int sub_no, String mem_id, int home_no) {
		super();
		this.sub_no = sub_no;
		this.mem_id = mem_id;
		this.home_no = home_no;
	}
	
	public HomeSubVO() {
		
	}
	public int getSub_no() {
		return sub_no;
	}
	public void setSub_no(int sub_no) {
		this.sub_no = sub_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getHome_no() {
		return home_no;
	}
	public void setHome_no(int home_no) {
		this.home_no = home_no;
	}
	
	

}
