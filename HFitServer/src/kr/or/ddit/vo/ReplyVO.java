package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Date;

public class ReplyVO implements Serializable{
	private int reply_no;
	private int post_no;
	private String reply_writer;
	private Date reply_date;
	private String reply_content;
	
	public ReplyVO(int reply_no, int post_no, String reply_writer, Date reply_date, String reply_content) {
		super();
		this.reply_no = reply_no;
		this.post_no = post_no;
		this.reply_writer = reply_writer;
		this.reply_date = reply_date;
		this.reply_content = reply_content;
	}
	
	public ReplyVO() {
		
	}
	public int getReply_no() {
		return reply_no;
	}
	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public String getReply_writer() {
		return reply_writer;
	}
	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}
	public Date getReply_date() {
		return reply_date;
	}
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}

	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

}
