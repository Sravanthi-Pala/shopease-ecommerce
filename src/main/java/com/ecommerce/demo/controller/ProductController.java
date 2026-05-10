package com.ecommerce.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @GetMapping
    public List<Map<String, Object>> getAllProducts() {
        List<Map<String, Object>> products = new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery("SELECT id, name, price, original_price, discount, category, brand, stock, rating, rating_count, description, image_url FROM products ORDER BY id DESC");
            List<Object[]> results = query.getResultList();
            
            for (Object[] row : results) {
                Map<String, Object> product = new HashMap<>();
                product.put("id", row[0]);
                product.put("name", row[1]);
                product.put("price", row[2]);
                product.put("originalPrice", row[3]);
                product.put("discount", row[4]);
                product.put("category", row[5]);
                product.put("brand", row[6]);
                product.put("stock", row[7]);
                product.put("rating", row[8]);
                product.put("ratingCount", row[9]);
                product.put("description", row[10]);
                product.put("imageUrl", row[11]);
                products.add(product);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    @GetMapping("/search")
    public List<Map<String, Object>> searchProducts(@RequestParam String query) {
        List<Map<String, Object>> products = new ArrayList<>();
        try {
            String sql = "SELECT id, name, price, original_price, discount, category, brand, stock, rating, rating_count, description, image_url FROM products WHERE LOWER(name) LIKE LOWER(?) OR LOWER(brand) LIKE LOWER(?) OR LOWER(category) LIKE LOWER(?)";
            Query nativeQuery = entityManager.createNativeQuery(sql);
            String searchPattern = "%" + query + "%";
            nativeQuery.setParameter(1, searchPattern);
            nativeQuery.setParameter(2, searchPattern);
            nativeQuery.setParameter(3, searchPattern);
            List<Object[]> results = nativeQuery.getResultList();
            
            for (Object[] row : results) {
                Map<String, Object> product = new HashMap<>();
                product.put("id", row[0]);
                product.put("name", row[1]);
                product.put("price", row[2]);
                product.put("originalPrice", row[3]);
                product.put("discount", row[4]);
                product.put("category", row[5]);
                product.put("brand", row[6]);
                product.put("stock", row[7]);
                product.put("rating", row[8]);
                product.put("ratingCount", row[9]);
                product.put("description", row[10]);
                product.put("imageUrl", row[11]);
                products.add(product);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    @GetMapping("/{id}")
    public Map<String, Object> getProductById(@PathVariable Long id) {
        Map<String, Object> product = new HashMap<>();
        try {
            Query query = entityManager.createNativeQuery("SELECT id, name, price, original_price, discount, category, brand, stock, rating, rating_count, description, image_url FROM products WHERE id = ?");
            query.setParameter(1, id);
            Object[] row = (Object[]) query.getSingleResult();
            product.put("id", row[0]);
            product.put("name", row[1]);
            product.put("price", row[2]);
            product.put("originalPrice", row[3]);
            product.put("discount", row[4]);
            product.put("category", row[5]);
            product.put("brand", row[6]);
            product.put("stock", row[7]);
            product.put("rating", row[8]);
            product.put("ratingCount", row[9]);
            product.put("description", row[10]);
            product.put("imageUrl", row[11]);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return product;
    }
    
    @PostMapping
    @Transactional
    public Map<String, Object> addProduct(@RequestBody Map<String, Object> productData) {
        Map<String, Object> response = new HashMap<>();
        try {
            String sql = "INSERT INTO products (name, price, original_price, discount, category, brand, stock, rating, rating_count, description, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, productData.get("name").toString());
            query.setParameter(2, Double.parseDouble(productData.get("price").toString()));
            query.setParameter(3, productData.get("originalPrice") != null ? Double.parseDouble(productData.get("originalPrice").toString()) : null);
            query.setParameter(4, productData.get("discount") != null ? productData.get("discount").toString() : null);
            query.setParameter(5, productData.get("category").toString());
            query.setParameter(6, productData.get("brand") != null ? productData.get("brand").toString() : null);
            query.setParameter(7, productData.get("stock") != null ? Integer.parseInt(productData.get("stock").toString()) : 0);
            query.setParameter(8, 4.5);
            query.setParameter(9, 100);
            query.setParameter(10, productData.get("description") != null ? productData.get("description").toString() : "");
            query.setParameter(11, productData.get("imageUrl") != null ? productData.get("imageUrl").toString() : "");
            query.executeUpdate();
            
            response.put("success", true);
            response.put("message", "Product added successfully!");
        } catch(Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    
    @PutMapping("/{id}")
    @Transactional
    public Map<String, Object> updateProduct(@PathVariable Long id, @RequestBody Map<String, Object> productData) {
        Map<String, Object> response = new HashMap<>();
        try {
            String sql = "UPDATE products SET name = ?, price = ?, original_price = ?, discount = ?, category = ?, brand = ?, stock = ?, description = ?, image_url = ? WHERE id = ?";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, productData.get("name").toString());
            query.setParameter(2, Double.parseDouble(productData.get("price").toString()));
            query.setParameter(3, productData.get("originalPrice") != null ? Double.parseDouble(productData.get("originalPrice").toString()) : null);
            query.setParameter(4, productData.get("discount") != null ? productData.get("discount").toString() : null);
            query.setParameter(5, productData.get("category").toString());
            query.setParameter(6, productData.get("brand") != null ? productData.get("brand").toString() : null);
            query.setParameter(7, productData.get("stock") != null ? Integer.parseInt(productData.get("stock").toString()) : 0);
            query.setParameter(8, productData.get("description") != null ? productData.get("description").toString() : "");
            query.setParameter(9, productData.get("imageUrl") != null ? productData.get("imageUrl").toString() : "");
            query.setParameter(10, id);
            query.executeUpdate();
            
            response.put("success", true);
            response.put("message", "Product updated successfully!");
        } catch(Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public Map<String, Object> deleteProduct(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Query query = entityManager.createNativeQuery("DELETE FROM products WHERE id = ?");
            query.setParameter(1, id);
            query.executeUpdate();
            response.put("success", true);
            response.put("message", "Product deleted successfully!");
        } catch(Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
}
