package com.ecommerce.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.*;

@RestController
@RequestMapping("/api/coupons")
@CrossOrigin(origins = "*")
public class CouponController {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @GetMapping("/list")
    public List<Map<String, Object>> getAllCoupons() {
        List<Map<String, Object>> coupons = new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery("SELECT coupon_code, discount_type, discount_value, min_order_amount FROM discount_coupons");
            List<Object[]> results = query.getResultList();
            
            for (Object[] row : results) {
                Map<String, Object> coupon = new HashMap<>();
                coupon.put("code", row[0].toString());
                coupon.put("discountType", row[1].toString());
                coupon.put("discountValue", row[2]);
                coupon.put("minOrderAmount", row[3]);
                coupons.add(coupon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coupons;
    }
    
    @GetMapping("/validate/{code}")
    public Map<String, Object> validateCoupon(@PathVariable String code, @RequestParam double orderAmount) {
        Map<String, Object> response = new HashMap<>();
        try {
            // First check if table exists
            try {
                entityManager.createNativeQuery("SELECT 1 FROM discount_coupons LIMIT 1").getSingleResult();
            } catch(Exception e) {
                // Create table if not exists
                entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS discount_coupons (id BIGINT AUTO_INCREMENT PRIMARY KEY, coupon_code VARCHAR(50) UNIQUE NOT NULL, description VARCHAR(255), discount_type VARCHAR(20), discount_value DOUBLE, min_order_amount DOUBLE, is_active INT DEFAULT 1)").executeUpdate();
                
                // Insert default coupons
                entityManager.createNativeQuery("INSERT INTO discount_coupons (coupon_code, description, discount_type, discount_value, min_order_amount, is_active) VALUES ('WELCOME10', '10% off', 'PERCENTAGE', 10, 500, 1), ('SAVE20', '20% off', 'PERCENTAGE', 20, 1000, 1), ('FLAT100', '?100 off', 'FIXED', 100, 500, 1)").executeUpdate();
            }
            
            Query query = entityManager.createNativeQuery("SELECT coupon_code, discount_type, discount_value, min_order_amount FROM discount_coupons WHERE UPPER(coupon_code) = UPPER(?)");
            query.setParameter(1, code);
            List<Object[]> results = query.getResultList();
            
            if (results.isEmpty()) {
                response.put("valid", false);
                response.put("message", "Invalid coupon code: " + code);
                return response;
            }
            
            Object[] coupon = results.get(0);
            String couponCode = coupon[0].toString();
            String discountType = coupon[1].toString();
            double discountValue = Double.parseDouble(coupon[2].toString());
            double minOrderAmount = Double.parseDouble(coupon[3].toString());
            
            if (orderAmount < minOrderAmount) {
                response.put("valid", false);
                response.put("message", "Need ?" + String.format("%.0f", minOrderAmount) + " to use this coupon");
                return response;
            }
            
            double discount = 0;
            if (discountType.equalsIgnoreCase("PERCENTAGE")) {
                discount = (orderAmount * discountValue) / 100;
            } else if (discountType.equalsIgnoreCase("FIXED")) {
                discount = discountValue;
                if (discount > orderAmount) discount = orderAmount;
            }
            
            response.put("valid", true);
            response.put("code", couponCode);
            response.put("discountAmount", Math.round(discount));
            response.put("finalAmount", orderAmount - Math.round(discount));
            response.put("message", "?? " + couponCode + " applied! Saved ?" + Math.round(discount));
            
        } catch (Exception e) {
            response.put("valid", false);
            response.put("message", "Error: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    
    @GetMapping("/best")
    public Map<String, Object> getBestCoupon(@RequestParam double orderAmount) {
        Map<String, Object> response = new HashMap<>();
        try {
            Query query = entityManager.createNativeQuery("SELECT coupon_code, discount_type, discount_value, min_order_amount FROM discount_coupons");
            List<Object[]> results = query.getResultList();
            
            double bestDiscount = 0;
            Map<String, Object> bestCoupon = null;
            
            for (Object[] coupon : results) {
                String couponCode = coupon[0].toString();
                String discountType = coupon[1].toString();
                double discountValue = Double.parseDouble(coupon[2].toString());
                double minOrderAmount = Double.parseDouble(coupon[3].toString());
                
                if (orderAmount < minOrderAmount) continue;
                
                double discount = 0;
                if (discountType.equalsIgnoreCase("PERCENTAGE")) {
                    discount = (orderAmount * discountValue) / 100;
                } else if (discountType.equalsIgnoreCase("FIXED")) {
                    discount = discountValue;
                    if (discount > orderAmount) discount = orderAmount;
                }
                
                if (discount > bestDiscount) {
                    bestDiscount = discount;
                    bestCoupon = new HashMap<>();
                    bestCoupon.put("code", couponCode);
                    bestCoupon.put("discountAmount", Math.round(discount));
                }
            }
            
            if (bestCoupon != null && bestDiscount > 0) {
                response.put("hasBestCoupon", true);
                response.put("coupon", bestCoupon);
                response.put("message", "Best coupon: " + bestCoupon.get("code") + " saves ?" + bestCoupon.get("discountAmount"));
            } else {
                response.put("hasBestCoupon", false);
                response.put("message", "No coupons available");
            }
        } catch (Exception e) {
            response.put("hasBestCoupon", false);
            response.put("message", "Error: " + e.getMessage());
        }
        return response;
    }
}
