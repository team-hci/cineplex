package edu.nju.cineplex.service.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.nju.cineplex.dao.ManagerDao;
import edu.nju.cineplex.service.ManagerService;
import edu.nju.cineplex.utildata.MovieInfo;
import edu.nju.cineplex.utildata.PlanItem;

@Stateless
public class ManagerServiceBean implements ManagerService {

	private static final int[] AGES = {-1,10,20,30,40,50,60};
	private static final String[] GENDERS = {"男","女"};
	private static final String[] LOCATIONS = {"南京","北京","上海","其它"};
	private static final int[] CONSUMPTIONS = {0,100,200,500,1000,5000};
	private static final int[] ACTIVITIES = {0,5,15,30,60,120};
	private static final String[] LEVELS = {"未激活","普通会员","高级会员","普通会员（暂停）","高级会员（暂停）"};
	private static final String[] HALLS = {"1号厅","2号厅","3号厅","4号厅","5号厅"};
	private static final int[] MONTH_DAYS = {31,28,31,30,31,30,31,31,30,31,30,31};
	//
	private static final int DAILY_COUNT = 10;
	private static final int MONTHLY_COUNT = 12;
	private static final int HALL_RATE_DAYS = 7;
	
	@EJB private ManagerDao managerDao;
//	@EJB private BaseDao baseDao;
	
	
	public ManagerServiceBean(){
//		System.out.println("fuck from bean");
	}
	
	@Override
	public List<PlanItem> getToBeCheckedPlan() {
		// TODO Auto-generated method stub
		return managerDao.getToBeCheckedPlan();
	}

	@Override
	public boolean passPlan(int planid) {
		// TODO Auto-generated method stub
		return managerDao.setPlanPass(planid, 1);
	}

	@Override
	public boolean failPlan(int planid) {
		// TODO Auto-generated method stub
		return managerDao.setPlanPass(planid, -1);
	}

	@Override
	public String getAgeStatistics() {
		// TODO Auto-generated method stub
		int length = AGES.length;
		double[] shares = new double[length];
		String ret = "";
		for(int i=0;i<length-1;i++){
			shares[i] = managerDao.getAgePercent(AGES[i]+1, AGES[i+1]);
			ret+="['"+(AGES[i]+1)+"~"+AGES[i+1]+"岁',"+String.format("%.3f", shares[i])+"],";
		}
		shares[length-1] = managerDao.getAgePercent(AGES[length-1], Integer.MAX_VALUE);
		ret+="['"+AGES[length-1]+"岁以上',"+String.format("%.3f", shares[length-1])+"]";
		return ret;
	}

	@Override
	public String getGenderStatistics() {
		// TODO Auto-generated method stub
		double shares1 = managerDao.getGenderPercent(GENDERS[0]);
		double shares2 = managerDao.getGenderPercent(GENDERS[1]);
		String ret = "['"+GENDERS[0]+"',"+String.format("%.3f", shares1)+"]"
				+ ",['"+GENDERS[1]+"',"+String.format("%.3f", shares2)+"]";
		return ret;
	}

	@Override
	public String getLocationStatistics() {
		// TODO Auto-generated method stub
		int length = LOCATIONS.length;
		double[] shares = new double[length];
		double shareSum = 0.0;
		String ret = "";
		for(int i=0;i<length-1;i++){
			shares[i] = managerDao.getLocationPercent(LOCATIONS[i]);
			shareSum+=shares[i];
			ret+="['"+LOCATIONS[i]+"',"+String.format("%.3f", shares[i])+"],";
		}
		shares[length-1] = 1-shareSum;
		ret+="['"+LOCATIONS[length-1]+"',"+String.format("%.3f", shares[length-1])+"]";
		return ret;
	}

	@Override
	public String getConsumptionStatistics() {
		// TODO Auto-generated method stub
		int length = CONSUMPTIONS.length;
		double[] shares = new double[length];
		double shareSum = 0.0;
		String ret = "";
		for(int i=0;i<length-1;i++){
			shares[i] = managerDao.getConsumptionPercent(CONSUMPTIONS[i]+1, CONSUMPTIONS[i+1]);
			shareSum += shares[i];
			ret+="['"+(CONSUMPTIONS[i]+1)+"~"+CONSUMPTIONS[i+1]+"元',"+String.format("%.3f", shares[i])+"],";
		}
		shares[length-1] = managerDao.getConsumptionPercent(CONSUMPTIONS[length-1], Integer.MAX_VALUE);
		ret+="['"+CONSUMPTIONS[length-1]+"元以上',"+String.format("%.3f", shares[length-1])+"],";
		shareSum += shares[length-1];
		ret+="['未消费',"+String.format("%.3f", 1-shareSum)+"]";
		return ret;
	}

