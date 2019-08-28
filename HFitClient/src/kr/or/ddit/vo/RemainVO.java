package kr.or.ddit.vo;

import java.util.Date;

public class RemainVO {

	private Date remain_YearMonth;
	private int prod_no;
	private int remain_preStock;
	private int remain_curStock;
	private int remain_inStock;
	private int remain_outStock;
	private Date remain_date;
	
	public RemainVO() {
		
	}
	
	public RemainVO(Date remain_YearMonth, int prod_no, int remain_preStock, int remain_curStock, int remain_inStock,
			int remain_outStock, Date remain_date) {
		super();
		this.remain_YearMonth = remain_YearMonth;
		this.prod_no = prod_no;
		this.remain_preStock = remain_preStock;
		this.remain_curStock = remain_curStock;
		this.remain_inStock = remain_inStock;
		this.remain_outStock = remain_outStock;
		this.remain_date = remain_date;
	}

	public Date getRemain_YearMonth() {
		return remain_YearMonth;
	}

	public void setRemain_YearMonth(Date remain_YearMonth) {
		this.remain_YearMonth = remain_YearMonth;
	}

	public int getProd_no() {
		return prod_no;
	}

	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}

	public int getRemain_preStock() {
		return remain_preStock;
	}

	public void setRemain_preStock(int remain_preStock) {
		this.remain_preStock = remain_preStock;
	}

	public int getRemain_curStock() {
		return remain_curStock;
	}

	public void setRemain_curStock(int remain_curStock) {
		this.remain_curStock = remain_curStock;
	}

	public int getRemain_inStock() {
		return remain_inStock;
	}

	public void setRemain_inStock(int remain_inStock) {
		this.remain_inStock = remain_inStock;
	}

	public int getRemain_outStock() {
		return remain_outStock;
	}

	public void setRemain_outStock(int remain_outStock) {
		this.remain_outStock = remain_outStock;
	}

	public Date getRemain_date() {
		return remain_date;
	}

	public void setRemain_date(Date remain_date) {
		this.remain_date = remain_date;
	}
	
	
	
	
}
