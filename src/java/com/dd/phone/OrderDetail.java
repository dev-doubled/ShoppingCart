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
public class OrderDetail {
    private int ODid;
    private String Pid;
    private int Oid;
    private int quantity;
    private int price;

    public OrderDetail() {
        this.ODid = 0;
        this.Pid = "";
        this.Oid = 0;
        this.quantity = 0;
        this.price = 0;
    }

    public OrderDetail(int ODid, String Pid, int Oid, int quantity, int price) {
        this.ODid = ODid;
        this.Pid = Pid;
        this.Oid = Oid;
        this.quantity = quantity;
        this.price = price;
    }
    
    public OrderDetail(String Pid, int Oid, int quantity, int price) {
        this.Pid = Pid;
        this.Oid = Oid;
        this.quantity = quantity;
        this.price = price;
    }

    public int getODid() {
        return ODid;
    }

    public void setODid(int ODid) {
        this.ODid = ODid;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String Pid) {
        this.Pid = Pid;
    }

    public int getOid() {
        return Oid;
    }

    public void setOid(int Oid) {
        this.Oid = Oid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
