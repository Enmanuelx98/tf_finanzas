package pe.edu.upc.americano.enums;

public enum TipoUsuario {
    ADMIN("Administrador"),
    EMISOR("Emisor"),
    BONISTA("Bonista");

    private final String descripcion;

    TipoUsuario(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
