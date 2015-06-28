package edu.nju.cineplex.service;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Payment;
import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.model.Region;
import edu.nju.cineplex.model.Seat;
import edu.nju.cineplex.model.Type;
import edu.nju.cineplex.model.User;
import edu.nju.cineplex.model.UserInfo;
import edu.nju.cineplex.model.UserQuestion;
import edu.nju.cineplex.utildata.ActivityInfo;
import edu.nju.cineplex.utildata.Consumption;
import edu.nju.cineplex.utildata.MovieInfo;
import edu.nju.cineplex.utildata.Order;
import edu.nju.cineplex.utildata.RegisterInfo;

@Remote
public interface UserService {
	//排序规则
	public static final int NEWEST = 1;
	public static final int HOTEST = 2;
	public static final int GOOD = 3;
	//地区
	public static final int ALL_REGION = 10;
	public static final int NEIDI = 11;
	public static final int GANGTAI = 12;
	public static final int RIHAN = 13;
	public static final int OUMEI = 14;
	//类型
	public static final int ALL_TYPE = 20;
	public static final int DONGZUO = 21;
	public static final int XIJU = 22;
	public static final int FANZUI = 23;
	public static final int AIQING = 24;
	public static final int KEHUAN = 25;
	public static final int KONGBU = 26;
	public static final int JUQING = 27;
	public static final int MAOXIAN = 28;
	public static final int QIHUAN = 29;
	public static final int XUANYI = 30;
	public static final int WUXIA = 31;
	public static final int ZHANZHENG = 32;
	public static final int JINGSONG = 33;
	public static final int DONGHUA = 34;
	public static final int ZHUANJI = 35;
	public static final int JINGDIAN = 36;
	public static final int LUNLI = 37;
	
	public UserInfo verifyUser(User user);
	
	public int register(RegisterInfo ri);
	
	public List<Movie> getMovies(int sort,int region,int type,int page);
	
	public List<Type> getAllTypes();
	
	public List<Region> getAllRegions();
	
	public MovieInfo getMovieInfo(int movieid);
	
	public List<Plan> getMoviePassedPlans(int movieid);
	
	public List<Seat> getSelectableSeats(int planid);
	
	public boolean buyTickets(List<Order> orders);
	
	public List<Activity> getActivities(int sort,int page);
	
	public int getTotalPage(String modelName);
	
	public ActivityInfo getActivityInfo(int activityid);
	
	public List<Integer> hasTakenActivity(int userid, int activityid);
	
	public UserInfo getUserInfo(int userid);
	
	public List<Consumption> getPersonalConsumptions(int userid);
	
	public List<Payment> getPersonalPayments(int userid);
	
	public String pay(int userid,int sum);
	
	public boolean cancelMember(int userid);
	
	public void modifyUserInfo(UserInfo ui);

	public void selectOption(UserQuestion uo);
	
	public void changeMoney(int userid);
	
	public List<Movie> searchMovie(String key);
	
	public List<Movie> getComingMovies(int quantity);
	
	public void addTicket(Order o);
	
	public void addActivityCredit(int userid,int activityid);
	
	public void increActivityCount(int activityid);
	
	public boolean payMoney(int userid,double sum);
	
	public void evaluateMovie(int userid,int movieid,int evaluation);
	
	public int getUserValuation(int userid,int movieid);
	
}
