package mvc;

import javax.servlet.http.HttpServletRequest;

import dao.UserDAO;
import entity.User;

public class LoginController {
	@RequestMapping("/login-form.do")
	public String form(HttpServletRequest request) {
		return "login";//转发至login.jsp页面
	}
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request) throws Exception {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		UserDAO dao = new UserDAO();
		User user = dao.find(userName);
		if (user!=null) {
			if (!user.getPassword().equals(passWord)) {
				request.setAttribute("msg2", "密码输入错误");
			}else {
				//登陆成功将信息保存至session中
				request.getSession().setAttribute(
						"loginUser", user);
				if (userName.equals("小明")) {
					return "redirect:/list.do";//登录成功，管理员重定向至choujiang.do
				} else {
					//判断游客是否已经抽过奖
					System.out.println(user);
					int price_num = user.getPrize_id();
					System.out.println(user.getName()+"中了"+user.getPrize_id()+"等奖");
					if (price_num!=0) {
						return "redirect:/over.do";
					}
					return "redirect:/choujiang.do";//登录成功，游客重定向至抽奖.do
				}
				
			}
		} else {
			request.setAttribute("msg", "用户名不存在");
		}
		return "login";//登陆失败，转发回login.jsp
	}
}
