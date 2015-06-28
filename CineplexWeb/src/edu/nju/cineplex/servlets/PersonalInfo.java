package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.model.Payment;
import edu.nju.cineplex.model.UserInfo;
import edu.nju.cineplex.service.UserService;
import edu.nju.cineplex.utildata.Consumption;

/**
 * Servlet implementation class SelfInfo
 */
@WebServlet("/PersonalInfo")
public class PersonalInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userid = (int)request.getSession().getAttribute("userid");
		UserInfo ui = userService.getUserInfo(userid);
		List<Consumption> cons = userService.getPersonalConsumptions(userid);
		List<Payment> pays = userService.getPersonalPayments(userid);
		
		request.setAttribute("userInfo", ui);
		request.setAttribute("consumptions", cons);
		request.setAttribute("payments", pays);
		
		request.getRequestDispatcher("user/personal_info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
