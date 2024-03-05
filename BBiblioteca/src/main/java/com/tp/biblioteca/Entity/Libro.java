package com.tp.biblioteca.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lId;

    @Column(name="titulo")
    private String titulo;

    @Column(name="autor")
    private String autor;

    @Column(name="sinopsis")
    private String sinopsis;

    @Column(name="cantPag")
    private int cantPag;

    @Column(name="fecPu")
    private Date fep;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Genero> generos;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Prestamo> prestamos;

    public Long getlId() {
        return lId;
    }
    public void setlId(Long lId) {
        this.lId = lId;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    public int getCantPag() {
        return cantPag;
    }
    public void setCantPag(int cantPag) {
        this.cantPag = cantPag;
    }
    public Date getFep() {
        return fep;
    }
    public void setFep(Date fep) {
        this.fep = fep;
    }
    public List<Genero> getGeneros() {
        return generos;
    }
    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
    public void setGenero(Genero genero) {
        this.generos.add(genero);
    }
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
    public void setPrestamo(Prestamo prestamo) {
        this.prestamos.add(prestamo);
    }
}
