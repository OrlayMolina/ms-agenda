package co.edu.uniquindio.agenda.dto.email;

import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
        @NotBlank String destinatario,
        @NotBlank String asunto,
        @NotBlank String cuerpo) {
}
