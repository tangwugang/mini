package cc.ichoice.util;

import cc.ichoice.constant.FrameworkConstant;
import cc.ichoice.core.ConfigHelper;
import cc.ichoice.log4j.DefaultLoggerFactory;
import org.apache.commons.lang.math.NumberUtils;

import java.lang.reflect.Field;

/**
 * 反射工具类
 * @author twg
 *
 */
public class ClassReflectUtil {
	
	/**
	 * 获取对象属性值
	 */
	public static Object getFieldValue(Object obj,String filename) throws Exception{
		//反射出类型
		Class<?> cls = obj.getClass();
		Field field = null;
		//反射出类型字段
		 try {
			 try {
				 field = cls.getDeclaredField(filename);
			} catch (Exception e) {
				cls = cls.getSuperclass();
				field = cls.getDeclaredField(filename);
			}
		   } catch (Exception e) {
			   DefaultLoggerFactory.getInstance().getLogger(ClassReflectUtil.class).error("没有这个字段："+filename);
			   throw new RuntimeException(e);
		   }
		//获取属性时，压制Java对访问修饰符的检查 
		field.setAccessible(true);
		//在对象obj上读取field属性的值
		Object val = field.get(obj);
		if(field.getType().getSimpleName().toLowerCase().equals("string") &&
				filename.equals(ConfigHelper.getString("PK_NAME", FrameworkConstant.PK_NAME)) && null == val){
			field.set(obj, UUIDGenerator.generate());
		}
		Object v = field.get(obj);
		field.setAccessible(false);
		return v;
	}
	/**
	 * 添加对象属性值
	 */
	public static Object setFieldValue(Object obj,String filename,Object value) throws Exception{
		//反射出类型
		Class<?> cls = obj.getClass();
		Field field = null;
		//反射出类型字段
		 try {
			 try {
				 field = cls.getDeclaredField(filename);
			} catch (Exception e) {
				cls = cls.getSuperclass();
				field = cls.getDeclaredField(filename);
			}
		   } catch (Exception e) {
			   e.printStackTrace();
			   System.out.println("没有这个字段："+filename);
		   }
		if(field==null){
			return null;
		}
		//获取属性时，压制Java对访问修饰符的检查 
		field.setAccessible(true);
		
		if(null != value && field.getType().getSimpleName().toLowerCase().equals("short")){
			field.set(obj, NumberUtils.toShort(String.valueOf(value)));
		}else if(null != value && field.getType().getSimpleName().toLowerCase().equals("int")){
			field.set(obj, NumberUtils.toInt(String.valueOf(value)));
		}else if(null != value && field.getType().getSimpleName().toLowerCase().equals("integer")){
			field.set(obj, NumberUtils.createInteger(String.valueOf(value)));
		}else if(null != value && field.getType().getSimpleName().toLowerCase().equals("double")){
			field.set(obj, NumberUtils.createDouble(String.valueOf(value)));
		}else if(null != value && field.getType().getSimpleName().toLowerCase().equals("float")){
			field.set(obj, NumberUtils.createFloat(String.valueOf(value)));
		}else if(null != value && field.getType().getSimpleName().toLowerCase().equals("long")){
			field.set(obj, NumberUtils.createLong(String.valueOf(value)));
		}else{
			field.set(obj, value);
		}
		return obj;
	}

}
