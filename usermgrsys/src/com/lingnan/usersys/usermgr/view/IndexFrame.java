package com.lingnan.usersys.usermgr.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.lingnan.usersys.common.util.TypeUtils;
import com.lingnan.usersys.usermgr.controller.UserController;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用于用户登录和注册操作
 * @author Administrator
 *
 */
public class IndexFrame implements BaseFrame{
	
	/**
	 * 用户登录和注册操作页面
	 */
	public void show(){
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//循环操作
		while (true) {
			//用户登录和注册界面
			System.out.println("欢迎使用lingnan的用户管理系统");
			System.out.println("============================");
			System.out.println("用户登录--------------------1");
			System.out.println("用户注册--------------------2");
			System.out.println("退出程序--------------------3");
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
			 * 判断用户输入值，如果值为1，进行用户登录操作
			 * 如果值为2，进行用户注册操作，如果值为3，退出系统
			 */
			switch (i) {
			case 1:
				this.loginShow();
				break;
			case 2:
				this.addShow("注册");
				break;//中断switch
			case 3:
				System.out.println("感谢您的使用。再会! ");
				//退出当前界面
				System.exit(0);

			default: //输入值是1-3之外的数字
				System.out.println("您输入的操作不正确，请重新输入。");
			}
		}
	}
	public void loginShow() {
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("用户登录界面");
		System.out.println("========================");
		System.out.println("请输入您的用户名");
		try {
			//以行为单位，读取输入的各个值，赋值给用户对象的各个属性
			String name = br.readLine();
			System.out.println("请输入您的密码");
			String password = br.readLine();
			//调用控制器中的doLogin方法，进行用户登录操作
			UserController uc = new UserController();
			UserVO user = uc.doLogin(name, password);     
			//如果返回值不为空，登录成功，显示用户信息操作界面，否则登录失败，显示失败信息
			if (user != null) {
				System.out.println("登录成功");
				System.out.println("-------------------");
				//调用主界面
				AdminFrame m = new AdminFrame(user);
				m.loginSuccShow();
				//退出当前界面
				System.exit(0);
			} else {
				//登录失败，显示失败信息
				System.out.println("登录失败! ! !");
			}
		} catch (Exception e) {
			//显示异常信息
			System.out.println(e.getMessage());
		}
	}
	
	public void addShow(String type) {
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if ("注册".equals(type)){
			System.out.println("用户注册界面");
			System.out.println("========================");
		} else {
			
		}
		
		System.out.println("请输入您的用户名");
		try {
			//加载用户对象
			UserVO user = new UserVO();
			//以行为单位，读取输入的各个值，赋值给用户对象的各个属性
			user.setName(br.readLine());
			System.out.println("请输入您的用户编号");
			user.setUserId(br.readLine());
			System.out.println("请输入您的密码");
			user.setPass(br.readLine());
			while (true) {
				System.out.println("请输入您的邮箱");
				String email = br.readLine();
				if (TypeUtils.checkEmail(email)) {
					user.setMail(email);
					break;
				}
				
			}
			System.out.println("请输入您的出生日期(YYYY-MM-DD)");
			user.setBirth(TypeUtils.strToDate(br.readLine()));

			UserController uc = new UserController();
			//调用用户控制器中的doRegister方法，进行用户注册操作
			Boolean flag = uc.doAddUser(user); 
		} catch (Exception e) {
			//显示异常信息
			System.out.println("注册失败! ! !"+e.getMessage());
		}
		
		
	}
	/**
	 * 查询
	 */
	public void searchShow() {
		
	}
	/**
	 * 修改
	 * @param type
	 * @param user
	 */
	public void updateShow(String type, UserVO user) {
		
	}
	

}
