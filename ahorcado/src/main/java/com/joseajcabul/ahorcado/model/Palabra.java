package com.joseajcabul.ahorcado.model;
import jakarta.persistence.*;
@Entity
@Table(name = "Palabras")
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_Palabra") 
    private Integer idPalabra;
    @Column(name = "palabra")
    private String palabra;
    @Column(name = "pista_1")
    private String pistaUno;
    @Column(name = "pista_2")
    private String pistaDos;
    @Column(name = "pista_3")
    private String pistaTres;

    public Integer getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(Integer idPalabra) {
        this.idPalabra = idPalabra;
    }

    public String getPistaUno() {
        return pistaUno;
    }

    public void setPistaUno(String pistaUno) {
        this.pistaUno = pistaUno;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPistaDos() {
        return pistaDos;
    }

    public void setPistaDos(String pistaDos) {
        this.pistaDos = pistaDos;
    }

    public String getPistaTres() {
        return pistaTres;
    }

    public void setPistaTres(String pistaTres) {
        this.pistaTres = pistaTres;
    }
}
