package com.zkyproject1.onlineorder.dao;



import com.zkyproject1.onlineorder.entity.Authorities;
import com.zkyproject1.onlineorder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository  // repository 就是 compoment 的一种
public class CustomerDao {

    @Autowired //
    private SessionFactory sessionFactory;

    public void signUp(Customer customer) {
        Session session = null;
        Authorities authorities = new Authorities();
        authorities.setEmail(customer.getEmail());
        authorities.setAuthorities(("ROLE-USER"));

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public Customer getCustomer(String email) {
        Customer customer = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            customer = session.get(Customer.class, email);

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (session != null) session.close();
        }

        return customer;
    }
}
