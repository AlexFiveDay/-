package mvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * 用于管理，URL与子控制器方法的映射方法
 * 如：
 * 	  /list.do -> Handle(controller,execute())
 * 	  /add.do -> Handle(controller,add())
 * @author 杨大龙
 *
 */
public class HandleMapping {
	private Map<String, Handle> mapping = new HashMap<String, Handle>();

	public HandleMapping() {
		super();
	}
	/**
	 * 将一个控制器类解析并且添加到map中
	 * 1、动态加载类到内存
	 * 2、找到全部的方法，并且查找方法上是否有RequestMapping 注解
	 * 3、如果有注解，就将注解URL，和控制器以及方法添加到map中
	 * @param className 一个控制器类名
	 * @throws Exception 
	 */
	public void parseController(String className) throws Exception {
		//加载类到内存中
		Class cls = Class.forName(className);
		//创建控制器对象
		Object controller = cls.newInstance();
		//找到全部方法
		Method[] methods = cls.getDeclaredMethods();
		for(Method method:methods) {
			//在方法上查找RequestMapping注解
			//在方法中获取  RequestMapping 类型(RequestMapping.class)的注解
			RequestMapping ann = method.getAnnotation(RequestMapping.class);
			//如果ann不为空，则方法上包含RequestMapping注解
			if (ann != null) {
				String url = ann.value();
				//将找到的方法添加到map中
				mapping.put(url, new Handle(controller, method));
			}
		}
	}
	
	@Override
	public String toString() {
		return "HandleMapping [mapping=" + mapping + "]";
	}
	//测试
	public static void main(String[] args) throws Exception {
		HandleMapping handleMapping = new HandleMapping();
		handleMapping.parseController("servlet11.text.Demo");
		System.out.println(handleMapping.mapping);
	}
	
	/**
	 * 根据请求路径找到Handle对象
	 * @param url
	 * @return
	 */
	public Handle get(String url) {
		return mapping.get(url);
	}
}
