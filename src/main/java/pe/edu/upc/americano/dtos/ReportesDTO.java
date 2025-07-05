package pe.edu.upc.americano.dtos;

public class ReportesDTO {
    private int idReporte;
    private int idbono;
    private double duracion;
    private double duracionmodificada;
    private double convexidad;
    private double tcea;
    private double trea;
    private double preciomaximo;

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
