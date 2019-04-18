package mvc;
/**
 * �����ߣ�������
 * @author �����
 *
 */

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class Handle {
	/**
	 * �ӿ���������
	 * @return
	 */
	private Object controller;
	private Method method;
	public Handle() {
		
	}
	public Handle (Object controller,Method method) {
		this.controller = controller;
		this.method = method;
	}
	@Override
	public String toString() {
		return "Handle [controller=" + controller + ", method=" + method + "]";
	}
	public String execute(HttpServletRequest request) throws Exception{
			//���÷���ִ�з���
			return (String)method.invoke(controller, request);
	}
}