	@Override
	public String getActivityStatistics() {
		// TODO Auto-generated method stub
		int length = ACTIVITIES.length;
		double[] shares = new double[length];
		double shareSum = 0.0;
		String ret = "";
		for(int i=0;i<length-1;i++){
			shares[i] = managerDao.getActivityPercent(ACTIVITIES[i]+1, ACTIVITIES[i+1]);
			shareSum += shares[i];
			ret+="['"+(ACTIVITIES[i]+1)+"~"+ACTIVITIES[i+1]+"次',"+String.format("%.3f", shares[i])+"],";
		}
		shares[length-1] = managerDao.getActivityPercent(ACTIVITIES[length-1], Integer.MAX_VALUE);
		ret+="['"+ACTIVITIES[length-1]+"次以上',"+String.format("%.3f", shares[length-1])+"],";
		shareSum += shares[length-1];
		ret+="['未参加活动',"+String.format("%.3f", 1-shareSum)+"]";
		return ret;
	}

	@Override
	public String getLevelStatistics() {
		// TODO Auto-generated method stub
		int length = LEVELS.length;
		double[] shares = new double[length];
		String ret = "";
		for(int i=0;i<length;i++){
			shares[i] = managerDao.getLevelPercent(LEVELS[i]);
			ret+="['"+LEVELS[i]+"',"+String.format("%.3f", shares[i])+"]";
			if(i!=length-1)
				ret+=",";
		}
		return ret;
	}

	@Override
	public String getDailyStatistics(int year,int month) {
		// TODO Auto-generated method stub
		String cardCount = "";
		String cashCount = "";
		String preDate = year+"-"+month;
		String date = null;
		for(int i=1;i<=MONTH_DAYS[month-1];i++){
			date = preDate+"-"+i;
			cardCount += managerDao.getDailyUserCount("卡", date);
			cashCount += managerDao.getDailyUserCount("现金", date);
			if(i!=MONTH_DAYS[month-1]){
				cardCount+=",";
				cashCount+=",";
			}
		}
		return cardCount+"#"+cashCount;
	}

	@Override
	public String getHallSeatRate(int year,int month) {
		// TODO Auto-generated method stub
		int hallLength = HALLS.length;
		String[] s = new String[hallLength];
		String preDate = year+"-"+month;
		String date = null;
		for(int i=0;i<hallLength;i++){
			s[i] = "{name:'"+HALLS[i]+"',data:[";
		}
		for(int i=0;i<hallLength;i++){
			for(int j=1;j<=MONTH_DAYS[month-1];j++){
				date = preDate+"-"+j;
				s[i] += String.format("%.3f", managerDao.getHallSeatRate(i+1, date));
				if(j!=MONTH_DAYS[month-1])
					s[i] += ",";
			}
		}
		String ret = "";
		for(int i=0;i<hallLength;i++){
			ret += (s[i]+"]}");
			if(i!=hallLength-1)
				ret += ",";
		}
		System.out.println(ret);
		return ret;
	}

	@Override
	public List<MovieInfo> getMovieSeatRate() {
		// TODO Auto-generated method stub
		List<MovieInfo> movies = managerDao.getMovies();
		for(MovieInfo m:movies){
			double r = managerDao.getMovieSeatRate(m.getMovieid());
			m.setSeatRate(r);
		}
		return movies;
	}

	@Override
	public boolean updateMemberLevel() {
		// TODO Auto-generated method stub
//		ManagerDao md = new ManagerDaoImpl();
//		List<Integer> idList = md.getUpdatableIds();
//		Date today = new Date();
//		for(int id:idList){
//			UserInfo user = (UserInfo)baseDao.find(UserInfo.class, id);
//			try {
//				Date d = new SimpleDateFormat("yyyy-MM-dd").parse(user.getConsumptiondate());
//				int days = daysBetween(d,today);
//				if(days>365){
//					String curLevel = user.getLevel();
//					if(curLevel.equals("普通会员") || curLevel.equals("高级会员")){
//						user.setLevel(curLevel+"（暂停）");
//						baseDao.update(UserInfo.class,user);
//						continue;
//					}
//					if(days>730 && !curLevel.equals("未激活")){
//						user.setLevel("未激活");
//						baseDao.update(UserInfo.class,user);
//						continue;
//					}
//				}
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		System.out.println("fuck from ejb");
		return true;
	}
	
	private int daysBetween(Date d1,Date d2) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		d1=sdf.parse(sdf.format(d1));  
		d2=sdf.parse(sdf.format(d2));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(d1);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(d2);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days)); 
	}
	
}
