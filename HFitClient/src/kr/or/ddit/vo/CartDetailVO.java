package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Date;

public class CartDetailVO implements Serializable {

	private int deal_no;
	private int cart_no;
	private int prod_no;
	private int deal_qty;
	
	
	public CartDetailVO() {
	}


	public CartDetailVO(int deal_no, int cart_no, int prod_no, int deal_qty) {
		super();
		this.deal_no = deal_no;
		this.cart_no = cart_no;
		this.prod_no = prod_no;
		this.deal_qty = deal_qty;
	}


	public int getDeal_no() {
		return deal_no;
	}


	public void setDeal_no(int deal_no) {
		this.deal_no = deal_no;
	}


	public int getCart_no() {
		return cart_no;
	}


	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}


	public int getProd_no() {
		return prod_no;
	}


	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}


	public int getDeal_qty() {
		return deal_qty;
	}


	public void setDeal_qty(int deal_qty) {
		this.deal_qty = deal_qty;
	}
	
	
	
}
