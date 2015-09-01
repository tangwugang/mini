package cc.ichoice.web.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.ContextLoaderListener;

/**
 * spring 监听器
 * @author twg
 * @see org.springframework.web.context.ContextLoaderListener
 */
@WebListener
public class ContextLoaderListener2 extends ContextLoaderListener {
	private static final String CONFIG_LOCATION_PARAM_VALUE= "classpath:spring-*.xml";
	
	public void contextInitialized(ServletContextEvent event) {
		event.getServletContext().setInitParameter(CONFIG_LOCATION_PARAM, CONFIG_LOCATION_PARAM_VALUE);
		super.contextInitialized(event);
	}
	

}
