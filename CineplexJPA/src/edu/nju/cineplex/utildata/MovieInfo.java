package edu.nju.cineplex.utildata;

import java.io.Serializable;

public class MovieInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int movieid;
	private String moviename;
	private String movieposter;
	private String moviedescription;
	private String moviebegin;
	private String movieover;
	private double grade;
	private int gcount;
	private String region;
	private String[] types;
	private String[] directors;
	private String[] actors;
	private double seatRate;
	
	public MovieInfo(){
		
	}
	
	public MovieInfo(int movieid,String moviename,String movieposter,String moviedescription,String moviebegin,String movieover,double grade,int gcount){
		this.movieid = movieid;
		this.moviename = moviename;
		this.movieposter = movieposter;
		this.moviedescription = moviedescription;
		this.moviebegin = moviebegin;
		this.movieover = movieover;
		this.gcount = gcount;
		this.grade = grade;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getMovieposter() {
		return movieposter;
	}

	public void setMovieposter(String movieposter) {
		this.movieposter = movieposter;
	}

	public String getMoviedescription() {
		return moviedescription;
	}

	public void setMoviedescription(String moviedescription) {
		this.moviedescription = moviedescription;
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

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public int getGcount() {
		return gcount;
	}

	public void setGcount(int gcount) {
		this.gcount = gcount;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String[] getDirectors() {
		return directors;
	}

	public void setDirectors(String[] directors) {
		this.directors = directors;
	}

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public double getSeatRate() {
		return seatRate;
	}

	public void setSeatRate(double seatRate) {
		this.seatRate = seatRate;
	}
	
}
