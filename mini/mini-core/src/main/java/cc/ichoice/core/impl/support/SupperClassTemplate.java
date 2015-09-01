package cc.ichoice.core.impl.support;

/**
 * 用于获取子类的模板类
 * @author twg
 *
 */
public abstract class SupperClassTemplate extends ClassTemplate {
	
	protected final Class<?> superClass;
	
	protected SupperClassTemplate(String packageName, Class<?> superClass) {
		super(packageName);
		this.superClass = superClass;
	}


}
