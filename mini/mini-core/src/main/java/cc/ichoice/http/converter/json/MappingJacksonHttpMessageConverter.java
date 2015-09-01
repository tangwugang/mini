package cc.ichoice.http.converter.json;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * 处理JSON格式编码转换
 * @author twg
 * @see org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
 */
public class MappingJacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {
	private static List<MediaType> supportedMediaTypes = Collections.emptyList();

	static {
		supportedMediaTypes.add(new MediaType("text","html",DEFAULT_CHARSET));
		supportedMediaTypes.add(new MediaType("application", "json", DEFAULT_CHARSET));
	}
	
	MappingJacksonHttpMessageConverter(){
		setSupportedMediaTypes(supportedMediaTypes);
	}

	public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
		Assert.notEmpty(supportedMediaTypes, "'supportedMediaTypes' 不能为空");
		super.setSupportedMediaTypes(supportedMediaTypes);
	}

	protected Object readInternal(Class<? extends Object> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		return super.readInternal(clazz, inputMessage);
	}
}
