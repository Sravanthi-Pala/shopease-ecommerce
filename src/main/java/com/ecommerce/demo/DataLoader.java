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
        System.out.println("DataLoader Started - Checking products...");
        
        if (productService.count() == 0) {
            System.out.println("No products found. Loading 41 products...");
            
            List<Product> products = Arrays.asList(
                // FASHION (6)
                new Product("Men Cotton T-Shirt", 499.0, 1299.0, "61% off", "Fashion", "Roadster", 500, 4.3, 2341, "Premium cotton t-shirt", "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=300"),
                new Product("Women Floral Top", 699.0, 1999.0, "65% off", "Fashion", "Here&Now", 400, 4.4, 1876, "Beautiful floral top", "https://images.unsplash.com/photo-1515372039744-b8f02a3ae446?w=300"),
                new Product("Men Slim Jeans", 999.0, 2999.0, "66% off", "Fashion", "Levis", 300, 4.5, 3456, "Stretchable slim jeans", "https://images.unsplash.com/photo-1541099649105-f69ad21f3246?w=300"),
                new Product("Women Denim Jacket", 1499.0, 4999.0, "70% off", "Fashion", "Zara", 200, 4.6, 1234, "Classic denim jacket", "https://images.pexels.com/photos/2065200/pexels-photo-2065200.jpeg?w=300"),
                new Product("Men Formal Shirt", 799.0, 2499.0, "68% off", "Fashion", "Arrow", 350, 4.4, 987, "Cotton formal shirt", "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=300"),
                new Product("Premium Men Leather Jacket", 3999.0, 7999.0, "50% off", "Fashion", "Hush Puppies", 220, 4.5, 876, "Stylish genuine leather jacket", "https://images.unsplash.com/photo-1614252369475-531eba835eb1?w=300"),
                
                // FOOTWEAR (4)
                new Product("Nike Free RN 2018 Red", 1999.0, 4999.0, "60% off", "Footwear", "Nike", 300, 4.6, 2345, "Red Nike Free RN 2018", "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=300"),
                new Product("Nike Air Force 1 Shadow Pastel", 1799.0, 4499.0, "60% off", "Footwear", "Nike", 280, 4.5, 1876, "Pastel color AF1 Shadow", "https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?w=300"),
                new Product("Women Nike Air Max 1 Magma Orange", 1499.0, 3999.0, "62% off", "Footwear", "Nike", 350, 4.4, 2341, "Women-specific Magma Orange", "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?w=300"),
                new Product("Women Heels", 999.0, 2999.0, "66% off", "Footwear", "Bata", 250, 4.3, 1234, "Party wear heels", "https://images.unsplash.com/photo-1543163521-1bf539c55dd2?w=300"),
                
                // ELECTRONICS (6)
                new Product("iPhone 15 Base Model", 71900.0, 79900.0, "10% off", "Electronics", "Apple", 50, 4.8, 1245, "Apple iPhone 15", "https://images.unsplash.com/photo-1695048133142-1a20484d2569?w=300"),
                new Product("Sony Headphones", 29990.0, 39990.0, "25% off", "Electronics", "Sony", 100, 4.7, 892, "Noise cancellation", "https://images.unsplash.com/photo-1618366712010-f4ae9c647dcb?w=300"),
                new Product("MacBook Pro", 169900.0, 189900.0, "10% off", "Electronics", "Apple", 30, 4.9, 2341, "Powerful laptop", "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=300"),
                new Product("Samsung Galaxy S21 Ultra", 52999.0, 105999.0, "50% off", "Electronics", "Samsung", 60, 4.7, 543, "S21 Ultra", "https://images.unsplash.com/photo-1610945415295-d9bbf067e59c?w=300"),
                new Product("iPad Pro 11-inch", 61900.0, 71900.0, "14% off", "Electronics", "Apple", 40, 4.8, 987, "iPad Pro", "https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=300"),
                new Product("Dell XPS 15", 59999.0, 149999.0, "60% off", "Electronics", "Dell", 25, 4.7, 1234, "Premium laptop", "/images/laptop.jpg"),
                
                // HOME DECOR (4)
                new Product("Wall Painting", 1499.0, 4999.0, "70% off", "Home Decor", "Art Street", 150, 4.6, 1234, "Canvas painting", "https://images.unsplash.com/photo-1513519245088-0e12902e5a38?w=300"),
                new Product("Wall Clock", 1299.0, 3999.0, "67% off", "Home Decor", "Ajanta", 200, 4.6, 2345, "Modern wall clock", "https://images.unsplash.com/photo-1563861826100-9cb868fdbe1c?w=300"),
                new Product("Cushion Cover Set", 599.0, 1999.0, "70% off", "Home Decor", "Home Centre", 400, 4.4, 1876, "Set of 4 covers", "https://images.unsplash.com/photo-1616486338812-3dadae4b4ace?w=300"),
                new Product("String Lights", 499.0, 1499.0, "66% off", "Home Decor", "LEDIAN", 600, 4.5, 3456, "Fairy lights", "https://images.unsplash.com/photo-1513506003901-1e6a229e2d15?w=300"),
                
                // JEWELLERY (4)
                new Product("Gold Necklace", 2499.0, 7999.0, "68% off", "Jewellery", "PC Jeweller", 150, 4.6, 1234, "American diamond necklace", "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=300"),
                new Product("Golden Earrings", 899.0, 2999.0, "70% off", "Jewellery", "Caratlane", 300, 4.5, 2345, "Golden earrings", "https://images.unsplash.com/photo-1617038220319-276d3cfab638?w=300"),
                new Product("Diamond Ring", 499.0, 1499.0, "66% off", "Jewellery", "Voylla", 400, 4.4, 1876, "Diamond ring", "https://images.unsplash.com/photo-1603561591411-07134e71a2a9?w=300"),
                new Product("Silver Anklet", 299.0, 899.0, "66% off", "Jewellery", "GIVA", 500, 4.4, 654, "Anklet with charms", "https://images.unsplash.com/photo-1617038220319-276d3cfab638?w=300"),
                
                // ACCESSORIES (5)
                new Product("Men Watch", 2999.0, 9999.0, "70% off", "Accessories", "Fastrack", 250, 4.7, 3456, "Analog watch", "https://images.unsplash.com/photo-1524805444758-089113d48a6d?w=300"),
                new Product("Women Watch", 2499.0, 4999.0, "50% off", "Accessories", "Titan", 280, 4.6, 2341, "Rose gold watch", "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?w=300"),
                new Product("Winter Wool Cap", 299.0, 699.0, "57% off", "Accessories", "Allen Solly", 350, 4.5, 1234, "Winter wool cap", "https://images.unsplash.com/photo-1556905055-8f358a7a47b2?w=300"),
                new Product("Designer Handbag", 1499.0, 4999.0, "70% off", "Accessories", "Lavie", 200, 4.7, 2345, "Sling bag", "https://images.unsplash.com/photo-1591561954557-26941169b49e?w=300"),
                new Product("Leather Wallet", 599.0, 1999.0, "70% off", "Accessories", "Wildhorn", 400, 4.4, 1876, "Leather wallet", "https://images.unsplash.com/photo-1622560480605-d83c853bc5c3?w=300"),
                
                // STATIONERY (6)
                new Product("Laptop Backpack", 1299.0, 3999.0, "67% off", "Stationery", "Skybags", 250, 4.6, 2341, "USB backpack", "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=300"),
                new Product("Study Lamp", 899.0, 2499.0, "64% off", "Stationery", "Philips", 300, 4.5, 1876, "LED desk lamp", "https://images.unsplash.com/photo-1507473885765-e6ed057f782c?w=300"),
                new Product("Parker Pen Set", 299.0, 699.0, "57% off", "Stationery", "Parker", 500, 4.7, 3456, "Premium pen set", "/images/paker.jpg"),
                new Product("Notebook Pack", 399.0, 1199.0, "66% off", "Stationery", "Classmate", 800, 4.4, 4567, "5 notebooks", "https://images.unsplash.com/photo-1531346878377-a5be20888e57?w=300"),
                new Product("Water Bottle", 399.0, 999.0, "60% off", "Stationery", "Milton", 450, 4.5, 1876, "Steel insulated", "https://images.unsplash.com/photo-1602143407151-7111542de6e8?w=300"),
                
                // BEAUTY (5)
                new Product("Lakme Absolute Lipstick", 499.0, 999.0, "50% off", "Beauty", "Lakme", 500, 4.5, 2341, "Matte finish lipstick", "https://images.unsplash.com/photo-1586495777744-4413f21062fa?w=300"),
                new Product("Maybelline Foundation", 449.0, 584.0, "23% off", "Beauty", "Maybelline", 150, 4.2, 311, "Liquid foundation", "/images/maybeline.jpg"),
                new Product("Nyx Eyeshadow Palette", 899.0, 1999.0, "55% off", "Beauty", "Nyx", 300, 4.7, 3456, "12-color palette", "/images/nyx-professional.jpg"),
                new Product("Curology Face Wash", 199.0, 499.0, "60% off", "Beauty", "Loreal", 800, 4.3, 4567, "Face wash", "https://images.unsplash.com/photo-1556228578-0d85b1a4d571?w=300"),
                new Product("Restorative Hair Mask", 249.0, 599.0, "58% off", "Beauty", "Biotique", 600, 4.5, 2345, "Hair mask", "https://images.unsplash.com/photo-1608248597279-f99d160bfcbc?w=300")
            );
            
            productService.saveAllProducts(products);
            System.out.println("✅ 41 products loaded successfully!");
        } else {
            System.out.println("✅ Products already exist: " + productService.count());
        }
        
        System.out.println("========================================");
    }
}
