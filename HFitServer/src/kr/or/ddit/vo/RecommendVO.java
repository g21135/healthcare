package kr.or.ddit.vo;

import java.io.Serializable;

public class RecommendVO implements Serializable{

	private int recom_num; // 추천 번호
	private int div_code; //구분 코드
	private String recom_image; // 사진
	private String recom_content; // 내용
	private String recom_title; //제목
	
	public String getRecom_title() {
		return recom_title;
	}
	public void setRecom_title(String recom_title) {
		this.recom_title = recom_title;
	}
	public int getRecom_num() {
		return recom_num;
	}
	public void setRecom_num(int recom_num) {
		this.recom_num = recom_num;
	}
	public int getDiv_code() {
		return div_code;
	}
	public void setDiv_code(int div_code) {
		this.div_code = div_code;
	}
	public String getRecom_image() {
		return recom_image;
	}
	public void setRecom_image(String recom_image) {
		this.recom_image = recom_image;
	}
	public String getRecom_content() {
		return recom_content;
	}
	public void setRecom_content(String recom_content) {
		this.recom_content = recom_content;
	}
	
	public RecommendVO(int recom_num, int div_code, String recom_image, String recom_content, String recom_title) {
		super();
		this.recom_num = recom_num;
		this.div_code = div_code;
		this.recom_image = recom_image;
		this.recom_content = recom_content;
		this.recom_title = recom_title;
	}
	
	public RecommendVO() {
		super();
	}	
}