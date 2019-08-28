package kr.or.ddit.vo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;

import javafx.scene.control.CheckBox;
import kr.or.ddit.service.trainerTalk.ChatClient;

public class MemberVO implements Serializable{
	
	private int mem_no;
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_bir;
	private String mem_gender;
	private String mem_email;
	private String mem_tel;
	private String mem_addr;
	private String mem_grade;
	private int mem_check;
	private CheckBox checkBox;
	public CheckBox getCheckBox() {
		return checkBox;
	}
	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
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
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_bir() {
		return mem_bir;
	}
	public void setMem_bir(String mem_bir) {
		this.mem_bir = mem_bir;
	}
	public String getMem_gender() {
		return mem_gender;
	}
	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	public String getMem_grade() {
		return mem_grade;
	}
	public void setMem_grade(String mem_grade) {
		this.mem_grade = mem_grade;
	}
	public int getMem_check() {
		return mem_check;
	}
	public void setMem_check(int mem_check) {
		this.mem_check = mem_check;
	}
	public MemberVO(int mem_no, String mem_id, String mem_pass, String mem_name, String mem_bir, String mem_gender,
			String mem_email, String mem_tel, String mem_addr, String mem_grade, int mem_check, CheckBox checkBox) {
		super();
		this.mem_no = mem_no;
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
		this.mem_name = mem_name;
		this.mem_bir = mem_bir;
		this.mem_gender = mem_gender;
		this.mem_email = mem_email;
		this.mem_tel = mem_tel;
		this.mem_addr = mem_addr;
		this.mem_grade = mem_grade;
		this.mem_check = mem_check;
		this.checkBox = checkBox;
	}
	public MemberVO() {
		super();
	}
	
	
	
	
	
	
}
