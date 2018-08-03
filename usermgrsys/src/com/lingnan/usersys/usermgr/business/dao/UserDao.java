package com.lingnan.usersys.usermgr.business.dao;

import java.util.List;
import java.util.Vector;

import com.lingnan.usersys.common.dao.BaseDao;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用户dao接口
 * @author Administrator
 *
 */
public interface UserDao extends BaseDao {
	/**
	 * 注册用户/添加用户
	 * @param user 用户信息
	 * @return 成功返回true，失败返回false
	 */
	public boolean addUser(UserVO user);
	/**
	 * 用户登录
	 * @param name 用户名
	 * @param password 密码
	 * @return 用户信息
	 */
	public UserVO login(String name, String password);	
	/**
	 * 查找最大id值
	 * @return 最大id值
	 */
	public int findMaxId();	
	/**
	 * 删除
	 * @param uid 用户编号
	 * @return 成功返回true，失败返回false
	 */
	public boolean deleteUser(String uid);	
	/**
	 * 修改
	 * @param user 用户信息
	 * @return 成功返回true，失败返回false
	 */
	public boolean updateUser(UserVO user);	
	/**
	 * 查询所有用户(分页查询)
	 * @param pageNum 页号
	 * @param pageSize 每页的记录数量
	 * @return  用户信息
	 */
	public Vector<UserVO> searchAllUser(int pageNum,int pageSize); 	
	/**
	 * 按userId查询用户(精准查询)
	 * @param userId 用户编号
	 * @return  用户信息
	 */
	public Vector<UserVO> searchUserById(String userId);	
	/**
	 * 按名字查询用户(模糊查询)
	 * @param name 用户名
	 * @return  用户信息
	 */
	public Vector<UserVO> searchUserByName(String name);
		
}
