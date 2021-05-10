package com.example.merda;

public class Users {
    private String  name,email, password, country;

    public Users() {
    }


    public Users(String name, String email, String contraseña, String pais) {
        this.name=name;
        this.email = email;
        this.password = contraseña;
        this.country = pais;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
