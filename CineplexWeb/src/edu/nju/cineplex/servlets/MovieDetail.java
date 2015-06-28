package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.service.UserService;
import edu.nju.cineplex.utildata.MovieInfo;

/**
 * Servlet implementation class MovieDetail
 */
@WebServlet("/MovieDetail")
public class MovieDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int mid = Integer.parseInt(request.getParameter("m"));
		MovieInfo movieInfo = userService.getMovieInfo(mid);
		
		Object userid = request.getSession().getAttribute("userid");
		int valuation = 0;
		if(userid!=null){
			valuation = userService.getUserValuation((int)userid, mid);
		}
		request.setAttribute("valuation", valuation);
//		List<Plan> plans = userService.getMoviePassedPlans(mid);
		
		request.setAttribute("movieInfo", movieInfo);
//		request.setAttribute("plans", plans);
		
//		request.setAttribute("test", "fuck");
//		request.getSession().setAttribute("test", "fucku");
		
		request.getRequestDispatcher("user/movie_detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
