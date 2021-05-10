package com.example.merda;
public class Errors {

    private String email,report;

    public Errors(String email, String report) {
        this.email = email;
        this.report = report;
    }

    public Errors() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
