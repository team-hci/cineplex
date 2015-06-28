package edu.nju.cineplex.servlets.waiter;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.service.WaiterService;
import edu.nju.cineplex.utildata.PlanItem;

/**
 * Servlet implementation class ModifyPlan
 */
@WebServlet("/ModifyPlan.waiter")
public class ModifyPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB WaiterService waiterService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<PlanItem> plans = waiterService.getBlockedPlans();
		
		request.setAttribute("plans", plans);
		
		request.getRequestDispatcher("waiter/modifyPlan.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
