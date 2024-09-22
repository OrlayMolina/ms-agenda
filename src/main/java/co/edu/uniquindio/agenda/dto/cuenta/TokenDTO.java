package co.edu.uniquindio.agenda.dto.cuenta;

import jakarta.validation.constraints.NotNull;

public record TokenDTO(
        @NotNull String token) {
}
