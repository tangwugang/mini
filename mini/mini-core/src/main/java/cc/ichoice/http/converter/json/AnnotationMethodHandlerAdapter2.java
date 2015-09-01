package cc.ichoice.http.converter.json;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * 
 * @author twg
 * @see org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter
 */
public class AnnotationMethodHandlerAdapter2 extends AnnotationMethodHandlerAdapter {
	
	private HttpMessageConverter<?>[] messageConverters ;
	
	static{
		new AnnotationMethodHandlerAdapter2();
	}
	
	AnnotationMethodHandlerAdapter2(){
		messageConverters = new HttpMessageConverter[] { new MappingJacksonHttpMessageConverter() };
		setMessageConverters(messageConverters);
	}

	public void setMessageConverters(HttpMessageConverter<?>[] messageConverters) {
		Assert.isNull(messageConverters, "消息转换器HttpMessageConverter 不能为空 ");
		super.setMessageConverters(messageConverters);
	}

}
