package mvc;

import javax.servlet.http.HttpServletRequest;

import dao.UserDAO;
import entity.User;

public class LoginController {
	@RequestMapping("/login-form.do")
	public String form(HttpServletRequest request) {
		return "login";//ת����login.jspҳ��
	}
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request) throws Exception {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		UserDAO dao = new UserDAO();
		User user = dao.find(userName);
		if (user!=null) {
			if (!user.getPassword().equals(passWord)) {
				request.setAttribute("msg2", "�����������");
			}else {
				//��½�ɹ�����Ϣ������session��
				request.getSession().setAttribute(
						"loginUser", user);
				if (userName.equals("С��")) {
					return "redirect:/list.do";//��¼�ɹ�������Ա�ض�����choujiang.do
				} else {
					//�ж��ο��Ƿ��Ѿ������
					System.out.println(user);
					int price_num = user.getPrize_id();
					System.out.println(user.getName()+"����"+user.getPrize_id()+"�Ƚ�");
					if (price_num!=0) {
						return "redirect:/over.do";
					}
					return "redirect:/choujiang.do";//��¼�ɹ����ο��ض������齱.do
				}
				
			}
		} else {
			request.setAttribute("msg", "�û���������");
		}
		return "login";//��½ʧ�ܣ�ת����login.jsp
	}
}
