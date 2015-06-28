package edu.nju.cineplex.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="plan")
public class Plan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id private int planid;
	private int hall;
	private int movieid;
	private String plandate;
	private String plantime;
	private String planover;
	private int planprice;
	private int planpass;
	
	public void setPlanid(int planid){
		this.planid = planid;
	}
	
	public int getPlanid(){
		return planid;
	}
	
	public void setMovieid(int movieid){
		this.movieid = movieid;
	}
	
	public int getMovieid(){
		return movieid;
	}
	
	public void setHall(int hall){
		this.hall = hall;
	}
	
	public int getHall(){
		return hall;
	}
	
	public void setPlanprice(int planprice){
		this.planprice = planprice;
	}
	
	public int getPlanprice(){
		return planprice;
	}
	
	public void setPlanpass(int planpass){
		this.planpass = planpass;
	}
	
	public int getPlanpass(){
		return planpass;
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

	public String getPlanover() {
		return planover;
	}

	public void setPlanover(String planover) {
		this.planover = planover;
	}
	
}
