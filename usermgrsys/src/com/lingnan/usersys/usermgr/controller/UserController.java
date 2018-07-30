package com.lingnan.usersys.usermgr.controller;

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
	 * 用户注册
	 * @param user 用户信息
	 * @return 成功返回true，失败返回false
	 */
	public boolean doAddUser(UserVO user) {
		boolean flag = false;
		try {
			//调用用户service接口中的login方法，进行用户登录操作
			flag = userMgrService.addUser(user);
			
			
		} catch (Exception e) {
			//显示异常信息
			System.out.println("用户注册时出错"+e.getMessage());
			
		}
		return flag;
		
	}

}
