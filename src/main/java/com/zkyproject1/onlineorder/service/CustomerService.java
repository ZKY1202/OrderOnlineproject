package com.zkyproject1.onlineorder.service;

import com.zkyproject1.onlineorder.dao.CustomerDao;
import com.zkyproject1.onlineorder.entity.Cart;
import com.zkyproject1.onlineorder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public void signUp(Customer customer) {
        customer.setEnabled(true);

        Cart cart = new Cart();
        customer.setCart(cart);

        customerDao.signUp(customer);
    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }
}
