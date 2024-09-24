package co.edu.uniquindio.agenda.dto.cita;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemCitaDTO(
        @NotBlank String id,
        @NotBlank String medico,
        @NotBlank String paciente,
        @NotBlank String especialidad,
        @NotBlank String nroDocumento,
        @NotBlank String estado,
        @NotBlank String usuarioCreacion,
        @NotBlank String fechaCreacion,
        @NotBlank String horaCreacion) {
}
