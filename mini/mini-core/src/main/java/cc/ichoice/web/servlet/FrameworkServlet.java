package cc.ichoice.web.servlet;


import org.springframework.web.servlet.DispatcherServlet;

@SuppressWarnings("serial")
public abstract class FrameworkServlet extends DispatcherServlet {
	
	public void setContextConfigLocation(String contextConfigLocation) {
		super.setContextConfigLocation(contextConfigLocation);
	}

}
