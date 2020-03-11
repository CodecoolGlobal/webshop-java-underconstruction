package com.codecool.shop.model;

public class Customer {

    private int userId;
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressDetails billingDetails;
    private AddressDetails shippingDetails;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDetails getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(AddressDetails billingDetails) {
        this.billingDetails = billingDetails;
    }

    public AddressDetails getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(AddressDetails shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", billingDetails=" + billingDetails +
                ", shippingDetails=" + shippingDetails +
                '}';
    }
}
