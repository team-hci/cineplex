package edu.nju.cineplex.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.ActivityQuestion;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Payment;
import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.model.Region;
import edu.nju.cineplex.model.Seat;
import edu.nju.cineplex.model.Type;
import edu.nju.cineplex.model.User;
import edu.nju.cineplex.utildata.Consumption;
import edu.nju.cineplex.utildata.Order;
import edu.nju.cineplex.utildata.PlanItem;
import edu.nju.cineplex.utildata.RegisterInfo;

@Remote
public interface UserDao extends BaseDao {

	public List<Integer> findUser(User user);
	
	public List<Integer> findCreditCard(int cardId);
	
	public int getMaxId();
	
	public boolean addUser(RegisterInfo ri);
	
	public List<Movie> getNewestMovies(int regionid,int typeid,int start,int count);
	
	public List<Movie> getHotestMovies(int regionid,int typeid,int start,int count);
	
	public List<Movie> getGoodMovies(int regionid,int typeid,int start,int count);
	
	public List<Type> getAllTypes();
	
	public List<Region> getAllRegions();
	
//	public Movie getBasicMovieInfo(int movieid);
	
	public String getMovieRegion(int movieid);
	
	public List<String> getMovieTypes(int movieid);
	
	public List<String> getMovieDirectors(int movieid);
	
	public List<String> getMovieActors(int movieid);
	
	public List<Plan> getMoviePassedPlans(int movieid);
	
	public List<Seat> getHallSeats(int planid);
	
	public List<Integer> getTakenSeats(int planid);
	
	public boolean payMoney(int userid,double price);
	
	public void addCredit(int userid,int credit);
	
	public boolean addTickets(List<Order> orders);
	
	public List<Activity> getNewestActivities(int start,int count);
	
	public List<Activity> getHotestActivities(int start,int count);
	
//	public Activity getBasicActivityInfo(int activityid);
	
	public List<ActivityQuestion> getActivityQuestions(int activityid);
	
	public List<String> getActivityMovies(int activityid);
	
	public long countAll(String modelName);
	
	public List<Integer> hasTakenActivity(int userid,int activityid);
	
	public List<Consumption> getConsumptions(int userid);
	
	public List<Payment> getPayments(int userid);
	
	public boolean addBalance(int userid,int sum);
	
//	public boolean setUserLevel(int userid,String level);
	
//	public void mergeUserInfo(UserInfo ui);
	
//	public void addUserOption(UserQuestion uo);
	
//	public ActivityQuestion getQuestion(int questionid);
	
	public void addUserCredit(int userid,int credit);
	
	public void exchangeCreditToBalance(int userid);
	
	public List<Movie> getMoviesByKey(String key);

	public List<Movie> getComingMovies(int quantity,String dateBefore);
	
	public List<PlanItem> getPlansByActivityId(int activityid);
	
//	public void addTicket(Ticket t);
	
	public void addActivityCredit(int userid,int activityid);
	
	public void increActivityCount(int activityid);
	
	public void addMovieGrade(int movieid,int evaluation);
	
	public int getUserValuation(int userid,int movieid);
	
	public boolean isPlanConflict(Plan plan);
}