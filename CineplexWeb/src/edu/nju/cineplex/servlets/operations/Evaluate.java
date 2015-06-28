package edu.nju.cineplex.servlets.operations;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.service.UserService;

/**
 * Servlet implementation class Evaluate
 */
@WebServlet("/Evaluate")
public class Evaluate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Evaluate() {
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
		Object userid = request.getSession().getAttribute("userid");
		int evaluation = Integer.parseInt(request.getParameter("valuation"));
		int movieid = Integer.parseInt(request.getParameter("movieid"));
		
		if(userid!=null){
			userService.evaluateMovie((int)userid, movieid, evaluation);
		}
		
		response.sendRedirect("/CineplexWeb/MovieDetail?m="+movieid);
	}

}
