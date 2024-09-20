package co.edu.uniquindio.agenda.dto.especialidad;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CrearEspecialidadDTO(
        @NotBlank @Length(min = 5) String nombre) {
}
