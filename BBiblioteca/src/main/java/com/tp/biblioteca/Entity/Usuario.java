package com.tp.biblioteca.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario{

    @Id
    @Column(name="nombreUsuario")
    private String nU;

    @Column(name="contra")
    private String con;

    public String getCon() {
        return con;
    }

    public String getnU() {
        return nU;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public void setnU(String nU) {
        this.nU = nU;
    }

}