package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import util.ResultSetUtil;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.mysql.jdbc.log.Log;

public class TestSession{
	private static Logger logger=Logger.getLogger(TestSession.class);
	protected static DataSource source;//数据源
	protected static SockIOPool pool;//缓存连接池
	protected static MemCachedClient cache=new MemCachedClient();//缓存客户端
	/**
	 * 查询某个实体类
	 * @param id 实体类主键ID
	 * @param clazz 实体类类型
	 * @param primaryKey 数据库表主键
	 * @param isCache 是否加入缓存
	 * @return 具体对象
	 * @throws SQLException
	 *///已测试
	public static <T> T get(Connection c,Integer id,Class<T> clazz,String primaryKey,boolean isCache) throws SQLException{
		T t = null;
		if (isCache) {
			t = (T) cache.get(clazz.getSimpleName() + id);
		}
		if (t==null) {
			PreparedStatement ps = c.prepareStatement("select * from "+clazz.getSimpleName()+" where "+primaryKey+"=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			t = ResultSetUtil.resultSet(rs, clazz);
			if (isCache) {
				cache.set(t.getClass().getSimpleName()+id, t);
			}
			close(rs, ps, null);
		}
		return t;
	}
	/**
	 * 查询级联操作(多对一)使用
	 * @param c 数据库连接
	 * @param id 要查询的主键ID
	 * @param clazz 实体类类型
	 * @param isCache 是否使用缓存
	 * @param primaryKey 实体类主键属性名
	 * @param cascade 实体类级联属性名
	 * @param manyToOneId 级联属性的主键名
	 * @return 返回多对一对象
	 *///查询级联操作(多对一)使用
	public static <T>T getManyToOne(Connection c,Integer id,Class<T> clazz,boolean isCache,String primaryKey,String cascade,String manyToOneId){
		T t=null;
		if (isCache) {
			t=(T) cache.get("cascade"+clazz.getSimpleName()+id);
		}
		if (t==null) {
			StringBuffer sql=new StringBuffer("select * from "+clazz.getSimpleName()+" where "+primaryKey+" =?;");//查询数据库
			try {
				PreparedStatement ps = c.prepareStatement(sql.toString());
				ps.setObject(1, id);
				ResultSet rs = ps.executeQuery();
				t = ResultSetUtil.resultManyToOne(rs, clazz, cascade, manyToOneId);
				Field[] fields = t.getClass().getDeclaredFields();
				for (Field field : fields) {
					if (field.getName().equals(cascade)) {//找到实体类级联属性
						Object value = getValue(field, t);//获取级联属性
						sql=new StringBuffer("select * from "+value.getClass().getSimpleName()+" where "+manyToOneId+"=?");//查询一一方的值
						ps=c.prepareStatement(sql.toString());
						Field[] fields2 = value.getClass().getDeclaredFields();
						for (Field field2 : fields2) {
							if (field2.getName().equals(manyToOneId)) {
								Object cascadeId = getValue(field2, value);
								ps.setObject(1, cascadeId);
							}
						}
						rs = ps.executeQuery();
						value = ResultSetUtil.resultSet(rs, value.getClass());
						setValue(field, t, value);
					}
				}
				if (isCache) {
					cache.set("cascade"+clazz.getSimpleName()+id,t);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	/**
	 * 多对一的方法(废弃不完善)
	 * @param c 数据库连接
	 * @param id 多方的外键
	 * @param foreignColumn 多方外键名
	 * @param primaryKeyColumn 一方的主键名
	 * @param one 一的类型
	 * @param many 多的类型
	 * @param isCache 是否使用缓存
	 * @return 返回一的对象
	 * @throws SQLException
	 */
	public static <O, M> O manyToOne(Connection c,Integer id,String foreignColumn,String primaryKeyColumn,Class<O> one,Class<M> many,boolean isCache) throws SQLException{
		O o=null;
		if (isCache) {
			o=(O) cache.get("manToOne" + one.getSimpleName() + id);
		}
		PreparedStatement ps=null;
		ResultSet rs=null;
		if (o==null) {
			c = source.getConnection();
			ps = c.prepareStatement("select b.* from " + many.getSimpleName()
					+ " a inner join " + one.getSimpleName() + " b on a."
					+ foreignColumn + "=b.?");
			ps.setObject(1, primaryKeyColumn);
			rs = ps.executeQuery();
			o = ResultSetUtil.resultSet(rs, one);
			if (isCache) {
				cache.set("manToOne"+one.getSimpleName()+id, o);
			}
		}
		close(rs, ps, null);
		return o;
	}
	
	/**
	 * 一对多的方法(废弃不完善)(不带缓存)
	 * @param 数据库连接 一的一方ID
	 * @param id 一的一方ID
	 * @param foreignColumn 多的一方外键字段名
	 * @param clazz 多一方的类型
	 * @return 返回一对多集合
	 * @throws SQLException
	 */
	public static <T> List<T> oneToMany(Connection c,Integer id,String foreignColumn,Class<T> clazz) throws SQLException{
		List<T>list=null;
		PreparedStatement ps = c.prepareStatement("select * from "+clazz.getSimpleName()+" where "+foreignColumn+"=?");
		ps.setObject(1, id);
		ResultSet rs = ps.executeQuery();
		list = ResultSetUtil.eachResultSet(rs, clazz);
		close(rs, ps, null);
		return list;
	}
	/**
	 * 新增方法
	 * @param c 数据库连接
	 * @param clazz 实体类类型
	 * @param t 新增对象
	 * @param isCache 是使用缓存
	 * @param primaryKey 主键字段
	 * @return 返回影响行数
	 * @throws SQLException 
	 */
	public static <T> int insert(Connection c,Class<T> clazz,T t,boolean isCache,String primaryKey) throws SQLException{
		int result = 0;
		StringBuffer sql=new StringBuffer(" insert into "+clazz.getSimpleName()+"( ");
		Field[] fields = clazz.getDeclaredFields();
		int flag=0;//判断拼接字段次数
		for (int i = 0; i < fields.length; i++) {//拼接需要执行的sql语句
			if (getValue(fields[i],t)!=null) {
				sql.append(" " + fields[i].getName() + ",");
				flag++;
			}
			if (i==fields.length-1) {
				sql = new StringBuffer(sql.substring(0, sql.length()-1));
				sql.append(" ) ");
			}
		}
		for (int i = 1; i <= flag; i++) {
			if (i==1) {
				sql.append(" value( ");
			}
			if (i!=flag) {
				sql.append(" ?, ");
			}
			if (i==flag) {
				sql.append(" ? ); ");
			}
		}
		PreparedStatement ps = c.prepareStatement(sql.toString());
		int a=0;//用于记录占位符的下标
		for (int i = 0; i < fields.length; i++) {//拼接需要执行的sql语句
			Object value=getValue(fields[i],t);
			if (value!=null) {
				a++;
				ps.setObject(a, value);
			}
		}
		result = ps.executeUpdate();
		if (result>0) {//判断新增是否成功
			if (isCache) {//是否更新缓存
				sql = new StringBuffer("SELECT LAST_INSERT_ID() AS id;");//查询
				ps=c.prepareStatement(sql.toString());
				ResultSet rs = ps.executeQuery();
				ResultSetMetaData data = rs.getMetaData();
				System.out.println(data.getColumnTypeName(1));
				if (rs.next()) {
					Integer id1 = rs.getInt(1);
					System.out.println(id1);
					for (int i = 0; i < fields.length; i++) {
						if (fields[i].getName().equals(primaryKey)) {//判断是否符合参数主键字段
							setValue(fields[i], t, id1);
						}
					}
					cache.set(t.getClass().getSimpleName() + rs.getObject(1), t);
				}
				close(rs, null, null);
			}
		}
		close(null, ps, null);
		return result;
	}
	
	/**
	 * 级联新增操作(使用多对一或一对多时使用)一对多无缓存机制
	 * @param c 数据库连接
	 * @param t 实体类对象
	 * @param isCache 是否使用缓存
	 * @param primaryKey 实体类主键
	 * @param cascade 级联属性名
	 * @param manyToOneId 级联属性的主键
	 * @param cascadeType 级联类型(true:多对一;false:一对多)
	 * @return 返回影响行数
	 * @throws SQLException 
	 *///多对一新增方法cascade属性为级联属性名 manyToOneId属性为多对一对象主键,cascadeType属性为true默认为多对一，false为一对多//已测试
	public static <T> int insert(Connection c,T t,boolean isCache,String primaryKey,String cascade,String manyToOneId,boolean cascadeType) throws SQLException{
		int result=0;
		Class<? extends Object> clazz=t.getClass();
		StringBuffer sql=new StringBuffer("insert into "+clazz.getSimpleName()+"( ");
		Field[] fields = clazz.getDeclaredFields();
		int flag=0;
		for (int i = 0; i < fields.length; i++) {//拼接需要执行的sql语句
			if (getValue(fields[i],t)!=null) {
				if (!fields[i].getName().equals(cascade)) {//判断是否是级联属性
					sql.append(" " + fields[i].getName() + ",");
					flag++;
				}else{//走else为级联属性
					if (cascadeType) {//是否是多对一
						sql.append(" " + fields[i].getName() + ",");
						flag++;
					}
				}
			}
			if (i==fields.length-1) {
				sql = new StringBuffer(sql.substring(0, sql.length()-1));
				sql.append(" ) ");
			}
		}
		for (int i = 1; i <= flag; i++) {//拼接value
			if (i==1) {
				sql.append(" value( ");
			}
			if (i!=flag) {
				sql.append(" ?, ");
			}
			if (i==flag) {
				sql.append(" ? ); ");
			}
		}
		PreparedStatement ps=c.prepareStatement(sql.toString());
		int a=0;//用于记录占位符的下标
		Object id=null;//用于当添加成功后使用
			for (int i = 0; i < fields.length; i++) {//拼接需要执行的sql语句
				Object value = getValue(fields[i], t);
				if (!fields[i].getName().equals(cascade)) {//判断是否是级联属性
					if (fields[i].getName().equals(primaryKey) && value!=null) {
						id=value;
					}
					if (value != null) {
						a++;
						ps.setObject(a, value);
					}
				}else{//是级联属性
					if (cascadeType) {//判断是否是多对一，进入if说明为多对一
						Field[] cascadeFields = value.getClass().getDeclaredFields();
						for (int j = 0; j < cascadeFields.length; j++) {
							if (cascadeFields[j].getName().equals(manyToOneId)) {
								Object manyForign = getValue(cascadeFields[j], value);//获取多一方的属性值
								a++;
								ps.setObject(a, manyForign);
							}
						}
					}
				}
			}
			result = ps.executeUpdate();
			if (result>0) {
				if (isCache) {//是否使用缓存
					if (cascadeType) {//多对一使用缓存
						ResultSet rs = null;
						if (id == null) {//实体类id没有值
							sql = new StringBuffer(
									"SELECT LAST_INSERT_ID() AS id;");//查询
							ps = c.prepareStatement(sql.toString());
							rs = ps.executeQuery();
							if (rs.next()) {
								id = rs.getInt(1);
								for (int i = 0; i < fields.length; i++) {
									if (fields[i].getName().equals(primaryKey)) {//判断是否符合参数主键字段
										if (setValue(fields[i], t, id)) {
											cache.set("cascade"
													+ t.getClass()
															.getSimpleName()
													+ id, t);
										}
									}
								}
							}
						} else {
							cache.set("cascade" + t.getClass().getSimpleName()
									+ id, t);
						}
						close(rs, ps, null);
					}
				}
			}
		return result;
	}
	/**
	 * 更新方法
	 * @param c 数据库连接
	 * @param clazz 实体类类型
	 * @param t 实体类对象
	 * @param primaryKey 主键字段
	 * @param isCache 是否使用缓存
	 * @return 返回影响行数
	 * @throws SQLException 
	 */
	public static <T> int update(Connection c,Class<T> clazz,T t,String primaryKey,boolean isCache) throws SQLException{
		int result=0;
			StringBuffer sql=new StringBuffer("update "+clazz.getSimpleName()+" set ");
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				if (getValue(fields[i], t)!=null && !fields[i].getName().equals(primaryKey)) {
					sql.append(fields[i].getName()+"=?,");
				}
				if (i==fields.length-1) {
					sql = new StringBuffer(sql.substring(0, sql.length()-1));
					sql.append(" where "+primaryKey+"=?;");
				}
			}
			PreparedStatement ps = c.prepareStatement(sql.toString());
			Integer id=null;//用于存放主键
			int a=0;//用于记录占位符的下标
			for (int i = 0; i < fields.length; i++) {//拼接需要执行的sql语句
				Object value=getValue(fields[i],t);
				if (fields[i].getName().equals(primaryKey)) {
					id=(Integer) value;
				}
				if (value!=null && !fields[i].getName().equals(primaryKey)) {
					a++;
					ps.setObject(a, value);
				}
				if (i==fields.length-1) {
					ps.setObject(a+1, id);
				}
			}
			result = ps.executeUpdate();
			if(result>0){//修改成功更新缓存
				if (isCache) {
					T t2 = get(c,id, clazz, primaryKey, false);//查询数据库
					cache.set(t.getClass().getSimpleName()+id, t2);
				}
			}
		return result;
	}
	
	/**
	 * 级联更新操作(多对一或一对多时使用)一对多无缓存机制
	 * @param c 数据库连接
	 * @param t 实体类对象
	 * @param isCache 是否使用缓存
	 * @param primaryKey 实体类主键
	 * @param cascade 级联属性名
	 * @param manyToOneId 级联属性主键
	 * @param cascadeType 级联类型(true:多对一;false:一对多)
	 * @return 返回影响行数
	 * @throws SQLException 
	 *///出现BUG添加缓存时出现bug
	public static <T> int update(Connection c,T t,boolean isCache,String primaryKey,String cascade,String manyToOneId,boolean cascadeType) throws SQLException{
		int result=0;
			Class<? extends Object> clazz=t.getClass();
			StringBuffer sql=new StringBuffer("update "+clazz.getSimpleName()+" set ");
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				if (getValue(fields[i], t)!=null && !fields[i].getName().equals(primaryKey)) {
					if (!fields[i].getName().equals(cascade)) {//判断是否是级联属性
						sql.append(fields[i].getName() + "=?,");
					}else{//是级联属性
						if (cascadeType) {//判断是否是多对一
							sql.append(fields[i].getName() + "=?,");
						}
					}
				}
				if (i==fields.length-1) {//将主键ID拼接到最后
					sql = new StringBuffer(sql.substring(0, sql.length()-1));
					sql.append(" where "+primaryKey+"=?;");
				}
			}
			PreparedStatement ps = c.prepareStatement(sql.toString());
			Integer id=null;//用于存放主键
			int a=0;//用于记录占位符的下标
			for (int i = 0; i < fields.length; i++) {//为sql语句赋值
				Object value=getValue(fields[i],t);
				if (fields[i].getName().equals(primaryKey)) {//判断是否是主键
					id=(Integer) value;
				}
				if (value!=null && !fields[i].getName().equals(primaryKey)) {//判断改值不为空并却改值不为主键
					a++;
					if (!fields[i].getName().equals(cascade)) {//判断是否是级联属性
						ps.setObject(a, value);
					}else{
						if (cascadeType) {//是多对一
							Field[] cascadeFields = value.getClass().getDeclaredFields();
							for (int j = 0; j < cascadeFields.length; j++) {
								if (cascadeFields[j].getName().equals(manyToOneId)) {//找到一方的主键
									value=getValue(cascadeFields[j], value);
									ps.setObject(a, value);
								}
							}
						}
					}
				}
				if (i==fields.length-1) {//为ID赋值
					ps.setObject(a+1, id);
				}
			}
			result = ps.executeUpdate();
			if (result>0) {
				if (isCache) {
					if (cascadeType) {//多对一添加缓存
						cache.set("cascade" + t.getClass().getSimpleName()+id, getManyToOne(c, id, clazz, false, primaryKey, cascade, manyToOneId));
					}
				}
			}
		
		return result;
	}
	
	/**
	 * 获取数据库连接
	 * @return 数据库连接
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		return source.getConnection();
	}
	
	/**
	 * 删除方法
	 * @param c 数据库连接
	 * @param clazz 实体类型
	 * @param id 删除数据库表的ID
	 * @param primaryKey 主键字段
	 * @param isCache 是否使用缓存
	 * @return 返回影响行数
	 */
	public static <T> int delete(Connection c,Class<T> clazz,Integer id,String primaryKey,boolean isCache){
		int result=0;
		try {
			StringBuffer sql=new StringBuffer("delete from "+clazz.getSimpleName()+" where "+primaryKey+"=?");
			PreparedStatement ps = c.prepareStatement(sql.toString());
			ps.setObject(1, id);
			result = ps.executeUpdate();
			if (result>0) {
				if (isCache) {
					cache.delete(clazz.getSimpleName()+id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取t对象中的属性为field的值
	 * @param field 属性
	 * @param t 新增对象
	 * @return field值
	 */
	private static <T> Object getValue(Field field,T t){
		String name=field.getName();
		String method="get"+name.substring(0, 1).toUpperCase()+name.substring(1);
		Object object=null;
			try {
				object = t.getClass().getMethod(method).invoke(t);
			} catch (IllegalAccessException e) {
				logger.error(t.getClass().getName()+"类中"+field.getName()+"属性的get方法无法访问");
				e.printStackTrace();//安全异常方法访问权限不够无法访问
			} catch (IllegalArgumentException e) {
				logger.error(t.getClass().getName()+"类中"+field.getName()+"属性的get方法参数不正确");
				e.printStackTrace();//参数不匹配异常
			} catch (InvocationTargetException e) {
				logger.error(t.getClass().getName()+"类中"+field.getName()+"属性的get方法中出现异常");
				e.printStackTrace();//用于捕获调用方法中未捕获的异常
			} catch (NoSuchMethodException e) {
				logger.error(t.getClass().getName()+"类中"+field.getName()+"属性没有找到get方法");
				e.printStackTrace();//没有找到改方法
			} catch (SecurityException e) {
				logger.error(t.getClass().getName()+"类没有找到");
				e.printStackTrace();//类没有找到异常
			}
		return object;
	}
	 
	
	/**
	 * 执行set方法为实体类赋值
	 * @param field 属性
	 * @param t 实体类
	 * @param value 要赋值的值
	 * @return 赋值是否成功
	 */
	private static <T> boolean setValue(Field field,T t,Object value){
		boolean flag=false;
		String name = field.getName();
		String method="set"+name.substring(0, 1).toUpperCase()+name.substring(1);
		try {
			t.getClass().getMethod(method, field.getType()).invoke(t, value);
			flag=true;
		} catch (IllegalAccessException e) {
			logger.error(t.getClass().getName()+"类中"+field.getName()+"属性的set方法无法访问");
			e.printStackTrace();//安全异常方法访问权限不够无法访问
		} catch (IllegalArgumentException e) {
			logger.error(t.getClass().getName()+"类中"+field.getName()+"属性的set方法的参数类型不匹配");
			e.printStackTrace();//参数不匹配异常
		} catch (InvocationTargetException e) {
			logger.error(field.getName()+"属性的set方法中出现异常");
			e.printStackTrace();//用于捕获调用方法中未捕获的异常
		} catch (NoSuchMethodException e) {
			logger.error(t.getClass().getName()+"类中"+field.getName()+"属性没有找到set方法");
			e.printStackTrace();//没有找到改方法
		} catch (SecurityException e) {
			logger.error(t.getClass().getName()+"类没有找到");
			e.printStackTrace();//类没有找到异常
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
	/**
	 * 关闭连接方法
	 * @param rs
	 * @param ps 
	 * @param c
	 * @return
	 */
	private static boolean close(ResultSet rs, PreparedStatement ps,Connection c){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		if (ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		if (c!=null) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	public static boolean setCache(String key,Object value){
		return cache.set(key, value);
	}
	public static boolean replaceCache(String key,Object newValue){
		return cache.replace(key,newValue);
	}
	public static boolean addCache(String key,Object value){
		return cache.add(key,value);
	}
	public static Object getCache(String key){
		return cache.get(key);
	}
	public static  DataSource getSource() {
		return source;
	}
	public static void setSource(DataSource source) {
		TestSession.source = source;
	}
	public static SockIOPool getPool() {
		return pool;
	}
	public static void setPool(SockIOPool pool) {
		TestSession.pool = pool;
	}
	
	
}

