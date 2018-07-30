package com.lingnan.usersys.usermgr.view;

import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用于用户管理，权限分为普通用户和管理员
 * @author Administrator
 *
 */
public class AdminFrame extends NormalFrame{
	
	/**
	 * 带参数的构造器，用于初始化user属性
	 * @param user
	 */
	public AdminFrame(UserVO user) {
		super(user);
	}
	
	public void loginSuccShow() {
		
		System.out.println("欢迎登录主窗体");
		
	}

}
