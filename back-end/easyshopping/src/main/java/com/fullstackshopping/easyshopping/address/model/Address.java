package com.fullstackshopping.easyshopping.address.model;

import com.fullstackshopping.easyshopping.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto to work with auto increment or sequence
    @Column(name = "address_id", nullable = false, updatable = false) // cannot update ID once set
    private int addressId;

    @ManyToOne
    private User user;

    @Column(name = "shipping_address_line1", nullable = false)
    private String shippingAddressLine1;

    @Column(name = "shipping_address_line2")
    private String shippingAddressLine2;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "state_name", nullable = false, length = 50)
    private String stateName;

    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    public Address(){

    }

    public Address( String shippingAddressLine1, String shippingAddressLine2, String city, String stateName, String postalCode) {
        this.shippingAddressLine1 = shippingAddressLine1;
        this.shippingAddressLine2 = shippingAddressLine2;
        this.city = city;
        this.stateName = stateName;
        this.postalCode = postalCode;
    }

    public int getAddressId() {
        return this.addressId;
    }

    public User getUser() {
        return this.user;
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


    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", shippingAddressLine1='" + shippingAddressLine1 + '\'' +
                ", shippingAddressLine2='" + shippingAddressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", stateName='" + stateName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
