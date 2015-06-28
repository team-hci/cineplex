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
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.service.UserService;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Home")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Movie> list = userService.getMovies(UserService.NEWEST, UserService.ALL_REGION, UserService.ALL_TYPE, 1);
		List<Activity> alist = userService.getActivities(UserService.NEWEST, 1);
		List<Movie> comingMovies = userService.getComingMovies(10);
		
		request.setAttribute("newMovies", list);
		request.setAttribute("newActivities", alist);
		request.setAttribute("comingMovies", comingMovies);
		
		request.getRequestDispatcher("pages/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
