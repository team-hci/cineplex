package edu.nju.cineplex.utildata;

import java.io.Serializable;

public class RegisterInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int creditCard;
	private String password;
	private int id;
	
	public RegisterInfo(String name,int creditCard,String password){
		this.name = name;
		this.creditCard = creditCard;
		this.password = password;
	}
	
	public String getName(){
		return name;
	}
	
	public int getCreditCard(){
		return creditCard;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
}
