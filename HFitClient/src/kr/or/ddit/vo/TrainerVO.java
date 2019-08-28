package kr.or.ddit.vo;

import java.io.Serializable;

public class TrainerVO implements Serializable{
	
	private int trainer_no;
	public int getTrainer_no() {
		return trainer_no;
	}
	public void setTrainer_no(int trainer_no) {
		this.trainer_no = trainer_no;
	}
	private String trainer_id;
	private String trainer_pass;
	private String trainer_name;
	private String trainer_gender;
	private String trainer_tel;
	private String trainer_bir;
	private String trainer_email;
	private String trainer_addr;
	private String trainer_career;
	private int trainer_age;
	private String trainer_Specialty;
	private int check;
	
	public TrainerVO() {
		super();
	}
	
	
	public TrainerVO(int trainer_no, String trainer_id, String trainer_pass, String trainer_name, String trainer_gender,
			String trainer_tel, String trainer_bir, String trainer_email, String trainer_addr, String trainer_career,
			int trainer_age, String trainer_Specialty, int check) {
		super();
		this.trainer_no = trainer_no;
		this.trainer_id = trainer_id;
		this.trainer_pass = trainer_pass;
		this.trainer_name = trainer_name;
		this.trainer_gender = trainer_gender;
		this.trainer_tel = trainer_tel;
		this.trainer_bir = trainer_bir;
		this.trainer_email = trainer_email;
		this.trainer_addr = trainer_addr;
		this.trainer_career = trainer_career;
		this.trainer_age = trainer_age;
		this.trainer_Specialty = trainer_Specialty;
		this.check = check;
	}
	public String getTrainer_id() {
		return trainer_id;
	}
	public void setTrainer_id(String trainer_id) {
		this.trainer_id = trainer_id;
	}
	public String getTrainer_pass() {
		return trainer_pass;
	}
	public void setTrainer_pass(String trainer_pass) {
		this.trainer_pass = trainer_pass;
	}
	public String getTrainer_name() {
		return trainer_name;
	}
	public void setTrainer_name(String trainer_name) {
		this.trainer_name = trainer_name;
	}
	public String getTrainer_gender() {
		return trainer_gender;
	}
	public void setTrainer_gender(String trainer_gender) {
		this.trainer_gender = trainer_gender;
	}
	public String getTrainer_tel() {
		return trainer_tel;
	}
	public void setTrainer_tel(String trainer_tel) {
		this.trainer_tel = trainer_tel;
	}
	public String getTrainer_bir() {
		return trainer_bir;
	}
	public void setTrainer_bir(String trainer_bir) {
		this.trainer_bir = trainer_bir;
	}
	public String getTrainer_email() {
		return trainer_email;
	}
	public void setTrainer_email(String trainer_email) {
		this.trainer_email = trainer_email;
	}
	public String getTrainer_addr() {
		return trainer_addr;
	}
	public void setTrainer_addr(String trainer_addr) {
		this.trainer_addr = trainer_addr;
	}
	public String getTrainer_career() {
		return trainer_career;
	}
	public void setTrainer_career(String trainer_career) {
		this.trainer_career = trainer_career;
	}
	public int getTrainer_age() {
		return trainer_age;
	}
	public void setTrainer_age(int trainer_age) {
		this.trainer_age = trainer_age;
	}
	public String getTrainer_Specialty() {
		return trainer_Specialty;
	}
	public void setTrainer_Specialty(String trainer_Specialty) {
		this.trainer_Specialty = trainer_Specialty;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
		
		
}













