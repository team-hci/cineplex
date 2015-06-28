package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.model.Activity;
import edu.nju.cineplex.service.UserService;

/**
 * Servlet implementation class ActivityList
 */
@WebServlet("/ActivityList")
public class ActivityList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivityList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Ò³Êý
		String p = request.getParameter("page");
		int page = p==null?1:Integer.parseInt(p);
		//ÅÅÐò
		String s = request.getParameter("sort");
		int sort = s==null?UserService.NEWEST:Integer.parseInt(s);
		
		List<Activity> activities = userService.getActivities(sort,page);
		
		request.setAttribute("activities", activities);
		request.setAttribute("sort", sort);
		request.setAttribute("page", page);
		request.setAttribute("totalPage", userService.getTotalPage("Activity"));
		
		request.getRequestDispatcher("user/activity.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
