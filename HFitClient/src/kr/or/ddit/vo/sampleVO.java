package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Date;

import javafx.scene.control.Button;

public class sampleVO implements Serializable, Comparable<sampleVO> {
	private int mem_no;
	private String mem_id;
	private String mem_name;
	private Date membership_startdate;
	private String trainer_id;
	private String mem_grade;

	private int btn;
	private int check;

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public sampleVO(int mem_no, String mem_id, String mem_name, Date membership_startdate, String trainer_id,
			String mem_grade, int btn, int check) {
		super();
		this.mem_no = mem_no;
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.membership_startdate = membership_startdate;
		this.trainer_id = trainer_id;
		this.mem_grade = mem_grade;
		this.btn = btn;
		this.check = check;
	}

	public String getMem_grade() {
		return mem_grade;
	}

	public void setMem_grade(String mem_grade) {
		this.mem_grade = mem_grade;
	}

	public int getBtn() {
		return btn;
	}

	public void setBtn(int btn) {
		this.btn = btn;
	}

	public sampleVO() {
		super();
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

	@Override
	public int compareTo(sampleVO o) {
		if (this.mem_no < o.getMem_no()) {
			return -1;
		} else if (this.mem_no > o.getMem_no()) {
			return 1;
		}
		return 0;
	}
}
