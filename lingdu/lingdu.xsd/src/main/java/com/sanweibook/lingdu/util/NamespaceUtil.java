package com.sanweibook.lingdu.util;

import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by twg on 17/2/14.
 */
public class NamespaceUtil {
    public static boolean setValueIfAttributeDefined(BeanDefinitionBuilder builder, Element element,
                                                     String attributeName) {
        return setValueIfAttributeDefined(builder, element, attributeName,
                Conventions.attributeNameToPropertyName(attributeName));
    }

    public static boolean setValueIfAttributeDefined(BeanDefinitionBuilder builder, Element element,
                                                     String attributeName, String propertyName) {
        String attributeValue = element.getAttribute(attributeName);
        if (StringUtils.hasText(attributeValue)) {
            builder.addPropertyValue(propertyName, new TypedStringValue(attributeValue));
            return true;
        }
        return false;
    }
}
