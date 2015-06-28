package edu.nju.cineplex.service;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.ActivityPlan;
import edu.nju.cineplex.model.ActivityQuestion;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.utildata.ActivityForm;
import edu.nju.cineplex.utildata.PlanItem;

@Remote
public interface WaiterService {

	/**
	 * 得到所有的电影
	 * @return 所有电影列表
	 */
	public List<Movie> getAllMovies();
	
	/**
	 * 提交放映计划
	 * @param plan 放映计划
	 * @return 是否提交成功
	 */
	public boolean submitPlan(Plan plan);
	
	/**
	 * 得到审核通过的放映计划
	 * @return 放映计划列表
	 */
	public List<PlanItem> getPassedPlans();

	/**
	 * 提交活动
	 * @param af 活动表单
	 * @return 是否提交成功
	 */
	public boolean submitActivity(ActivityForm af);
	
	/**
	 * 得到未通过审核的放映计划
	 * @return 放映计划列表
	 */
	public List<PlanItem> getBlockedPlans();
	
	public PlanItem getPlanItemById(int planid);
	
	public int addActivity(Activity a);
	
	public void addActivityPlan(ActivityPlan ap);
	
	public void addActivityQuestion(ActivityQuestion aq);

	public List<Movie> getShowingMovies();
	
	public List<PlanItem> getAllComingPlans();
}
