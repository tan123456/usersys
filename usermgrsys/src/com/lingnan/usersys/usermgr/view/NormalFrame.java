package com.lingnan.usersys.usermgr.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Vector;

import com.lingnan.usersys.common.util.TypeUtils;
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
	 * @param user 用户信息
	 */
	public NormalFrame(UserVO user) {
		this.user = user;
	}
	
	/**
	 * 进行用户管理的操作页面和操作结果
	 */
	public void show() {
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserVO user = new UserVO();
		while (true) {
			//显示管理员登录后的操作界面
			System.out.println("更新个人信息--------------------1");
			System.out.println("查询个人信息--------------------2");
			System.out.println("程序退出------------------------3");
			//声明变量，用于接受从控制台输入的数据
			int i = -1;
			//读取用户控制台输入，如果输入值正确，中断循环，否则提示错误信息，再重新输入
			while (true) {
				try {
					//读取用户输入操作选项的数字，同时转换为int型
					i = Integer.parseInt(br.readLine());
					//中断该循环，进入下一步操作：i值判断
					break;
				} catch (Exception e) {
					//出现异常时，提示错误信息，再重新输入
					System.out.println("输入错误，只能输入1~3的数字。");
					System.out.println("请您重新输入");
				}	
			}
			/*
			 * 判断用户输入值，如果值为1，进行更新个人信息操作
			 * 如果值为2，进行查询个人信息操作，如果值为3，退出系统
			 */
			switch (i) {
			case 1:
			    this.updateShow("普通用户", user);
				break;//中断switch
			case 2:
				this.searchShow("");
				break;//中断switch
			case 3:
				System.out.println("感谢您使用本软件!!");
				//退出系统
				System.exit(0);
				

			default: //输入值是1-3之外的数字
				System.out.println("您输入的操作不正确，请重新输入。");
			}
			
			
		}
	}
	
	public void updateShow(String type, UserVO user) {
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if ("普通用户".equals(type)){
			System.out.println("修改个人信息界面");
			System.out.println("========================");
		} 

		try {
			//以行为单位，读取输入的各个值，赋值给用户对象的各个属性
			System.out.println("请输入您的用户编号");
			user.setUserId(br.readLine());
			System.out.println("请输入您修改的用户名");
			user.setName(br.readLine());
			System.out.println("请输入您修改的密码");
			user.setPass(br.readLine());
			while (true) {
				System.out.println("请输入您修改的邮箱");
				String email = br.readLine();
				if (TypeUtils.checkEmail(email)) {
					user.setMail(email);
					break;
				}		
			}
			System.out.println("请输入您修改的出生日期(YYYY-MM-DD)");
			user.setBirth(TypeUtils.strToDate(br.readLine()));
			//System.out.println(user.getId()+"  "+user.getUserId()+"  "+user.getName()+"  "+user.getMail()+user.getPass()+user.getPower()+user.getBirth());
			UserController uc = new UserController();
			//调用用户控制器中的doRegister方法，进行用户注册操作
			Boolean flag = uc.doUpdateUser(user);
			if (flag) {
					System.out.println("更新成功");
			} else {
					System.out.println("更新失败");
			}
		} catch (Exception e) {
			//显示异常信息
			System.out.println("更新失败! ! !"+e.getMessage());
		}
		
	}
	
	/**
	 * 进行查询的操作界面和操作结果
	 * @param userId 用户编号
	 */
	public void searchShow(String userId) {
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			//加载用户对象
			Vector<UserVO> user = new Vector<UserVO>();
			//输入需要页号和页数
			System.out.println("请输入您的用户编号");
			userId = br.readLine();		
			UserController uc = new UserController();
			//调用用户控制器中的searchUserById方法，进行查询用户操作
			user = uc.searchUserById(userId);
			System.out.println("账号："+user.get(0).getUserId()+"    姓名："+user.get(0).getName()
					+"    邮箱："+user.get(0).getMail()+"    密码："+user.get(0).getPass()
					+"    权限："+user.get(0).getPower()+"    出生日期："+user.get(0).getBirth());
		} catch (Exception e) {
			//显示异常信息
			System.out.println("查询失败! ! !"+e.getMessage());
		}	
	}

}
