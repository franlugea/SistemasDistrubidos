package SistemasDistribuidos.Exepciones;

import SistemasDistribuidos.Entidades.Dto.RespuestaError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ManejadorGlobalDeExcepciones {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaError> manejarErroresValidacion(MethodArgumentNotValidException ex) {

        String detalle = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        RespuestaError error = crearRespuestaError("Error de validación",HttpStatus.BAD_REQUEST.value(),detalle);

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(InscripcionNoDisponibleExceptcion.class)
    public ResponseEntity<RespuestaError> manejadorInscripcionNoDisponible(InscripcionNoDisponibleExceptcion ex) {
        RespuestaError error = crearRespuestaError("Inscripciones no disponibles para esta clase",HttpStatus.BAD_REQUEST.value(),ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(SinCuposDisponiblesException.class)
    public ResponseEntity<RespuestaError> manejadorSinCuposDisponibles(SinCuposDisponiblesException ex) {
        RespuestaError error =crearRespuestaError("No hay cupos disponibles para esta clase",HttpStatus.BAD_REQUEST.value(),ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UsuarioYaInscriptoExcepcion.class)
    public ResponseEntity<RespuestaError> manejadorUsuarioYaInscripto(UsuarioYaInscriptoExcepcion ex) {
        RespuestaError error = crearRespuestaError("Usuario ya inscripto en esta clase",HttpStatus.CONFLICT.value(),ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }



    @ExceptionHandler(RecursoNoEncontradoExcepcion.class)
    public ResponseEntity<RespuestaError> RecursoNoEncontradoExcepcion(RecursoNoEncontradoExcepcion ex) {
        RespuestaError error = crearRespuestaError("Recurso no encontrado",HttpStatus.BAD_REQUEST.value(),ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RespuestaError> manejarArgumentoInvalido(IllegalArgumentException ex) {
        RespuestaError error = crearRespuestaError("Argumento inválido",HttpStatus.BAD_REQUEST.value(),ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    // ❗ Violación de integridad (BD: duplicados, FK, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RespuestaError> manejarErrorIntegridadDatos(DataIntegrityViolationException ex) {
        RespuestaError error = crearRespuestaError("Error de integridad de datos",HttpStatus.CONFLICT.value(),
                ex.getMostSpecificCause().getMessage());


        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // ❗ Excepción general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError> manejarExcepcionGeneral(Exception ex) {
        RespuestaError error = crearRespuestaError("Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private RespuestaError crearRespuestaError(String mensaje,int estado,String detalle ){
        return new RespuestaError(mensaje,
                detalle,
                estado,
                LocalDateTime.now());
    }
}
