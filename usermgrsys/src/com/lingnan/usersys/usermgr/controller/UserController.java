package com.lingnan.usersys.usermgr.controller;

import java.util.Vector;

import com.lingnan.usersys.usermgr.business.service.UserService;
import com.lingnan.usersys.usermgr.business.service.UserServiceImpl;
import com.lingnan.usersys.usermgr.domain.UserVO;

public class UserController {
	//声明用户service接口对象，用于业务处理
	UserService userMgrService = UserServiceImpl.getInstance();
	/**
	 * 用户登录
	 * @param name 用户名
	 * @param password 密码
	 * @return 用户信息
	 */
	public UserVO doLogin(String name, String password) {
		UserVO user = null;
		try {
			//调用用户service接口中的login方法，进行用户登录操作
			user = userMgrService.login(name, password);
			
			
		} catch (Exception e) {
			//显示异常信息
			System.out.println("用户登录时出错"+e.getMessage());
			
		}
		return user;
		
	}
	/**
	 * 用户注册/添加用户
	 * @param user 用户信息
	 * @return 成功返回true，失败返回false
	 */
	public boolean doAddUser(UserVO user) {
		boolean flag = false;
		try {
			//调用用户service接口中的addUser方法，进行用户注册/添加用户操作
			flag = userMgrService.addUser(user);
			
			
		} catch (Exception e) {
			//显示异常信息
			System.out.println("用户注册时出错"+e.getMessage());
			
		}
		return flag;
		
	}
	/**
	 * 删除
	 * @param uid 用户编号
	 * @return 成功返回true，失败返回false
	 */
	public boolean doDeleteUser(String uid){
		boolean flag = false;
		try {
			//调用用户service接口中的deleteUser方法，进行删除用户操作
			flag = userMgrService.deleteUser(uid);		
		} catch (Exception e) {
			//显示异常信息
			System.out.println("删除用户时出错"+e.getMessage());
			
		}
		return flag;
	}
	/**
	 * 修改
	 * @param user 用户信息
	 * @return 成功返回true，失败返回false
	 */
	public boolean doUpdateUser(UserVO user) {
		boolean flag = false;
		try {
			//调用用户service接口中的updateUser方法，进行修改用户操作
			flag = userMgrService.updateUser(user);			
		} catch (Exception e) {
			//显示异常信息
			System.out.println("更新用户时出错"+e.getMessage());
			
		}
		return flag;
	}
	
	/**
	 * 查询所有用户(分页查询)
	 * @param pageNum 页号
	 * @param pageSize 每页的记录数量
	 * @return  用户信息
	 */
	public Vector<UserVO> searchAllUser(int pageNum,int pageSize) {
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			//调用用户service接口中的updateUser方法，进行修改用户操作
			v = userMgrService.searchAllUser(pageNum, pageSize);			
		} catch (Exception e) {
			//显示异常信息
			System.out.println("更新用户时出错"+e.getMessage());
			
		}
		return v;
	}
	
	/**
	 * 按id查询用户
	 * @param userId 用户编号
	 * @return  用户信息
	 */
	public Vector<UserVO> searchUserById(String userId) {
		Vector<UserVO> user = new Vector<UserVO>();
		try {
			//调用用户service接口中的login方法，进行用户登录操作
			user = userMgrService.searchUserById(userId);
			
			
		} catch (Exception e) {
			//显示异常信息
			System.out.println("用户登录时出错"+e.getMessage());
			
		}
		return user;
	}
	
	/**
	 * 按名字查询用户(模糊查询)
	 * @param name 用户名
	 * @return  用户信息
	 */
	public Vector<UserVO> searchUserByName(String name) {
		Vector<UserVO> user = new Vector<UserVO>();
		try {
			//调用用户service接口中的searchUserByName方法，进行用户登录操作
			user = userMgrService.searchUserByName(name);
			
			
		} catch (Exception e) {
			//显示异常信息
			System.out.println("用户登录时出错"+e.getMessage());
			
		}
		return user;
	}

}
