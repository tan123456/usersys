package com.lingnan.usersys.common.exception;

/**
 * service异常类
 * @author Administrator
 *
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 默认构造方法
	 * 
	 */
	public ServiceException() {	
	}
	
	/**
	 * 构造方法
	 * @param arg0异常的详细信息
	 */
	public ServiceException(String arg0) {	
		super(arg0);
	}
	
	/**
	 * 构造方法
	 * @param arg0异常的详细信息
	 * @param arg1产生异常的原因
	 */
	public ServiceException(String arg0, Throwable arg1) {	
		super(arg0, arg1);
	}
	
	/**
	 * 构造方法
	 * @param arg1产生异常的原因
	 */
	public ServiceException(Throwable arg1) {	
		super(arg1);
	}
}
