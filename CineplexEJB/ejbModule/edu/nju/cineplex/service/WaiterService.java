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
	 * �õ����еĵ�Ӱ
	 * @return ���е�Ӱ�б�
	 */
	public List<Movie> getAllMovies();
	
	/**
	 * �ύ��ӳ�ƻ�
	 * @param plan ��ӳ�ƻ�
	 * @return �Ƿ��ύ�ɹ�
	 */
	public boolean submitPlan(Plan plan);
	
	/**
	 * �õ����ͨ���ķ�ӳ�ƻ�
	 * @return ��ӳ�ƻ��б�
	 */
	public List<PlanItem> getPassedPlans();

	/**
	 * �ύ�
	 * @param af ���
	 * @return �Ƿ��ύ�ɹ�
	 */
	public boolean submitActivity(ActivityForm af);
	
	/**
	 * �õ�δͨ����˵ķ�ӳ�ƻ�
	 * @return ��ӳ�ƻ��б�
	 */
	public List<PlanItem> getBlockedPlans();
	
	public PlanItem getPlanItemById(int planid);
	
	public int addActivity(Activity a);
	
	public void addActivityPlan(ActivityPlan ap);
	
	public void addActivityQuestion(ActivityQuestion aq);

	public List<Movie> getShowingMovies();
	
	public List<PlanItem> getAllComingPlans();
}
