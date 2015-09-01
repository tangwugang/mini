package cc.ichoice.util;

import cc.ichoice.log4j.DefaultLoggerFactory;

/**
 * 对象操作工具类
 * @author twg
 *
 */
public class ObjectUtil {
	
	/**
	 * 通过反射创建实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className) {
        T instance;
        try {
            Class<?> commandClass = ClassUtils.loadClass(className);
            instance = (T) commandClass.newInstance();
        } catch (Exception e) {
            DefaultLoggerFactory.getInstance().getLogger(ObjectUtil.class).error("创建实例出错！", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

}
