package com.tp.biblioteca.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Genero")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gId;
    @Column(name="genero")
    private String genero;

    public Long getgId() {
        return gId;
    }
    public void setgId(Long gId) {
        this.gId = gId;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
}
