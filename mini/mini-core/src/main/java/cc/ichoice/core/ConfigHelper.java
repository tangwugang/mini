package cc.ichoice.core;
import java.util.Properties;
import cc.ichoice.util.PropertiesUtil;


/**
 * 获取属性文件中的属性值
 * @author twg
 *
 */
public class ConfigHelper {
	
	/**
     * 属性文件对象
     */
    private static final Properties configProps = PropertiesUtil.loadDefaultProperties();
    
    /**
     * 获取 String 类型的属性值
     */
    public static String getString(String key) {
        return PropertiesUtil.getConfig(configProps, key);
    }

    /**
     * 获取 String 类型的属性值（可指定默认值）
     */
    public static String getString(String key, String defaultValue) {
        return PropertiesUtil.getConfig(configProps, key, defaultValue);
    }
}
