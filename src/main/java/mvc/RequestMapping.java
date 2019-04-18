package mvc;
//import static静态导入
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) //注解一直保留到运行期
@Target(METHOD) //此注解只能写在方法上
public @interface RequestMapping {
	String value();
}
