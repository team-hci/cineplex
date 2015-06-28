package edu.nju.cineplex.utildata;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Consumption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String moviename;
	private int count;
	private String plandate;
	private String plantime;
	private double singlePrice;
	private double totalPrice;
	private String payDatetime;
	
	public Consumption(){}
	
	public Consumption(String moviename,String plandate, String plantime, double singlePrice,String payDatetime) {
		super();
		this.moviename = moviename;
		this.plandate = plandate;
		this.plantime = plantime;
		this.singlePrice = singlePrice;
		this.payDatetime = payDatetime;
	}
	
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSinglePrice() {
		return singlePrice;
	}
	public void setSinglePrice(double singlePrice) {
		this.singlePrice = singlePrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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

	public String getPayDatetime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.format(sdf.parse(payDatetime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setPayDatetime(String payDatetime) {
		this.payDatetime = payDatetime;
	}
	
}
