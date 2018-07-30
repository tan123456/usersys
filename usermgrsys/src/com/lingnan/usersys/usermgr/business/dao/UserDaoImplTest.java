package com.lingnan.usersys.usermgr.business.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.lingnan.usersys.common.util.DBUtils;
import com.lingnan.usersys.usermgr.domain.UserVO;

public class UserDaoImplTest {

	@Test
	public void testLogin() {
		UserDaoImpl impl = new UserDaoImpl(DBUtils.getConnection());
		Date date = new Date();
		UserVO user = new UserVO();
		user.setId(6);
		user.setUserId("6");
		user.setName("ll");
		user.setPass("ll");
		user.setMail("aa@qq.com");	
		user.setPower("普通用户");
		user.setBirth(date);
		
		boolean flag = impl.addUser(user);
		System.out.println(flag);
		
	}

}
