package com.fullstackshopping.easyshopping.model.enums;

public enum Role {
    USER("User"),
    ADMIN("Administrator");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
