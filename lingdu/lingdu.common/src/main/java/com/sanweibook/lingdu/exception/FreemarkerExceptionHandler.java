package com.sanweibook.lingdu.exception;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by twg on 16/9/30.
 */
@Slf4j
public class FreemarkerExceptionHandler implements TemplateExceptionHandler {
    @Override
    public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
        try {
            out.write("模板错误 :["+te.getMessage()+"]");
            log.error("freemarkerTemplate error : {}", te.getMessage());
        } catch (IOException e) {
            log.error("freemarkerTemplate 处理异常，异常信息：",e);
            throw new TemplateException(e,env);
        }
    }
}
