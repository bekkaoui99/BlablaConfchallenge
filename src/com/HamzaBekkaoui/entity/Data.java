package com.HamzaBekkaoui.entity;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<Product> products;
    private double totalPrice;

    public Data() {
        products = new ArrayList<>();
    }


    public List<Product> getProducts() {
        return products;
    }


    public double getTotalPrice() {
        return totalPrice;
    }


    public void addProduct(Product product){
        this.products.add(product);
        this.totalPrice += product.productPrice();
    }
}
