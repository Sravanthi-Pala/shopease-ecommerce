package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> getUserCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }
}