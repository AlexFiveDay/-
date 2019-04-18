package mvc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.Award;
import entity.User;

public class Controller {
	/**
	 * 子控制器方法
	 * 
	 * @param request
	 *            用于在控制器和JSP直接传递数据
	 * @return 转发的目标页面
	 * @throws Exception
	 */

	@RequestMapping("/list.do")
	public String execute(HttpServletRequest request) throws Exception {
		UserDAO dao = new UserDAO();
		List<User> users = dao.findAll();
		// 将users数据传递到JSP
		request.setAttribute("users", users);
		// 可以将jsp网页隐藏，防止用户直接访问jsp不显示数据,此处显示完整路径
		return "listUser";
	}

	@RequestMapping("/add.do")
	public String add(HttpServletRequest request) {
		return "addUser";//转发到addUser.jsp
	}
	
	@RequestMapping("/save.do")
	public String save(HttpServletRequest request) throws Exception {
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		String email = request.getParameter("email");
		UserDAO dao = new UserDAO();
		User user = null;
		user = dao.find(username);
		System.out.println(user);
		if (user != null) {
			request.setAttribute("msg", "用户名已存在");
			return "addUser";//传到addUser.jsp
		}
		user = new User();
		user.setName(username);
		user.setPassword(pwd);
		user.setEmail(email);
		dao.save(user);
		/*if (username.equals("小明")) {
			return "redirect:/choujiang.do";
		}*/
		return "redirect:/login-form.do";//重定向至登录界面
	}
	
	@RequestMapping("/delete.do")
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		UserDAO dao = new UserDAO();
		dao.delete(id);
		return "redirect:/list.do";//重定向至list
	}
	
	@RequestMapping("/test.do")
	public String test(HttpServletRequest request) {
		//如果站内没找到数据，
		//就转到http://www.baidu.com
		return "redirect:http://www.baidu.com";//重定向至list
	}
	
	@RequestMapping("/choujiang.do")
	public String choujiang(HttpServletRequest request) throws SQLException {
		UserDAO dao = new UserDAO();
		List<Award> awards = dao.findAwards();
		System.out.println(awards.size());
		//定义奖励包含的类型数组
		List<String > awardType = new ArrayList<String>();
		//将建立列表中的奖励添加至奖励数组中
		for(Award award:awards) {
			//先判断奖励库存是否足够，若库存足够，则添加至奖励列表
			if (award.getPrize_amount()>0) {
				System.out.println(award.getPrize_name());
				awardType.add(award.getPrize_name());
			}
		}
		//奖励的种类数量
		int num = awardType.size();
		System.out.println("num:"+num);
		int m;
		for (int i = 0; i < 8; i++) {
			m = (int) (Math.random()*num);
			request.setAttribute("message"+i, awardType.get(m));
		}
		return "choujiang";//转发至抽奖页面choujiang.jsp
	}
	
	@RequestMapping("/handleChoujiang.do")
	public String handleChoujiang(HttpServletRequest request) throws Exception {
		//通过id获取中奖信息
		String awardName = request.getParameter("tip999");
		System.out.println("awardName:"+awardName);
		//通过session获取游客用户名
		HttpSession session = request.getSession(false);
		System.out.println("session:"+session);
		User user = (User) session.getAttribute("loginUser");
		String username = user.getName();
		System.out.println("username:"+username);
		UserDAO dao = new UserDAO();
		//查询游客获得的奖品信息
		Award award = dao.findAward(awardName);
		//将获奖数据更新至奖品表中
		dao.updateUser(username, award.getId());
		return "redirect:/over.do";//重定向至抽奖结束页面
	}
	
	@RequestMapping("/over.do")
	public String over(HttpServletRequest request) throws SQLException {
		UserDAO dao = new UserDAO();
		List<User> users = dao.findAll();
		// 将users数据传递到JSP
		request.setAttribute("users", users);
		return "over";//转发至抽奖页面choujiang.jsp
	}
	
	@RequestMapping("/handleListAward.do")
	public String listAward(HttpServletRequest request) throws Exception {
		UserDAO dao = new UserDAO();
		List<Award> awards = dao.findAwards();
		// 将awards数据传递到JSP
		request.setAttribute("awards", awards);
		// 可以将jsp网页隐藏，防止用户直接访问jsp不显示数据,此处显示完整路径
		return "listAward";//转发至jsp
	}
	
	@RequestMapping("/handleChangeAward.do")
	public String changeAward(HttpServletRequest request) throws Exception {
		return "changeAward";//转发至jsp
	}
	
	@RequestMapping("/saveChangeAward.do")
	public String saveChangeAward(HttpServletRequest request) throws Exception {
		UserDAO dao = new UserDAO();
		String prize_name;
		int prize_amount;
		
		prize_name = request.getParameter("prize_name1");
		prize_amount = Integer.parseInt(request.getParameter("prize_amount1"));
		dao.changeAward(prize_name, prize_amount, 1);
		
		prize_name = request.getParameter("prize_name2");
		prize_amount = Integer.parseInt(request.getParameter("prize_amount2"));
		dao.changeAward(prize_name, prize_amount, 2);
		
		prize_name = request.getParameter("prize_name3");
		prize_amount = Integer.parseInt(request.getParameter("prize_amount3"));
		dao.changeAward(prize_name, prize_amount, 3);
		
		prize_name = request.getParameter("prize_name4");
		prize_amount = Integer.parseInt(request.getParameter("prize_amount4"));
		dao.changeAward(prize_name, prize_amount, 4);
		
		prize_name = request.getParameter("prize_name5");
		prize_amount = Integer.parseInt(request.getParameter("prize_amount5"));
		dao.changeAward(prize_name, prize_amount, 5);
		return "redirect:/list.do";//重定向至管理员界面
	}
}
