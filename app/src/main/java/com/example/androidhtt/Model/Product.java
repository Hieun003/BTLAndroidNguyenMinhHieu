package com.example.androidhtt.Model;

public class Product {
    private String name;
    private String category;
    private double rating;
    private double price;
    private int imageResId;

    public Product(String category, int imageResId, String name, double rating, double price) {
        this.category = category;
        this.imageResId = imageResId;
        this.name = name;
        this.rating = rating;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
