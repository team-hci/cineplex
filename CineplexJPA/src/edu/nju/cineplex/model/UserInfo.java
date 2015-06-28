package edu.nju.cineplex.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userinfo")
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id private int userid;
	private String username;
	private int cardid;
	private String level;
	private int credit;
	private int age;
	private String gender;
	private String location;
	private double balance;
	private String consumptiondate;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setUserid(int userid){
		this.userid = userid;
	}
	
	public int getUserid(){
		return userid;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public int getCardid(){
		return cardid;
	}
	
	public void setCardid(int cardid){
		this.cardid = cardid;
	}
	
	public String getLevel(){
		return level;
	}
	
	public void setLevel(String level){
		this.level = level;
	}
	
	public int getCredit(){
		return credit;
	}
	
	public void setCredit(int credit){
		this.credit = credit;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public String getGender(){
		return gender;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
	
	public String getLocation(){
		return location;
	}
	
	public void setLocation(String location){
		this.location = location;
	}

	public String getConsumptiondate() {
		return consumptiondate;
	}

	public void setConsumptiondate(String consumptiondate) {
		this.consumptiondate = consumptiondate;
	}
	
}
