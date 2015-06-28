package edu.nju.cineplex.utildata;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userid;
	private int planid;
	private int seatid;
	private String ticketType;
	private String datetime;
	private double price;
	
	public Order(int userid, int planid, int seatid,String ticketType,String datetime,double price) {
		this.userid = userid;
		this.planid = planid;
		this.seatid = seatid;
		this.ticketType = ticketType;
		this.datetime = datetime;
		this.price = price;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getPlanid() {
		return planid;
	}
	public void setPlanid(int planid) {
		this.planid = planid;
	}
	public int getSeatid() {
		return seatid;
	}
	public void setSeatid(int seatid) {
		this.seatid = seatid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
