package cc.ichoice.web.multipart;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 多文件上传下载解析器
 * @author twg
 * @see org.springframework.web.multipart.commons.CommonsMultipartResolver
 */
@Component
public class CommonsMultipartResolver2 extends CommonsMultipartResolver {
	private static final String DEFAULT_ENCODEING = "UTF-8";
	private static final int MAX_UPLOAD_SIZE = 104857600;
	private static final int MAX_IN_MEMORY_SIZE = 4096;
	
	
	CommonsMultipartResolver2(){
		setDefaultEncoding(DEFAULT_ENCODEING);
		setMaxUploadSize(MAX_UPLOAD_SIZE);
		setMaxInMemorySize(MAX_IN_MEMORY_SIZE);
	}
	
	
	public void setDefaultEncoding(String defaultEncoding) {
		super.setDefaultEncoding(defaultEncoding);
	}
	
	public void setMaxUploadSize(long maxUploadSize) {
		super.setMaxUploadSize(maxUploadSize);
	}
	
	public void setMaxInMemorySize(int maxInMemorySize) {
		super.setMaxInMemorySize(maxInMemorySize);
	}

}
