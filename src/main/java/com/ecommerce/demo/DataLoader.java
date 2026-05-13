package com.ecommerce.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class DataLoader implements CommandLineRunner {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("========================================");
        System.out.println("🔄 Checking products in database...");
        
        // Check if products exist
        Long count = 0L;
        try {
            Object result = entityManager.createNativeQuery("SELECT COUNT(*) FROM products").getSingleResult();
            if (result instanceof Number) {
                count = ((Number) result).longValue();
            }
        } catch (Exception e) {
            System.out.println("Products table not found, will create it...");
        }
        
        if (count == 0) {
            System.out.println("📦 Loading 41 products with your images...");
            
            // Insert products with your local images
            String insertSQL = 
                "INSERT INTO products (id, name, price, original_price, discount, category, brand, stock, rating, rating_count, description, image_url) VALUES " +
                "(1, 'iPhone 15 Pro Max', 129900, 149900, '13% off', 'Electronics', 'Apple', 50, 4.8, 1245, 'Latest iPhone', '/images/laptop.jpg')," +
                "(2, 'Samsung Galaxy S24', 79999, 99999, '20% off', 'Electronics', 'Samsung', 60, 4.7, 892, 'Samsung flagship', '/images/laptop.jpg')," +
                "(3, 'MacBook Pro M3', 169900, 189900, '10% off', 'Electronics', 'Apple', 30, 4.9, 2341, 'Powerful laptop', '/images/laptop.jpg')," +
                "(4, 'Sony WH-1000XM5', 29990, 39990, '25% off', 'Electronics', 'Sony', 100, 4.8, 892, 'Noise cancellation', '/images/laptop.jpg')," +
                "(5, 'Nike Air Max 270', 8999, 12999, '31% off', 'Fashion', 'Nike', 200, 4.5, 567, 'Running shoes', '/images/laptop.jpg')," +
                "(6, 'Maybelline Foundation', 449, 899, '50% off', 'Beauty', 'Maybelline', 300, 4.2, 311, 'Liquid foundation', '/images/maybeline.jpg')," +
                "(7, 'Nyx Eyeshadow Palette', 899, 1799, '50% off', 'Beauty', 'Nyx', 250, 4.7, 456, '12-color palette', '/images/nyx-professional.jpg')," +
                "(8, 'Parker Pen Set', 299, 699, '57% off', 'Stationery', 'Parker', 500, 4.7, 456, 'Premium pen', '/images/paker.jpg')," +
                "(9, 'Men Cotton T-Shirt', 499, 1299, '61% off', 'Fashion', 'Roadster', 500, 4.3, 2341, 'Premium cotton', '/images/laptop.jpg')," +
                "(10, 'Women Floral Top', 699, 1999, '65% off', 'Fashion', 'Here&Now', 400, 4.4, 1876, 'Beautiful top', '/images/laptop.jpg')," +
                "(11, 'Men Slim Jeans', 999, 2999, '66% off', 'Fashion', 'Levis', 300, 4.5, 3456, 'Stretchable jeans', '/images/laptop.jpg')," +
                "(12, 'Women Denim Jacket', 1499, 4999, '70% off', 'Fashion', 'Zara', 200, 4.6, 1234, 'Classic jacket', '/images/laptop.jpg')," +
                "(13, 'AirPods Pro 2', 24900, 29900, '17% off', 'Electronics', 'Apple', 150, 4.7, 2341, 'ANC earbuds', '/images/laptop.jpg')," +
                "(14, 'iPad Pro', 99900, 119900, '17% off', 'Electronics', 'Apple', 80, 4.8, 1567, 'M2 tablet', '/images/laptop.jpg')," +
                "(15, 'PS5 Console', 49990, 54990, '9% off', 'Gaming', 'Sony', 45, 4.9, 4321, 'Next-gen gaming', '/images/laptop.jpg')," +
                "(16, 'Adidas Ultraboost', 12999, 17999, '28% off', 'Fashion', 'Adidas', 180, 4.6, 2876, 'Running shoes', '/images/laptop.jpg')," +
                "(17, 'Ray-Ban Sunglasses', 5999, 9999, '40% off', 'Accessories', 'Ray-Ban', 120, 4.7, 987, 'Classic aviator', '/images/laptop.jpg')," +
                "(18, 'Fossil Gen 6 Watch', 19999, 29999, '33% off', 'Accessories', 'Fossil', 90, 4.5, 654, 'Smart watch', '/images/laptop.jpg')," +
                "(19, 'Memory Foam Mattress', 14999, 29999, '50% off', 'Home', 'Wakefit', 75, 4.6, 432, 'Orthopedic', '/images/laptop.jpg')," +
                "(20, 'Air Fryer', 7999, 12999, '38% off', 'Appliances', 'Philips', 110, 4.7, 876, 'Oil-free', '/images/laptop.jpg')," +
                "(21, 'Robot Vacuum', 24999, 39999, '38% off', 'Appliances', 'Mi', 65, 4.5, 543, 'Smart navigation', '/images/laptop.jpg')," +
                "(22, 'Lakme Lipstick', 499, 999, '50% off', 'Beauty', 'Lakme', 400, 4.4, 654, 'Matte finish', '/images/laptop.jpg')," +
                "(23, 'Men Watch', 2999, 9999, '70% off', 'Accessories', 'Fastrack', 180, 4.7, 456, 'Analog watch', '/images/laptop.jpg')," +
                "(24, 'Designer Handbag', 1499, 4999, '70% off', 'Accessories', 'Lavie', 140, 4.7, 345, 'Sling bag', '/images/laptop.jpg')," +
                "(25, 'Leather Wallet', 599, 1999, '70% off', 'Accessories', 'Wildhorn', 350, 4.4, 876, 'Genuine leather', '/images/laptop.jpg')," +
                "(26, 'Laptop Backpack', 1299, 3999, '67% off', 'Stationery', 'Skybags', 250, 4.6, 341, 'USB backpack', '/images/laptop.jpg')," +
                "(27, 'Study Lamp', 899, 2499, '64% off', 'Stationery', 'Philips', 300, 4.5, 876, 'LED lamp', '/images/laptop.jpg')," +
                "(28, 'Water Bottle', 399, 999, '60% off', 'Sports', 'Milton', 450, 4.5, 876, 'Insulated', '/images/laptop.jpg')," +
                "(29, 'Yoga Mat', 999, 1999, '50% off', 'Sports', 'Decathlon', 380, 4.4, 654, 'Non-slip', '/images/laptop.jpg')," +
                "(30, 'Fitness Tracker', 2499, 4999, '50% off', 'Sports', 'Mi', 220, 4.3, 543, 'Activity tracker', '/images/laptop.jpg')," +
                "(31, 'Gold Necklace', 2499, 7999, '69% off', 'Jewellery', 'PC Jeweller', 150, 4.6, 234, 'Diamond necklace', '/images/laptop.jpg')," +
                "(32, 'Golden Earrings', 899, 2999, '70% off', 'Jewellery', 'Caratlane', 300, 4.5, 345, 'Trendy earrings', '/images/laptop.jpg')," +
                "(33, 'Wall Painting', 1499, 4999, '70% off', 'Home Decor', 'Art Street', 150, 4.6, 234, 'Canvas art', '/images/laptop.jpg')," +
                "(34, 'Wall Clock', 1299, 3999, '67% off', 'Home Decor', 'Ajanta', 200, 4.6, 345, 'Modern clock', '/images/laptop.jpg')," +
                "(35, 'Cushion Cover Set', 599, 1999, '70% off', 'Home Decor', 'Home Centre', 400, 4.4, 876, 'Set of 4', '/images/laptop.jpg')," +
                "(36, 'Bean Bag', 3999, 7999, '50% off', 'Home', 'Urban Ladder', 120, 4.4, 345, 'Comfortable', '/images/laptop.jpg')," +
                "(37, 'Bookshelf', 8999, 14999, '40% off', 'Home', 'IKEA', 80, 4.5, 234, '5-tier', '/images/laptop.jpg')," +
                "(38, 'Men Formal Shirt', 799, 2499, '68% off', 'Fashion', 'Arrow', 350, 4.4, 987, 'Cotton formal', '/images/laptop.jpg')," +
                "(39, 'Women Watch', 2499, 4999, '50% off', 'Accessories', 'Titan', 280, 4.6, 341, 'Rose gold', '/images/laptop.jpg')," +
                "(40, 'OnePlus 12', 64999, 74999, '13% off', 'Electronics', 'OnePlus', 70, 4.5, 654, 'Flagship killer', '/images/laptop.jpg')," +
                "(41, 'Google Pixel 8 Pro', 106999, 124999, '14% off', 'Electronics', 'Google', 55, 4.6, 543, 'Best camera', '/images/laptop.jpg')";
            
            entityManager.createNativeQuery(insertSQL).executeUpdate();
            
            System.out.println("✅ 41 products loaded successfully!");
        } else {
            System.out.println("✅ Products already exist: " + count);
        }
        
        System.out.println("========================================");
    }
}