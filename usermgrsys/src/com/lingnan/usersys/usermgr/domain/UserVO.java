package com.lingnan.usersys.usermgr.domain;

import java.util.Date;

public class UserVO {
	private int id;          //主键
	private String name;     //用户名
	private String pass;     //密码
	private String mail;     //邮箱
	private String power;    //权限
	private Date birth;      //出生日期
	private String userId;   //用户编号
	private String status;   
	
	/**
	 * 取得用户编号
	 * @return 编号
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * 设置用户编号为指定参数的值
	 * @param id 用户编号
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	
	/**
	 * 取得用户名
	 * @return 用户名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置用户名为指定参数的值
	 * @param name 用户名
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * 取得用户密码
	 * @return 密码
	 */
	public String getPass() {
		return pass;
	}
	
	/**
	 * 设置用户密码为指定参数的值
	 * @param pass 用户密码
	 */
	public void setPass(String pass){
		this.pass = pass;
	}
	
	/**
	 * 取得用户邮箱
	 * @return mail 邮箱
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * 设置用户邮箱为指定参数的值
	 * @param mail 邮箱
	 */
	public void setMail(String mail){
		this.mail = mail;
	}
	
	/**
	 * 取得用户权限
	 * @return power 权限
	 */
	public String getPower() {
		return power;
	}
	
	/**
	 * 设置用户权限为指定参数的值
	 * @param power 权限
	 */
	public void setPower(String power){
		this.power = power;
	}
	
	/**
	 * 取得出生日期
	 * @return 出生日期
	 */
	public Date getBirth() {
		return birth;
	}
	
	/**
	 * 设置出生日期为指定参数的值
	 * @param birth 出生日期
	 */
	public void setBirth(Date birth){
		this.birth = birth;
	}
	

	
	/**
	 * 取得状态
	 * @return 状态
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 设置状态为指定参数的值
	 * @param status 状态
	 */
	public void setStatus(String status){
		this.status = status;
	}
	
	
	
	

}
