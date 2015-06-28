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
		System.out.println("��ʱ������");   
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
//		managerService.updateMemberLevel();
		timer = new Timer(true);
		System.out.println("��ʱ������");
		timer.schedule(new UpdateMember(event.getServletContext()),0,60*60*1000);    
		System.out.println("�Ѿ����������ȱ�");    
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
					System.out.println("��ʼִ������");
					//task begin
//					managerService.updateMemberLevel();
					updateMemberState();
					//task done
					isRunning = false;
					System.out.println("����ִ�н���");
				}
			}else{
				System.out.println("��һ������ִ�л�δ����");
			}
		}
		
		private void updateMemberState(){
			 // ����������
	           String driver = "com.mysql.jdbc.Driver";
	           // URLָ��Ҫ���ʵ����ݿ���scutcs
	           String url = "jdbc:mysql://127.0.0.1:3306/cineplex";
	           // MySQL����ʱ���û���
	           String user = "root"; 
	           // MySQL����ʱ������
	           String password = "root";
	           try { 
		            // ������������
		            Class.forName(driver);
		            // �������ݿ�
		            Connection conn = DriverManager.getConnection(url, user, password);
		            // statement����ִ��SQL���
		            Statement statement = conn.createStatement();
		            // Ҫִ�е�SQL���
		            String sql1 = "update userinfo set level='��ͨ��Ա����ͣ��' where level='��ͨ��Ա' and consumptiondate<DATE_SUB(CURDATE(), INTERVAL 1 YEAR) and consumptiondate>DATE_SUB(CURDATE(), INTERVAL 2 YEAR)";
		            String sql2 = "update userinfo set level='�߼���Ա����ͣ��' where level='�߼���Ա' and consumptiondate<DATE_SUB(CURDATE(), INTERVAL 1 YEAR) and consumptiondate>DATE_SUB(CURDATE(), INTERVAL 2 YEAR)";
		            String sql3 = "update userinfo set level='δ����' where level<>'δ����' and consumptiondate<DATE_SUB(CURDATE(), INTERVAL 2 YEAR)";
		            // �����
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
