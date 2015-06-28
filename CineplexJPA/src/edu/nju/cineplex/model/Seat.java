package edu.nju.cineplex.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seat")
public class Seat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id private int seatid;
	private int hall;
	private int seatrow;
	private int seatcolumn;
	private boolean seatactive;
	
	public int getSeatid() {
		return seatid;
	}
	public void setSeatid(int seatid) {
		this.seatid = seatid;
	}
	public int getHall() {
		return hall;
	}
	public void setHall(int hall) {
		this.hall = hall;
	}
	public int getSeatrow() {
		return seatrow;
	}
	public void setSeatrow(int seatrow) {
		this.seatrow = seatrow;
	}
	public int getSeatcolumn() {
		return seatcolumn;
	}
	public void setSeatcolumn(int seatcolumn) {
		this.seatcolumn = seatcolumn;
	}
	public boolean isSeatactive() {
		return seatactive;
	}
	public void setSeatactive(boolean seatactive) {
		this.seatactive = seatactive;
	}
	
	
}
