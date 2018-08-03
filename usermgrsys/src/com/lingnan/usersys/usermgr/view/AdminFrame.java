package com.lingnan.usersys.usermgr.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Vector;

import com.lingnan.usersys.common.util.TypeUtils;
import com.lingnan.usersys.usermgr.controller.UserController;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用于用户管理，权限分为普通用户和管理员
 * @author Administrator
 *
 */
public class AdminFrame extends NormalFrame{
	
	/**
	 * 带参数的构造器，用于初始化user属性
	 * @param user 用户信息
	 */
	public AdminFrame(UserVO user) {
		super(user);
	}
	
	public void loginSuccShow() {
		
		System.out.println("欢迎登录主窗体");
		System.out.println(" 您好:  "+user.getName()+"  您的权限是: "+user.getPower());
		System.out.println("============================");
		if (user.getPower().equals("管理员")) {
			this.show();
		} else {
			new NormalFrame(user).show();
		}
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
			System.out.println("添加用户--------------------1");
			System.out.println("删除用户--------------------2");
			System.out.println("修改用户--------------------3");
			System.out.println("查询用户--------------------4");
			System.out.println("程序退出--------------------5");
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
					System.out.println("输入错误，只能输入1~5的数字。");
					System.out.println("请您重新输入");
				}	
			}
			/*
			 * 判断用户输入值，如果值为1，进行添加用户操作
			 * 如果值为2，进行删除用户操作，如果值为3，进行修改用户操作，
			 * 如果值为4，进行查询用户操作，如果值为5，退回上一级
			 */
			switch (i) {
			case 1:
				this.addShow("");
				break;
			case 2:
				this.deleteShow(user.getUserId());
				break;//中断switch
			case 3:
			    this.updateShow("管理员", user);
				break;//中断switch
			case 4:
				this.searchShow();
				break;//中断switch
			case 5:
				System.out.println("感谢您使用本软件!!");
				//退出当前界面
				System.exit(0);

			default: //输入值是1-5之外的数字
				System.out.println("您输入的操作不正确，请重新输入。");
			}
			
		}
	}
	
	
	/**
	 * 进行删除用户的操作页面和操作结果
	 * @param uid 用户编号
	 */
	public void deleteShow(String uid) {
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			//加载用户对象
			UserVO user = new UserVO();
			//输入需要删除的用户的id
			System.out.println("请输入您的用户编号");
			uid = br.readLine();
			UserController uc = new UserController();
			//调用用户控制器中的doDeleteUser方法，进行删除用户操作
			Boolean flag = uc.doDeleteUser(uid); 
			if (flag) {			
				System.out.println("删除成功!");
			} else {		
				System.out.println("删除失败");
			}
		} catch (Exception e) {
			//显示异常信息
			System.out.println("删除失败! ! !"+e.getMessage());
		}	
	}
	
	
	/**
	 * 进行修改用户的操作页面和操作结果
	 */
	public void updateShow(String type, UserVO user) {
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if ("管理员".equals(type)){
			System.out.println("修改用户界面");
			System.out.println("========================");
		} 

		try {
			//以行为单位，读取输入的各个值，赋值给用户对象的各个属性
			System.out.println("请输入您要修改的用户的编号");
			user.setUserId(br.readLine());
			System.out.println("请输入您修改的用户名");
			user.setName(br.readLine());
			user.setId(Integer.parseInt(user.getUserId()));
			System.out.println("请输入您修改的用户的密码");
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
			System.out.println("请输入您修改的用户的权限(管理员/普通用户)");
			user.setPower(br.readLine());
			//System.out.println(user.getId()+user.getUserId()+user.getName()+user.getMail()+user.getPass()+user.getPower()+user.getBirth());

			UserController uc = new UserController();
			//调用用户控制器中的doUpdateUser方法，进行用户注册操作
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
	 * 进行用户查询的操作页面和操作结果
	 */
	public void searchShow() {
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			//显示选择查询后的操作界面
            System.out.println("请选择您要查询的方式：");
			System.out.println("==============================");
			System.out.println("分页查询用户------------------1");
			System.out.println("按用户编号查询(精准)----------2");
			System.out.println("按用户名查询(模糊)------------3");
			System.out.println("退出系统---------------- -----4");
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
		 * 判断用户输入值，如果值为1，进行添加用户操作
		 * 如果值为2，进行删除用户操作，如果值为3，进行修改用户操作，
		 * 如果值为4，进行查询用户操作，如果值为5，退回上一级
		 */
		switch (i) {
		case 1:
			this.searchAllShow(1, 1);
			break;
		case 2:
			this.searchByIdShow("");
			break;//中断switch
		case 3:
			this.searchByNameShow("");
			break;//中断switch
		case 4:
			System.out.println("感谢您使用本软件!!");
			//退出当前界面
			System.exit(0);

		default: //输入值是1-4之外的数字
			System.out.println("您输入的操作不正确，请重新输入。");
		}
		}
	}
	
	
	/**
	 * 进行分页查询用户的操作页面和操作结果
	 * @param pageNum 页号
	 * @param pageSize 每页的记录数量
	 */
	public void searchAllShow(int pageNum, int pageSize) {
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			//加载用户对象
			Vector<UserVO> user = new Vector<UserVO>();
			//输入需要页号和页数
			System.out.println("请输入您要查看的页号");
			pageNum = Integer.parseInt(br.readLine());
			System.out.println("请输入每页查询的记录数量");
			pageSize = Integer.parseInt(br.readLine());
			UserController uc = new UserController();
			//调用用户控制器中的searchAllUser方法，进行查询用户操作
			user = uc.searchAllUser(pageNum, pageSize);
			//声明一个变量，用于保存查询结果记录的数量
			Iterator it=user.iterator();
		    while(it.hasNext())
		    {
		    	UserVO vo=(UserVO)it.next();
		    	System.out.println("账号："+vo.getUserId()+"    姓名："+vo.getName()+"    密码："+vo.getPass()+
		    			"    邮箱："+vo.getMail()+"    权限："+vo.getPower()+"    状态："+
		    			vo.getStatus()+"    出生日期："+vo.getBirth()+"    邮件："+vo.getMail());
		    }
		} catch (Exception e) {
			//显示异常信息
			System.out.println("查询失败! ! !"+e.getMessage());
		}	
	}
	
	/**
	 * 进行按id查询的操作界面和操作结果
	 * @param userId 用户编号
	 */
	public void searchByIdShow(String userId) {
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			//加载用户对象
			Vector<UserVO> user = new Vector<UserVO>();
			//输入需要页号和页数
			System.out.println("请输入您要查询的用户编号");
			userId = br.readLine();
			
			UserController uc = new UserController();
			//调用用户控制器中的searchAllUser方法，进行查询用户操作
			user = uc.searchUserById(userId);
			//声明一个变量，用于保存查询结果记录的数量
			Iterator it=user.iterator();
		    while(it.hasNext())
		    {
		    	UserVO vo=(UserVO)it.next();
		    	System.out.println("账号："+vo.getUserId()+"    姓名："+vo.getName()+"    密码："+vo.getPass()+
		    			"    邮箱："+vo.getMail()+"    权限："+vo.getPower()+"    状态："+
		    			vo.getStatus()+"    出生日期："+vo.getBirth()+"    邮件："+vo.getMail());
		    }
		} catch (Exception e) {
			//显示异常信息
			System.out.println("查询失败! ! !"+e.getMessage());
		}	
	}
	
	/**
	 * 进行按名字查询的操作界面和操作结果
	 * @param name 用户名
	 */
	public void searchByNameShow(String name) {
		//声明缓冲处理对象，用于接受控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			//加载用户对象
			Vector<UserVO> user = new Vector<UserVO>();
			//输入需要页号和页数
			System.out.println("请输入您要查询的用户名字");
			name = br.readLine();
			
			UserController uc = new UserController();
			//调用用户控制器中的searchAllUser方法，进行查询用户操作
			user = uc.searchUserByName(name);
			//声明一个变量，用于保存查询结果记录的数量
			Iterator it=user.iterator();
		    while(it.hasNext())
		    {
		    	UserVO vo=(UserVO)it.next();
		    	System.out.println("账号："+vo.getUserId()+"    姓名："+vo.getName()+"    密码："+vo.getPass()+
		    			"    邮箱："+vo.getMail()+"    权限："+vo.getPower()+"    状态："+
		    			vo.getStatus()+"    出生日期："+vo.getBirth()+"    邮件："+vo.getMail());
		    }
		} catch (Exception e) {
			//显示异常信息
			System.out.println("查询失败! ! !"+e.getMessage());
		}	
	}
	
	
	

	
}
