package com.nj_projects.cartservice.services;

import com.nj_projects.cartservice.models.Cart;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CartService {
    List<Cart> getAllCart();
    Cart getSingleCart(Long Id);

    List<Cart> getByDateRange(LocalDate start, LocalDate end);
    List<Cart> getUserCart(Long userId);

    Cart addNewCart(Cart cart);

    Cart updateCart(Long Id,Cart cart);

    Cart deleteCart(Long Id);

}
