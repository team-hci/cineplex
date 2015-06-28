package edu.nju.cineplex.servlets.operations;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.model.ActivityPlan;
import edu.nju.cineplex.model.ActivityQuestion;
import edu.nju.cineplex.service.WaiterService;
import edu.nju.cineplex.utildata.ActivityForm;

/**
 * Servlet implementation class SubmitActivity
 */
@WebServlet("/SubmitActivity")
public class SubmitActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int OPTIONS_PER_QUESTION = 3;   
	
	@EJB WaiterService waiterService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] planids = request.getParameterValues("match_checkbox");
		int[] planids2 = new int[planids.length];//planids
		for(int i=0;i<planids2.length;i++){
			planids2[i] = Integer.parseInt(planids[i]);
			System.out.println(planids2[i]);
		}
		String title = request.getParameter("title");//question
		int credit = Integer.parseInt(request.getParameter("credit"));//credit
		int questionCount = Integer.parseInt(request.getParameter("question_count"));
		
		ActivityQuestion[] questions = new ActivityQuestion[questionCount];//questions
		for(int i=0;i<questionCount;i++){
			questions[i] = new ActivityQuestion();
			questions[i].setContent(request.getParameter("question"+(i+1)));
			questions[i].setOption1(request.getParameter("option"+(i+1)+"-"+1));
			questions[i].setOption2(request.getParameter("option"+(i+1)+"-"+2));
			questions[i].setOption3(request.getParameter("option"+(i+1)+"-"+3));
		}
		ActivityForm af = new ActivityForm(planids2,title,questions,credit);
//		waiterService.submitActivity(af);
		////
		Activity activity = new Activity();
		activity.setTitle(af.getTitle());
		String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		activity.setActivitytime(datetime);
		activity.setCredit(af.getCredit());
		
//		System.out.println(activity);
//		for(ActivityQuestion question:questions){
//			System.out.println(question);
//		}
		
		int aid = waiterService.addActivity(activity);
		
		System.out.println("aid : " + aid);
//		
		for(int pid:af.getPlanids()){
			ActivityPlan ap = new ActivityPlan();
			
			ap.setActivityid(aid);
			ap.setPlanid(pid);
			
			waiterService.addActivityPlan(ap);
		}
		
		for(ActivityQuestion q:af.getQuestions()){
			q.setActivityid(aid);
			
			waiterService.addActivityQuestion(q);
		}
		////
		response.sendRedirect("MakeActivity.waiter");
	}

}
