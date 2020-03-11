package com.codecool.shop.model;

public class Customer {

    private Integer userId = null;
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    //is it necessary to have Addressdetails typed fields here? for order history I only need the id of customerAddress
    private AddressDetails billingDetails;
    private AddressDetails shippingDetails;
    private int customerCurrentBillingAddressId;
    private int customerCurrentShippingAddressId;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerCurrentBillingAddressId() {
        return customerCurrentBillingAddressId;
    }

    public void setCustomerCurrentBillingAddressId(int customerCurrentBillingAddressId) {
        this.customerCurrentBillingAddressId = customerCurrentBillingAddressId;
    }

    public int getCustomerCurrentShippingAddressId() {
        return customerCurrentShippingAddressId;
    }

    public void setCustomerCurrentShippingAddressId(int customerCurrentShippingAddressId) {
        this.customerCurrentShippingAddressId = customerCurrentShippingAddressId;
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
