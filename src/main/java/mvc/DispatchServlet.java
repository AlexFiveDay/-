package mvc;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 核心前端控制器，处理任何*.do请求
 * 前端控制器处理全部的web功能
 */
public class DispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandleMapping handleMapping;
	
	
	@Override
	public void init() throws ServletException {
		try {
			handleMapping = new HandleMapping();
			//handleMapping.parseController("mvc.Controller");
			SAXReader reader = new SAXReader();
			
			//读取servlet配置参数
			String file = getInitParameter("config");
			
			InputStream is = getClass().getClassLoader().getResourceAsStream(file);
			System.out.println("file"+file);
			Document doc = reader.read(is);
			Element root = doc.getRootElement();
			//找到全部的bean元素上的class属性
			List<Element> list = root.elements("bean");
			for (Element element : list) {
				//获取bean元素上的class属性
				String className = element.attributeValue("class");
				System.out.println("Controller："+className);
				//将得到的类名交给handlerMapping 
				handleMapping.parseController(className);
			}
			is.close();
			System.out.println(handleMapping);
		} catch (Exception e) {
			e.printStackTrace();
			//把我们产生的异常转化为servlet异常，能处理就处理，不能处理就跑出去
			throw new ServletException("控制器解析错误",e);
		}
	}


	/**
	 *  1. 重构service()方法，根据用户请求的URL执行对应的控制器方法
		2. 重构service()方法，支持重定向功能：
			1. 控制返回值以redirect:为开头是重定向功能
				- 如果返回http为开头的就是跳转到其他网站
				- 否则是本网站的地址，拼接 ContextPath以后再重定向。
			2. 其他情况认为是转发到JSP页面
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建子控制器
		//执行子控制器方法
		//根据子控制器的返回值，转发到JSP页面
		request.setCharacterEncoding("utf-8");//解决请求的编码问题
		try {
			
			//获取用户请求路径
			String  path = request.getRequestURI();
			System.out.println(path);
			String contextPath = request.getContextPath();
			System.out.println(contextPath);
			path = path.substring(contextPath.length());
			System.out.println(path);
			//根据请求的URL找到Handle
			Handle handle = handleMapping.get(path);
			System.out.println(handle);
			//执行控制器方法(利用反射执行方法)
			path = handle.execute(request);
			//创建子控制器
			//Controller controller = new Controller();
			//调用子控制器的方法(封装业务逻辑)
			//可以将jsp网页隐藏，防止用户直接访问jsp不显示数据,此处显示完整路径
			//String path = "/WEB-INF/jsp/"+controller.execute(request);
			
			
			if (path.startsWith("redirect:")) {
				//支持重定向功能
				path = path.substring("redirect:".length());
					if (path.startsWith("http")) {
						//如果是http开头就直接重定向
						response.sendRedirect(path);
					} else {
						//否则就拼接绝对路径
						//   /servlet13/list.do
						path = contextPath+path;
						response.sendRedirect(path);
					}
			} else {
				//否则就转发到jsp
				request.getRequestDispatcher("/WEB-INF/jsp/"+path+".jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * 作用1：设置content-type消息头的值(设置服务端返回的数据类型)
			 * 作用2：out.println在输出时会使用指定的字符集来编码
			 */
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("系统故障"+e.getMessage());
		}
	}
}
