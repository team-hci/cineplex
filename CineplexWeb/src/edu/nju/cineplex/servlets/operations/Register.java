package edu.nju.cineplex.servlets.operations;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.service.UserService;
import edu.nju.cineplex.utildata.RegisterInfo;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		System.out.println("зЂВс");
		String name = request.getParameter("name");
		name = java.net.URLDecoder.decode(name,"UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=utf-8");
//		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(name);
		int creditCard = Integer.parseInt(request.getParameter("creditCard"));
		String password = request.getParameter("password");
		RegisterInfo ri = new RegisterInfo(name,creditCard,password);
		PrintWriter out = response.getWriter();
		int result = userService.register(ri);
		if(result!=0){//success
			out.println(result);
		}else{
			out.println("no");
		}
	}
	
}
