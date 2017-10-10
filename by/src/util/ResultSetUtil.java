package util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dll on 2017/8/14.
 *
 */
public class ResultSetUtil {
    /**
     *
     * @param rs  从数据取出来的结果集
     * @param clazz T类型
     * @param <T>
     * @return  T类型的集合
     */
    public static <T> List<T> eachResultSet(ResultSet rs,Class<T> clazz){
        List<T> list=new ArrayList<>();
        //循环ResultSet 01.获取对象  02.循环属性赋值 03.放进集合
        try {
            T t=null;
            while (rs.next()){
                t=clazz.newInstance();//实例化对象
                Field[] fields = clazz.getDeclaredFields();//获取实体类所有属性  返回field数组
                for (Field field : fields) {
                    field.setAccessible(true);//可以访问私有属性并赋值
                    if(isColumn(rs,field.getName())){
                        String method=getMethod(field.getName());
                        try {
                            t.getClass().getMethod(method,field.getType()).invoke(t,rs.getObject(field.getName()));
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
                list.add(t);//放进集合
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 从rs中拿数据封装为多对一
     * @param rs
     * @param clazz
     * @param cascade
     * @param cascadeId
     * @return
     */
    public static <T>T resultManyToOne(ResultSet rs,Class<T> clazz,String cascade,String cascadeId){
    	T t=null;
    	try {
			if (rs.next()) {
				t=clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
			        field.setAccessible(true);//可以访问私有属性并赋值
			        if(isColumn(rs,field.getName())){//判断rs是否有该字段
			            String method = getMethod(field.getName());//获取级联属性set方法
			            Object value = rs.getObject(field.getName());
			            try {
			            	if (field.getName().equals(cascade)) {//级联属性
								Class<?> type = field.getType();
								Object cascadeMate = type.newInstance();
								Field[] fields2 = type.getDeclaredFields();
								for (Field field2 : fields2) {
									if (field2.getName().equals(cascadeId)) {
										String method2 = getMethod(field2.getName());
										cascadeMate.getClass().getMethod(method2, field2.getType()).invoke(cascadeMate, value);
									}
								}
								value=cascadeMate;
							}
							t.getClass().getMethod(method,field.getType()).invoke(t,value);
			            } catch (InvocationTargetException e) {
			                e.printStackTrace();
			            } catch (NoSuchMethodException e) {
			                e.printStackTrace();
			            }
			        }
			    }
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return t;
    }
    /**
     * 从rs中拿数据封装为多对一
     * @param rs
     * @param clazz
     * @param cascade
     * @param cascadeId
     * @return
     */
    public static <T>List<T> resultManyToOneLIst(ResultSet rs,Class<T> clazz,String cascade,String cascadeId){
    	List<T> list=new ArrayList<>();
    	try {
    		while(rs.next()) {
    			T t=clazz.newInstance();
    			Field[] fields = clazz.getDeclaredFields();
    			for (Field field : fields) {
    				field.setAccessible(true);//可以访问私有属性并赋值
    				if(isColumn(rs,field.getName())){//判断rs是否有该字段
    					String method = getMethod(field.getName());//获取级联属性set方法
    					Object value = rs.getObject(field.getName());
    					try {
    						if (field.getName().equals(cascade)) {//级联属性
    							Class<?> type = field.getType();
    							Object cascadeMate = type.newInstance();
    							Field[] fields2 = type.getDeclaredFields();
    							for (Field field2 : fields2) {
    								if (field2.getName().equals(cascadeId)) {
    									String method2 = getMethod(field2.getName());
    									cascadeMate.getClass().getMethod(method2, field2.getType()).invoke(cascadeMate, value);
    								}
    							}
    							value=cascadeMate;
    						}
    						t.getClass().getMethod(method,field.getType()).invoke(t,value);
    					} catch (InvocationTargetException e) {
    						e.printStackTrace();
    					} catch (NoSuchMethodException e) {
    						e.printStackTrace();
    					}
    				}
    			}
    			list.add(t);
    		}
    	} catch (InstantiationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IllegalAccessException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (SecurityException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IllegalArgumentException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public static <T>T resultSet(ResultSet rs,Class<T> clazz){
        T t=null;
        try {
            if (rs.next()==false){
                return null;
            }
            t=clazz.newInstance();//实例化对象
            Field[] fields = clazz.getDeclaredFields();//获取实体类所有属性  返回field数组
            for (Field field : fields) {
                field.setAccessible(true);//可以访问私有属性并赋值
                if(isColumn(rs,field.getName())==false){
                    continue;
                }
                String method = getMethod(field.getName());
                try {
                    t.getClass().getMethod(method,field.getType()).invoke(t,rs.getObject(field.getName()));
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return t;
    }
    //
    private static boolean isColumn(ResultSet rs,String columnName){
        boolean flag=false;
        try {
	        if (rs.findColumn(columnName)>0){
	            flag=true;
	        }
        } catch (SQLException e) {

        }
        return flag;
    }
    /**
     *给我一个字段名称 ，我给你返回一个set字段名  给类中的属性 赋值
     * @param name  字段名称
     * @return   setName()
     */
    private static String getMethod(String name) {
        return "set"+name.substring(0, 1).toUpperCase()+name.substring(1);
    }



}
