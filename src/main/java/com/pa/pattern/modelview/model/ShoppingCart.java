/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.pattern.modelview.model;

import com.pa.pattern.observer.Subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 *
 * @author brunomnsilva
 */
public class ShoppingCart extends Subject{
    
    private String name;
    private List<Product> products;
    private static final double MAX_PRICE = 650.0;

    public ShoppingCart(String name) {
        super();
        this.name = name;
        products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        if(getTotal() + p.getCost() > MAX_PRICE) throw new ShoppingCartException("Valor do carrinho excede o valor total");
        products.add(p);
    }

    public void addProduct(int id) throws ShoppingCartException {
        Product p= ProductListFactory.createProduct(id);
        addProduct(p);
        notifyObservers(null);
    }

    public void removerProduct(Product p) {
        products.remove(p);
        notifyObservers(null);
    }
    public Product lastProductAdded(){
          return products.get(products.size()-1);

    }
    public String getName() {
        return name;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public float getTotal() {
        float total=0.0f;
        for(Product p: products)
            total+=p.getCost();
        return total;
    }


}
