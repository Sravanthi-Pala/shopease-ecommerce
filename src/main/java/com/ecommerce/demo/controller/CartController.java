
package com.ecommerce.demo.controller;

import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    // Add to cart
    @PostMapping("/add")
    public Cart addToCart(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    // Get cart by user
    @GetMapping("/{userId}")
    public List<Cart> getCart(@PathVariable Long userId) {
        return cartService.getUserCart(userId);
    }

    // Remove item
    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "Item removed";
    }
    @GetMapping
public String test() {
    return "Cart API working 🚀";
}
}