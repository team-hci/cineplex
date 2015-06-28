package edu.nju.cineplex.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.nju.cineplex.service.ManagerService;

@WebListener
public class DailyTaskListener implements ServletContextListener {

	private Timer timer = null;
//	@EJB private ManagerService managerService;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		timer.cancel();    
		System.out.println("定时器销毁");   
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
//		managerService.updateMemberLevel();
		timer = new Timer(true);
		System.out.println("定时器启动");
		timer.schedule(new UpdateMember(event.getServletContext()),0,60*60*1000);    
		System.out.println("已经添加任务调度表");    
	}

	private static class UpdateMember extends TimerTask{
		
		private static final int C_SCHEDULE_HOUR = 0;
		private static boolean isRunning = false;
		private ServletContext context = null;
		
		
		public UpdateMember(ServletContext context){
			this.context = context;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Calendar cal = Calendar.getInstance();
			if(!isRunning){
				if(C_SCHEDULE_HOUR==cal.get(Calendar.HOUR_OF_DAY)){
					isRunning = true;
					System.out.println("开始执行任务");
					//task begin
//					managerService.updateMemberLevel();
					updateMemberState();
					//task done
					isRunning = false;
					System.out.println("任务执行结束");
				}
			}else{
				System.out.println("上一次任务执行还未结束");
			}
		}
		
		private void updateMemberState(){
			 // 驱动程序名
	           String driver = "com.mysql.jdbc.Driver";
	           // URL指向要访问的数据库名scutcs
	           String url = "jdbc:mysql://127.0.0.1:3306/cineplex";
	           // MySQL配置时的用户名
	           String user = "root"; 
	           // MySQL配置时的密码
	           String password = "root";
	           try { 
		            // 加载驱动程序
		            Class.forName(driver);
		            // 连续数据库
		            Connection conn = DriverManager.getConnection(url, user, password);
		            // statement用来执行SQL语句
		            Statement statement = conn.createStatement();
		            // 要执行的SQL语句
		            String sql1 = "update userinfo set level='普通会员（暂停）' where level='普通会员' and consumptiondate<DATE_SUB(CURDATE(), INTERVAL 1 YEAR) and consumptiondate>DATE_SUB(CURDATE(), INTERVAL 2 YEAR)";
		            String sql2 = "update userinfo set level='高级会员（暂停）' where level='高级会员' and consumptiondate<DATE_SUB(CURDATE(), INTERVAL 1 YEAR) and consumptiondate>DATE_SUB(CURDATE(), INTERVAL 2 YEAR)";
		            String sql3 = "update userinfo set level='未激活' where level<>'未激活' and consumptiondate<DATE_SUB(CURDATE(), INTERVAL 2 YEAR)";
		            // 结果集
		            statement.execute(sql1);
		            statement.execute(sql2);
		            statement.execute(sql3);
	
//		            rs.close();
		            conn.close();
	           } catch(ClassNotFoundException e) {
	            System.out.println("Sorry,can`t find the Driver!"); 
	            e.printStackTrace();
	           } catch(SQLException e) {
	            e.printStackTrace();
	           } catch(Exception e) {
	            e.printStackTrace();
	           } 
		}
		
	}
	
}
