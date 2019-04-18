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
	 * �ӿ���������
	 * 
	 * @param request
	 *            �����ڿ�������JSPֱ�Ӵ�������
	 * @return ת����Ŀ��ҳ��
	 * @throws Exception
	 */

	@RequestMapping("/list.do")
	public String execute(HttpServletRequest request) throws Exception {
		UserDAO dao = new UserDAO();
		List<User> users = dao.findAll();
		// ��users���ݴ��ݵ�JSP
		request.setAttribute("users", users);
		// ���Խ�jsp��ҳ���أ���ֹ�û�ֱ�ӷ���jsp����ʾ����,�˴���ʾ����·��
		return "listUser";
	}

	@RequestMapping("/add.do")
	public String add(HttpServletRequest request) {
		return "addUser";//ת����addUser.jsp
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
			request.setAttribute("msg", "�û����Ѵ���");
			return "addUser";//����addUser.jsp
		}
		user = new User();
		user.setName(username);
		user.setPassword(pwd);
		user.setEmail(email);
		dao.save(user);
		/*if (username.equals("С��")) {
			return "redirect:/choujiang.do";
		}*/
		return "redirect:/login-form.do";//�ض�������¼����
	}
	
	@RequestMapping("/delete.do")
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		UserDAO dao = new UserDAO();
		dao.delete(id);
		return "redirect:/list.do";//�ض�����list
	}
	
	@RequestMapping("/test.do")
	public String test(HttpServletRequest request) {
		//���վ��û�ҵ����ݣ�
		//��ת��http://www.baidu.com
		return "redirect:http://www.baidu.com";//�ض�����list
	}
	
	@RequestMapping("/choujiang.do")
	public String choujiang(HttpServletRequest request) throws SQLException {
		UserDAO dao = new UserDAO();
		List<Award> awards = dao.findAwards();
		System.out.println(awards.size());
		//���影����������������
		List<String > awardType = new ArrayList<String>();
		//�������б��еĽ������������������
		for(Award award:awards) {
			//���жϽ�������Ƿ��㹻��������㹻��������������б�
			if (award.getPrize_amount()>0) {
				System.out.println(award.getPrize_name());
				awardType.add(award.getPrize_name());
			}
		}
		//��������������
		int num = awardType.size();
		System.out.println("num:"+num);
		int m;
		for (int i = 0; i < 8; i++) {
			m = (int) (Math.random()*num);
			request.setAttribute("message"+i, awardType.get(m));
		}
		return "choujiang";//ת�����齱ҳ��choujiang.jsp
	}
	
	@RequestMapping("/handleChoujiang.do")
	public String handleChoujiang(HttpServletRequest request) throws Exception {
		//ͨ��id��ȡ�н���Ϣ
		String awardName = request.getParameter("tip999");
		System.out.println("awardName:"+awardName);
		//ͨ��session��ȡ�ο��û���
		HttpSession session = request.getSession(false);
		System.out.println("session:"+session);
		User user = (User) session.getAttribute("loginUser");
		String username = user.getName();
		System.out.println("username:"+username);
		UserDAO dao = new UserDAO();
		//��ѯ�οͻ�õĽ�Ʒ��Ϣ
		Award award = dao.findAward(awardName);
		//�������ݸ�������Ʒ����
		dao.updateUser(username, award.getId());
		return "redirect:/over.do";//�ض������齱����ҳ��
	}
	
	@RequestMapping("/over.do")
	public String over(HttpServletRequest request) throws SQLException {
		UserDAO dao = new UserDAO();
		List<User> users = dao.findAll();
		// ��users���ݴ��ݵ�JSP
		request.setAttribute("users", users);
		return "over";//ת�����齱ҳ��choujiang.jsp
	}
	
	@RequestMapping("/handleListAward.do")
	public String listAward(HttpServletRequest request) throws Exception {
		UserDAO dao = new UserDAO();
		List<Award> awards = dao.findAwards();
		// ��awards���ݴ��ݵ�JSP
		request.setAttribute("awards", awards);
		// ���Խ�jsp��ҳ���أ���ֹ�û�ֱ�ӷ���jsp����ʾ����,�˴���ʾ����·��
		return "listAward";//ת����jsp
	}
	
	@RequestMapping("/handleChangeAward.do")
	public String changeAward(HttpServletRequest request) throws Exception {
		return "changeAward";//ת����jsp
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
		return "redirect:/list.do";//�ض���������Ա����
	}
}
