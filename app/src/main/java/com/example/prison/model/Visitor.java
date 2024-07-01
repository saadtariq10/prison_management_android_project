package com.example.prison.model;

import androidx.annotation.NonNull;

public class Visitor {
    @NonNull
    @Override
    public String toString() {
        // Return a string representation of the visitor object
        return "Visitor Name: " + visitorName +
                "\nContact: " + contact +
                "\nInmate Name: " + inmateName +
                "\nRelationship: " + relationship +
                "\nVisit Date: " + visitDate +
                "\nVisit Time: " + visitTime;
    }
    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getInmateName() {
        return inmateName;
    }

    public void setInmateName(String inmateName) {
        this.inmateName = inmateName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    private String visitorName;
    private String contact;
    private String inmateName;
    private String relationship;
    private String visitDate;
    private String visitTime;

    // Constructors
    public Visitor() {}

    public Visitor(String visitorName, String contact, String inmateName, String relationship, String visitDate, String visitTime) {
        this.visitorName = visitorName;
        this.contact = contact;
        this.inmateName = inmateName;
        this.relationship = relationship;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
    }


    public void setId(int anInt) {
    }
}
