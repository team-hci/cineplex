package edu.nju.cineplex.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id private int movieid;
	private String moviename;
	private String movieposter;
	private String moviedescription;
	private int regionid;
	private double moviegrade;
	private int moviegcount;
	private String moviebegin;
	private String movieover;
	private int movielength;
	
	public int getMovieid(){
		return movieid;
	}
	
	public void setMovieid(int movieid){
		this.movieid = movieid;
	}
	
	public String getMoviename(){
		return moviename;
	}
	
	public void setMoviename(String moviename){
		this.moviename = moviename;
	}
	
	public String getMovieposter(){
		return movieposter;
	}
	
	public void setMovieposter(String movieposter){
		this.movieposter = movieposter;
	}
	
	public String getMoviedescription(){
		return moviedescription;
	}
	
	public void setMoviedescription(String moviedescription){
		this.moviedescription = moviedescription;
	}
	
	public int getRegionid(){
		return regionid;
	}
	
	public void setRegionid(int regionid){
		this.regionid = regionid;
	}
	
	public double getMoviegrade(){
		return Double.parseDouble(String.format("%.1f", moviegrade));
	}
	
	public void setMoviegrade(double moviegrade){
		this.moviegrade = moviegrade;
	}
	
	public int getMoviegcount(){
		return moviegcount;
	}
	
	public void setMoviegcount(int moviegcount){
		this.moviegcount = moviegcount;
	}

	public String getMoviebegin() {
		return moviebegin;
	}

	public void setMoviebegin(String moviebegin) {
		this.moviebegin = moviebegin;
	}

	public String getMovieover() {
		return movieover;
	}

	public void setMovieover(String movieover) {
		this.movieover = movieover;
	}

	public int getMovielength() {
		return movielength;
	}

	public void setMovielength(int movielength) {
		this.movielength = movielength;
	}

}
