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
public class Phone {
    private String phoneID;
    private String phoneName;
    private String phoneImage;
    private double phonePrice;
    private int phoneQuantity;

    public Phone() {
        this.phoneID = "";
        this.phoneName = "";
        this.phoneImage = "";
        this.phonePrice = 0;
        this.phoneQuantity = 0;
    }

    public Phone(String phoneID, String phoneName, String phoneImage, double phonePrice, int phoneQuantity) {
        this.phoneID = phoneID;
        this.phoneName = phoneName;
        this.phoneImage = phoneImage;
        this.phonePrice = phonePrice;
        this.phoneQuantity = phoneQuantity;
    }

    public String getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(String phoneID) {
        this.phoneID = phoneID;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhoneImage() {
        return phoneImage;
    }

    public void setPhoneImage(String phoneImage) {
        this.phoneImage = phoneImage;
    }

    public double getPhonePrice() {
        return phonePrice;
    }

    public void setPhonePrice(double phonePrice) {
        this.phonePrice = phonePrice;
    }

    public int getPhoneQuantity() {
        return phoneQuantity;
    }

    public void setPhoneQuantity(int phoneQuantity) {
        this.phoneQuantity = phoneQuantity;
    }

    
    
    
}
