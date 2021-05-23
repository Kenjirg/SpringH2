package com.sp.SpringH.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JPATemplate {
	private ClassPathXmlApplicationContext ctx;
    private SessionFactory sessionFactory;
    protected Session session;
    private Transaction trans;
    
    @org.junit.Before
    public void Before() {
    	ctx = new ClassPathXmlApplicationContext("jpa-config.xml");
    	
    	// 取得連線工廠
    	sessionFactory = ctx.getBean("sessionFactory", SessionFactory.class);
    	// 取得連線物件
    	session = sessionFactory.getCurrentSession();
    	// 取得交易管理並開始
        trans = session.beginTransaction();
    }
	
	@org.junit.After
    public void After() {
		trans.commit(); // 提交
        sessionFactory.close(); // 關閉
        ctx.close();
    }
}
