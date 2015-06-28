package edu.nju.cineplex.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activity")
public class Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id private int activityid;
	private int credit;
	private String activitytime;
	private int acount;
	private String title;
	
	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public void setActivityid(int activityid){
		this.activityid = activityid;
	}
	
	public int getActivityid(){
		return activityid;
	}

	public String getActivitytime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.format(sdf.parse(activitytime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setActivitytime(String activitytime) {
		this.activitytime = activitytime;
	}

	public int getAcount() {
		return acount;
	}

	public void setAcount(int acount) {
		this.acount = acount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String toString(){
		String ret = "title:" + this.title + "\n" +
				"credit:" + this.credit + "\n" +
				"time:" + this.activitytime + "\n";
		return ret;
	}

}
