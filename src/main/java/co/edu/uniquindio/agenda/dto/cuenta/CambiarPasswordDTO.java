package co.edu.uniquindio.agenda.dto.cuenta;

public record CambiarPasswordDTO(
        String email,
        String codigoVerificacion,
        String passwordNueva) {
}
