package mvc;
//import static��̬����
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) //ע��һֱ������������
@Target(METHOD) //��ע��ֻ��д�ڷ�����
public @interface RequestMapping {
	String value();
}
