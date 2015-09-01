package cc.ichoice.util;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by twg on 15/8/20.
 * freemarker工具类
 */
public class FreemarkerUtil {

    /**
     * 渲染模板
     * @param template freemarker模板
     * @param model 数据
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String renderTemplate(Template template, Object model) throws IOException, TemplateException {
        StringWriter result = new StringWriter();
        template.process(model, result);
        return result.toString();
    }
}
