package edu.nju.cineplex.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class WaiterAccessFilter
 */
@WebFilter("/WaiterAccessFilter")
public class WaiterAccessFilter implements Filter {

    /**
     * Default constructor. 
     */
    public WaiterAccessFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("waiter's filter here");
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpSession session = httpReq.getSession(true);
		
		Object lvl = session.getAttribute("userlevel");
		
		if(lvl==null || !((String)lvl).equals("·þÎñÔ±")){
			((HttpServletResponse)response).sendRedirect("error.html");
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
