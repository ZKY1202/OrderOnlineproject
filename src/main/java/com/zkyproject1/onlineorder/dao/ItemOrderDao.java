package com.zkyproject1.onlineorder.dao;

import com.zkyproject1.onlineorder.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    // 这里不能用try with resource 因为需要check if session == null
    public void save(OrderItem orderItem) {// 添加购物车里的item, 写操作
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(orderItem);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
