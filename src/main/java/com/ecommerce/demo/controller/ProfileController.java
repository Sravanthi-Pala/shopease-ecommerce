package com.ecommerce.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    // Get user profile
    @GetMapping("/{email}")
    public Map<String, Object> getProfile(@PathVariable String email) {
        Map<String, Object> profile = new HashMap<>();
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM user_profiles WHERE email = ?");
            query.setParameter(1, email);
            List<Object[]> results = query.getResultList();
            
            if (!results.isEmpty()) {
                Object[] row = results.get(0);
                profile.put("email", row[1]);
                profile.put("fullName", row[2]);
                profile.put("phone", row[3]);
            }
            
            // Get saved addresses
            Query addrQuery = entityManager.createNativeQuery("SELECT * FROM saved_addresses WHERE user_email = ? ORDER BY is_default DESC");
            addrQuery.setParameter(1, email);
            List<Object[]> addresses = addrQuery.getResultList();
            List<Map<String, Object>> addressList = new ArrayList<>();
            for (Object[] addr : addresses) {
                Map<String, Object> address = new HashMap<>();
                address.put("id", addr[0]);
                address.put("addressType", addr[2]);
                address.put("fullAddress", addr[3]);
                address.put("city", addr[4]);
                address.put("state", addr[5]);
                address.put("pincode", addr[6]);
                address.put("isDefault", addr[7]);
                addressList.add(address);
            }
            profile.put("addresses", addressList);
            
            // Get saved cards
            Query cardQuery = entityManager.createNativeQuery("SELECT * FROM saved_cards WHERE user_email = ? ORDER BY is_default DESC");
            cardQuery.setParameter(1, email);
            List<Object[]> cards = cardQuery.getResultList();
            List<Map<String, Object>> cardList = new ArrayList<>();
            for (Object[] card : cards) {
                Map<String, Object> cardMap = new HashMap<>();
                cardMap.put("id", card[0]);
                cardMap.put("cardNumber", maskCardNumber(card[2].toString()));
                cardMap.put("cardHolderName", card[3]);
                cardMap.put("expiryMonth", card[4]);
                cardMap.put("expiryYear", card[5]);
                cardMap.put("cardType", card[6]);
                cardMap.put("isDefault", card[7]);
                cardList.add(cardMap);
            }
            profile.put("cards", cardList);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profile;
    }
    
    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() > 4) {
            return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        }
        return cardNumber;
    }
    
    // Create or update profile
    @PostMapping("/save")
    @Transactional
    public Map<String, Object> saveProfile(@RequestBody Map<String, Object> profileData) {
        Map<String, Object> response = new HashMap<>();
        try {
            String email = profileData.get("email").toString();
            String fullName = profileData.get("fullName").toString();
            String phone = profileData.get("phone").toString();
            
            // Check if profile exists
            Query checkQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM user_profiles WHERE email = ?");
            checkQuery.setParameter(1, email);
            long count = ((Number) checkQuery.getSingleResult()).longValue();
            
            if (count > 0) {
                // Update existing
                Query updateQuery = entityManager.createNativeQuery("UPDATE user_profiles SET full_name = ?, phone = ?, updated_at = CURRENT_TIMESTAMP WHERE email = ?");
                updateQuery.setParameter(1, fullName);
                updateQuery.setParameter(2, phone);
                updateQuery.setParameter(3, email);
                updateQuery.executeUpdate();
            } else {
                // Insert new
                Query insertQuery = entityManager.createNativeQuery("INSERT INTO user_profiles (email, full_name, phone) VALUES (?, ?, ?)");
                insertQuery.setParameter(1, email);
                insertQuery.setParameter(2, fullName);
                insertQuery.setParameter(3, phone);
                insertQuery.executeUpdate();
            }
            
            response.put("success", true);
            response.put("message", "Profile saved successfully");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
    
    // Add address
    @PostMapping("/address/add")
    @Transactional
    public Map<String, Object> addAddress(@RequestBody Map<String, Object> addressData) {
        Map<String, Object> response = new HashMap<>();
        try {
            String userEmail = addressData.get("userEmail").toString();
            String addressType = addressData.get("addressType").toString();
            String fullAddress = addressData.get("fullAddress").toString();
            String city = addressData.get("city").toString();
            String state = addressData.get("state").toString();
            String pincode = addressData.get("pincode").toString();
            boolean isDefault = Boolean.parseBoolean(addressData.get("isDefault").toString());
            
            if (isDefault) {
                // Remove default from other addresses
                Query updateDefault = entityManager.createNativeQuery("UPDATE saved_addresses SET is_default = FALSE WHERE user_email = ?");
                updateDefault.setParameter(1, userEmail);
                updateDefault.executeUpdate();
            }
            
            Query insertQuery = entityManager.createNativeQuery("INSERT INTO saved_addresses (user_email, address_type, full_address, city, state, pincode, is_default) VALUES (?, ?, ?, ?, ?, ?, ?)");
            insertQuery.setParameter(1, userEmail);
            insertQuery.setParameter(2, addressType);
            insertQuery.setParameter(3, fullAddress);
            insertQuery.setParameter(4, city);
            insertQuery.setParameter(5, state);
            insertQuery.setParameter(6, pincode);
            insertQuery.setParameter(7, isDefault);
            insertQuery.executeUpdate();
            
            response.put("success", true);
            response.put("message", "Address added successfully");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
}
