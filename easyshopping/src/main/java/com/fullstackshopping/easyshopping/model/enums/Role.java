package com.fullstackshopping.easyshopping.model.enums;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
