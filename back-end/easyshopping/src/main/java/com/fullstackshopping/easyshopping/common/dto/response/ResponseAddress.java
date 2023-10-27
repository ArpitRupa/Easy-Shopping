package com.fullstackshopping.easyshopping.common.dto.response;

import com.fullstackshopping.easyshopping.address.model.Address;
import com.fullstackshopping.easyshopping.common.dto.request.CreateAddress;

public class ResponseAddress extends CreateAddress {


    private int addressId;
    private int userID;


    public ResponseAddress(String shippingAddressLine1, String shippingAddressLine2, String city, String stateName, String postalCode, int addressId, int userID) {
        super(shippingAddressLine1, shippingAddressLine2, city, stateName, postalCode);
        this.addressId = addressId;
        this.userID = userID;
    }

    public ResponseAddress (Address address){
        super(
                address.getShippingAddressLine1(),
                address.getShippingAddressLine2(),
                address.getCity(),
                address.getStateName(),
                address.getPostalCode()
                );
        this.addressId = address.getAddressId();
        this.userID = address.getUser().getId();
    }

    public int getAddressId() {
        return this.addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
