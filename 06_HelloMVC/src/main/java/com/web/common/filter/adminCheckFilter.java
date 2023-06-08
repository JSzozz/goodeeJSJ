package com.web.common.filter;

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

import com.web.model.dto.MemberDTO;

/**
 * Servlet Filter implementation class adminCheckFilter
 */

@WebFilter(urlPatterns={"/admin/*","/notice/insertForm.do"})
// #springsecurity : 보안 시큐리티API(?)
public class adminCheckFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public adminCheckFilter() {
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
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req=((HttpServletRequest)request);
		HttpSession session=((HttpServletRequest)request).getSession();//import
		MemberDTO loginMember=(MemberDTO)session.getAttribute("loginMember");
		
		if(loginMember==null||!loginMember.getUserId().equals("admin")) {
			req.setAttribute("msg", "관리자 아이디로 접속해주세요");
			String prevPage=req.getHeader("Referer");//이전페이지로 이동
			System.out.println(prevPage+"(*CheckAthunticate)");
			req.setAttribute("loc", "/");
			req.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}else {
		
		// pass the request along the filter chain
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
