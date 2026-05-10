package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Find products by category
    List<Product> findByCategory(String category);
    
    // Find products by name containing keyword
    List<Product> findByNameContainingIgnoreCase(String name);
    
    // Search by name or description
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    
    // Find products with price less than
    List<Product> findByPriceLessThan(Double price);
    
    // Find products by brand
    List<Product> findByBrand(String brand);
}