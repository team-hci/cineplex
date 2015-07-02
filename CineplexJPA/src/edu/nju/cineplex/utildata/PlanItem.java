package edu.nju.cineplex.utildata;

import java.io.Serializable;

public class PlanItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int planid;
	private String moviename;
	private int movieid;
	private int hall;
	private String plandate;
	private String plantime;
	private int planprice;
	
	private int planStatus;
	
	public PlanItem(){}
	
	
	public PlanItem(int planid,String moviename,int hall,String plandate,String plantime,int planprice,int movieid){
		this.planid = planid;
		this.moviename = moviename;
		this.hall = hall;
		this.plandate = plandate;
		this.plantime = plantime;
		this.planprice = planprice;
		this.movieid = movieid;
	}
	
	public PlanItem(int planid,String moviename,int hall,String plandate,String plantime,int planprice){
		this.planid = planid;
		this.moviename = moviename;
		this.hall = hall;
		this.plandate = plandate;
		this.plantime = plantime;
		this.planprice = planprice;
	}
	
	public PlanItem(int planid,String moviename,int hall,String plandate,String plantime,int planprice,int movieid,int planStatus){
		this.planid = planid;
		this.moviename = moviename;
		this.hall = hall;
		this.plandate = plandate;
		this.plantime = plantime;
		this.planprice = planprice;
		this.movieid = movieid;
		this.planStatus = planStatus;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public int getPlanprice() {
		return planprice;
	}

	public void setPlanprice(int planprice) {
		this.planprice = planprice;
	}

	public void setPlanid(int planid){
		this.planid = planid;
	}
	
	public int getPlanid(){
		return planid;
	}
	
	public void setMoviename(String moviename){
		this.moviename = moviename;
	}
	
	public String getMoviename(){
		return moviename;
	}
	
	public void setHall(int hall){
		this.hall = hall;
	}
	
	public int getHall(){
		return hall;
	}
	
	public String getPlandate() {
		return plandate;
	}

	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}

	public String getPlantime() {
		return plantime;
	}

	public void setPlantime(String plantime) {
		this.plantime = plantime;
	}


	public int getPlanStatus() {
		return planStatus;
	}


	public void setPlanStatus(int planStatus) {
		this.planStatus = planStatus;
	}


	public int getMovieid() {
		return movieid;
	}
	
	
	
}
