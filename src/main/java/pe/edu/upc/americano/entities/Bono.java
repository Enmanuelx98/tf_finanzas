package pe.edu.upc.americano.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Bono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBono;

    @Column(name = "valorNominal",nullable = false)
    private int valorNominal;

    @Column(name = "numerodetiempo",nullable = false)
    private int numerodetiempo; //como el numero de años(siempre sera años)

    @Column(name = "tipodemoneda",nullable = false)
    private String tipodemoneda;

    @Column(name = "frecuenciadepago",nullable = false)
    private String frecuenciadepago; //semestral, anual, mensual

    @Column(name = "diasporyear",nullable = false)
    private int diasporyear;//360 o 365

    @Column(name = "tasadedescuento",nullable = false) //Por defecto debe ser anual TEA
    private double tasadedescuento;//cok

    @Column(name = "tasadeinteres",nullable = false)
    private double tasadeinteres;//el porcentaje

    @Column(name = "tiempotasadeinteres",nullable = false)
    private String tiempotasadeinteres; //años, meses

    @Column(name = "tipotasadeinteres",nullable = false)
    private String tipotasadeinteres; //nominal o efectiva

    @Column(name = "capitalizacion")
    private String capitalizacion;

    @Column(name = "periodogracia",nullable = false)
    private String periodogracia; // No tiene, parcial o total

    @Column(name = "tiempogracia")
    private int tiempogracia; //cuantos periodos

    @Column(name = "fechaemision",nullable = false)
    private LocalDate fechaemision;

    @Column(name = "gprima",nullable = false)
    private double gprima;

    @Column(name = "gestructuracion",nullable = false)
    private double gestructuracion;

    @Column(name = "gcolocacion",nullable = false)
    private double gcolocacion;

    @Column(name = "gflotacion",nullable = false)
    private double gflotacion;

    @Column(name = "gCAVALI",nullable = false)
    private double gCAVALI;

    @Column(name = "estado",nullable = false)
    private String estado; // "Emitido" o "En Circulación"

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario; // Propietario actual del bono

    @ManyToOne
    @JoinColumn(name = "idEmisor")
    private Usuario emisor; // Emisor original del bono

    public Bono (){}

    public Bono(String capitalizacion, int diasporyear, String estado, LocalDate fechaemision, String frecuenciadepago, double gCAVALI, double gcolocacion, double gestructuracion, double gflotacion, double gprima, int idBono, int numerodetiempo, String periodogracia, double tasadedescuento, double tasadeinteres, int tiempogracia, String tiempotasadeinteres, String tipodemoneda, String tipotasadeinteres, Usuario usuario, int valorNominal) {
        this.capitalizacion = capitalizacion;
        this.diasporyear = diasporyear;
        this.estado = estado;
        this.fechaemision = fechaemision;
        this.frecuenciadepago = frecuenciadepago;
        this.gCAVALI = gCAVALI;
        this.gcolocacion = gcolocacion;
        this.gestructuracion = gestructuracion;
        this.gflotacion = gflotacion;
        this.gprima = gprima;
        this.idBono = idBono;
        this.numerodetiempo = numerodetiempo;
        this.periodogracia = periodogracia;
        this.tasadedescuento = tasadedescuento;
        this.tasadeinteres = tasadeinteres;
        this.tiempogracia = tiempogracia;
        this.tiempotasadeinteres = tiempotasadeinteres;
        this.tipodemoneda = tipodemoneda;
        this.tipotasadeinteres = tipotasadeinteres;
        this.usuario = usuario;
        this.emisor = usuario; // Al crear el bono, el usuario es el emisor
        this.valorNominal = valorNominal;
    }

    public String getCapitalizacion() {
        return capitalizacion;
    }

    public void setCapitalizacion(String capitalizacion) {
        this.capitalizacion = capitalizacion;
    }

    public int getDiasporyear() {
        return diasporyear;
    }

    public void setDiasporyear(int diasporyear) {
        this.diasporyear = diasporyear;
    }

    public LocalDate getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(LocalDate fechaemision) {
        this.fechaemision = fechaemision;
    }

    public String getFrecuenciadepago() {
        return frecuenciadepago;
    }

    public void setFrecuenciadepago(String frecuenciadepago) {
        this.frecuenciadepago = frecuenciadepago;
    }

    public double getgCAVALI() {
        return gCAVALI;
    }

    public void setgCAVALI(double gCAVALI) {
        this.gCAVALI = gCAVALI;
    }

    public double getGcolocacion() {
        return gcolocacion;
    }

    public void setGcolocacion(double gcolocacion) {
        this.gcolocacion = gcolocacion;
    }

    public double getGestructuracion() {
        return gestructuracion;
    }

    public void setGestructuracion(double gestructuracion) {
        this.gestructuracion = gestructuracion;
    }

    public double getGflotacion() {
        return gflotacion;
    }

    public void setGflotacion(double gflotacion) {
        this.gflotacion = gflotacion;
    }

    public double getGprima() {
        return gprima;
    }

    public void setGprima(double gprima) {
        this.gprima = gprima;
    }

    public int getIdBono() {
        return idBono;
    }

    public void setIdBono(int idBono) {
        this.idBono = idBono;
    }

    public int getNumerodetiempo() {
        return numerodetiempo;
    }

    public void setNumerodetiempo(int numerodetiempo) {
        this.numerodetiempo = numerodetiempo;
    }

    public String getPeriodogracia() {
        return periodogracia;
    }

    public void setPeriodogracia(String periodogracia) {
        this.periodogracia = periodogracia;
    }

    public double getTasadedescuento() {
        return tasadedescuento;
    }

    public void setTasadedescuento(double tasadedescuento) {
        this.tasadedescuento = tasadedescuento;
    }

    public double getTasadeinteres() {
        return tasadeinteres;
    }

    public void setTasadeinteres(double tasadeinteres) {
        this.tasadeinteres = tasadeinteres;
    }

    public int getTiempogracia() {
        return tiempogracia;
    }

    public void setTiempogracia(int tiempogracia) {
        this.tiempogracia = tiempogracia;
    }

    public String getTiempotasadeinteres() {
        return tiempotasadeinteres;
    }

    public void setTiempotasadeinteres(String tiempotasadeinteres) {
        this.tiempotasadeinteres = tiempotasadeinteres;
    }

    public String getTipodemoneda() {
        return tipodemoneda;
    }

    public void setTipodemoneda(String tipodemoneda) {
        this.tipodemoneda = tipodemoneda;
    }

    public String getTipotasadeinteres() {
        return tipotasadeinteres;
    }

    public void setTipotasadeinteres(String tipotasadeinteres) {
        this.tipotasadeinteres = tipotasadeinteres;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getValorNominal() {
        return valorNominal;
    }

    public void setValorNominal(int valorNominal) {
        this.valorNominal = valorNominal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }
}
