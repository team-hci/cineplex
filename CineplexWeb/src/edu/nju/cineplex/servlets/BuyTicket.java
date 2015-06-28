package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.model.Plan;
import edu.nju.cineplex.model.Seat;
import edu.nju.cineplex.service.UserService;
import edu.nju.cineplex.utildata.Order;

/**
 * Servlet implementation class Seat
 */
@WebServlet("/BuyTicket")
public class BuyTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String aa = request.getParameter("fuck");
//		request.setAttribute("test", aa==null?"first":aa);
//		System.out.println(mid);
		int mid = Integer.parseInt(request.getParameter("m"));
		String pid = request.getParameter("p");
		String did = request.getParameter("d");
		
		List<Plan> plans = userService.getMoviePassedPlans(mid);
		request.setAttribute("plans", plans);
		
		int planid = 0;
		if(pid==null){
			if(plans.size()>0)
				planid = plans.get(0).getPlanid();
		}else{
			planid = Integer.parseInt(pid);
		}
		List<Seat> seats = userService.getSelectableSeats(planid);
		request.setAttribute("seats", seats);
		
		int dateid = did==null?1:Integer.parseInt(did);
		
		System.out.println(planid);
		
		request.setAttribute("movieid", mid);
		request.setAttribute("planid", planid);
		request.setAttribute("dateid", dateid);
		
		if(plans.size()>0)
			request.getRequestDispatcher("user/seats.jsp").forward(request, response);
		else
			request.getRequestDispatcher("user/noplan.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("pickedSeats"));
		PrintWriter out = response.getWriter();
		Object uid = request.getSession().getAttribute("userid");
		if(uid==null){
			out.print("n");
			return;
		}
		int userid = (int)uid;
		String[] pickedSeats = request.getParameter("pickedSeats").split(",");
		int planid = Integer.parseInt(request.getParameter("planid"));
		double price = Double.parseDouble(request.getParameter("finalPrice"));
		String payType = request.getParameter("payType");
		
		String lvl = (String)request.getSession().getAttribute("userlevel");
		if(!lvl.equals("管理员") && !lvl.equals("经理")){//会员
			payType = "卡";
		}
		
		String datetime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		
		double totalPrice = price*pickedSeats.length;
		if(!userService.payMoney(userid, totalPrice)){//钱不够
			out.print("n");
			return;
		}
		
//		List<Order> orders = new ArrayList<Order>();
		for(int i=0;i<pickedSeats.length;i++){
//			orders.add(new Order(userid, planid, Integer.parseInt(pickedSeats[i]),payType,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()),price));
			Order o = new Order(userid, planid, Integer.parseInt(pickedSeats[i]),payType,datetime,price);
			userService.addTicket(o);
		}
//		if(userService.buyTickets(orders))
			out.print("y");
//		else
//			out.print("n");
	}

}
