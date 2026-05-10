package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Wishlist;
import com.ecommerce.demo.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository repo;

    public Wishlist add(Wishlist w) {
        return repo.save(w);
    }

    public List<Wishlist> get(Long userId) {
        return repo.findByUserId(userId);
    }

    public void remove(Long id) {
        repo.deleteById(id);
    }
}