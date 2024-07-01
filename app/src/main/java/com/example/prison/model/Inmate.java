package com.example.prison.model;

import androidx.annotation.NonNull;

public class Inmate {
    @NonNull
    @Override
    public String toString() {
        // Return a string representation of the inmate object
        return "Full Name: " + fullName +
                "\nDate of Birth: " + dob +
                "\nGender: " + gender +
                "\nMarital Status: " + maritalStatus +
                "\nAddress: " + address +
                "\nCrime: " + crime +
                "\nComplexion: " + complexion +
                "\nEye Color: " + eyeColor +
                "\nSentence Start Date: " + sentenceStartDate +
                "\nSentence End Date: " + sentenceEndDate;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getComplexion() {
        return complexion;
    }

    public void setComplexion(String complexion) {
        this.complexion = complexion;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getSentenceStartDate() {
        return sentenceStartDate;
    }

    public void setSentenceStartDate(String sentenceStartDate) {
        this.sentenceStartDate = sentenceStartDate;
    }

    public String getSentenceEndDate() {
        return sentenceEndDate;
    }

    public void setSentenceEndDate(String sentenceEndDate) {
        this.sentenceEndDate = sentenceEndDate;
    }

    private String fullName;
    private String dob;
    private String gender;
    private String maritalStatus;
    private String address;
    private String crime;
    private String complexion;
    private String eyeColor;
    private String sentenceStartDate;
    private String sentenceEndDate;

    // Constructors
    public Inmate() {}

    public Inmate(String fullName, String dob, String gender, String maritalStatus, String address, String crime, String complexion, String eyeColor, String sentenceStartDate, String sentenceEndDate) {
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.crime = crime;
        this.complexion = complexion;
        this.eyeColor = eyeColor;
        this.sentenceStartDate = sentenceStartDate;
        this.sentenceEndDate = sentenceEndDate;
    }


    public void setId(int anInt) {
    }
}
