package com.ecommerce.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private Double price;
    private Double originalPrice;
    private String discount;
    private String category;
    private String brand;
    private Integer stock;
    private Double rating;
    private Integer ratingCount;
    private String description;
    private String imageUrl;
    
    public Product() {}
    
    public Product(String name, Double price, Double originalPrice, String discount, String category, String brand, Integer stock, Double rating, Integer ratingCount, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.originalPrice = originalPrice;
        this.discount = discount;
        this.category = category;
        this.brand = brand;
        this.stock = stock;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.description = description;
        this.imageUrl = imageUrl;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public Double getPrice() { return price; }
    public Double getOriginalPrice() { return originalPrice; }
    public String getDiscount() { return discount; }
    public String getCategory() { return category; }
    public String getBrand() { return brand; }
    public Integer getStock() { return stock; }
    public Double getRating() { return rating; }
    public Integer getRatingCount() { return ratingCount; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(Double price) { this.price = price; }
    public void setOriginalPrice(Double originalPrice) { this.originalPrice = originalPrice; }
    public void setDiscount(String discount) { this.discount = discount; }
    public void setCategory(String category) { this.category = category; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setStock(Integer stock) { this.stock = stock; }
    public void setRating(Double rating) { this.rating = rating; }
    public void setRatingCount(Integer ratingCount) { this.ratingCount = ratingCount; }
    public void setDescription(String description) { this.description = description; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
