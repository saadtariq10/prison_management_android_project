package com.example.prison.model;

public class Block {
    @Override
    public String toString() {
        // Return a string representation of the cell block object
        return "Block Name: " + blockName +
                "\nCapacity: " + capacity +
                "\nSecurity Level: " + securityLevel +
                "\nFloor: " + floor +
                "\nBuilding: " + building +
                "\nDescription: " + description;
    }
    private String blockName;
    private String capacity;

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String securityLevel;
    private String floor;
    private String building;
    private String description;

    // Constructors
    public Block() {}

    public Block(String blockName, String capacity, String securityLevel, String floor, String building, String description) {
        this.blockName = blockName;
        this.capacity = capacity;
        this.securityLevel = securityLevel;
        this.floor = floor;
        this.building = building;
        this.description = description;
    }


    public void setId(int anInt) {
    }
}
