/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dd.phone;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DevDD
 */
public class Cart {

    private Map<String, Phone> cart;

    public Cart() {
    }

    public Cart(Map<String, Phone> cart) {
        this.cart = cart;
    }

    public Map<String, Phone> getCart() {
        return cart;
    }

    public void setCart(Map<String, Phone> cart) {
        this.cart = cart;
    }

    public boolean add(Phone phone) {
        boolean check = false;
        if(this.cart == null) {
            this.cart = new HashMap<>();
        }
        if(this.cart.containsKey(phone.getPhoneID()))
        {
            int currentQuantity = this.cart.get(phone.getPhoneID()).getPhoneQuantity();
            phone.setPhoneQuantity(currentQuantity + phone.getPhoneQuantity());
        }
        this.cart.put(phone.getPhoneID(), phone);
        check = true;
        return check;
    }

    public boolean update(String phoneID, Phone phone) {
        boolean check = false;
        if(this.cart != null) {
            if(this.cart.containsKey(phoneID)) {
                this.cart.replace(phoneID, phone);
                check = true;
            }
        }
        return check;
    }

    public boolean remove(String phoneID) {
        boolean check = false;
        if(this.cart != null) {
            if(this.cart.containsKey(phoneID)) {
                this.cart.remove(phoneID);
                check = true;
            }
        }
        return check;
    }
}
