package cc.ichoice.log4j;

import cc.ichoice.util.ClassUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.net.URL;
import java.util.Properties;

/**
 * 默认log4j日志管理器
 * @author twg
 *
 */
public class DefaultLoggerFactory implements LoggerFactory {
	private static DefaultLoggerFactory defaultLoggerFactory = new DefaultLoggerFactory();
	private static final String DEFAULT_FILE_NAME = "log4j.properties";

	public DefaultLoggerFactory() {
		initLogger();
	}

	public static DefaultLoggerFactory getInstance() {
		if (null == defaultLoggerFactory)
			defaultLoggerFactory = new DefaultLoggerFactory();
		return defaultLoggerFactory;
	}

	public void initLogger() {
		URL url = ClassUtils.getDefaultClassLoader().getResource(DEFAULT_FILE_NAME);
		if(null == url){PropertyConfigurator.configure(getDefaultProperties());}else{PropertyConfigurator.configure(url);}
	}
	
	private Properties getDefaultProperties(){
		Properties prop = new Properties();
		prop.setProperty("log4j.rootLogger", "info,A1,R");
		prop.setProperty("log4j.appender.R.File", "../mini.log");
		prop.setProperty("log4j.appender.A1.Target", "System.out");
		prop.setProperty("log4j.appender.A1", "org.apache.log4j.ConsoleAppender");
		prop.setProperty("log4j.appender.A1.layout.ConversionPattern", "[%c]%m%n");
		prop.setProperty("log4j.appender.R.layout", "org.apache.log4j.PatternLayout");
		prop.setProperty("log4j.appender.A1.layout", "org.apache.log4j.PatternLayout");
		prop.setProperty("log4j.appender.R", "org.apache.log4j.DailyRollingFileAppender");
		prop.setProperty("log4j.appender.R.layout.ConversionPattern", "[%p][%d{yyyy-MM-dd HH:mm:ss,SSS}][%c]%m%n");
		return prop;
	}

	public Logger getLogger(String name) {
		return Logger.getLogger(name);
	}

	public Logger getLogger(Class<?> clazz) {
		return Logger.getLogger(clazz);
	}

}
