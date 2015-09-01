package cc.ichoice.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * 字符编码过滤器
 * @author twg
 *
 */
@WebFilter(filterName = "encodingFilter", urlPatterns = "/*", initParams = { @WebInitParam(name = "encoding", value = "UTF-8") })
public class CharacterEncodingFilter implements Filter {
    private static final Logger logger = Logger.getLogger(CharacterEncodingFilter.class);
	private String encoding;

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
        if(null == encoding){
            setEncoding("UTF-8");
        }
        logger.info("=======字符编码过滤器初始化=======");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (null != encoding && request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	public void destroy() {

	}

}
