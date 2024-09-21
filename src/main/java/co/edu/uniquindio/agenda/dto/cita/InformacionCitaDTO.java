package co.edu.uniquindio.agenda.dto.cita;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InformacionCitaDTO(
        @NotBlank String id,
        @NotBlank String codigo,
        @NotBlank String paciente,
        @NotBlank String nroDocumento,
        @NotBlank String telefonoPaciente,
        @NotBlank String sede,
        @NotNull boolean confirmado,
        @Future(message = "La cita médica no puede ser asignada en una fecha anterior a la actual.")
        LocalDateTime fechaCita,
        @NotBlank String especialidad,
        @NotBlank String duracion,
        @NotBlank String horacita,
        @NotBlank String consultorio,
        @NotBlank String comentarios,
        @NotBlank String estado) {
}
