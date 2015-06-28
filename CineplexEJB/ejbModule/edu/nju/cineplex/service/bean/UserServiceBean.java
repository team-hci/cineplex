package edu.nju.cineplex.service.bean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
import edu.nju.cineplex.model.UserEvaluate;
import edu.nju.cineplex.model.UserInfo;
import edu.nju.cineplex.model.UserQuestion;
import edu.nju.cineplex.service.UserService;
import edu.nju.cineplex.utildata.ActivityInfo;
import edu.nju.cineplex.utildata.Consumption;
import edu.nju.cineplex.utildata.MovieInfo;
import edu.nju.cineplex.utildata.Order;
import edu.nju.cineplex.utildata.PlanItem;
import edu.nju.cineplex.utildata.RegisterInfo;

@Stateless
public class UserServiceBean implements UserService {

	@EJB private UserDao userDao;
//	@EJB private BaseDao baseDao;
	
	private static int maxId = 0;
	private static final int MOVIES_PER_PAGE = 36;
	private static final int ACTIVITIES_PER_PAGE = 24;
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
	//
	private static final int LEVEL1_MONEY = 200;
	private static final int LEVEL2_MONEY = 1000;
	private static final String[] LEVELS = {"未激活","普通会员","高级会员"};
	
	@Override
	public UserInfo verifyUser(User user) {
		// TODO Auto-generated method stub
		List<Integer> list = userDao.findUser(user);
		if(list.size()>0){
			return (UserInfo)userDao.find(UserInfo.class, user.getUserid());
		}
		else 
			return null;
	}

	@Override
	public int register(RegisterInfo ri) {
		// TODO Auto-generated method stub
		if(!verifyCreditCard(ri.getCreditCard()))//card id exists
			return 0;
		int newId = generateID();
		ri.setId(newId);
		userDao.addUser(ri);
		return newId;
	}

	private boolean verifyCreditCard(int creditCard){
		List<Integer> list = userDao.findCreditCard(creditCard);
		return list.size()==0;
	}
	
	private int generateID(){
		if(maxId==0)
			maxId = userDao.getMaxId();
		return ++maxId;
	}

	@Override
	public List<Movie> getMovies(int sort,int region,int type, int page) {
		// TODO Auto-generated method stub
		switch(sort){
		case NEWEST:
			return userDao.getNewestMovies(region-ALL_REGION, type-ALL_TYPE, (page-1)*MOVIES_PER_PAGE, MOVIES_PER_PAGE);
		case HOTEST:
			return userDao.getHotestMovies(region-ALL_REGION, type-ALL_TYPE, (page-1)*MOVIES_PER_PAGE, MOVIES_PER_PAGE);
		case GOOD:
			return userDao.getGoodMovies(region-ALL_REGION, type-ALL_TYPE, (page-1)*MOVIES_PER_PAGE, MOVIES_PER_PAGE);
		default:
			return null;
		}
	}

	@Override
	public List<Type> getAllTypes() {
		// TODO Auto-generated method stub
		return userDao.getAllTypes();
	}

	@Override
	public List<Region> getAllRegions() {
		// TODO Auto-generated method stub
		return userDao.getAllRegions();
	}

	@Override
	public MovieInfo getMovieInfo(int movieid) {
		// TODO Auto-generated method stub
		Movie movie = (Movie)userDao.find(Movie.class,movieid);
		String date = movie.getMoviebegin();
		String date2 = movie.getMovieover();
		String[] dateSplits = date.split("-");
		String[] dateSplits2 = date2.split("-");
//		double totalGrades = movie.getMoviegrade();
		double gcount = movie.getMoviegcount();
		MovieInfo movieInfo = new MovieInfo(movie.getMovieid(), movie.getMoviename(), movie.getMovieposter(), movie.getMoviedescription(), dateSplits[0]+"年"+dateSplits[1]+"月"+dateSplits[2]+"日",dateSplits2[0]+"年"+dateSplits2[1]+"月"+dateSplits2[2]+"日", Double.parseDouble(new DecimalFormat("#.0").format(movie.getMoviegrade())), (int)gcount);
		//
		movieInfo.setRegion(userDao.getMovieRegion(movieid));
		
		List<String> typeList = userDao.getMovieTypes(movieid);
		String[] types = (String[])typeList.toArray(new String[typeList.size()]);
		movieInfo.setTypes(types);
		
		List<String> directorList = userDao.getMovieDirectors(movieid);
		String[] directors = (String[])directorList.toArray(new String[directorList.size()]);
		movieInfo.setDirectors(directors);
		
		List<String> actorList = userDao.getMovieActors(movieid);
		String[] actors = (String[])actorList.toArray(new String[actorList.size()]);
		movieInfo.setActors(actors);
		
		return movieInfo;
	}

