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
 * ����ǰ�˿������������κ�*.do����
 * ǰ�˿���������ȫ����web����
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
			
			//��ȡservlet���ò���
			String file = getInitParameter("config");
			
			InputStream is = getClass().getClassLoader().getResourceAsStream(file);
			System.out.println("file"+file);
			Document doc = reader.read(is);
			Element root = doc.getRootElement();
			//�ҵ�ȫ����beanԪ���ϵ�class����
			List<Element> list = root.elements("bean");
			for (Element element : list) {
				//��ȡbeanԪ���ϵ�class����
				String className = element.attributeValue("class");
				System.out.println("Controller��"+className);
				//���õ�����������handlerMapping 
				handleMapping.parseController(className);
			}
			is.close();
			System.out.println(handleMapping);
		} catch (Exception e) {
			e.printStackTrace();
			//�����ǲ������쳣ת��Ϊservlet�쳣���ܴ���ʹ������ܴ�����ܳ�ȥ
			throw new ServletException("��������������",e);
		}
	}


	/**
	 *  1. �ع�service()�����������û������URLִ�ж�Ӧ�Ŀ���������
		2. �ع�service()������֧���ض����ܣ�
			1. ���Ʒ���ֵ��redirect:Ϊ��ͷ���ض�����
				- �������httpΪ��ͷ�ľ�����ת��������վ
				- �����Ǳ���վ�ĵ�ַ��ƴ�� ContextPath�Ժ����ض���
			2. ���������Ϊ��ת����JSPҳ��
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����ӿ�����
		//ִ���ӿ���������
		//�����ӿ������ķ���ֵ��ת����JSPҳ��
		request.setCharacterEncoding("utf-8");//�������ı�������
		try {
			
			//��ȡ�û�����·��
			String  path = request.getRequestURI();
			System.out.println(path);
			String contextPath = request.getContextPath();
			System.out.println(contextPath);
			path = path.substring(contextPath.length());
			System.out.println(path);
			//���������URL�ҵ�Handle
			Handle handle = handleMapping.get(path);
			System.out.println(handle);
			//ִ�п���������(���÷���ִ�з���)
			path = handle.execute(request);
			//�����ӿ�����
			//Controller controller = new Controller();
			//�����ӿ������ķ���(��װҵ���߼�)
			//���Խ�jsp��ҳ���أ���ֹ�û�ֱ�ӷ���jsp����ʾ����,�˴���ʾ����·��
			//String path = "/WEB-INF/jsp/"+controller.execute(request);
			
			
			if (path.startsWith("redirect:")) {
				//֧���ض�����
				path = path.substring("redirect:".length());
					if (path.startsWith("http")) {
						//�����http��ͷ��ֱ���ض���
						response.sendRedirect(path);
					} else {
						//�����ƴ�Ӿ���·��
						//   /servlet13/list.do
						path = contextPath+path;
						response.sendRedirect(path);
					}
			} else {
				//�����ת����jsp
				request.getRequestDispatcher("/WEB-INF/jsp/"+path+".jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * ����1������content-type��Ϣͷ��ֵ(���÷���˷��ص���������)
			 * ����2��out.println�����ʱ��ʹ��ָ�����ַ���������
			 */
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("ϵͳ����"+e.getMessage());
		}
	}
}
