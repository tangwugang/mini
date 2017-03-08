package com.sanweibook.lingdu.mysql.config;


import com.sanweibook.lingdu.util.NamespaceUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.Assert;
import org.w3c.dom.Element;

/**
 * Created by twg on 17/2/14.
 */
public class MySqlNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("datasource", new DataSourcePasar());
    }

    class DataSourcePasar extends AbstractSingleBeanDefinitionParser{

        private static final String DRIVER_CLASS_NAME = "driver-class-name";
        private static final String PASSWORD = "password";
        private static final String USERNAME = "username";
        private static final String URL = "url";

        @Override
        protected Class<?> getBeanClass(Element element) {
            return BasicDataSource.class;
        }

        @Override
        protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
            Assert.hasLength(element.getAttribute(DRIVER_CLASS_NAME),"driver-class-name not null");
            Assert.hasLength(element.getAttribute(PASSWORD),"password not null");
            Assert.hasLength(element.getAttribute(URL),"url not null");
            Assert.hasLength(element.getAttribute(USERNAME),"username not null");
            NamespaceUtil.setValueIfAttributeDefined(builder, element, DRIVER_CLASS_NAME);
            NamespaceUtil.setValueIfAttributeDefined(builder,element,PASSWORD);
            NamespaceUtil.setValueIfAttributeDefined(builder,element,USERNAME);
            NamespaceUtil.setValueIfAttributeDefined(builder,element,URL);
        }
    }
}
