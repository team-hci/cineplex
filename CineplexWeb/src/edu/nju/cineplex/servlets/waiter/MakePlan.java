package edu.nju.cineplex.servlets.waiter;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.service.ManagerService;
import edu.nju.cineplex.service.WaiterService;
import edu.nju.cineplex.utildata.PlanItem;

/**
 * Servlet implementation class MakePlan
 */
@WebServlet("/MakePlan.waiter")
public class MakePlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB WaiterService waiterService;
	@EJB ManagerService managerService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakePlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pid = request.getParameter("p");
		
		List<Movie> list = waiterService.getShowingMovies();
//		System.out.println(list.get(0).getName());
		request.setAttribute("movies", list);
		
		List<PlanItem> notHandledPlans = managerService.getToBeCheckedPlan() ;
		request.setAttribute("plans1", notHandledPlans);
		
		List<PlanItem> passedPlans = waiterService.getPassedPlans() ;
		request.setAttribute("plans2", passedPlans);
		
		List<PlanItem> rejectedPlans = waiterService.getBlockedPlans() ;
		request.setAttribute("plans3", rejectedPlans);
		
		
		if(pid!=null){
			int planid = Integer.parseInt(pid);
			PlanItem pi = waiterService.getPlanItemById(planid);
			request.setAttribute("plan", pi);
		}
		
		request.getRequestDispatcher("waiter/plan.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
