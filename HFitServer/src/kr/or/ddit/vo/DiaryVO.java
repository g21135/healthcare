package kr.or.ddit.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class DiaryVO implements Serializable {

	private int id;
	private String mem_id;
	private String title;
	private String fullDay;
	private Date startDate;
	private Date endDate;
	private Time startTime;
	private Time endTime;
	private String zoneId;
	private String recurring;
	private String rrule;
	private String recurrence;
	private String entryType;
	
	public DiaryVO(int id, String mem_id, String title, String fullDay, Date startDate, Date endDate, Time startTime,
			Time endTime, String zoneId, String recurring, String rrule, String recurrence, String entryType) {
		super();
		this.id = id;
		this.mem_id = mem_id;
		this.title = title;
		this.fullDay = fullDay;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.zoneId = zoneId;
		this.recurring = recurring;
		this.rrule = rrule;
		this.recurrence = recurrence;
		this.entryType = entryType;
	}
	public DiaryVO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFullDay() {
		return fullDay;
	}
	public void setFullDay(String fullDay) {
		this.fullDay = fullDay;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getRecurring() {
		return recurring;
	}
	public void setRecurring(String recurring) {
		this.recurring = recurring;
	}
	public String getRrule() {
		return rrule;
	}
	public void setRrule(String rrule) {
		this.rrule = rrule;
	}
	public String getRecurrence() {
		return recurrence;
	}
	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}
	public String getEntryType() {
		return entryType;
	}
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}


}
