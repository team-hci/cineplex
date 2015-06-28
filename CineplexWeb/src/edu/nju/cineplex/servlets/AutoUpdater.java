package edu.nju.cineplex.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.service.ManagerService;

/**
 * Servlet implementation class AutoUpdater
 */
@WebServlet("/AutoUpdater")
public class AutoUpdater extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private ManagerService managerService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoUpdater() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("fuck from servlet");
//        managerService.updateMemberLevel();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("fuck from servlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
