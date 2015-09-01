package cc.ichoice.util;

import cc.ichoice.log4j.DefaultLoggerFactory;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties文件工具类
 * @author twg
 *
 */
public class PropertiesUtil {
	private static final String DEFAULT_FILE_NAME = "mini.properties";
	
	/**
	 * 加载默认mini.properties属性文件
	 * @return
	 */
	public static Properties loadDefaultProperties(){
		InputStream is = null;
		Properties properties = new Properties();
		try {
			is = ClassUtils.getDefaultClassLoader().getResourceAsStream(DEFAULT_FILE_NAME);
			if (is != null) {
				properties.load(is);
	        }else{
	        	throw new Exception("加载"+DEFAULT_FILE_NAME+"属性文件失败！");
	        }
		} catch (Exception e) {
			DefaultLoggerFactory.getInstance().getLogger(PropertiesUtil.class).error("加载属性文件出错！", e);
			throw new RuntimeException(e);
		}finally{
			try {
				if(is != null){
					is.close();
				}
			} catch (IOException e) {
				DefaultLoggerFactory.getInstance().getLogger(PropertiesUtil.class).error("释放资源出错！", e);
			}
		}
		return properties;
	}
	
	/**
	 * 加载指定属性文件
	 * @param propsPath 文件路径
	 * @return
	 */
	public static Properties loadDefaultProperties(String propsPath){
        InputStream is = null;
        Properties properties = new Properties();
        try {
        	if (StringUtils.isEmpty(propsPath)) {
                throw new RuntimeException("指定属性文件不存在！");
            }
            String suffix = ".properties";
            if (propsPath.lastIndexOf(suffix) == -1) {
                propsPath += suffix;
            }
            is = ClassUtils.getDefaultClassLoader().getResourceAsStream(propsPath);
            if (is != null) {
            	properties.load(is);
            }
			
		} catch (Exception e) {
			DefaultLoggerFactory.getInstance().getLogger(PropertiesUtil.class).error("加载指定属性文件出错！", e);
			throw new RuntimeException(e);
		}finally{
			try {
				if(is != null){
					is.close();
				}
			} catch (IOException e) {
				DefaultLoggerFactory.getInstance().getLogger(PropertiesUtil.class).error("释放资源出错！", e);
			}
		}
		return properties;
	}
	
	
	/**
	 * 获取键值
	 * @param key
	 * @return
	 */
	public static String getConfig(Properties properties,String key){
		return properties.getProperty(key);
	}
	/**
	 * 获取键值 (可指定默认值)
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getConfig(Properties properties,String key,String defaultValue){
		return properties.getProperty(key, defaultValue);
	}
	
}
