package co.edu.uniquindio.agenda.dto.exception;

import jakarta.validation.constraints.NotBlank;

public record ValidacionDTO(
        @NotBlank String campo,
        @NotBlank String error) {
}
