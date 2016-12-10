package com.litop.motorroom.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litop.motorroom.litop.LitopMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Yk权限过滤器
 * 
 * @author
 */
public class LitopAuthFilter implements Filter {

	private static final Log log = LogFactory.getLog(LitopAuthFilter.class);
	private AuthService authService;

	/**
	 * 初始化过滤器
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
		this.authService = (AuthService) wac.getBean("authService");
		try {
			authService.resetCache(filterConfig.getServletContext().getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
			log.equals(e.getMessage());
		}
		
	}

	/**
	 * URL 过滤方法
	 */
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterchain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		try {
			if (!authService.filterUrl(request)) {
				LitopMsg<String> LitopMsg = new LitopMsg<String>(false, "您的登陆已经失效<BR/>或您无访问非公共URL:"+request.getRequestURI()+"权限，<BR/>请重新登陆!");
				LitopMsg.setForwardUrl("/sys/goLogon.do");
				LitopMsg.setForwardMsg("登陆");
				request.setAttribute("LitopMsg", LitopMsg);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/sys/exception.do");
				requestDispatcher.forward(request, response);
			} else {
				filterchain.doFilter(servletRequest, servletResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.equals(e.getMessage());
			request.setAttribute("LitopMsg", new LitopMsg<String>(false, "权限检查出现异常!"));
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/sys/exception.do");
			requestDispatcher.forward(request, response);
		}
	}
	/**
	 * 摧毁过滤器前需要完成的操作
	 */
	public void destroy() {

	}

}
