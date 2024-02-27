package com.HamzaBekkaoui.entity;



import java.util.List;

public class City {
    private final String name;
    private final List<Product> products;
    private double totalPrice;

    public City(String name, List<Product> products, double totalPrice) {
        this.name = name;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice += totalPrice;
    }

}