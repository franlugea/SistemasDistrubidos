package SistemasDistribuidos.Exepciones;

public class UsuarioYaInscriptoExcepcion extends RuntimeException {
    public UsuarioYaInscriptoExcepcion(String message) {
        super(message);
    }
}
