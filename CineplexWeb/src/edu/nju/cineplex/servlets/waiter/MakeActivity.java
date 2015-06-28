package edu.nju.cineplex.servlets.waiter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.service.WaiterService;
import edu.nju.cineplex.utildata.PlanItem;

/**
 * Servlet implementation class MakeActivity
 */
@WebServlet("/MakeActivity.waiter")
public class MakeActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB WaiterService waiterService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(new Date());
		List<PlanItem> list = waiterService.getPassedPlans();
		request.setAttribute("plans", list);
		request.getRequestDispatcher("waiter/activity.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
