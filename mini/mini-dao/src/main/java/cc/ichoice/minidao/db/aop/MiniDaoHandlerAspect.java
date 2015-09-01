package cc.ichoice.minidao.db.aop;

import cc.ichoice.minidao.db.MiniDao;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class MiniDaoHandlerAspect{
	@Autowired
	private MiniDao miniDao;
	
	
	public void doBefore(JoinPoint jp){
		try {
			String temp = jp.getStaticPart().toShortString();
			String longTemp = jp.getStaticPart().toLongString();
			jp.getStaticPart().toString();
			String classType = jp.getTarget().getClass().getName();
			String methodName = temp.substring(10, temp.length() - 1);
			Class<?> className = Class.forName(classType);
			Class[] args = new Class[jp.getArgs().length];
			String[] sArgs = (longTemp.substring(longTemp.lastIndexOf("(") + 1,
					longTemp.length() - 2)).split(",");
			for (int i = 0; i < args.length; i++) {
				if (sArgs[i].endsWith("String[]")) {
					args[i] = Array.newInstance(Class.forName("java.lang.String"),
							1).getClass();
				} else if (sArgs[i].endsWith("Long[]")) {
					args[i] = Array.newInstance(Class.forName("java.lang.Long"), 1)
							.getClass();
				} else if (sArgs[i].indexOf(".") == -1) {
					if (sArgs[i].equals("int")) {
						args[i] = int.class;
					} else if (sArgs[i].equals("char")) {
						args[i] = char.class;
					} else if (sArgs[i].equals("float")) {
						args[i] = float.class;
					} else if (sArgs[i].equals("long")) {
						args[i] = long.class;
					}
				} else {
					args[i] = Class.forName(sArgs[i]);
				}
			}
			Method method = className.getMethod(
					methodName.substring(methodName.indexOf(".") + 1,
							methodName.indexOf("(")), args);
			
		} catch (Exception e) {
		}
		
	}
	
	
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Method method = methodInvocation.getMethod();
		Object[] args = methodInvocation.getArguments();
		//返回结果
		Object returnObj = null;
		//SQL模板
		String templateSql = null;
		//SQL模板参数
		Map<String, Object> sqlParamsMap = new HashMap<String, Object>();
		System.out.println("MiniDaoHandler.invoke() method ==="+method.getName());
		methodInvocation.proceed();
		return null;
	}

}
