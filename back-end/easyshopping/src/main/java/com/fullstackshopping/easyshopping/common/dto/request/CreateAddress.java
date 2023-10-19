package com.fullstackshopping.easyshopping.common.dto.request;

public class CreateAddress {

    private String shippingAddressLine1;
    private String shippingAddressLine2;
    private String city;
    private String stateName;
    private String postalCode;

    public CreateAddress(String shippingAddressLine1, String shippingAddressLine2, String city, String stateName, String postalCode) {
        this.shippingAddressLine1 = shippingAddressLine1;
        this.shippingAddressLine2 = shippingAddressLine2;
        this.city = city;
        this.stateName = stateName;
        this.postalCode = postalCode;
    }

    public String getShippingAddressLine1() {
        return this.shippingAddressLine1;
    }

    public void setShippingAddressLine1(String shippingAddressLine1) {
        this.shippingAddressLine1 = shippingAddressLine1;
    }

    public String getShippingAddressLine2() {
        return this.shippingAddressLine2;
    }

    public void setShippingAddressLine2(String shippingAddressLine2) {
        this.shippingAddressLine2 = shippingAddressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateName() {
        return this.stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
