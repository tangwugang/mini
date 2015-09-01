package cc.ichoice.core.impl;

import java.lang.annotation.Annotation;
import java.util.List;

import cc.ichoice.core.ClassScanner;
import cc.ichoice.core.impl.support.AnnotationClassTemplate;
import cc.ichoice.core.impl.support.ClassTemplate;
import cc.ichoice.core.impl.support.SupperClassTemplate;

/**
 * 默认类扫描器
 * @author twg
 *
 */
public class DefaultClassScanner implements ClassScanner {

	public List<Class<?>> getClassList(String packageName) {
		return new ClassTemplate(packageName) {
            public boolean checkAddClass(Class<?> cls) {
                String className = cls.getName();
                String pkgName = className.substring(0, className.lastIndexOf("."));
                return pkgName.startsWith(packageName);
            }
        }.getClassList();
	}

	public List<Class<?>> getClassListByAnnotation(String packageName,
			Class<? extends Annotation> annotationClass) {
		return new AnnotationClassTemplate(packageName, annotationClass) {
            public boolean checkAddClass(Class<?> cls) {
                return cls.isAnnotationPresent(annotationClass);
            }
        }.getClassList();
	}

	public List<Class<?>> getClassListBySuper(String packageName,
			Class<?> superClass) {
		return new SupperClassTemplate(packageName, superClass) {
            public boolean checkAddClass(Class<?> cls) {
                return superClass.isAssignableFrom(cls) && !superClass.equals(cls);
            }
        }.getClassList();
	}

}
