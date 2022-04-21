package com.example.webservices_jueves;

public class ClsUsuario {
    private String usr;
    private String clave;
    private String nombre;
    private String correo;

    //constructor
    public ClsUsuario() {
    }

    //Encapsular campos
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsr() {

        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}