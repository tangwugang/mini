package cc.ichoice.minidao.db.config;

import cc.ichoice.core.ClassHelper;
import cc.ichoice.minidao.db.annotation.Column;
import cc.ichoice.minidao.db.annotation.Entity;
import cc.ichoice.minidao.db.annotation.Table;
import cc.ichoice.minidao.db.annotation.Temporary;
import cc.ichoice.util.StringUtil;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取使用注解Entity实体
 * @author twg
 * 
 */
public class EntityHelper {
	/**
	 * 实体类 => 表名
	 */
	private static final Map<Class<?>, String> entityClassTableNameMap = new HashMap<Class<?>, String>();
	/**
	 * 实体类 => (字段名 => 列名)
	 */
	private static final Map<Class<?>, Map<String, String>> entityClassFieldMapMap = new LinkedHashMap<Class<?>, Map<String, String>>();

	static {
		// 获取并遍历所有使用注解Entity实体类
		List<Class<?>> entityClassList = ClassHelper
				.getClassListByAnnotation(Entity.class);
		for (Class<?> entityClass : entityClassList) {
			initEntityNameMap(entityClass);
			initEntityFieldMapMap(entityClass);
		}
	}

	private static void initEntityNameMap(Class<?> entityClass) {
		// 判断该实体类上是否存在 Table 注解
		String tableName;
		if (entityClass.isAnnotationPresent(Table.class)) {
			// 若已存在，则使用该注解中定义的表名
			tableName = entityClass.getAnnotation(Table.class).value();
		} else {
			// 若不存在，则将实体类名转换为下划线风格的表名
			tableName = StringUtil.camelhumpToUnderline(entityClass
					.getSimpleName());
		}
		entityClassTableNameMap.put(entityClass, tableName);
	}

	private static void initEntityFieldMapMap(Class<?> entityClass) {
		// 获取并遍历该实体类中所有的字段（不包括父类中的方法）
		Field[] fields = entityClass.getDeclaredFields();
		if (ArrayUtils.isNotEmpty(fields)) {
			// 创建一个 fieldMap（用于存放列名与字段名的映射关系）
			Map<String, String> fieldMap = new HashMap<String, String>();
			for (Field field : fields) {
				String fieldName = field.getName();
				String columnName;
				//判断该字段上是否不存在Temporary 注解
				if (!field.isAnnotationPresent(Temporary.class)) {
					// 判断该字段上是否存在 Column 注解
					if (field.isAnnotationPresent(Column.class)) {
						// 若已存在，则使用该注解中定义的列名
						columnName = field.getAnnotation(Column.class).value();
					} else {
						columnName = fieldName;
					}
					fieldMap.put(fieldName, columnName);
				}
			}
			entityClassFieldMapMap.put(entityClass, fieldMap);
		}
	}

	/**
	 * 实体类 => 表名
	 */
	public static String getTableName(Class<?> entityClass) {
		return entityClassTableNameMap.get(entityClass);
	}
	/**
	 * 实体类 => (字段名 => 列名)
	 */
	public static Map<String, String> getFieldMap(Class<?> entityClass) {
        return entityClassFieldMapMap.get(entityClass);
    }

}
