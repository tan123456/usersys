package com.lingnan.usersys.usermgr.view;

import com.lingnan.usersys.usermgr.domain.UserVO;

public interface BaseFrame {
	/**
	 * 页面显示
	 */
	public void show();
	/**
	 * 增加用户页面显示
	 * @param type 用户类型
	 */
	public void addShow(String type);
	/**
	 * 查询
	 */
	public void searchShow();
	/**
	 * 修改
	 * @param type 用户类型
	 * @param user 用户信息
	 */
	public void updateShow(String type, UserVO user);
	/**
	 * 删除
	 * @param id 用户编号
	 */
	public void deleteShow(int id);
	
}
