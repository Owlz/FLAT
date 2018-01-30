package applicationLogic.controller;

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

/**
 * Servlet Filter per bloccare l'accesso a pagine .jsp (che devono gestire la sola view)
 */
@WebFilter("*.jsp")
public class FilterNoJSP implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		res.sendRedirect(req.getContextPath());
	}

	@Override
	public void destroy() {		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
