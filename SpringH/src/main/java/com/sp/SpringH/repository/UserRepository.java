package com.sp.SpringH.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sp.SpringH.entities.User;
//import com.sp.SpringH.repository.JPATemplate;

@Repository
public class UserRepository{
	@Autowired
    private SessionFactory sessionFactory;
	private Session getSession() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (Exception e) {
            session = sessionFactory.openSession();
        }
        return session;
    }
//	private User user;
	String sql = null;
//	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//	static final String DB_URL = "jdbc:mysql://localhost/test?serverTimezone=UTC";
//	static final String USER = "root";
//	static final String PASS = "aaaa";

	public void createUser(User user) {//註冊 新增user
		System.out.println("進了DAO create user");
//		Boolean result = false;
		System.out.println(user);
		Session session=getSession();
		Transaction trans = session.beginTransaction();
		
		session.persist(user);

        trans.commit();
        //		return result;
	}
	
//	public User readUser(User user) {//登入 提取user的password
//		String dbCode=null;
//		User u = new User();
//		sql = "select * from test.users where username=?";
//		// try JDBC
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//				PreparedStatement stmt = conn.prepareStatement(sql);) {
//			stmt.setString(1, user.getUsername());
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				u.setId(rs.getInt("id"));
//				u.setUsername(rs.getString("username"));
//				u.setPassword(rs.getString("password"));
//				u.setSex(rs.getString("sex"));
//				u.setEmail(rs.getString("email"));
////				System.out.println(u.getPassword());
//				dbCode=rs.getString("password");
//			}
//			rs.close();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////		System.out.println(u);
//		return u;
//	}
//	
//	public Boolean findUsername(String username) {//檢查帳號重覆 return Boolean
//		sql = "select * from test.users where username=?";
//		Boolean isExist=true;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//				PreparedStatement stmt = conn.prepareStatement(sql);) {
//			stmt.setString(1, username);
//			ResultSet rs = stmt.executeQuery();
//			if(!rs.next()) {
//				isExist=false;
//			}
//			rs.close();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(isExist);
//		return isExist;
//	}
//	
//	public String findEmail(String username) {//取出EMAIL
//		sql = "select email from test.users where username=?";
//		String email=null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//				PreparedStatement stmt = conn.prepareStatement(sql);) {
//			stmt.setString(1, username);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) { //需要while嗎?
//				email=rs.getString("email");
//			}
//			rs.close();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return email;
//	}
//	
//	public Boolean putNewCode(User user) {//放置新密碼
//		Boolean result = false;
//		sql = "UPDATE test.users SET password=? WHERE username=?";
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//				PreparedStatement stmt = conn.prepareStatement(sql);) {
//			stmt.setString(1, user.getPassword());
//			stmt.setString(2, user.getUsername());
//			int ins = stmt.executeUpdate();
//			System.out.println("更新結果: "+ ins);
//			if (ins >0) {
//				result = true;
//			}
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

}
