package com.fullstackshopping.easyshopping.user.role;


/**
 * Enum representing user roles.
 */
public enum Role {
    // Possible roles of USER and ADMIN
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
