package pe.edu.upc.americano.dtos;

import pe.edu.upc.americano.entities.Usuario;

import java.time.LocalDate;

public class BonoDTO {
    private int idBono;
    private int valorNominal;
    private int numerodetiempo;
    private String tipodemoneda;
    private String frecuenciadepago;
    private int diasporyear;
    private double tasadedescuento;
    private double tasadeinteres;
    private String tiempotasadeinteres;
    private String tipotasadeinteres;
    private String capitalizacion;
    private String periodogracia;
    private int tiempogracia;
    private LocalDate fechaemision;
    private double gprima;
    private double gestructuracion;
    private double gcolocacion;
    private double gflotacion;
    private double gCAVALI;
    private String estado;
    private Usuario usuario;
    private Usuario emisor;

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
