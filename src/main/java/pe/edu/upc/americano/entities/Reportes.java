package pe.edu.upc.americano.entities;

import jakarta.persistence.*;

@Entity
public class Reportes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReporte;

    @Column(name = "idbono",nullable = false)
    private int idbono;

    @Column(name = "duracion",nullable = false)
    private double duracion;

    @Column(name = "duracionmodificada",nullable = false)
    private double duracionmodificada;

    @Column(name = "convexidad",nullable = false)
    private double convexidad;

    @Column(name = "tcea",nullable = false)
    private double tcea;

    @Column(name = "trea",nullable = false)
    private double trea;

    @Column(name = "preciomaximo",nullable = false)
    private double preciomaximo;

    public Reportes() {}

    public Reportes(double convexidad, double duracion, double duracionmodificada, int idbono, int idReporte, double preciomaximo, double tcea, double trea) {
        this.convexidad = convexidad;
        this.duracion = duracion;
        this.duracionmodificada = duracionmodificada;
        this.idbono = idbono;
        this.idReporte = idReporte;
        this.preciomaximo = preciomaximo;
        this.tcea = tcea;
        this.trea = trea;
    }

    public double getConvexidad() {
        return convexidad;
    }

    public void setConvexidad(double convexidad) {
        this.convexidad = convexidad;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public double getDuracionmodificada() {
        return duracionmodificada;
    }

    public void setDuracionmodificada(double duracionmodificada) {
        this.duracionmodificada = duracionmodificada;
    }

    public int getIdbono() {
        return idbono;
    }

    public void setIdbono(int idbono) {
        this.idbono = idbono;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public double getPreciomaximo() {
        return preciomaximo;
    }

    public void setPreciomaximo(double preciomaximo) {
        this.preciomaximo = preciomaximo;
    }

    public double getTcea() {
        return tcea;
    }

    public void setTcea(double tcea) {
        this.tcea = tcea;
    }

    public double getTrea() {
        return trea;
    }

    public void setTrea(double trea) {
        this.trea = trea;
    }
}
