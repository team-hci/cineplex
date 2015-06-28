package edu.nju.cineplex.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.cineplex.utildata.MovieInfo;
import edu.nju.cineplex.utildata.PlanItem;

@Remote
public interface ManagerDao extends BaseDao {
	
	public List<PlanItem> getToBeCheckedPlan();
	
	public boolean setPlanPass(int planid,int planpass);
	
	public double getAgePercent(int low,int high);
	
	public double getGenderPercent(String gender);
	
	public double getLocationPercent(String location);
	
	public double getConsumptionPercent(int low,int high);
	
	public double getActivityPercent(int low,int high);
	
	public double getLevelPercent(String lvl);
	
	public long getDailyUserCount(String type,String date);
	
//	public long getMonthlyUserCount(String type,int monthsAgo);
	
	public double getHallSeatRate(int hall,String date);
	
	public double getMovieSeatRate(int movieid);
	
	public List<MovieInfo> getMovies();
	
	public List<Integer> getUpdatableIds();
	
}
