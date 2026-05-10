package com.ecommerce.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @PostMapping("/register")
    @Transactional
    public Map<String, Object> register(@RequestBody Map<String, Object> userData) {
        Map<String, Object> response = new HashMap<>();
        try {
            String email = userData.get("email").toString();
            String name = userData.get("name").toString();
            String password = userData.get("password").toString();
            String phone = userData.get("phone").toString();
            
            Query checkQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM users WHERE email = ?");
            checkQuery.setParameter(1, email);
            long count = ((Number) checkQuery.getSingleResult()).longValue();
            
            if (count > 0) {
                response.put("success", false);
                response.put("message", "Email already registered");
                return response;
            }
            
            Query insertQuery = entityManager.createNativeQuery("INSERT INTO users (email, name, password, phone) VALUES (?, ?, ?, ?)");
            insertQuery.setParameter(1, email);
            insertQuery.setParameter(2, name);
            insertQuery.setParameter(3, password);
            insertQuery.setParameter(4, phone);
            insertQuery.executeUpdate();
            
            response.put("success", true);
            response.put("message", "Registration successful!");
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        return response;
    }
    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> loginData) {
        Map<String, Object> response = new HashMap<>();
        try {
            String email = loginData.get("email").toString();
            String password = loginData.get("password").toString();
            
            Query query = entityManager.createNativeQuery("SELECT * FROM users WHERE email = ? AND password = ?");
            query.setParameter(1, email);
            query.setParameter(2, password);
            List<Object[]> results = query.getResultList();
            
            if (!results.isEmpty()) {
                Object[] user = results.get(0);
                response.put("success", true);
                response.put("message", "Login successful!");
                response.put("email", email);
                response.put("name", user[2]);
            } else {
                response.put("success", false);
                response.put("message", "Invalid email or password");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        return response;
    }
    
    @GetMapping
    public List<Map<String, Object>> getAllUsers() {
        List<Map<String, Object>> users = new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery("SELECT id, email, name, phone FROM users ORDER BY id DESC");
            List<Object[]> results = query.getResultList();
            
            for (Object[] row : results) {
                Map<String, Object> user = new HashMap<>();
                user.put("id", row[0]);
                user.put("email", row[1]);
                user.put("name", row[2]);
                user.put("phone", row[3]);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public Map<String, Object> deleteUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Query query = entityManager.createNativeQuery("DELETE FROM users WHERE id = ?");
            query.setParameter(1, id);
            int deleted = query.executeUpdate();
            response.put("success", deleted > 0);
            response.put("message", deleted > 0 ? "User deleted" : "User not found");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
}
