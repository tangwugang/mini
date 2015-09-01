package cc.ichoice.log4j;

import org.apache.log4j.Logger;

/**
 * 日志工厂
 * @author twg
 *
 */
public interface LoggerFactory {
	
	Logger getLogger(String name);
	
	Logger getLogger(Class<?> clazz);
}
