package edu.nju.cineplex.servlets.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.service.ManagerService;
import edu.nju.cineplex.utildata.PlanItem;

/**
 * Servlet implementation class CheckPlan
 */
@WebServlet("/CheckPlan.manager")
public class CheckPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private ManagerService managerService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<PlanItem> pis = managerService.getToBeCheckedPlan();
		
		request.setAttribute("plans", pis);
		
		request.getRequestDispatcher("manager/check_plan.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation");
		int planid = Integer.parseInt(request.getParameter("planid"));
		
		boolean result = false;
		if(operation.equals("pass")){
			result = managerService.passPlan(planid);
		}else if(operation.equals("fail")){
			result = managerService.failPlan(planid);
		}
		
		PrintWriter out = response.getWriter();
		out.print(result?"y":"n");
	}

}
