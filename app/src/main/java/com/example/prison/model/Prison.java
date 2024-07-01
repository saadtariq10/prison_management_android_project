package com.example.prison.model;

import androidx.annotation.NonNull;

public class Prison {
    public String getName() {
        return name;
    }
    @NonNull
    @Override
    public String toString() {
        return "Prison Name: " + name +
                "\nLocation: " + location +
                "\nCapacity: " + capacity +
                "\nSecurity Level: " + securityLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    private String name;
    private String location;
    private int capacity;
    private String securityLevel;

    // Constructors
    public Prison() {}

    public Prison(String name, String location, int capacity, String securityLevel) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.securityLevel = securityLevel;
    }


    public void setId(int anInt) {
    }
}
