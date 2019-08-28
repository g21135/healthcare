package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Date;

public class ChartVO implements Serializable {

	private String mem_id;
	private int mem_kg;
	private int mem_height;
	private Date chart_date;

	public ChartVO() {

	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getMem_kg() {
		return mem_kg;
	}

	public void setMem_kg(int mem_kg) {
		this.mem_kg = mem_kg;
	}

	public int getMem_height() {
		return mem_height;
	}

	public void setMem_height(int mem_height) {
		this.mem_height = mem_height;
	}

	public Date getChart_date() {
		return chart_date;
	}

	public void setChart_date(Date chart_date) {
		this.chart_date = chart_date;
	}

	public ChartVO(String mem_id, int mem_kg, int mem_height, Date chart_date) {
		super();
		this.mem_id = mem_id;
		this.mem_kg = mem_kg;
		this.mem_height = mem_height;
		this.chart_date = chart_date;
	}

}
