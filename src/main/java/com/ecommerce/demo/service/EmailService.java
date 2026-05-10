package com.ecommerce.demo.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {
    
    public void sendOrderConfirmation(String toEmail, String userName, String orderId, double totalAmount, String items) {
        System.out.println("\n" + "?".repeat(70));
        System.out.println("?  ?? EMAIL CONFIRMATION - ORDER PLACED");
        System.out.println("?" + "?".repeat(68));
        System.out.println("?  ?? To: " + toEmail);
        System.out.println("?  ?? Subject: Order Confirmation #" + orderId);
        System.out.println("?" + "?".repeat(68));
        System.out.println("?  Dear " + userName + ",");
        System.out.println("?");
        System.out.println("?  ? Thank you for shopping with ShopEase!");
        System.out.println("?");
        System.out.println("?  ?? Order ID: " + orderId);
        System.out.println("?  ?? Total Amount: ?" + String.format("%.2f", totalAmount));
        System.out.println("?  ?? Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("?");
        System.out.println("?  ?? Order Items:");
        System.out.println("?  " + items.replace(",", "\n?  ").replace("[", "").replace("]", "").replace("{", "").replace("}", ""));
        System.out.println("?");
        System.out.println("?  ?? Your order will be processed soon.");
        System.out.println("?  ?? You will receive another email when your order is shipped.");
        System.out.println("?");
        System.out.println("?  Thank you for choosing ShopEase!");
        System.out.println("?" + "?".repeat(68));
        System.out.println("?  ?? EMAIL SENT SUCCESSFULLY");
        System.out.println("?" + "?".repeat(68) + "\n");
    }
    
    public void sendOrderCancellation(String toEmail, String userName, String orderId) {
        System.out.println("\n" + "?".repeat(70));
        System.out.println("?  ?? EMAIL CONFIRMATION - ORDER CANCELLED");
        System.out.println("?" + "?".repeat(68));
        System.out.println("?  ?? To: " + toEmail);
        System.out.println("?  ?? Subject: Order Cancelled #" + orderId);
        System.out.println("?" + "?".repeat(68));
        System.out.println("?  Dear " + userName + ",");
        System.out.println("?");
        System.out.println("?  ? Your Order #" + orderId + " has been cancelled.");
        System.out.println("?");
        System.out.println("?  ?? Refund will be processed within 5-7 business days.");
        System.out.println("?");
        System.out.println("?  ??? We hope to see you again soon!");
        System.out.println("?");
        System.out.println("?  Thank you for choosing ShopEase!");
        System.out.println("?" + "?".repeat(68));
        System.out.println("?  ?? CANCELLATION EMAIL SENT SUCCESSFULLY");
        System.out.println("?" + "?".repeat(68) + "\n");
    }
}
