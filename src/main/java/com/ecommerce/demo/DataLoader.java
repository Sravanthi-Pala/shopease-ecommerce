package com.ecommerce.demo;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private ProductService productService;
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("========================================");
        System.out.println("🔄 DataLoader Started - Checking products...");
        
        List<Product> existingProducts = productService.getAllProducts();
        
        if (existingProducts.isEmpty()) {
            System.out.println("📦 No products found. Loading 41 products...");
            
            List<Product> products = Arrays.asList(
                new Product("iPhone 15 Pro Max", 129900.0, 149900.0, "13% off", "Electronics", "Apple", 50, 4.8, 1245, "Latest iPhone", "/images/laptop.jpg"),
                new Product("Samsung Galaxy S24 Ultra", 129999.0, 149999.0, "13% off", "Electronics", "Samsung", 60, 4.7, 892, "Samsung flagship", "/images/laptop.jpg"),
                new Product("MacBook Pro M3", 169900.0, 189900.0, "10% off", "Electronics", "Apple", 30, 4.9, 2341, "Powerful laptop", "/images/laptop.jpg"),
                new Product("Sony WH-1000XM5", 29990.0, 39990.0, "25% off", "Electronics", "Sony", 100, 4.8, 892, "Noise cancellation", "/images/laptop.jpg"),
                new Product("Nike Air Max 270", 8999.0, 12999.0, "31% off", "Footwear", "Nike", 200, 4.5, 567, "Running shoes", "/images/laptop.jpg"),
                new Product("Maybelline Foundation", 449.0, 899.0, "50% off", "Beauty", "Maybelline", 300, 4.2, 311, "Liquid foundation", "/images/maybeline.jpg"),
                new Product("Nyx Eyeshadow Palette", 899.0, 1799.0, "50% off", "Beauty", "Nyx", 250, 4.7, 456, "12-color palette", "/images/nyx-professional.jpg"),
                new Product("Parker Pen Set", 299.0, 699.0, "57% off", "Stationery", "Parker", 500, 4.7, 456, "Premium pen", "/images/paker.jpg"),
                new Product("Men Cotton T-Shirt", 499.0, 1299.0, "61% off", "Fashion", "Roadster", 500, 4.3, 2341, "Premium cotton", "/images/laptop.jpg"),
                new Product("Women Floral Top", 699.0, 1999.0, "65% off", "Fashion", "Here&Now", 400, 4.4, 1876, "Beautiful top", "/images/laptop.jpg")
                // Add remaining 31 products here...
            );
            
            productService.saveAllProducts(products);
            System.out.println("✅ " + products.size() + " products loaded successfully!");
        } else {
            System.out.println("✅ Products already exist: " + existingProducts.size());
        }
        
        System.out.println("========================================");
    }
}
