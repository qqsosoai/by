package service.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.BaseDao;
import util.BeanFactory;
import util.TestSession;
import dao.product.ProductDao;
import dao.shopping.Shopping_CarDao;
import dao.user.UserDao;
import dao.user.address.AddressDao;
import dao.user.address.UserAddressDao;
import bean.Address;
import bean.Product;
import bean.Shopping_Car;
import bean.User;
import bean.UserAddress;

public class UserServiceImpl implements UserService {
	private UserDao dao;
	private Shopping_CarDao shopping_CarDao;//用户购物车
	private ProductDao productDao;//商品Dao
	private UserAddressDao userAddressDao;//用户收货地址Dao
	private AddressDao addressDao;//用户收货地址Dao
	public UserServiceImpl(){
		BeanFactory.factoryByType(this);
	}
	@Override//验证用户名是否存在
	public boolean isExistLoginName(String loginName) {
		Connection c = null;
		try {
			c = TestSession.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = dao.findByLoginName(c, loginName);
		if (user!=null) {
			return true;
		}
		return false;
	}

	@Override//根据用户名查询对象
	public User login(String loginName) {
		Connection c=null;
		User user=null;
		try {
			c=TestSession.getConnection();
			user = dao.findByLoginName(c, loginName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.close(c, null, null);
		}
		return user;
	}
	@Override//新增用户方法
	public boolean addUser(User user) {
		boolean flag=false;
		Connection c = null;
		try {
			c = TestSession.getConnection();
			c.setAutoCommit(false);
			Integer result = dao.add(c, user);
			if (result>0) {
				c.commit();
				flag=true;
			}
		} catch (SQLException e) {
			if (c!=null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			BaseDao.close(c, null, null);
		}
		return flag;
	}
	@Override//查询用户ID下的购物车商品
	public List<Shopping_Car> findByUserId(Integer userId) {
		Connection c = null;
		List<Shopping_Car> userCar=null;
		try {
			c = TestSession.getConnection();
			userCar = shopping_CarDao.findByUserId(c, userId);
			for (int i = 0; i < userCar.size(); i++) {
				Shopping_Car car = userCar.get(i);
				car.setProductId(productDao.findById(c, car.getProductId().getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userCar;
	}
	@Override//根据用户ID查询用户收货地址
	public List<UserAddress> findByUserAddress(Integer userId) {
		Connection c=null;
		List<UserAddress> list=null;
		try {
			c = TestSession.getConnection();
			list = userAddressDao.findByUserId(c, userId);
			for (int i = 0; i < list.size(); i++) {
				UserAddress userAddress = list.get(i);
				//获取省对象
				userAddress.setProvince(addressDao.findById(c, userAddress.getProvince().getAreano()));
				//获取市对象
				userAddress.setCity(addressDao.findById(c, userAddress.getCity().getAreano()));
				//获取区对象
				userAddress.setZone(addressDao.findById(c, userAddress.getZone().getAreano()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(c, null, null);
		}
		return list;
	}
	@Override//根据用户地址ID删除地址
	public boolean deleteUserAddress(Integer id) {
		boolean flag=false;
		Connection c;
		try {
			c = TestSession.getConnection();
			Integer row = userAddressDao.delete(c, new UserAddress(id));
			if (row>0) {
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	@Override//根据用户ID与用户地址ID设置默认地址
	public boolean updateDefaultUserAddrees(Integer userId, Integer id) {
		boolean flag=false;
		Connection c=null;
		try {
			c = TestSession.getConnection();
			c.setAutoCommit(false);
			Integer row = userAddressDao.updateUserAddressToNoDefault(c, userId);
			if (row>0) {
				UserAddress t=new UserAddress(id);
				t.setIsDefault(1);
				row = userAddressDao.updateIsDefault(c, id);
				if (row>0) {
					c.commit();
					flag=true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if (c!=null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return flag;
	}
	@Override//添加用户地址
	public boolean addUserAddress(UserAddress t) {
		boolean flag=false;
		Connection c=null;
		try {
			c = TestSession.getConnection();
			Integer count = userAddressDao.selectAddressCount(c, t.getUserId());
			if (count<1) {
				t.setIsDefault(1);
			}
			Integer row = userAddressDao.add(c, t);
			if (row>0) {
				flag=true;
			}
			t.setId(userAddressDao.selectInsterId(c));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(c, null, null);
		}
		return flag;
	}
	@Override//根据城市编号查询城市详情
	public Address findAddressByAreano(Integer areano) {
		Address address=null;
		Connection c=null;
		try {
			c = TestSession.getConnection();
			address = addressDao.findById(c, areano);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(c, null, null);
		}
		return address;
	}
	@Override//查询所有中国地址
	public List<Address> findAddressAll() {
		Connection c=null;
		List<Address> oneAddresses=null;
		try {
			c = TestSession.getConnection();
			oneAddresses = addressDao.findByParentno(c, 0);
			for (int i = 0; i < oneAddresses.size(); i++) {//查询二级地址
				Address one = oneAddresses.get(i);
				List<Address> twAddresses = null;
				if (one.getAreacode() != null
						&& !one.getAreacode().equals("")) {//找到直辖市
					twAddresses = new ArrayList<>();
					twAddresses.add(new Address(one.getAreano(), one
							.getAreaname(), one.getParentno(), one
							.getAreacode(), one.getArealeveltiny(), one
							.getTypename()));
					one.setChild(twAddresses);
				} else {//代表不是直辖市
					twAddresses = addressDao.findByParentno(c,
							one.getAreano());
					one.setChild(twAddresses);
				}
				for (int j = 0; j < twAddresses.size(); j++) {//查询三级地址
					Address twAddress = twAddresses.get(j);
					List<Address> threeAddresses = addressDao
							.findByParentno(c, twAddress.getAreano());
					twAddress.setChild(threeAddresses);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(c, null, null);
		}
		return oneAddresses;
	}
	@Override//根据ID删除购物车
	public boolean deleteShopping_Car(Integer id) {
		boolean flag=false;
		Connection c=null;
		try {
			c = TestSession.getConnection();
			Integer row = shopping_CarDao.delete(c, new Shopping_Car(id));
			if (row>0) {
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(c, null, null);
		}
		
		return flag;
	}
	@Override//修改购物车信息
	public boolean updateShopping_Car(Shopping_Car t) {
		boolean flag=false;
		Connection c;
		try {
			c = TestSession.getConnection();
			Integer update = shopping_CarDao.update(c, t);
			if (update>0) {
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