	@Override
	public List<Plan> getMoviePassedPlans(int movieid) {
		// TODO Auto-generated method stub
		return userDao.getMoviePassedPlans(movieid);
	}

	@Override
	public List<Seat> getSelectableSeats(int planid) {
		// TODO Auto-generated method stub
		List<Seat> ret = userDao.getHallSeats(planid);
		List<Integer> takens = userDao.getTakenSeats(planid);
		for(int taken:takens){
			for(Seat seat:ret){
				if(seat.getSeatid()==taken)
					seat.setSeatactive(false);
			}
		}
		return ret;
	}

	@Override
	public boolean buyTickets(List<Order> orders) {
		// TODO Auto-generated method stub
		
		int id = orders.get(0).getUserid();
		double totalPrice = orders.get(0).getPrice()*orders.size();
		
		if(userDao.payMoney(id, totalPrice)){//检查余额，付钱
			UserInfo user = (UserInfo)userDao.find(UserInfo.class, id);
			if(user.getLevel().equals("普通会员（暂停）")){
				user.setLevel("普通会员");
			}else if(user.getLevel().equals("高级会员（暂停）")){
				user.setLevel("高级会员");
			}
			userDao.update(UserInfo.class,user);//更新会员状态
			
			userDao.addCredit(id, (int)totalPrice);//增加积分
			
			userDao.addTickets(orders);//生成订单
			
//			for(Order o:orders){
//				userDao.addTickets(orders)
//			}
			
			return true;
		}
		else
			return false;
	}

	@Override
	public List<Activity> getActivities(int sort,int page) {
		// TODO Auto-generated method stub
		if(sort==NEWEST){
			return userDao.getNewestActivities((page-1)*ACTIVITIES_PER_PAGE,ACTIVITIES_PER_PAGE);
		}
		if(sort==HOTEST){
			return userDao.getHotestActivities((page-1)*ACTIVITIES_PER_PAGE,ACTIVITIES_PER_PAGE);
		}
		return null;
	}

	@Override
	public int getTotalPage(String modelName) {
		// TODO Auto-generated method stub
		if(modelName.equals("Movie"))
			return (int)Math.ceil((double)userDao.countAll(modelName)/(double)MOVIES_PER_PAGE);
		else if(modelName.equals("Activity"))
			return (int)Math.ceil((double)userDao.countAll(modelName)/(double)ACTIVITIES_PER_PAGE);
		return -1;
	}

	@Override
	public ActivityInfo getActivityInfo(int activityid) {
		// TODO Auto-generated method stub
		Activity activity = (Activity)userDao.find(Activity.class,activityid);
		ActivityInfo ai = new ActivityInfo(activity.getTitle(),activity.getActivityid(), activity.getActivitytime(), activity.getAcount(),activity.getCredit());
		
		List<ActivityQuestion> options = userDao.getActivityQuestions(activityid);
		ActivityQuestion[] optionArray = (ActivityQuestion[])options.toArray(new ActivityQuestion[options.size()]);
		ai.setQuestions(optionArray);
		
		List<PlanItem> planItems = userDao.getPlansByActivityId(activityid);
		PlanItem[] movieArray = (PlanItem[])planItems.toArray(new PlanItem[planItems.size()]);
		ai.setPlans(movieArray);
		
		return ai;
	}

	@Override
	public List<Integer> hasTakenActivity(int userid, int activityid) {
		// TODO Auto-generated method stub
		return userDao.hasTakenActivity(userid, activityid);
	}

	@Override
	public UserInfo getUserInfo(int userid) {
		// TODO Auto-generated method stub
		return (UserInfo)userDao.find(UserInfo.class, userid);
	}

	@Override
	public List<Consumption> getPersonalConsumptions(int userid) {
		// TODO Auto-generated method stub
		return userDao.getConsumptions(userid);
	}

	@Override
	public List<Payment> getPersonalPayments(int userid) {
		// TODO Auto-generated method stub
		return userDao.getPayments(userid);
	}

