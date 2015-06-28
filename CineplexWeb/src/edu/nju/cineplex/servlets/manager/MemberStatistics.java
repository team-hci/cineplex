package edu.nju.cineplex.servlets.manager;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.service.ManagerService;
import edu.nju.cineplex.utildata.MovieInfo;

/**
 * Servlet implementation class Statistics
 */
@WebServlet("/MemberStatistics.manager")
public class MemberStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private ManagerService managerService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberStatistics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ageStatistics = managerService.getAgeStatistics();
		String genderStatistics = managerService.getGenderStatistics();
		String locationStat = managerService.getLocationStatistics();
		String consumptionStat = managerService.getConsumptionStatistics();
		String activityStat = managerService.getActivityStatistics();
		String levelStat = managerService.getLevelStatistics();
//		//
//		String dailyStat = managerService.getDailyStatistics();
//		String monthlyStat = managerService.getMonthlyStatistics();
//		List<MovieInfo> rates = managerService.getMovieSeatRate();
//		String hallRates = managerService.getHallSeatRate();
		
		//
		request.setAttribute("age", ageStatistics);
		request.setAttribute("gender", genderStatistics);
		request.setAttribute("location", locationStat);
		request.setAttribute("consumption", consumptionStat);
		request.setAttribute("activity", activityStat);
		request.setAttribute("level", levelStat);
		//
//		request.setAttribute("daily", dailyStat);
//		request.setAttribute("monthly", monthlyStat);
//		request.setAttribute("rates", rates);
//		request.setAttribute("halls", hallRates);
		
		request.getRequestDispatcher("manager/statistics.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
