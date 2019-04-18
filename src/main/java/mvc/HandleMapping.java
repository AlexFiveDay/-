package mvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * ���ڹ���URL���ӿ�����������ӳ�䷽��
 * �磺
 * 	  /list.do -> Handle(controller,execute())
 * 	  /add.do -> Handle(controller,add())
 * @author �����
 *
 */
public class HandleMapping {
	private Map<String, Handle> mapping = new HashMap<String, Handle>();

	public HandleMapping() {
		super();
	}
	/**
	 * ��һ�������������������ӵ�map��
	 * 1����̬�����ൽ�ڴ�
	 * 2���ҵ�ȫ���ķ��������Ҳ��ҷ������Ƿ���RequestMapping ע��
	 * 3�������ע�⣬�ͽ�ע��URL���Ϳ������Լ�������ӵ�map��
	 * @param className һ������������
	 * @throws Exception 
	 */
	public void parseController(String className) throws Exception {
		//�����ൽ�ڴ���
		Class cls = Class.forName(className);
		//��������������
		Object controller = cls.newInstance();
		//�ҵ�ȫ������
		Method[] methods = cls.getDeclaredMethods();
		for(Method method:methods) {
			//�ڷ����ϲ���RequestMappingע��
			//�ڷ����л�ȡ  RequestMapping ����(RequestMapping.class)��ע��
			RequestMapping ann = method.getAnnotation(RequestMapping.class);
			//���ann��Ϊ�գ��򷽷��ϰ���RequestMappingע��
			if (ann != null) {
				String url = ann.value();
				//���ҵ��ķ�����ӵ�map��
				mapping.put(url, new Handle(controller, method));
			}
		}
	}
	
	@Override
	public String toString() {
		return "HandleMapping [mapping=" + mapping + "]";
	}
	//����
	public static void main(String[] args) throws Exception {
		HandleMapping handleMapping = new HandleMapping();
		handleMapping.parseController("servlet11.text.Demo");
		System.out.println(handleMapping.mapping);
	}
	
	/**
	 * ��������·���ҵ�Handle����
	 * @param url
	 * @return
	 */
	public Handle get(String url) {
		return mapping.get(url);
	}
}
