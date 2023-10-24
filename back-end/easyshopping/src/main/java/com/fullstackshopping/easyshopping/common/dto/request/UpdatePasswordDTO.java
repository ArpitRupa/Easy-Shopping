package com.fullstackshopping.easyshopping.common.dto.request;

public class UpdatePasswordDTO {

    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;


    public String getCurrentPassword() {
        return this.currentPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getConfirmNewPassword() {
        return this.confirmNewPassword;
    }
}
