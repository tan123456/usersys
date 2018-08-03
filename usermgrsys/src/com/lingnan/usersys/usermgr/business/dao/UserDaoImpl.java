package com.lingnan.usersys.usermgr.business.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lingnan.usersys.common.exception.DaoException;
import com.lingnan.usersys.common.util.DBUtils;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用户dao接口的实现类
 * @author Administrator
 *
 */
public class UserDaoImpl implements UserDao {
	/**
	 * 数据库连接
	 */
	private Connection conn;
	
	/**
	 * 构造方法
	 * 
	 * @param conn 数据库连接
	 */
	
	public UserDaoImpl(Connection conn) {
		//给属性赋初始化值
		this.conn = conn;
	}
	
	
	/**
	 * 用户登录
	 * @param name 用户名
	 * @param password 密码
	 * @return 用户信息
	 */
	public UserVO login(String name, String password) {
		//声明结果集对象变量，用于保存数据库查询结果
		ResultSet rs = null;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement pstam = null;
		//声明用户对象变量，用于保存从结果集中提取出来的数据
		UserVO user = null;
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象
			pstam = conn.prepareStatement("select * from t_user where name = ? and pass = ? and status = '0'");
			//调用预编译对象的setXxx方法，给？号赋值
			pstam.setString(1, name);
			pstam.setString(2, password);
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = pstam.executeQuery();
			//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中
			if (rs.next()) {
				//创建一个新用户对象，赋值给用户对象变量
				user = new UserVO();
				/*
				 * 调用结果集对象的getXxx方法，取出各个字段的值，
				 * 然后再调用用户对象的setXxx方法，给属性赋值
				 * 最后新新创建的对象中包含了查询结果中的所有字段的值
				 */
				user.setId(rs.getInt("id"));
				user.setUserId(rs.getString("userId"));
				user.setName(rs.getString("name"));
				user.setMail(rs.getString("mail"));
				user.setPass(rs.getString("pass"));
				user.setPower(rs.getString("power"));
				user.setBirth(rs.getDate("birth"));
			}
			//如果出现异常，输出异常信息
		} catch (Exception e) {
			System.out.println("在插入用户的时候出错了，错误信息是："+e.getMessage());
			//将异常封装成自定义异常
			throw new DaoException("登录时查询数据出错");
		} finally {
			//调用数据库工具类，关闭结果集和声明对象
			DBUtils.closeStatement(rs, pstam);
		}
		/**
		 * 最后，返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 * 如果查询结果为空，该对象为空值null
		 */
		return user;
	}
	
	/**
	 * 查找最大id值
	 * @return 最大id值
	 */
	public int findMaxId() {
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement pstam = null;
		
		ResultSet rs = null;
		//声明用户对象变量，用于保存从结果集中提取出来的数据
		UserVO u = new UserVO();
		int i;
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象
			pstam = conn.prepareStatement("select max(id) from t_user");
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = pstam.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DaoException("查询失败", e);
		} finally {
			// 调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(rs, pstam);
	    }
	
	}
	/**
	 * 注册用户/添加用户
	 * @param user 用户信息
	 * @return 成功返回true，失败返回false
	 */
	public boolean addUser(UserVO user) {
		//先判断参数是否为空，如果不为空，进行数据库插入操作
		if (user != null) {
			//声明预编译的声明对象变量，用于进行数据库操作的载体
			PreparedStatement pstam = null;
			//声明变量，用于保存数据库更新结果
			int result = -1;
			
			try {
				//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象
				pstam = conn.prepareStatement("insert into t_user values (?, ?, ?, ?, ?,'普通用户', ?, '0')");
				//调用预编译对象的setXxx方法，给？号赋值
				pstam.setInt(1, user.getId());
				pstam.setString(2, user.getUserId());
				pstam.setString(3, user.getName());
				pstam.setString(4, user.getPass());
				pstam.setString(5, user.getMail());
				//将java.util.Date类型转换为java.sql.Date类型
				pstam.setDate(6, new java.sql.Date(user.getBirth().getTime()));
				//调用预编译结果对象的executeUpdate方法，执行插入操作，返回插入结果，赋值给变量
				result = pstam.executeUpdate();				
				//判断插入结果变量，如果结果大于0，数据库插入成功，返回true，否则插入失败，返回false
				if (result > 0) {
					return true;
				} else {
					return false;	
				}
			//如果出现异常，输出异常信息，并返回false
			} catch (SQLException e) {
				System.out.println("在插入用户的时候出错了，错误信息是：" + e.getMessage());
				throw new DaoException("在插入用户时出错了", e);
			} finally {
				try {
					if (pstam != null) 
						pstam.close();
					} catch (SQLException e) {
						//将异常封装为自定义异常
						throw new DaoException("关闭声明对象失败", e);
					}
				}
			}
		//如果参数为空，直接返回false
		else {
			return false;
		}
		
	}
	
	
	/**
	 * 删除
	 * @param uid 用户编号
	 * @return 成功返回true，失败返回false
	 */
	public boolean deleteUser(String uid) {
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement pstam = null;
		//声明用户对象变量，用于保存从结果集中提取出来的数据
		UserVO user = null;
		//声明变量，用于保存数据库更新结果
		int result = -1;
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象
			pstam = conn.prepareStatement("update t_user set status = '1' where id = ? ");
			pstam.setString(1, uid);
			//调用预编译对象的executeUpdate方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			result = pstam.executeUpdate();
			//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中
			if (result > 0) {
				return true;
			}
			else {
				return false;
			}
			//如果出现异常，输出异常信息
			} catch (Exception e) {
				System.out.println("在删除用户的时候出错了，错误信息是："+e.getMessage());
				//将异常封装成自定义异常
				throw new DaoException("登录时查询数据出错");
			} finally {
				try {
					if (pstam != null) 
						pstam.close();
					} catch (SQLException e) {
						//将异常封装为自定义异常
						throw new DaoException("关闭声明对象失败", e);
					}
			}
    }
	
	/**
	 * 修改
	 * @param user 用户信息
	 * @return 成功返回true，失败返回false
	 */
	public boolean updateUser(UserVO user) {
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement pstam = null;
		//声明变量，用于保存数据库更新结果
		int result = -1;
		
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象
			pstam = conn.prepareStatement("update t_user set name = ?, pass = ?, mail=?, birth = ?, power=? where userId = ?");
			//调用预编译对象的setXxx方法，给？号赋值
			pstam.setString(1, user.getName());
			pstam.setString(2, user.getPass());
			pstam.setString(3, user.getMail());
			//将java.util.Date类型转换为java.sql.Date类型
			pstam.setDate(4, new java.sql.Date(user.getBirth().getTime()));
			pstam.setString(5, user.getPower());
			pstam.setString(6, user.getUserId());
			//调用预编译结果对象的executeUpdate方法，执行插入操作，返回插入结果，赋值给变量
			result = pstam.executeUpdate();				
			//判断插入结果变量，如果结果大于0，数据库插入成功，返回true，否则插入失败，返回false
			if (result > 0) {
				return true;
			} else {
				return false;	
			}
		//如果出现异常，输出异常信息，并返回false
		} catch (SQLException e) {
			System.out.println("在更新用户的时候出错了，错误信息是：" + e.getMessage());
			throw new DaoException("在更新用户时出错了", e);
		} finally {
			try {
				if (pstam != null) 
					pstam.close();
				} catch (SQLException e) {
					//将异常封装为自定义异常
					throw new DaoException("关闭声明对象失败", e);
				}
			}
		}
	
	/**
	 * 实例化查询所有用户信息(分页查询)
	 * @param pageNum 页号
	 * @param pageSize 每页的记录数量
	 * @return 用户信息
	 */
	public Vector<UserVO> searchAllUser(int pageNum,int pageSize) {
		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			/*firstIndex:起始索引
			 * pageSize:每页显示的数量
			 * orderColumn:排序的字段名
			 * sql:可以是简单的单表查询语句，也可以是复杂的多表联合查询语句
			 * rownum:行号
			 */
			int count = -1; //初始化数据库中数据的数量
			ps1 = conn.prepareStatement("select count(*) as count from t_user where status='0'");
			rs1 = ps1.executeQuery();
			if(rs1.next()){
				count = rs1.getInt("count"); //计算出数据的总数量
			}
			int a = count % pageSize;
			int allPageNum = -1;
			if(a==0){
				allPageNum = count/pageSize;
			}else{
				allPageNum = (count/pageSize)+1;
			}
			if(pageNum>allPageNum){
				System.out.println("没有该页数，请重新输入1~"+allPageNum+"之间的页数");
			}			
			ps = conn.prepareStatement("select * from (select t2.*,rownum rn from (select t1.* from t_user t1 where status = '0' order by userid) t2) " +
					"where rn>? and rn<=?");
			ps.setInt(1, (pageNum-1)*pageSize);
			ps.setInt(2, pageNum*pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				/*
				 * UserVO uservo = new UserVO()这条语句一定一定要放在while语句里面
				 * 因为每次new的对象都是不一样的，如果在循环外面的话，循环的时候后面的对象会覆盖掉前面的对象
				 */
				UserVO uservo = new UserVO();
				uservo.setId(rs.getInt("id"));
				uservo.setUserId(rs.getString("userId"));
				uservo.setName(rs.getString("name"));
				uservo.setPass(rs.getString("pass"));
				uservo.setPower(rs.getString("power"));
				uservo.setMail(rs.getString("mail"));
				uservo.setBirth(rs.getDate("birth"));
				uservo.setStatus(rs.getString("status"));
				v.add(uservo);
			}
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DaoException("查询所有用户失败", e);
		} finally {
			// 调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(rs, ps);
			DBUtils.closeStatement(rs1, ps1);
		}
		return v;
	}
	
	/**
	 * 实例化根据用户编号查询用户信息(精准查询)
	 * @param userId 用户编号
	 * @return 用户信息
	 */
	public Vector<UserVO> searchUserById(String userId) {
		ResultSet rs = null;
		PreparedStatement pstam = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			pstam = conn.prepareStatement("select * from t_user where userid = '"+ userId + "'");
			rs = pstam.executeQuery();
			if (rs.next()) {
				UserVO user = new UserVO();
				user.setUserId(rs.getString("userId"));
				user.setName(rs.getString("name"));
				user.setPass(rs.getString("pass"));
				user.setPower(rs.getString("power"));
				user.setMail(rs.getString("mail"));
				user.setBirth(rs.getDate("birth"));
				user.setStatus(rs.getString("status"));
				v.add(user);

			}
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DaoException("根据用户编号查询失败", e);
		} finally {
			// 调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(rs, pstam);
		}
		return v;
	}
	
	/**
	 * 实例化根据用户名查询用户信息(模糊查询)
	 * @param name 用户名
	 * @return 用户信息
	 */
	public Vector<UserVO> searchUserByName(String name) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			String userName1 = "%" + name + "%"; // 指定的模糊查询用户名
			ps = conn.prepareStatement("select * from t_user where name like '"
							+ userName1 + "'");
			rs = ps.executeQuery();
			while (rs.next()) {
				UserVO uservo = new UserVO();
				uservo.setId(rs.getInt("id"));
				uservo.setUserId(rs.getString("userId"));
				uservo.setName(rs.getString("name"));
				uservo.setPass(rs.getString("pass"));
				uservo.setPower(rs.getString("power"));
				uservo.setMail(rs.getString("mail"));
				uservo.setBirth(rs.getDate("birth"));
				uservo.setStatus(rs.getString("status"));
				v.add(uservo);
			}
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DaoException("根据用户名查询用户失败", e);
		} finally {
			// 调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(rs, ps);
		}
		return v;
	}

}
