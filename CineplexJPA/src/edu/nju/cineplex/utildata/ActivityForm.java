package edu.nju.cineplex.utildata;

import java.io.Serializable;

import edu.nju.cineplex.model.ActivityQuestion;

public class ActivityForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int[] planids;
	private String title;
	private ActivityQuestion[] questions;
	private int credit;
	
	
	public ActivityForm(int[] planids, String title, ActivityQuestion[] questions,
			int credit) {
		super();
		this.planids = planids;
		this.title = title;
		this.questions = questions;
		this.credit = credit;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int[] getPlanids(){
		return planids;
	}
	
	public void setPlanids(int[] planids){
		this.planids = planids;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ActivityQuestion[] getQuestions() {
		return questions;
	}

	public void setQuestions(ActivityQuestion[] questions) {
		this.questions = questions;
	}
	
	
}
