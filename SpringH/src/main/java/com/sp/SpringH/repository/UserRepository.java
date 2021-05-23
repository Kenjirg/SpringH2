package com.sp.SpringH.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.sp.SpringH.entities.User;
//import com.sp.SpringH.repository.JPATemplate;

@Repository
@Transactional
public class UserRepository{
	@Autowired
    private SessionFactory sessionFactory;
	
	private Session getSession() {
        Session s = sessionFactory.getCurrentSession();
        return s;
    }
//	private Session session = getSession();
//	private Session getSession() {
//        Session s = null;
//        try {
//            s = sessionFactory.getCurrentSession();
//        } catch (Exception e) {
//            s = sessionFactory.openSession();
//        }
//        return s;
//    }
//	private User user;

	public void createUser(User user) {//註冊 新增user
		System.out.println("進了DAO create user");
		System.out.println(user);
		
		Session session=getSession();
		//CRUD
		session.persist(user);
		//
//      return result;
	}
	
	public User readUser(User user) {//登入 提取user的password
		System.out.println("進DAO read user by username");
		String dbCode=null;
		User u = new User();
		Session session=getSession();
		//CRUD
		Query query = session.createQuery("from User u where u.username = ?");
        query.setParameter(0, user.getUsername());
        Iterator<User> it=query.iterate();
        u=it.next();
        //
        System.out.println("印u"+u);
        return u;
	}
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
	public User findEmail(String username) {//取出EMAIL
		System.out.println("進DAO read email by username");
		User u = new User();
		Session session=getSession();
		//CRUD
		Query query = session.createQuery("from User u where u.username = ?");
        query.setParameter(0, username);
        
        Iterator<User> it=query.iterate();
        u=it.next();
        //
        System.out.println("印u"+u);
        return u;
	}
//	
	public Boolean putNewCode(User newCodeUser) {//放置新密碼
		System.out.println("進DAO put newCode by id");
		User u=null;
		
		Session session=getSession();
		//CRUD
		u=newCodeUser;
		session.update(u);
		//
		System.out.println(u);
		return true;
	}
	
}
