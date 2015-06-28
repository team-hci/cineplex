package edu.nju.cineplex.servlets.manager;

import java.io.IOException;
import java.util.Calendar;
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
 * Servlet implementation class MonthlyStatistics
 */
@WebServlet("/MonthlyStatistics.manager")
public class MonthlyStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final int[] MONTH_DAYS = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	@EJB private ManagerService managerService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonthlyStatistics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		int currYear = calendar.get(Calendar.YEAR);
		int currMonth = calendar.get(Calendar.MONTH)+1;
		
		request.setAttribute("currentYear", currYear);
		request.setAttribute("currentMonth", currMonth);
		
		String requestYear = request.getParameter("year");
		String requestMonth = request.getParameter("month");
		
		int showYear = requestYear==null?currYear:Integer.parseInt(requestYear);
		int showMonth = requestMonth==null?currMonth:Integer.parseInt(requestMonth);
		
		request.setAttribute("showYear", showYear);
		request.setAttribute("showMonth", showMonth);
		
		request.setAttribute("showDates", MONTH_DAYS[showMonth-1]);
		
		String dailyStat = managerService.getDailyStatistics(showYear,showMonth);
//		String monthlyStat = managerService.getMonthlyStatistics();
		List<MovieInfo> rates = managerService.getMovieSeatRate();
		String hallRates = managerService.getHallSeatRate(showYear,showMonth);
		
		request.setAttribute("daily", dailyStat);
//		request.setAttribute("monthly", monthlyStat);
		request.setAttribute("rates", rates);
		request.setAttribute("halls", hallRates);
		
		request.getRequestDispatcher("manager/monthly_statistics.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
