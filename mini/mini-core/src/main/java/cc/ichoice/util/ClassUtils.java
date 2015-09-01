package cc.ichoice.util;

import cc.ichoice.log4j.DefaultLoggerFactory;

import java.net.URL;

/**
 * 类操作工具类
 * @author twg
 *
 */
public class ClassUtils {
	
	/**
     * 获取类加载器
     */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex) {
		}
		if (cl == null) {
			cl = ClassUtils.class.getClassLoader();
		}
		return cl;
	}
	
	/**
     * 获取类路径
     */
    public static String getClassPath() {
        String classpath = "";
        URL resource = getDefaultClassLoader().getResource("");
        if (resource != null) {
            classpath = resource.getPath();
        }
        return classpath;
    }
    
    /**
     * 加载类（将自动初始化）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }
    
    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getDefaultClassLoader());
        } catch (ClassNotFoundException e) {
            DefaultLoggerFactory.getInstance().getLogger(ClassUtils.class).error("加载类出错！", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

}
