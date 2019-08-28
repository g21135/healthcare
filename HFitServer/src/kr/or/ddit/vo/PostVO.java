package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Date;

public class PostVO implements Serializable{
	private int post_no;
	private int board_no;
	private String mem_id;
	private String post_title;
	private String post_content;
	private Date post_date;
	private String post_photo;
	
	public PostVO(int post_no, int board_no, String mem_id, String post_title, String post_content,
			Date post_date, String post_photo) {
		super();
		this.post_no = post_no;
		this.board_no = board_no;
		this.mem_id = mem_id;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_date = post_date;
		this.post_photo = post_photo;
	}

	public PostVO() {
		
	}
	

	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	public String getPost_photo() {
		return post_photo;
	}
	public void setPost_photo(String post_photo) {
		this.post_photo = post_photo;
	}
	
	

}
