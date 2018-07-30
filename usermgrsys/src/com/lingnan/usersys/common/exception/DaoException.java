package com.lingnan.usersys.common.exception;

/**
 * dao异常类
 * @author Administrator
 *
 */
public class DaoException extends RuntimeException{
	
	/**
	 * 默认的构造方法
	 * 
	 */
	public DaoException() {
    }
	
	/**
	 * 构造方法
	 * @param arg0异常的详细信息
	 */
	public DaoException(String arg0) {
		super(arg0);
    }
	
	/**
	 * 构造方法
	 * @param arg0异常的详细信息
	 * @param arg1产生异常的原因
	 */
	public DaoException(String arg0, Throwable arg1) {
		super(arg0,arg1);
    }
	
	/**
	 * 构造方法
	 * @param arg1产生异常的原因
	 */
	public DaoException(Throwable arg1) {
		super(arg1);
    }

}
