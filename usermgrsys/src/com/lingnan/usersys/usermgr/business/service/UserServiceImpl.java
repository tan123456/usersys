package com.lingnan.usersys.usermgr.business.service;

import java.sql.Connection;
import java.util.Vector;

import com.lingnan.usersys.common.constant.EnumType;
import com.lingnan.usersys.common.dao.DaoFactory;
import com.lingnan.usersys.common.exception.DaoException;
import com.lingnan.usersys.common.exception.ServiceException;
import com.lingnan.usersys.common.util.DBUtils;
import com.lingnan.usersys.usermgr.business.dao.UserDao;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用户service接口的实现类
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService{
	/**
	 * 用户service类实例
	 */
	private static UserService userService = new UserServiceImpl();
	/**
	 * 构造方法私有化，private
	 */
	private  UserServiceImpl(){
		
	}
	/**
	 * 取得用户service实例
	 * 
	 * @return 实例对象
	 */
	public static UserService getInstance() {
		return userService;	
	}
	/**
	 * 用户登录
	 * @param name 用户名, password 密码
	 * @return 用户信息
	 */
	public UserVO login(String name, String password) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		UserVO user = null;
		
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao中的login方法，进行登录操作，结果赋值给登录结果变量
			user = userMgrDao.login(name, password);		
		} catch (DaoException e) {
			//自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常抛出
			throw new ServiceException("用户登录错误",e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		//返回用户登录结果
		return user;
	}
	
	/**
	 * 查找最大id值
	 * @return 最大id值
	 */
	public int findMaxId() {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		int i;		
		try {
		    //调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
		    conn = DBUtils.getConnection();
		    //调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
		    UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao中的login方法，进行登录操作，结果赋值给登录结果变量
			i = userMgrDao.findMaxId();					
		} catch (DaoException e) {
			//自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常抛出
			throw new ServiceException("用户登录错误",e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		//返回用户登录结果
		return i;
	}
	/**
	 * 注册用户/添加用户
	 * @param user 用户信息
	 * @return 成功返回true，失败返回false
	 */
	public boolean addUser(UserVO user) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;	
		boolean flag = false;
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用数据库工具类的beginTransaction方法，开启事务
			DBUtils.beginTransaction(conn);
			//实现id自动分配，调用findMaxId方法
			user.setId(userMgrDao.findMaxId()+1);
			user.setUserId(Integer.toString(userMgrDao.findMaxId()+1));
			//调用dao中的addUser方法，进行添加操作，结果赋值给添加结果变量
			flag = userMgrDao.addUser(user);
			//调用数据库工具类的commit方法，提交事务
			DBUtils.commit(conn);
			
		} catch (DaoException e) {
			//自定义异常抛出
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);  //回滚事务
			//将异常封装成自定义异常抛出
			throw new ServiceException("用户注册错误",e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		//返回用户注册结果
		return flag;
	}
	/**
	 * 删除
	 * @param uid 用户编号
	 * @return 成功返回true，失败返回false
	 */
	public boolean deleteUser(String uid){
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;	
		boolean flag = false;
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用数据库工具类的beginTransaction方法，开启事务
			DBUtils.beginTransaction(conn);
			//调用dao中的deleteUser方法，进行登录操作，结果赋值给删除结果变量
			flag = userMgrDao.deleteUser(uid);
			//调用数据库工具类的commit方法，提交事务
			DBUtils.commit(conn);
			
		} catch (DaoException e) {
			DBUtils.rollback(conn);  //回滚事务
			//自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常抛出
			throw new ServiceException("删除用户出错",e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		//返回删除用户结果
		return flag;
	}
	
	/**
	 * 修改
	 * @param user 用户信息
	 * @return 成功返回true，失败返回false
	 */
	public boolean updateUser(UserVO user) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;	
		boolean flag = false;
		try {
		    //调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用数据库工具类的beginTransaction方法，开启事务
			DBUtils.beginTransaction(conn);
			//调用dao中的updateUser方法，进行登录操作，结果赋值给删除结果变量
			flag = userMgrDao.updateUser(user);
			//调用数据库工具类的commit方法，提交事务
			DBUtils.commit(conn);
					
			} catch (DaoException e) {
				DBUtils.rollback(conn);  //回滚事务
				//自定义异常抛出
				throw e;
			} catch (Exception e) {
				//将异常封装成自定义异常抛出
				throw new ServiceException("更新用户出错",e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			//返回更新用户结果
			return flag;
			
	}
	
	/**
	 * 查询所有用户(分页查询)
	 * @param pageNum 页号，pageSize 每页的记录数量
	 * @return  用户信息
	 */
	public Vector<UserVO> searchAllUser(int pageNum,int pageSize) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;	
		boolean flag = false;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao中的searchAllUser方法，进行查询操作，结果赋值给查询结果变量
			v = userMgrDao.searchAllUser(pageNum, pageSize);
			
			
		} catch (DaoException e) {
			//自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常抛出
			throw new ServiceException("查询用户失败",e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		//返回查询用户结果
		return v;
	}
	
	/**
	 * 按id查询用户
	 * @param userId 用户编号
	 * @return  用户信息
	 */
	public Vector<UserVO> searchUserById(String userId) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;	
		boolean flag = false;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao中的searchUserById方法，进行查询操作，结果赋值给查询结果变量
			v = userMgrDao.searchUserById(userId);
			
			
		} catch (DaoException e) {
			//自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常抛出
			throw new ServiceException("查询用户失败",e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		//返回查询用户结果
		return v;
	}
	
	
	/**
	 * 按名字查询用户(模糊查询)
	 * @param name 用户名
	 * @return  用户信息
	 */
	public Vector<UserVO> searchUserByName(String name) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;	
		boolean flag = false;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao中的searchUserByName方法，进行查询操作，结果赋值给查询结果变量
			v = userMgrDao.searchUserByName(name);	
		} catch (DaoException e) {
			//自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常抛出
			throw new ServiceException("查询用户失败",e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		//返回查询用户结果
		return v;
	}


}
