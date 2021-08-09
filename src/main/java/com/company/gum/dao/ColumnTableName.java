package com.company.gum.dao;

public enum ColumnTableName {
    USERS_ID("user_id"),
    USERS_LOGIN("login"),
    USERS_PASSWORD("password"),
    USERS_ROLE("role"),
    USERS_NAME("name"),
    USERS_SURNAME("surname"),
    IS_ACTIVE("is_active"),

    USERS_PROFILE_IMAGE_PATH("profileImage");

    private final String ColumnName;

    ColumnTableName(String ColumnName) {
        this.ColumnName = ColumnName;
    }

    public String getColumnName() {
        return ColumnName;
    }

}
