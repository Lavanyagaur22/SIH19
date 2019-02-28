package com.codingblocks.sih19;

public class NGODetailClass {
    String contactNo,description,location,name,website;

    public NGODetailClass(String contactNo, String description, String location, String name, String website) {
        this.contactNo = contactNo;
        this.description = description;
        this.location = location;
        this.name = name;
        this.website = website;
    }

    public NGODetailClass() {

    }

    public NGODetailClass(String contactNo, String description,String name, String website) {
        this.contactNo = contactNo;
        this.description = description;
        this.name = name;
        this.website = website;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
