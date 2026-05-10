package com.ecommerce.demo.controller;

import com.ecommerce.demo.model.Wishlist;
import com.ecommerce.demo.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin
public class WishlistController {

    @Autowired
    private WishlistService service;

    @PostMapping("/add")
    public Wishlist add(@RequestBody Wishlist w) {
        return service.add(w);
    }

    @GetMapping("/{userId}")
    public List<Wishlist> get(@PathVariable Long userId) {
        return service.get(userId);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id) {
        service.remove(id);
        return "Removed";
    }
    @GetMapping("/test")
public String test() {
    return "Wishlist working 🚀";
}
}