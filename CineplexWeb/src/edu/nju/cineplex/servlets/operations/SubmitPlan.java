package edu.nju.cineplex.servlets.operations;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.service.WaiterService;
import edu.nju.cineplex.utildata.PlanItem;

/**
 * Servlet implementation class SubmitPlan
 */
@WebServlet("/SubmitPlan")
public class SubmitPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB WaiterService waiterService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitPlan() {
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
		int mid = Integer.parseInt(request.getParameter("movie"));
		int hall = Integer.parseInt(request.getParameter("hall"));
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		int price = Integer.parseInt(request.getParameter("price"));
		int planid = Integer.parseInt(request.getParameter("planid"));
		//
		Plan plan = new Plan();
		plan.setPlanid(planid);
		plan.setMovieid(mid);
		plan.setHall(hall);
		plan.setPlandate(date);
		plan.setPlantime(time);
		plan.setPlanprice(price);
		if(waiterService.submitPlan(plan)){
			response.sendRedirect(request.getContextPath()+"/MakePlan.waiter");
		}
	}

}
