package com.btc.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.btc.member.model.dto.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = {"/member/*","/myPage/*"})
public class LoginFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
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
		HttpSession session=((HttpServletRequest)request).getSession();
		Member m=(Member)session.getAttribute("loginMember");
		
		if(m==null) {
			String msg="잘못된 접근입니다", loc="/";
			request.setAttribute("msg",msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/views/LOGIN/login.jsp").forward(request, response);
	
		}else {
			chain.doFilter(request, response);
		}
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
