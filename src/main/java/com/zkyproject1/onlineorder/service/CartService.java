package com.zkyproject1.onlineorder.service;

import com.zkyproject1.onlineorder.dao.CartDao;
import com.zkyproject1.onlineorder.entity.Cart;
import com.zkyproject1.onlineorder.entity.Customer;
import com.zkyproject1.onlineorder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CustomerService customerService;

    public Cart getCart() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);

        if (customer != null) {
            Cart cart = customer.getCart();
            double totalPrice = 0;
            for (OrderItem item : cart.getOrderItemList()) {
                totalPrice += item.getPrice() * item.getQuantity();
            }
            cart.setTotalPrice(totalPrice);
            return cart;
        }
        return new Cart();
    }

    public void cleanCart() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);
        if (customer  != null) cartDao.removeAllCartItems(customer.getCart());
    }


}
