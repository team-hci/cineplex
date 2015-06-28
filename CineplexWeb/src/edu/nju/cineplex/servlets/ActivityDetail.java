package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.model.UserQuestion;
import edu.nju.cineplex.service.UserService;
import edu.nju.cineplex.utildata.ActivityInfo;

/**
 * Servlet implementation class ActivityDetail
 */
@WebServlet("/ActivityDetail")
public class ActivityDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivityDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int aid = Integer.parseInt(request.getParameter("a"));
		ActivityInfo activityInfo = userService.getActivityInfo(aid);
//		System.out.println(activityInfo.getActivityquestion());
		request.setAttribute("activityInfo", activityInfo);
		
		List<Integer> taken = null;
		Object userid = request.getSession().getAttribute("userid");
		if(userid!=null){
			taken = userService.hasTakenActivity((int)userid, aid);
		}
		request.setAttribute("taken", taken==null?new ArrayList<Integer>():taken);
		
		int tb = 0;//没买
		if(taken!=null){
			System.out.println("takenSize:"+taken.size());
			
			if(taken.size()==0)
				tb=1;//没参加
			else
				tb=2;//参加过
		}
		request.setAttribute("tb", tb);
		
		System.out.println("tb:"+tb);
//		System.out.println("taken:"+taken.size());
		
		request.getRequestDispatcher("user/activity_detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userid = (int)request.getSession().getAttribute("userid");
		String answer = request.getParameter("options");
		String[] answers = answer.split(",");
		
		for(String a:answers){
			String[] pair = a.split("-");
			UserQuestion uo = new UserQuestion();
			uo.setUserid(userid);
			uo.setQuestionid(Integer.parseInt(pair[0]));
			uo.setOptionid(Integer.parseInt(pair[1]));
			userService.selectOption(uo);
		}
		
		int activityid = Integer.parseInt(request.getParameter("activityid"));
		userService.addActivityCredit(userid, activityid);//给参加用户增加活动对应积分
		
		userService.increActivityCount(activityid);
		
		PrintWriter out = response.getWriter();
		out.print("y");
		
//		System.out.println(optionid);
//		uo.setUserid(userid);
//		uo.setOptionid(optionid);
//		userService.selectOption(uo);
	}

}
