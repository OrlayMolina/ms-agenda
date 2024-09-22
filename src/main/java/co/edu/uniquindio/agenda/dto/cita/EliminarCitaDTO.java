package co.edu.uniquindio.agenda.dto.cita;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EliminarCitaDTO(
        @NotBlank String id,
        @NotBlank String estadoRegistro) {
}
