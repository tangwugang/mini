package cc.ichoice.web.servlet;

import cc.ichoice.log4j.DefaultLoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 前端控制器DispatcherServlet
 * @author twg
 *
 */
@SuppressWarnings("serial")
@WebServlet(name = "DispatcherServlet", urlPatterns = { "*.do" }, loadOnStartup = 0, initParams = { @WebInitParam(name = "contextConfigLocation", value = "classpath:spring-*.xml") })
public class DispatcherServlet extends FrameworkServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DefaultLoggerFactory.getInstance().getLogger(DispatcherServlet.class).info("servlet容器初始化......");
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.service(req, resp);
	}
	
	public void destroy() {
		super.destroy();
		DefaultLoggerFactory.getInstance().getLogger(DispatcherServlet.class).info("servlet容器销毁......");
	}
}
