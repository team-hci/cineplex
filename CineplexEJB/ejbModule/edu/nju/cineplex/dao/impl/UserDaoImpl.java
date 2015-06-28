package edu.nju.cineplex.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.cineplex.dao.UserDao;
import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.ActivityQuestion;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Payment;
import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.model.Region;
import edu.nju.cineplex.model.Seat;
import edu.nju.cineplex.model.Ticket;
import edu.nju.cineplex.model.Type;
import edu.nju.cineplex.model.User;
import edu.nju.cineplex.model.UserInfo;
import edu.nju.cineplex.utildata.Consumption;
import edu.nju.cineplex.utildata.Order;
import edu.nju.cineplex.utildata.PlanItem;
import edu.nju.cineplex.utildata.RegisterInfo;

@Stateless
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@PersistenceContext
	protected EntityManager em;
	
//	private static UserDaoImpl instance = new UserDaoImpl();

	private static final double EXCHANGE_RATE = 100.0;
	
//	private UserDaoImpl() {
//	}
//
//	public static UserDaoImpl getInstance(){
//		return instance;
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findUser(User user) {
		// TODO Auto-generated method stub
//		int id = Integer.parseInt(username);
		int id = user.getUserid();
		String password = user.getPassword();
		Query query =em.createQuery("select userid from User where userid="
				+ id + " and password='" + password+"'");
		return query.getResultList();
//		if(query.getResultList().size()>0)
//			return true;
//		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findCreditCard(int cardId) {
		// TODO Auto-generated method stub
		Query query =em.createQuery("select cardid from UserInfo where cardid="
				+ cardId);
		return query.getResultList();
	}

	@Override
	public int getMaxId() {
		// TODO Auto-generated method stub
		Query query =em.createQuery("select max(userid) from UserInfo");
		return (int)query.getResultList().get(0);
	}

	@Override
	public boolean addUser(RegisterInfo ri) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserid(ri.getId());
		user.setPassword(ri.getPassword());
		em.persist(user);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUserid(ri.getId());
		userInfo.setUsername(ri.getName());
		userInfo.setCardid(ri.getCreditCard());
		userInfo.setConsumptiondate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		userInfo.setLevel("未激活");
		em.persist(userInfo);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getNewestMovies(int regionid, int typeid, int start,
			int count) {
		// TODO Auto-generated method stub
//		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String region = "";
		String type = "";
		if(regionid != 0)
			region = " and m.regionid="+regionid;
		if(typeid != 0)
			type = " and mt.typeid="+typeid;
		Query query = em.createQuery("select distinct m from Movie m,Mtype mt where m.movieid=mt.movieid"+region+type+" order by m.moviebegin desc");
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getHotestMovies(int regionid, int typeid, int start,
			int count) {
		// TODO Auto-generated method stub
//		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String region = "";
		String type = "";
		if(regionid != 0)
			region = " and m.regionid="+regionid;
		if(typeid != 0)
			type = " and mt.typeid="+typeid;
		Query query = em.createQuery("select distinct m from Movie m,Mtype mt where m.movieid=mt.movieid"+region+type+" order by m.moviegcount desc");
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getGoodMovies(int regionid, int typeid, int start,
			int count) {
		// TODO Auto-generated method stub
//		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String region = "";
		String type = "";
		if(regionid != 0)
			region = " and m.regionid="+regionid;
		if(typeid != 0)
			type = " and mt.typeid="+typeid;
		Query query = em.createQuery("select distinct m from Movie m,Mtype mt where m.movieid=mt.movieid"+region+type+" order by m.moviegrade desc");
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getAllTypes() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("from Type order by typeid");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Region> getAllRegions() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("from Region order by regionid");
		return query.getResultList();
	}

	@Override
	public String getMovieRegion(int movieid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select r.regionname from Movie m,Region r where m.movieid="+movieid+" and m.regionid=r.regionid");
		return (String)query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getMovieTypes(int movieid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select distinct t.typename from Mtype mt,Type t where mt.movieid="+movieid+" and mt.typeid=t.typeid");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getMovieDirectors(int movieid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select distinct md.directorname from Mdirector md where md.movieid="+movieid);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getMovieActors(int movieid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select distinct ma.actorname from Mactor ma where ma.movieid="+movieid);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Plan> getMoviePassedPlans(int movieid) {//通过并在今后的放映计划
		// TODO Auto-generated method stub
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Query query = em.createQuery("select p from Plan p where p.movieid="+movieid+" and p.planpass=1 and p.plandate>='"+today+"' order by p.plandate asc,p.plantime asc");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Seat> getHallSeats(int planid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select s from Plan p,Seat s where p.planid="+planid+" and p.hall=s.hall order by s.seatrow asc,s.seatcolumn asc");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getTakenSeats(int planid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select seatid from Ticket where planid="+planid);
		return query.getResultList();
	}
	
	@Override
	public boolean payMoney(int userid, double price) {
		// TODO Auto-generated method stub
		UserInfo user = em.find(UserInfo.class, userid);
		double balance = user.getBalance();
		if(balance<price)
			return false;
		else{
			user.setBalance(balance-price);
			em.merge(user);
			return true;
		}
	}
	
	@Override
	public boolean addTickets(List<Order> orders) {
		// TODO Auto-generated method stub
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		Query query = null;
		for(Order o:orders){
//			query = em.createQuery("select s.seatid from Plan p,Seat s,Ticket t where p.planid="+o.getPlanid()+" and p.hall=s.hall and s.seatactive=1 and s.seatid="+o.getSeatid());
//			if(query.getResultList().size()==0){
//				tx.rollback();
//				return false;
//			}
//			query = em.createQuery("from Ticket where userid="+o.getUserid()+" and planid="+o.getPlanid()+" and seatid="+o.getSeatid());
//			if(query.getResultList().size()>0){
//				tx.rollback();
//				return false;
//			}
			Ticket t = new Ticket();
			t.setPlanid(o.getPlanid());
			t.setSeatid(o.getSeatid());
			t.setUserid(o.getUserid());
			t.setTickettype(o.getTicketType());
			t.setTickettime(o.getDatetime());
			t.setPrice(o.getPrice());
			em.persist(t);
			
		}
//		tx.commit();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getNewestActivities(int start,int count) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("from Activity order by activitytime desc");
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getHotestActivities(int start,int count) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("from Activity order by acount desc");
		query.setFirstResult(start);
		query.setMaxResults(count);
		return query.getResultList();
	}

	@Override
	public long countAll(String modelName) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select count(x) from "+modelName+" x");
		return (long)query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityQuestion> getActivityQuestions(int activityid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("from ActivityQuestion where activityid="+activityid+" order by questionid");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getActivityMovies(int activityid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select m.moviename from Movie m,ActivityMovie am where am.activityid="+activityid+" and am.movieid=m.movieid");
		return query.getResultList();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Integer> hasTakenActivity(int userid, int activityid) {
		// TODO Auto-generated method stub
		Query query2 = em.createQuery("from ActivityPlan ap,Ticket t where ap.activityid="+activityid+" and ap.planid=t.planid and t.userid="+userid);
		if(query2.getResultList().size()==0)//没买匹配的电影
			return null;
		Query query = em.createQuery("select uq.optionid from UserQuestion uq,ActivityQuestion aq where uq.userid="+userid+" and aq.activityid="+activityid+" and uq.questionid=aq.questionid order by uq.questionid");
		if(query.getResultList().size()>0)//参加过活动
			return query.getResultList();
		return new ArrayList();//看了电影没参加活动
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consumption> getConsumptions(int userid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select new edu.nju.cineplex.utildata.Consumption(m.moviename,p.plandate,p.plantime,t.price,t.tickettime) from Ticket t,Plan p,Movie m where t.userid="+userid+" and t.planid=p.planid and p.movieid=m.movieid order by t.tickettime desc");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> getPayments(int userid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("from Payment where userid="+userid+" order by datetime desc");
		return query.getResultList();
	}

	@Override
	public boolean addBalance(int userid, int sum) {
		// TODO Auto-generated method stub
		UserInfo ui = em.find(UserInfo.class, userid);
		ui.setBalance(ui.getBalance()+sum);
		em.merge(ui);
		
		Payment payment = new Payment();
		payment.setUserid(userid);
		payment.setSum(sum);
		payment.setDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		em.persist(payment);
		return true;
	}

//	@Override
//	public boolean setUserLevel(int userid, String level) {
//		// TODO Auto-generated method stub
//		UserInfo ui = em.find(UserInfo.class, userid);
//		if(!ui.getLevel().equals(level)){
//			ui.setLevel(level);
//			em.merge(ui);
//			return true;
//		}
//		return false;
//	}

//	@Override
//	public void mergeUserInfo(UserInfo ui) {
//		// TODO Auto-generated method stub
//		em.merge(ui);
//	}
//
//	@Override
//	public void addUserOption(UserQuestion uo) {
//		// TODO Auto-generated method stub
//		em.persist(uo);
//	}
//
//	@Override
//	public ActivityQuestion getQuestion(int questionid) {
//		// TODO Auto-generated method stub
//		return em.find(ActivityQuestion.class, questionid);
//	}

	@Override
	public void addUserCredit(int userid, int credit) {
		// TODO Auto-generated method stub
		UserInfo ui = em.find(UserInfo.class, userid);
		ui.setCredit(ui.getCredit()+credit);
		em.merge(ui);
	}

	@Override
	public void exchangeCreditToBalance(int userid) {
		// TODO Auto-generated method stub
		UserInfo ui = em.find(UserInfo.class, userid);
		int credit = ui.getCredit();
		double x = (double)credit/EXCHANGE_RATE;
		
		ui.setCredit(0);
		ui.setBalance(ui.getBalance()+x);
		em.merge(ui);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMoviesByKey(String key) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("from Movie where moviename like '%"+key+"%' or moviedescription like '%"+key+"%'");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getComingMovies(int quantity,String dateBefore) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("from Movie where moviebegin>'"+dateBefore+"' order by moviebegin");
		query.setFirstResult(0);
		query.setMaxResults(quantity);
		return query.getResultList();
	}

	@Override
	public void addCredit(int userid, int credit) {
		// TODO Auto-generated method stub
		UserInfo user = em.find(UserInfo.class, userid);
		user.setCredit(user.getCredit()+credit);
		em.merge(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanItem> getPlansByActivityId(int activityid) {
		// TODO Auto-generated method stub
		Query query =em.createQuery("select new edu.nju.cineplex.utildata.PlanItem(p.planid,m.moviename,p.hall,p.plandate,p.plantime,p.planprice) from Plan p,Movie m,ActivityPlan ap where ap.activityid="+activityid+" and ap.planid=p.planid and p.movieid=m.movieid");
		return query.getResultList();
	}

//	@Override
//	public void addTicket(Ticket t) {
//		// TODO Auto-generated method stub
//		em.persist(t);
//	}

	@Override
	public void addActivityCredit(int userid, int activityid) {
		// TODO Auto-generated method stub
		UserInfo user = em.find(UserInfo.class, userid);
		Activity activity = em.find(Activity.class, activityid);
		
		user.setCredit(user.getCredit()+activity.getCredit());
		em.merge(user);
		
	}

	@Override
	public void increActivityCount(int activityid) {
		// TODO Auto-generated method stub
		Activity activity = em.find(Activity.class, activityid);
		activity.setAcount(activity.getAcount()+1);
		em.merge(activity);
	}

	@Override
	public void addMovieGrade(int movieid, int evaluation) {
		// TODO Auto-generated method stub
		Movie movie = em.find(Movie.class, movieid);
		int count = movie.getMoviegcount();
		double ngrade = (movie.getMoviegrade()*count+evaluation)/(count+1);
		movie.setMoviegrade(ngrade);
		movie.setMoviegcount(count+1);
		em.merge(movie);
	}

	@Override
	public int getUserValuation(int userid, int movieid) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select value from UserEvaluate where userid="+userid+" and movieid="+movieid);
		return query.getResultList().size()>0?(int)query.getResultList().get(0):0;
	}

	@Override
	public boolean isPlanConflict(Plan plan) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select planid from Plan where planpass=1 and hall="+plan.getHall()+" and plandate="+plan.getPlandate());
		return false;
	}

}