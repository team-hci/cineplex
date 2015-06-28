package edu.nju.cineplex.service;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.cineplex.utildata.MovieInfo;
import edu.nju.cineplex.utildata.PlanItem;

@Remote
public interface ManagerService {

	public List<PlanItem> getToBeCheckedPlan();
	
	public boolean passPlan(int planid);
	
	public boolean failPlan(int planid);
	
	public String getAgeStatistics();
	
	public String getGenderStatistics();
	
	public String getLocationStatistics();
	
	public String getConsumptionStatistics();
	
	public String getActivityStatistics();
	
	public String getLevelStatistics();
	
	//
	public String getDailyStatistics(int year,int month);
	
	public String getHallSeatRate(int year,int month);
	
	public List<MovieInfo> getMovieSeatRate();
	
	public boolean updateMemberLevel();
	
}
