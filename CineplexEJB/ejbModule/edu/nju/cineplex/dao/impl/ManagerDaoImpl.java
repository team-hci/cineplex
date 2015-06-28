package edu.nju.cineplex.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.cineplex.dao.ManagerDao;
import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.utildata.MovieInfo;
import edu.nju.cineplex.utildata.PlanItem;

@Stateless
public class ManagerDaoImpl extends BaseDaoImpl implements ManagerDao {
	
	@PersistenceContext
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PlanItem> getToBeCheckedPlan() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select new edu.nju.cineplex.utildata.PlanItem(p.planid,m.moviename,p.hall,p.plandate,p.plantime,p.planprice) from Plan p,Movie m where p.movieid=m.movieid and p.planpass=0 order by p.plantime desc");
		return query.getResultList();
	}

	@Override
	public boolean setPlanPass(int planid, int planpass) {
		// TODO Auto-generated method stub
		Plan plan = em.find(Plan.class, planid);
		plan.setPlanpass(planpass);
		em.merge(plan);
		return true;
	}

	@Override
	public double getLocationPercent(String location) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select count(userid) from UserInfo where location like '%"+location+"%'");
		long count = (long)query.getResultList().get(0);
		return (double)count/(double)getUserCount();
	}

	@Override
	public double getConsumptionPercent(int low, int high) {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery("select count(f.id) from (select t.userid as id,sum(p.planprice) as cons from Ticket t,Plan p where t.planid=p.planid group by t.userid) as f where f.cons>="+low+" and f.cons<="+high);
		BigInteger count = (BigInteger)query.getResultList().get(0);
		return count.intValue()/(double)getUserCount();
	}

	@Override
	public double getActivityPercent(int low, int high) {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery("select count(f.uid) from (select uq.userid as uid,count(id) as cnt from UserQuestion uq group by uq.userid) as f where f.cnt>="+low+" and f.cnt<="+high);
		BigInteger count = (BigInteger)query.getResultList().get(0);
		return count.intValue()/(double)getUserCount();
	}

	@Override
	public double getLevelPercent(String lvl) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select count(userid) from UserInfo where level='"+lvl+"'");
		long count = (long)query.getResultList().get(0);
		return (double)count/(double)getUserCount();
	}

	@Override
	public double getAgePercent(int low, int high) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select count(userid) from UserInfo where age>="+low+" and age<="+high);
		long count = (long)query.getResultList().get(0);
		return (double)count/(double)getUserCount();
	}

	@Override
	public double getGenderPercent(String gender) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select count(userid) from UserInfo where gender='"+gender+"'");
		long count = (long)query.getResultList().get(0);
		return (double)count/(double)getUserCount();
	}

	private long getUserCount(){
		Query query = em.createQuery("select count(userid) from UserInfo");// where level<>-1 and level<>-2
		return (long)query.getResultList().get(0);
	}

	@Override
	public long getDailyUserCount(String type, String date) {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery("select count(t.ticketid) from Ticket t,Plan p where t.planid=p.planid and tickettype='"+type+"' and p.plandate='"+date+"'");//+" and TO_DAYS(NOW())-TO_DAYS(p.plandate)="+daysAgo
		BigInteger count = (BigInteger)query.getResultList().get(0);
		return count.longValue();
	}

//	@Override
//	public long getMonthlyUserCount(String type, int monthsAgo) {
//		// TODO Auto-generated method stub
//		Query query = em.createNativeQuery("select count(t.ticketid) from Ticket t,Plan p where t.planid=p.planid and tickettype='"+type+"' and date_format(p.plandate,'%Y-%m')=date_format(DATE_SUB(curdate(), INTERVAL "+monthsAgo+" MONTH),'%Y-%m')");//+" and TO_DAYS(NOW())-TO_DAYS(p.plandate)="+daysAgo
//		BigInteger count = (BigInteger)query.getResultList().get(0);
//		return count.longValue();
//	}

	@Override
	public double getHallSeatRate(int hall, String date) {
		// TODO Auto-generated method stub
		Query query1 = em.createNativeQuery("select count(p.planid) from Plan p where p.hall="+hall+" and p.plandate='"+date+"'");
		int plans = ((BigInteger)query1.getResultList().get(0)).intValue();
		Query query2 = em.createQuery("select count(s.seatid) from Seat s where s.hall="+hall);
		long seatsPerHall = (long)query2.getResultList().get(0);
		Query query3 = em.createNativeQuery("select count(t.ticketid) from Ticket t,Plan p where p.planid=t.planid and p.hall="+hall+" and p.plandate='"+date+"'");
		int seatedCount = ((BigInteger)query3.getResultList().get(0)).intValue();
		return plans==0?0:(double)seatedCount/((double)plans*(double)seatsPerHall);
	}

	@Override
	public double getMovieSeatRate(int movieid) {
		// TODO Auto-generated method stub
		Query query1 = em.createQuery("select count(t.ticketid) from Plan p,Ticket t where p.movieid="+movieid+" and p.planid=t.planid");
		Query query2 = em.createQuery("select count(s.seatid) from Plan p,Seat s where p.movieid="+movieid+" and p.hall=s.hall");
		long seated = (long)query1.getResultList().get(0);
		long total = (long)query2.getResultList().get(0);
		return total==0?0:(double)seated/(double)total;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieInfo> getMovies() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select new edu.nju.cineplex.utildata.MovieInfo(m.movieid,m.moviename,m.movieposter,m.moviedescription,m.moviebegin,m.movieover,m.moviegrade,m.moviegcount) from Movie m order by m.movieid");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getUpdatableIds() {
		// TODO Auto-generated method stub
		Query idQuery = em.createQuery("select userid from UserInfo where username<>'经理' and username<>'服务员'");
		return idQuery.getResultList();
	}

}
