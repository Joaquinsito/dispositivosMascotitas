package com.example.projectmascotitas.modelo;

public class RegistroDatos {
    private String name;
    private String lastname;
    private String address;
    private String email;
    private String phone;

    public RegistroDatos(){

    }

    public RegistroDatos(String name, String lastname, String address, String email, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
