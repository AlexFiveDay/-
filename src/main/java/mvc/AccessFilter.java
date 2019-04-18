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
		//ǿתΪ����
		HttpServletRequest request2 = (HttpServletRequest)request;
		HttpServletResponse response2 = (HttpServletResponse)response;
		
		//ֻ�����¼���������ͨ��,����ǰ������ /login ��ʼ�ģ��ͷŹ�
		String path = request2.getRequestURI();
		String contextPath = request2.getContextPath();
		path = path.substring(contextPath.length());
		if (path.startsWith("/login")) {
			//���õ���HttpServletRequest��HttpServletResponse�е��������Ӧ
			chain.doFilter(request2, response2);
			return;
		}
		HttpSession session = request2.getSession();
		User user = (User)session.getAttribute("loginUser");
		if(user==null) {
			//�Ź�ע��ҳ�氡����������������a
			if (request2.getRequestURI().equals("/servlet/add.do") || request2.getRequestURI().equals("/servlet/save.do")) {
				chain.doFilter(request2, response2);
			} else {
				//û�е�¼��,�ض��򵽵�¼ҳ��
				response2.sendRedirect(request2.getContextPath()+ 
						"/login-form.do"); 
			}
		}else {
			//�����¼�˾ͷŹ�
			chain.doFilter(request2, response2);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
