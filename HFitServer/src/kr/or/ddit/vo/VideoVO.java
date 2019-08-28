package kr.or.ddit.vo;

import java.io.Serializable;

public class VideoVO implements Serializable{
	private int video_no;
	private int home_no;
	private String video_name;
	private String video_content;
	private String video_url;
	private String video_thumbnail;

	public VideoVO(int video_no, int home_no, String video_name, String video_content, String video_url,
			String video_thumbnail) {
		super();
		this.video_no = video_no;
		this.home_no = home_no;
		this.video_name = video_name;
		this.video_content = video_content;
		this.video_url = video_url;
		this.video_thumbnail = video_thumbnail;
	}

	public VideoVO() {
	}

	public int getVideo_no() {
		return video_no;
	}

	public void setVideo_no(int video_no) {
		this.video_no = video_no;
	}

	public int getHome_no() {
		return home_no;
	}

	public void setHome_no(int home_no) {
		this.home_no = home_no;
	}

	public String getVideo_name() {
		return video_name;
	}

	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}

	public String getVideo_content() {
		return video_content;
	}

	public void setVideo_content(String video_content) {
		this.video_content = video_content;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public String getVideo_thumbnail() {
		return video_thumbnail;
	}

	public void setVideo_thumbnail(String video_thumbnail) {
		this.video_thumbnail = video_thumbnail;
	}

}
