package cc.ichoice.annotation;

import org.springframework.util.Assert;

import java.lang.annotation.Annotation;

/**
 * annotation工具类
 * @author twg
 */
public abstract class AnnotationUtils {
	/**
	 * 获取带注解类型的注解
	 * @param clazz 使用annotation注解的类
	 * @param annotationType 注解类型
	 * @return
	 */
	public static <A extends Annotation> A findAnnotation(Class<?> clazz, Class<A> annotationType) {
		Assert.notNull(clazz, "Class 不能为 null");
		A annotation = clazz.getAnnotation(annotationType);
		if (annotation != null) {
			return annotation;
		}
		for (Class<?> ifc : clazz.getInterfaces()) {
			annotation = findAnnotation(ifc, annotationType);
			if (annotation != null) {
				return annotation;
			}
		}
		if (!Annotation.class.isAssignableFrom(clazz)) {
			for (Annotation ann : clazz.getAnnotations()) {
				annotation = findAnnotation(ann.annotationType(), annotationType);
				if (annotation != null) {
					return annotation;
				}
			}
		}
		Class<?> superClass = clazz.getSuperclass();
		if (superClass == null || superClass == Object.class) {
			return null;
		}
		return findAnnotation(superClass, annotationType);
	}

}
