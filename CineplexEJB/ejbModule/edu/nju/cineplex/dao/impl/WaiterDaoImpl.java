package edu.nju.cineplex.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.cineplex.dao.WaiterDao;
import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.ActivityPlan;
import edu.nju.cineplex.model.ActivityQuestion;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.utildata.ActivityForm;
import edu.nju.cineplex.utildata.PlanItem;

@Stateless
public class WaiterDaoImpl extends BaseDaoImpl implements WaiterDao {
	
	@PersistenceContext
	protected EntityManager em;
	
//	private static WaiterDaoImpl instance = new WaiterDaoImpl();

//	private WaiterDaoImpl() {
//	}
//
//	public static WaiterDaoImpl getInstance(){
//		return instance;
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		Query query =em.createQuery("from Movie order by moviename asc");
		return query.getResultList();
	}

//	@Override
//	public boolean addPlan(Plan plan) {
//		// TODO Auto-generated method stub
//		em.persist(plan);
//		return true;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanItem> getPassedPlans() {
		// TODO Auto-generated method stub
		String currDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Query query =em.createQuery("select new edu.nju.cineplex.utildata.PlanItem(p.planid,m.moviename,p.hall,p.plandate,p.plantime,p.planprice) from Plan p,Movie m where p.movieid=m.movieid and p.planpass=1 and p.plandate>='"+currDate+"'");
//		List list = query.getResultList();
//		System.out.println(((PlanItem)list.get(0)).getMoviename());
		return query.getResultList();
	}

	@Override
	public boolean addActivity(ActivityForm af) {
		// TODO Auto-generated method stub
		Activity activity = new Activity();
		activity.setTitle(af.getTitle());
		String datetime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		activity.setActivitytime(datetime);
		activity.setCredit(af.getCredit());
		em.persist(activity);
		
		Query query = em.createQuery("select activityid from Activity where activitytime='"+datetime+"'");
		int aid = (int)query.getResultList().get(0);
		System.out.println(aid);
		
		for(int pid:af.getPlanids()){
			ActivityPlan ap = new ActivityPlan();
			
			ap.setActivityid(aid);
			ap.setPlanid(pid);
			
			em.persist(ap);
		}
		
		ActivityQuestion[] aqs = new ActivityQuestion[af.getQuestions().length];
		for(int i=0;i<af.getQuestions().length;i++){
			aqs[i] = new ActivityQuestion(); 
			
			aqs[i].setActivityid(aid);
			aqs[i].setContent(af.getQuestions()[i].getContent());
			aqs[i].setOption1(af.getQuestions()[i].getOption1());
			aqs[i].setOption2(af.getQuestions()[i].getOption2());
			aqs[i].setOption3(af.getQuestions()[i].getOption3());
			
			em.persist(aqs[i]);
		}
//		System.out.println("aid:"+activity.getActivityid());
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanItem> getBlockedPlans() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select new edu.nju.cineplex.utildata.PlanItem(p.planid,m.moviename,p.hall,p.plandate,p.plantime,p.planprice,p.movieid) from Plan p,Movie m where p.movieid=m.movieid and p.planpass=-1 order by p.plantime desc");
		return query.getResultList();
	}

	@Override
	public PlanItem getPlanItemById(int planid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select new edu.nju.cineplex.utildata.PlanItem(p.planid,m.moviename,p.hall,p.plandate,p.plantime,p.planprice,p.movieid) from Plan p,Movie m where p.movieid=m.movieid and p.planid="+planid);
		return (PlanItem)query.getResultList().get(0);
	}

//	@Override
//	public Plan getPlanById(int planid) {
//		// TODO Auto-generated method stub
//		return em.find(Plan.class, planid);
//	}

//	@Override
//	public boolean updatePlan(Plan plan) {
//		// TODO Auto-generated method stub
//		em.merge(plan);
//		return true;
//	}

	@Override
	public int addActivity(Activity a) {
		// TODO Auto-generated method stub
		em.persist(a);
		
		Query query = em.createQuery("select activityid from Activity where activitytime='"+a.getActivitytime()+"'");
		int aid = (int)query.getResultList().get(0);
		return aid;
	}

//	@Override
//	public void addActivityPlan(ActivityPlan ap) {
//		// TODO Auto-generated method stub
//		em.persist(ap);
//	}
//
//	@Override
//	public void addActivityQuestion(ActivityQuestion aq) {
//		// TODO Auto-generated method stub
//		em.persist(aq);
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getShowingMovies() {
		// TODO Auto-generated method stub
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Query query =em.createQuery("from Movie where moviebegin<='"+today+"' and movieover>='"+today+"' order by moviename asc");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanItem> getAllComingPlans() {
		// TODO Auto-generated method stub
		String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Query query = em.createQuery("select new edu.nju.cineplex.utildata.PlanItem(p.planid,m.moviename,p.hall,p.plandate,p.plantime,p.planprice,p.movieid,p.planpass) from Plan p,Movie m where p.movieid=m.movieid and p.plandate>='"+nowDate+"' and p.planpass>=0 order by p.plandate asc,p.plantime asc");
		return query.getResultList();
	}

}
