package com.tp.biblioteca.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pId;

    @Column(name="fInicio")
    private Date fInicio;

    @Column(name="bFin")
    private boolean bFin;
    @Column(name="fFin")
    private Date fFin;

    public Long getpId() {
        return pId;
    }
    public void setpId(Long pId) {
        this.pId = pId;
    }
    public Date getfInicio() {
        return fInicio;
    }
    public void setfInicio(Date fInicio) {
        this.fInicio = fInicio;
    }
    public Date getfFin() {
        return fFin;
    }
    public void setfFin(Date fFin) {
        this.fFin = fFin;
    }
    public boolean isbFin() {
        return bFin;
    }
    public void setbFin(boolean bFin) {
        this.bFin = bFin;
    }
}
