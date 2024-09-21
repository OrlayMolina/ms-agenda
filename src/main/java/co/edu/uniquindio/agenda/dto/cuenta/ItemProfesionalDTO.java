package co.edu.uniquindio.agenda.dto.cuenta;

import co.edu.uniquindio.agenda.models.enums.TipoDocumento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ItemProfesionalDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 15) String nombres,
        @NotBlank @Length(max = 15) String apellidos,
        @NotNull String tipoDocumento,
        @NotBlank @Length(max = 10) String nroDocumento,
        @NotBlank @Length(min = 5) String especialidad,
        @NotBlank @Length(max = 10) String telefono,
        @Length(max = 100) String direccion,
        @NotBlank @Length(max = 40) @Email String email) {
}
