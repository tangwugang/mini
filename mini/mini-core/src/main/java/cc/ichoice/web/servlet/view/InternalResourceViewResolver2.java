package cc.ichoice.web.servlet.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 模型视图名称的解析
 * @author twg
 * @see org.springframework.web.servlet.view.InternalResourceViewResolver
 */
@Component
public class InternalResourceViewResolver2 extends InternalResourceViewResolver{
	private static final String SUFFIX = ".jsp";
	private static final String PREFIX = "/webpage/";
	private static final String CONTENT_TYPE = "text/html";
	
	public InternalResourceViewResolver2(){
		setViewClass(JstlView.class);
		setPrefix(PREFIX);
		setSuffix(SUFFIX);
		setContentType(CONTENT_TYPE);
	}
	
	public void setContentType(String contentType) {
		super.setContentType(contentType);
	}

	public void setViewClass(Class viewClass) {
		super.setViewClass(viewClass);
	}

	public void setPrefix(String prefix) {
		super.setPrefix(prefix);
	}

	public void setSuffix(String suffix) {
		super.setSuffix(suffix);
	}

}
