package com.HamzaBekkaoui.entity;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<Product> products;
    private double totalPrice;

    public Data() {
        products = new ArrayList<>();
        totalPrice = 0;
    }


    public List<Product> getProducts() {
        return products;
    }


    public double getTotalPrice() {
        return totalPrice;
    }


   public void setTotalPrice(double totalPrice){
        this.totalPrice += totalPrice;
   }

    public void setProducts(Product products){
        this.products.add(products);
    }

}
