package com.ecommerce.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @PostMapping("/create")
    @Transactional
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> orderData) {
        Map<String, Object> response = new HashMap<>();
        try {
            String orderId = "ORD" + System.currentTimeMillis();
            String userEmail = orderData.get("userEmail").toString();
            String userName = orderData.get("userName").toString();
            Double totalAmount = Double.parseDouble(orderData.get("totalAmount").toString());
            String paymentMethod = orderData.get("paymentMethod").toString();
            String shippingAddress = orderData.get("shippingAddress").toString();
            String phone = orderData.get("phone").toString();
            String items = orderData.get("items").toString();
            
            String sql = "INSERT INTO orders (order_id, user_email, user_name, total_amount, status, payment_method, shipping_address, phone, items) VALUES (?, ?, ?, ?, 'Pending', ?, ?, ?, ?)";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, orderId);
            query.setParameter(2, userEmail);
            query.setParameter(3, userName);
            query.setParameter(4, totalAmount);
            query.setParameter(5, paymentMethod);
            query.setParameter(6, shippingAddress);
            query.setParameter(7, phone);
            query.setParameter(8, items);
            query.executeUpdate();
            
            response.put("success", true);
            response.put("orderId", orderId);
            response.put("message", "Order placed successfully!");
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    
    @GetMapping("/user/{email}")
    public List<Map<String, Object>> getUserOrders(@PathVariable String email) {
        List<Map<String, Object>> orders = new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM orders WHERE user_email = ? ORDER BY id DESC");
            query.setParameter(1, email);
            List<Object[]> results = query.getResultList();
            
            for (Object[] row : results) {
                Map<String, Object> order = new HashMap<>();
                order.put("id", row[0]);
                order.put("orderId", row[1]);
                order.put("userEmail", row[2]);
                order.put("userName", row[3]);
                order.put("orderDate", row[4]);
                order.put("totalAmount", row[5]);
                order.put("status", row[6]);
                order.put("paymentMethod", row[7]);
                order.put("shippingAddress", row[8]);
                order.put("phone", row[9]);
                order.put("items", row[10]);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    @GetMapping("/all")
    public List<Map<String, Object>> getAllOrders() {
        List<Map<String, Object>> orders = new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM orders ORDER BY id DESC");
            List<Object[]> results = query.getResultList();
            
            for (Object[] row : results) {
                Map<String, Object> order = new HashMap<>();
                order.put("id", row[0]);
                order.put("orderId", row[1]);
                order.put("userEmail", row[2]);
                order.put("userName", row[3]);
                order.put("orderDate", row[4]);
                order.put("totalAmount", row[5]);
                order.put("status", row[6]);
                order.put("paymentMethod", row[7]);
                order.put("shippingAddress", row[8]);
                order.put("phone", row[9]);
                order.put("items", row[10]);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    @PostMapping("/cancel/{orderId}")
    @Transactional
    public Map<String, Object> cancelOrder(@PathVariable String orderId, @RequestParam String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            Query getOrderQuery = entityManager.createNativeQuery("SELECT status FROM orders WHERE order_id = ? AND user_email = ?");
            getOrderQuery.setParameter(1, orderId);
            getOrderQuery.setParameter(2, email);
            List<?> results = getOrderQuery.getResultList();
            
            if (results.isEmpty()) {
                response.put("success", false);
                response.put("message", "Order not found");
                return response;
            }
            
            String currentStatus = results.get(0).toString();
            
            if (!currentStatus.equals("Pending")) {
                response.put("success", false);
                response.put("message", "Cannot cancel order. Order status is: " + currentStatus);
                return response;
            }
            
            Query updateQuery = entityManager.createNativeQuery("UPDATE orders SET status = 'Cancelled' WHERE order_id = ?");
            updateQuery.setParameter(1, orderId);
            updateQuery.executeUpdate();
            
            response.put("success", true);
            response.put("message", "Order #" + orderId + " has been cancelled successfully!");
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        return response;
    }
    
    @PutMapping("/status/{orderId}")
    @Transactional
    public Map<String, Object> updateOrderStatus(@PathVariable String orderId, @RequestParam String status) {
        Map<String, Object> response = new HashMap<>();
        try {
            Query updateQuery = entityManager.createNativeQuery("UPDATE orders SET status = ? WHERE order_id = ?");
            updateQuery.setParameter(1, status);
            updateQuery.setParameter(2, orderId);
            updateQuery.executeUpdate();
            response.put("success", true);
            response.put("message", "Order status updated to: " + status);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
    
    // NEW: DELETE order by ID (Admin only)
    @DeleteMapping("/{id}")
    @Transactional
    public Map<String, Object> deleteOrder(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Get order details before deleting
            Query getOrderQuery = entityManager.createNativeQuery("SELECT order_id FROM orders WHERE id = ?");
            getOrderQuery.setParameter(1, id);
            List<?> results = getOrderQuery.getResultList();
            
            if (results.isEmpty()) {
                response.put("success", false);
                response.put("message", "Order not found");
                return response;
            }
            
            String orderId = results.get(0).toString();
            
            // Delete the order
            Query deleteQuery = entityManager.createNativeQuery("DELETE FROM orders WHERE id = ?");
            deleteQuery.setParameter(1, id);
            deleteQuery.executeUpdate();
            
            response.put("success", true);
            response.put("message", "Order #" + orderId + " has been deleted successfully!");
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
