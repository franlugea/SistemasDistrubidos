package SistemasDistribuidos.Entidades.Enums;

public enum DiaSemana {
    LUNES("Lunes"),
    MARTES("Martes"),
    MIERCOLES("Miercoles"),
    JUEVES("Jueves"),
    VIERNES("Viernes");

    private final String descripcion;

    DiaSemana(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
