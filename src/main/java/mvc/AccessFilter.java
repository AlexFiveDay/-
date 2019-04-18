package mvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.net.httpserver.Filter.Chain;

import entity.User;

public class AccessFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//强转为子类
		HttpServletRequest request2 = (HttpServletRequest)request;
		HttpServletResponse response2 = (HttpServletResponse)response;
		
		//只允许登录界面的请求通过,即当前请求是 /login 开始的，就放过
		String path = request2.getRequestURI();
		String contextPath = request2.getContextPath();
		path = path.substring(contextPath.length());
		if (path.startsWith("/login")) {
			//调用的是HttpServletRequest和HttpServletResponse中的请求和响应
			chain.doFilter(request2, response2);
			return;
		}
		HttpSession session = request2.getSession();
		User user = (User)session.getAttribute("loginUser");
		if(user==null) {
			//放过注册页面啊啊啊爱哎啊啊爱哎a
			if (request2.getRequestURI().equals("/servlet/add.do") || request2.getRequestURI().equals("/servlet/save.do")) {
				chain.doFilter(request2, response2);
			} else {
				//没有登录过,重定向到登录页面
				response2.sendRedirect(request2.getContextPath()+ 
						"/login-form.do"); 
			}
		}else {
			//如果登录了就放过
			chain.doFilter(request2, response2);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
