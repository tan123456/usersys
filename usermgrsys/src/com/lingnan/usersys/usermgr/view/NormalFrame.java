package com.lingnan.usersys.usermgr.view;

import com.lingnan.usersys.usermgr.controller.UserController;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用于用户管理：权限分为普通用户和管理员
 * @author Administrator
 *
 */
public class NormalFrame extends IndexFrame {
	
	//用户对象
	UserVO user = null;
	/**
	 * 带参数的构造器，用于初始化user属性
	 * @param user
	 */
	public NormalFrame(UserVO user) {
		this.user = user;
	}
	

}