	@Override
	public String pay(int userid, int sum) {
		// TODO Auto-generated method stub
		userDao.addBalance(userid, sum);//充值并生成记录
		
		String ret = null;
		UserInfo ui = (UserInfo)userDao.find(UserInfo.class, userid);
//		boolean result = false;
		
		String currentLevel = ui.getLevel();
		//恢复暂停状态
		if(currentLevel.equals("普通会员（暂停）")){
			ret = "普通会员";
			ui.setLevel(ret);
		}else if(currentLevel.equals("高级会员（暂停）")){
			ret = "高级会员";
			ui.setLevel(null);
		}
		
		//检查会员升级
		if(sum>=LEVEL2_MONEY){
			if(!currentLevel.equals(LEVELS[2])){
				ret = LEVELS[2];
				ui.setLevel(ret);
			}
		}else if(sum>=LEVEL1_MONEY){
			if(!currentLevel.equals(LEVELS[1])){
				ret = LEVELS[1];
				ui.setLevel(ret);
			}
		}
		
		userDao.update(UserInfo.class, ui);
		return ret;
	}

	@Override
	public boolean cancelMember(int userid) {
		// TODO Auto-generated method stub
		UserInfo user = (UserInfo)userDao.find(UserInfo.class, userid);
		user.setLevel(LEVELS[0]);
		userDao.update(UserInfo.class, user);
		return true;
	}

	@Override
	public void modifyUserInfo(UserInfo ui) {
		// TODO Auto-generated method stub
		UserInfo cui = (UserInfo)userDao.find(UserInfo.class, ui.getUserid());;
		cui.setUsername(ui.getUsername());
		cui.setAge(ui.getAge());
		cui.setGender(ui.getGender());
		cui.setLocation(ui.getLocation());
		userDao.update(UserInfo.class,cui);
	}

	@Override
	public void selectOption(UserQuestion uo) {
		// TODO Auto-generated method stub
		userDao.add(UserQuestion.class,uo);
//		
//		ActivityOption ao = userDao.getOption(uo.getOptionid());
//		Activity a = userDao.getBasicActivityInfo(ao.getActivityid());
//		int credit = a.getCredit();
//		userDao.addUserCredit(uo.getUserid(), credit);
	}

	@Override
	public void changeMoney(int userid) {
		// TODO Auto-generated method stub
		userDao.exchangeCreditToBalance(userid);
	}

	@Override
	public List<Movie> searchMovie(String key) {
		// TODO Auto-generated method stub
		return userDao.getMoviesByKey(key);
	}

	@Override
	public List<Movie> getComingMovies(int quantity) {
		// TODO Auto-generated method stub
		return userDao.getComingMovies(quantity,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}

	@Override
	public void addTicket(Order o) {
		// TODO Auto-generated method stub
		Ticket t = new Ticket();
		t.setPlanid(o.getPlanid());
		t.setSeatid(o.getSeatid());
		t.setUserid(o.getUserid());
		t.setTickettype(o.getTicketType());
		t.setTickettime(o.getDatetime());
		t.setPrice(o.getPrice());
		userDao.add(Ticket.class,t);
	}

	@Override
	public void addActivityCredit(int userid, int activityid) {
		// TODO Auto-generated method stub
		userDao.addActivityCredit(userid, activityid);
	}

	@Override
	public void increActivityCount(int activityid) {
		// TODO Auto-generated method stub
		userDao.increActivityCount(activityid);
	}

	@Override
	public boolean payMoney(int userid, double sum) {
		// TODO Auto-generated method stub
		UserInfo ui = (UserInfo)userDao.find(UserInfo.class, userid);
		String currentLevel = ui.getLevel();
		//恢复暂停状态
		if(currentLevel.equals("普通会员（暂停）")){
			ui.setLevel("普通会员");
		}else if(currentLevel.equals("高级会员（暂停）")){
			ui.setLevel("高级会员");
		}
		
		return userDao.payMoney(userid, sum);
	}

	@Override
	public void evaluateMovie(int userid, int movieid, int evaluation) {
		// TODO Auto-generated method stub
		UserEvaluate ue = new UserEvaluate();
		ue.setUserid(userid);
		ue.setMovieid(movieid);
		ue.setValue(evaluation);
		userDao.add(UserEvaluate.class, ue);
		
		userDao.addMovieGrade(movieid, evaluation);
	}

	@Override
	public int getUserValuation(int userid, int movieid) {
		// TODO Auto-generated method stub
		return userDao.getUserValuation(userid, movieid);
	}
	
}
