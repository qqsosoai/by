package util;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
/**
 * 静态工厂
 * @author hasee
 *
 */
public class BeanFactory {
	private static Logger logger=Logger.getLogger(BeanFactory.class);
	/**
	 * 根据类型匹配
	 * @param bean 需要赋值的对象
	 */
	public static void factoryByType(Object bean){
		Class<? extends Object> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (!field.getType().getSimpleName().equals("Logger")) {
				field.setAccessible(true);
				Object object = null;
				try {
					String name = field.getType().getName();
					logger.debug(name);
					object = Class.forName(name + "Impl")
							.newInstance();
					field.set(bean, object);
				} catch (IllegalArgumentException e) {
					logger.error("属性" + field.getName() + "的类型为"
							+ field.getType().getName() + "与属性值不是一个类型，属性值类型为:"
							+ object.getClass().getName());
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					logger.error(field.getType().getName() + "Impl类型没有找到");
					e.printStackTrace();
				}
			}
		}
	}
	public static void daoFactoryByName(){
		
	}
	
}
