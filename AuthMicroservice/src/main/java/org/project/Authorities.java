package org.project;

public enum Authorities {
    USER("USER"),
    ADMIN("ADMIN");

    private String value;

    Authorities(String authority) {
        this.value = authority;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
