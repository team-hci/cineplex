package edu.nju.cineplex.utildata;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import edu.nju.cineplex.model.ActivityQuestion;

public class ActivityInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int activityid;
	private String activitytime;
	private int acount;
	private int credit;
	private ActivityQuestion[] questions;
	private PlanItem[] plans;
	private String title;

	public ActivityInfo(String title,int activityid, String activitytime, int acount,
			int credit) {
		super();
		this.title = title;
		this.activityid = activityid;
		this.activitytime = activitytime;
		this.acount = acount;
		this.credit = credit;
	}
	
	public int getActivityid() {
		return activityid;
	}
	public void setActivityid(int activityid) {
		this.activityid = activityid;
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
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public ActivityQuestion[] getQuestions() {
		return questions;
	}
	public void setQuestions(ActivityQuestion[] questions) {
		this.questions = questions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PlanItem[] getPlans() {
		return plans;
	}

	public void setPlans(PlanItem[] plans) {
		this.plans = plans;
	}
	
}
