package mvc;
/**
 * 处理者，处理器
 * @author 杨大龙
 *
 */

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class Handle {
	/**
	 * 子控制器对象
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
			//利用反射执行方法
			return (String)method.invoke(controller, request);
	}
}
