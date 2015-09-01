package cc.ichoice.listener;


import cc.ichoice.log4j.DefaultLoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 容器监听器
 * @author twg
 *
 */
@WebListener
public class ApplicationStartupListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
		DefaultLoggerFactory.getInstance().getLogger(ApplicationStartupListener.class).info("======容器监听器初始化======");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		DefaultLoggerFactory.getInstance().getLogger(ApplicationStartupListener.class).info("======容器监听器销毁======");
	}

}
