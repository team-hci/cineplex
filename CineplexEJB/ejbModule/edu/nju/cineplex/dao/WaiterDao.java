package edu.nju.cineplex.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.utildata.ActivityForm;
import edu.nju.cineplex.utildata.PlanItem;

@Remote
public interface WaiterDao extends BaseDao {

	/**
	 * 得到所有电影
	 * @return 所有电影列表
	 */
	public List<Movie> getAllMovies();
	
	/**
	 * 得到正在放映的电影
	 * @return 电影列表
	 */
	public List<Movie> getShowingMovies();
	
	/**
	 * 添加放映计划
	 * @param plan 放映计划
	 * @return 是否添加成功
	 */
//	public boolean addPlan(Plan plan);
	
	/**
	 * 得到审核通过的放映计划
	 * @return 放映计划列表
	 */
	public List<PlanItem> getPassedPlans();
	
	/**
	 * 添加活动
	 * @param af 活动表单
	 * @return 是否添加成功
	 */
	public boolean addActivity(ActivityForm af);
	
	public List<PlanItem> getBlockedPlans();
	
	public PlanItem getPlanItemById(int planid);
	
//	public Plan getPlanById(int planid);
	
//	public boolean updatePlan(Plan plan);
	
	public int addActivity(Activity a);
	
//	public void addActivityPlan(ActivityPlan ap);
	
//	public void addActivityQuestion(ActivityQuestion aq);
	
	public List<PlanItem> getAllComingPlans();
	
}
