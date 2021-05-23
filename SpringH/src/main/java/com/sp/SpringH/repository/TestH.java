package com.sp.SpringH.repository;

import java.util.List;

import org.hibernate.Query;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import com.sp.SpringH.entities.User;

//@Repository
public class TestH extends JPATemplate{
	
//	@Test
	public void testH() {
        
	}
	
	@Test
	public void create() {
		User user=new User();
        user.setUsername("h2");
        session.persist(user);
	}
	
//	@Test
	public void read() {
		User user=(User) session.get(User.class, 1);
        System.out.println(user.getId()+user.getUsername()+user.getSex());
        //
        List list = session.createSQLQuery("select * from user").list();
        list.stream().forEach(System.out::println);
        //
        List<User> list2 = session.createQuery("from User").list();
        list2.stream().forEach(System.out::println);
        //
        Query query = session.createQuery("from User u where u.id <= ?");
        query.setParameter(0, 2);
        List<User> list3 = query.list();
        System.out.println("3)");
        list3.stream().forEach(System.out::println);
	}
}
