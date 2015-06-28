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
	 * �õ����е�Ӱ
	 * @return ���е�Ӱ�б�
	 */
	public List<Movie> getAllMovies();
	
	/**
	 * �õ����ڷ�ӳ�ĵ�Ӱ
	 * @return ��Ӱ�б�
	 */
	public List<Movie> getShowingMovies();
	
	/**
	 * ��ӷ�ӳ�ƻ�
	 * @param plan ��ӳ�ƻ�
	 * @return �Ƿ���ӳɹ�
	 */
//	public boolean addPlan(Plan plan);
	
	/**
	 * �õ����ͨ���ķ�ӳ�ƻ�
	 * @return ��ӳ�ƻ��б�
	 */
	public List<PlanItem> getPassedPlans();
	
	/**
	 * ��ӻ
	 * @param af ���
	 * @return �Ƿ���ӳɹ�
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
