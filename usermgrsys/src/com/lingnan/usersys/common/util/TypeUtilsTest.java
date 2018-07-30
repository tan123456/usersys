package com.lingnan.usersys.common.util;

import static org.junit.Assert.*;


import java.util.Date;

import org.junit.Test;

public class TypeUtilsTest {

	@Test
//	public void testStrToDate() {
//		Date date = TypeUtils.strToDate("2019-09-09");
//		System.out.println(date);	
//	}
	
	public void testDateToStr() {
	    Date date = new Date(); 
		String str = TypeUtils.dateToStr(date);
		System.out.println(str);	
	}
	
//	public void testCheckEmail() {
//		boolean flag = TypeUtils.checkEmail("aadsaf@qq.com");
//		System.out.println(flag);
//		
//	}
	
//	public void testIsEmpty() {
//		boolean flag = TypeUtils.isEmpty("123456@qq.com");
//		System.out.println(flag);
//		
//	}

}
