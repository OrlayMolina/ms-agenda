package co.edu.uniquindio.agenda.dto.cuenta;

public record MensajeDTO<T>(
        boolean error,
        T respuesta) {
}