package co.edu.uniquindio.agenda.dto.especialidad;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ActualizarEspecialidadDTO(
        @NotBlank String id,
        @NotBlank @Length(min = 5) String nombre) {
}
