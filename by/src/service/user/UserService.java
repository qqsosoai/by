package service.user;

import java.sql.SQLException;
import java.util.List;

import bean.Address;
import bean.Shopping_Car;
import bean.User;
import bean.UserAddress;

public interface UserService {
	/**
	 * 判断改用户名是否存在
	 * @param loginName
	 * @return
	 */
	boolean isExistLoginName(String loginName);
	/**
	 * 登录方法查询账号返回对象
	 * @param loginName 登录账号
	 * @return 返回用户
	 */
	User login(String loginName);
	/**
	 * 添加用户方法
	 * @param user
	 * @return
	 */
	boolean addUser(User user);
	/**
	 * 查询购物车方法根据用户ID
	 * @param userId 用户ID
	 * @return 返回用户购物车集合
	 */
	List<Shopping_Car> findByUserId(Integer userId);
	/**
	 * 删除购物车
	 * @param id 购物车ID
	 * @return 是否删除成功
	 */
	boolean deleteShopping_Car(Integer id);
	/**
	 * 更改购物车信息方法
	 * @param t 购物车实体类
	 * @return 是否修改成功
	 */
	boolean updateShopping_Car(Shopping_Car t);
	/**
	 * 根据用户ID查询用户地址
	 * @param userId 用户ID
	 * @return 用户地址集合
	 */
	List<UserAddress> findByUserAddress(Integer userId);
	/**
	 * 将用户地址改为默认
	 * @param userId 用户ID
	 * @param id 用户地址ID
	 * @return 是否修改成功
	 */
	boolean updateDefaultUserAddrees(Integer userId,Integer id);
	/**
	 * 新增用户地址
	 * @param t 用户地址
	 * @return 是否添加成功
	 */
	boolean addUserAddress(UserAddress t);
	/**
	 * 根据用户地址ID删除用户地址
	 * @param id 用户地址ID
	 * @return 删除是否成功
	 */
	boolean deleteUserAddress(Integer id);
	/**
	 * 查询所有地址
	 * @return
	 */
	List<Address> findAddressAll();
	/**
	 * 根据城市编号查询城市
	 * @param areano 城市编号
	 * @return 返回城市详情
	 */
	Address findAddressByAreano(Integer areano);
}
