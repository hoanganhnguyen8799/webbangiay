package filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class RouterFilter
 */
@WebFilter(filterName = "routerfilter", urlPatterns = { "/*" })
public class RouterFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RouterFilter() {
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String url = httRequest.getServletPath() ;
		
		if(url.endsWith("Home.jsp")  || url.equals("log.jsp")) {
			
			
			httpResponse.sendRedirect("home");
		}
		System.out.println("#INFO " + new Date() + " - ServletPath :" + url //
				+ ", URL =" + httRequest.getRequestURL());
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
