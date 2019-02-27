package com.codingblocks.sih19;

public class GovtDetailClass {

    String Content,Detail,EligibilityCriteria,Name,Status;

    public GovtDetailClass(String content, String detail, String eligibilityCriteria, String name, String status) {
        Content = content;
        Detail = detail;
        EligibilityCriteria = eligibilityCriteria;
        Name = name;
        Status = status;
    }

    public GovtDetailClass(String content, String detail, String eligibilityCriteria, String name) {
        Content = content;
        Detail = detail;
        EligibilityCriteria = eligibilityCriteria;
        Name = name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getEligibilityCriteria() {
        return EligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        EligibilityCriteria = eligibilityCriteria;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public GovtDetailClass() {
    }
}
