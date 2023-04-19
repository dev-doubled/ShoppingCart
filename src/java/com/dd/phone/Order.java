/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dd.phone;

/**
 *
 * @author DevDD
 */
public class Order {
    private int Oid;
    private String username;
    private String email;
    private String phone;
    private String address;
    private int total;

    public Order() {
        this.Oid = 0;
        this.username = "";
        this.email = "";
        this.phone = "";
        this.address = "";
        this.total = 0;
    }

    public Order(int Oid, String username, String email, String phone, String address, int total) {
        this.Oid = Oid;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.total = total;
    }

    public Order(String username, String email, String phone, String address) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getOid() {
        return Oid;
    }

    public void setOid(int Oid) {
        this.Oid = Oid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
