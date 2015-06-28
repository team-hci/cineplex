package edu.nju.cineplex.service.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.nju.cineplex.dao.WaiterDao;
import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.ActivityPlan;
import edu.nju.cineplex.model.ActivityQuestion;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.service.WaiterService;
import edu.nju.cineplex.utildata.ActivityForm;
import edu.nju.cineplex.utildata.PlanItem;

@Stateless
public class WaiterServiceBean implements WaiterService {

	@EJB private WaiterDao waiterDao;
	
	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return waiterDao.getAllMovies();
	}

	@Override
	public boolean submitPlan(Plan plan) {
		// TODO Auto-generated method stub
//		Movie movie = (Movie)waiterDao.find(Movie.class, plan.getMovieid());
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		String over = null;
//		try {
//			over = sdf.format(new Date(sdf.parse(plan.getPlantime()).getTime()+movie.getMovielength()*60*1000));
//			System.out.println(over);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		plan.setPlanover(over);
		
		int pid = plan.getPlanid();
		
		if(pid!=0){
			plan.setPlanpass(0);
			waiterDao.update(Plan.class,plan);
			return true;
		}
		else{
			waiterDao.add(Plan.class,plan);
			return true;
		}
	}

	@Override
	public List<PlanItem> getPassedPlans() {
		// TODO Auto-generated method stub
		return waiterDao.getPassedPlans();
	}

	@Override
	public boolean submitActivity(ActivityForm af) {
		// TODO Auto-generated method stub
//		return waiterDao.addActivity(af);
		
		Activity activity = new Activity();
		activity.setTitle(af.getTitle());
		String datetime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		activity.setActivitytime(datetime);
		activity.setCredit(af.getCredit());
		int aid = waiterDao.addActivity(activity);
		
		for(int pid:af.getPlanids()){
			ActivityPlan ap = new ActivityPlan();
			
			ap.setActivityid(aid);
			ap.setPlanid(pid);
			
			waiterDao.add(ActivityPlan.class,ap);
		}
		
		for(ActivityQuestion q:af.getQuestions()){
			q.setActivityid(aid);
			
			waiterDao.add(ActivityQuestion.class,q);
		}
		return true;
	}

	@Override
	public List<PlanItem> getBlockedPlans() {
		// TODO Auto-generated method stub
		return waiterDao.getBlockedPlans();
	}

	@Override
	public PlanItem getPlanItemById(int planid) {
		// TODO Auto-generated method stub
		return waiterDao.getPlanItemById(planid);
	}

	@Override
	public int addActivity(Activity a) {
		// TODO Auto-generated method stub
		return waiterDao.addActivity(a);
	}

	@Override
	public void addActivityPlan(ActivityPlan ap) {
		// TODO Auto-generated method stub
		waiterDao.add(ActivityPlan.class,ap);
	}

	@Override
	public void addActivityQuestion(ActivityQuestion aq) {
		// TODO Auto-generated method stub
		waiterDao.add(ActivityQuestion.class,aq);
	}

	@Override
	public List<Movie> getShowingMovies() {
		// TODO Auto-generated method stub
		return waiterDao.getShowingMovies();
	}

	@Override
	public List<PlanItem> getAllComingPlans() {
		// TODO Auto-generated method stub
		return waiterDao.getAllComingPlans();
	}

}
