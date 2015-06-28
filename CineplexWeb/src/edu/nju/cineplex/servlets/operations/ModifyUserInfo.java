package edu.nju.cineplex.servlets.operations;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.model.UserInfo;
import edu.nju.cineplex.service.UserService;

/**
 * Servlet implementation class ModifyUserInfo
 */
@WebServlet("/ModifyUserInfo")
public class ModifyUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserInfo() {
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
		Object uid = request.getSession().getAttribute("userid");
		if(uid==null){
			response.sendRedirect("error.html");
			return;
		}
		
		int userid = (int)uid;
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String location = request.getParameter("location");
		
		int agei = 0;
		try {
			agei = Integer.parseInt(age);
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			UserInfo ui = new UserInfo();
			ui.setUserid(userid);
			ui.setUsername(username);
			ui.setAge(agei);
			ui.setGender(gender);
			ui.setLocation(location);
			userService.modifyUserInfo(ui);
		}
		response.sendRedirect("PersonalInfo");
	}

}
