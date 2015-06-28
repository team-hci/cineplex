package edu.nju.cineplex.servlets.operations;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.model.User;
import edu.nju.cineplex.model.UserInfo;
import edu.nju.cineplex.service.UserService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		User user = new User();
		user.setUserid(id);
		user.setPassword(password);
		UserInfo result = userService.verifyUser(user);
		if(null!=result){
//			request.getSession(true).setAttribute("id", id);
//			request.getRequestDispatcher("success.html").forward(request, response);
			request.getSession(true).setAttribute("userid", result.getUserid());
			request.getSession(true).setAttribute("userlevel", result.getLevel());
			request.getSession(true).setAttribute("username", result.getUsername());
			out.println("yes");
		}
		else{//failure
//			request.getRequestDispatcher("error.html").forward(request, response);
			out.println("no");
		}
	}

}
