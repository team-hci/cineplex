package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Region;
import edu.nju.cineplex.model.Type;
import edu.nju.cineplex.service.UserService;

/**
 * Servlet implementation class Movie
 */
@WebServlet("/MovieList")
public class MovieList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB private UserService userService;
	private List<Type> types;
	private List<Region> regions;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieList() {
        super();
//        types = userService.getAllTypes();
//        regions = userService.getAllRegions();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("done");
		if(types==null)
			types = userService.getAllTypes();
		if(regions==null)
			regions = userService.getAllRegions();
		
		//页数
		String p = request.getParameter("page");
		int page = p==null?1:Integer.parseInt(p);
		//排序
		String s = request.getParameter("sort");
		int sort = s==null?UserService.NEWEST:Integer.parseInt(s);
		//地区
		String r = request.getParameter("region");
		int region = r==null?UserService.ALL_REGION:Integer.parseInt(r);
		//类型
		String t = request.getParameter("type");
		int type = t==null?UserService.ALL_TYPE:Integer.parseInt(t);
		//
		List<Movie> list = userService.getMovies(sort, region, type, page);
		request.setAttribute("types", types);
		request.setAttribute("regions", regions);
		request.setAttribute("movies", list);
		
		request.setAttribute("sort", sort);
		request.setAttribute("region", region);
		request.setAttribute("type", type);
		request.setAttribute("page", page);
		request.setAttribute("totalPage", userService.getTotalPage("Movie"));
//		System.out.println(userService.getTotalPage("Movie"));
		//request.setAttribute("href", request.getContextPath()+"/MovieList?type="+type+"&region="+region);
//		System.out.println("page="+page+",sort="+sort+",region="+region+",type="+type);
		request.getRequestDispatcher("user/movie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
