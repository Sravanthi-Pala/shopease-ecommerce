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
        try {
            System.out.println("🔄 Checking if products need to be loaded...");
            
            // Check if products table exists and has data
            Long count = 0L;
            try {
                Object result = entityManager
                    .createNativeQuery("SELECT COUNT(*) FROM products")
                    .getSingleResult();
                
                if (result instanceof Number) {
                    count = ((Number) result).longValue();
                } else if (result != null) {
                    count = Long.parseLong(result.toString());
                }
            } catch (Exception e) {
                System.out.println("Products table not found, will create and load data");
                count = 0L;
            }
            
            if (count == 0) {
                System.out.println("📦 Loading 41 products into database...");
                
                // Insert all 41 products
                String insertSQL = 
                    "INSERT INTO products (name, price, original_price, discount, category, brand, stock, rating, rating_count, description, image_url) VALUES " +
                    "('Men Cotton T-Shirt', 499, 1299, '61% off', 'Fashion', 'Roadster', 500, 4.3, 2341, 'Premium cotton t-shirt', 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=300')," +
                    "('Women Floral Top', 699, 1999, '65% off', 'Fashion', 'Here&Now', 400, 4.4, 1876, 'Beautiful floral top', 'https://images.unsplash.com/photo-1515372039744-b8f02a3ae446?w=300')," +
                    "('Men Slim Jeans', 999, 2999, '66% off', 'Fashion', 'Levis', 300, 4.5, 3456, 'Stretchable slim jeans', 'https://images.unsplash.com/photo-1541099649105-f69ad21f3246?w=300')," +
                    "('Women Denim Jacket', 1499, 4999, '70% off', 'Fashion', 'Zara', 200, 4.6, 1234, 'Classic denim jacket', 'https://images.pexels.com/photos/2065200/pexels-photo-2065200.jpeg?w=300')," +
                    "('Men Formal Shirt', 799, 2499, '68% off', 'Fashion', 'Arrow', 350, 4.4, 987, 'Cotton formal shirt', 'https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=300')," +
                    "('Nike Free RN 2018 Red', 1999, 4999, '60% off', 'Footwear', 'Nike', 300, 4.6, 2345, 'Red Nike Free RN 2018', 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=300')," +
                    "('Nike Air Force 1 Shadow Pastel', 1799, 4499, '60% off', 'Footwear', 'Nike', 280, 4.5, 1876, 'Pastel color AF1 Shadow', 'https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?w=300')," +
                    "('Women Heels', 999, 2999, '66% off', 'Footwear', 'Bata', 250, 4.3, 1234, 'Party wear heels', 'https://images.unsplash.com/photo-1543163521-1bf539c55dd2?w=300')," +
                    "('iPhone 15', 71900, 79900, '10% off', 'Electronics', 'Apple', 50, 4.8, 1245, 'Latest iPhone', 'https://images.unsplash.com/photo-1695048133142-1a20484d2569?w=300')," +
                    "('Sony Headphones', 29990, 39990, '25% off', 'Electronics', 'Sony', 100, 4.7, 892, 'Noise cancellation', 'https://images.unsplash.com/photo-1618366712010-f4ae9c647dcb?w=300')," +
                    "('MacBook Pro', 169900, 189900, '10% off', 'Electronics', 'Apple', 30, 4.9, 2341, 'Powerful laptop', 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=300')," +
                    "('Samsung Galaxy S21 Ultra', 52999, 105999, '50% off', 'Electronics', 'Samsung', 60, 4.7, 543, 'S21 Ultra', 'https://images.unsplash.com/photo-1610945415295-d9bbf067e59c?w=300')," +
                    "('Wall Painting', 1499, 4999, '70% off', 'Home Decor', 'Art Street', 150, 4.6, 1234, 'Canvas painting', 'https://images.unsplash.com/photo-1513519245088-0e12902e5a38?w=300')," +
                    "('Wall Clock', 1299, 3999, '67% off', 'Home Decor', 'Ajanta', 200, 4.6, 2345, 'Modern wall clock', 'https://images.unsplash.com/photo-1563861826100-9cb868fdbe1c?w=300')," +
                    "('Cushion Cover Set', 599, 1999, '70% off', 'Home Decor', 'Home Centre', 400, 4.4, 1876, 'Set of 4 covers', 'https://images.unsplash.com/photo-1616486338812-3dadae4b4ace?w=300')," +
                    "('Gold Necklace', 2499, 7999, '68% off', 'Jewellery', 'PC Jeweller', 150, 4.6, 1234, 'American diamond necklace', 'https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=300')," +
                    "('Golden Earrings', 899, 2999, '70% off', 'Jewellery', 'Caratlane', 300, 4.5, 2345, 'Golden earrings', 'https://images.unsplash.com/photo-1617038220319-276d3cfab638?w=300')," +
                    "('Silver Anklet', 299, 899, '66% off', 'Jewellery', 'GIVA', 500, 4.4, 654, 'Anklet with charms', 'https://images.unsplash.com/photo-1617038220319-276d3cfab638?w=300')," +
                    "('Men Watch', 2999, 9999, '70% off', 'Accessories', 'Fastrack', 250, 4.7, 3456, 'Analog watch', 'https://images.unsplash.com/photo-1524805444758-089113d48a6d?w=300')," +
                    "('Women Watch', 2499, 4999, '50% off', 'Accessories', 'Titan', 280, 4.6, 2341, 'Rose gold watch', 'https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?w=300')," +
                    "('Designer Handbag', 1499, 4999, '70% off', 'Accessories', 'Lavie', 200, 4.7, 2345, 'Sling bag', 'https://images.unsplash.com/photo-1591561954557-26941169b49e?w=300')," +
                    "('Leather Wallet', 599, 1999, '70% off', 'Accessories', 'Wildhorn', 400, 4.4, 1876, 'Leather wallet', 'https://images.unsplash.com/photo-1622560480605-d83c853bc5c3?w=300')," +
                    "('Laptop Backpack', 1299, 3999, '67% off', 'Stationery', 'Skybags', 250, 4.6, 2341, 'USB backpack', 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=300')," +
                    "('Study Lamp', 899, 2499, '64% off', 'Stationery', 'Philips', 300, 4.5, 1876, 'LED desk lamp', 'https://images.unsplash.com/photo-1507473885765-e6ed057f782c?w=300')," +
                    "('Parker Pen Set', 299, 699, '57% off', 'Stationery', 'Parker', 500, 4.7, 3456, 'Premium pen set', 'https://images.unsplash.com/photo-1586074299757-dc655f1851f1?w=300')," +
                    "('Notebook Pack', 399, 1199, '66% off', 'Stationery', 'Classmate', 800, 4.4, 4567, '5 notebooks', 'https://images.unsplash.com/photo-1531346878377-a5be20888e57?w=300')," +
                    "('Water Bottle', 399, 999, '60% off', 'Stationery', 'Milton', 450, 4.5, 1876, 'Steel insulated', 'https://images.unsplash.com/photo-1602143407151-7111542de6e8?w=300')," +
                    "('Lakme Absolute Lipstick', 499, 999, '50% off', 'Beauty', 'Lakme', 500, 4.5, 2341, 'Matte finish lipstick', 'https://images.unsplash.com/photo-1586495777744-4413f21062fa?w=300')," +
                    "('Maybelline Foundation', 449, 584, '23% off', 'Beauty', 'Maybelline', 150, 4.2, 311, 'Liquid foundation', 'https://images.unsplash.com/photo-1571781926291-c4771a89292c?w=300')," +
                    "('Nyx Eyeshadow Palette', 899, 1999, '55% off', 'Beauty', 'Nyx', 300, 4.7, 3456, '12-color palette', 'https://images.unsplash.com/photo-1512496015851-a90f38f96f6c?w=300')," +
                    "('Curology Face Wash', 199, 499, '60% off', 'Beauty', 'Loreal', 800, 4.3, 4567, 'Face wash', 'https://images.unsplash.com/photo-1556228578-0d85b1a4d571?w=300')," +
                    "('Restorative Hair Mask', 249, 599, '58% off', 'Beauty', 'Biotique', 600, 4.5, 2345, 'Hair mask', 'https://images.unsplash.com/photo-1608248597279-f99d160bfcbc?w=300')";
                
                entityManager.createNativeQuery(insertSQL).executeUpdate();
                
                // Verify insertion
                Object finalCount = entityManager.createNativeQuery("SELECT COUNT(*) FROM products").getSingleResult();
                Long finalCountLong = ((Number) finalCount).longValue();
                System.out.println("✅ " + finalCountLong + " products loaded successfully!");
            } else {
                System.out.println("✅ Products already exist in database: " + count);
            }
        } catch (Exception e) {
            System.err.println("❌ Error in DataLoader: " + e.getMessage());
            e.printStackTrace();
        }
    }
}