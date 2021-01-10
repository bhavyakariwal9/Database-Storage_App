package com.ridobiko.ridobikosolutions;

public class Customer {
    long aadhar;
    long mobile;
    String name,address;

    public Customer(String name, long mobile, String address, long aadhar) {
        this.mobile = mobile;
        this.aadhar = aadhar;
        this.name = name;
        this.address = address;
    }
    public Customer(){}

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public long getAadhar() {
        return aadhar;
    }

    public void setAadhar(long aadhar) {
        this.aadhar = aadhar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
