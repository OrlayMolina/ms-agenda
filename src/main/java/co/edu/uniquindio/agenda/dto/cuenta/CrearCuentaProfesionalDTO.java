package co.edu.uniquindio.agenda.dto.cuenta;

import co.edu.uniquindio.agenda.models.enums.Nacionalidad;
import co.edu.uniquindio.agenda.models.enums.Regimen;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record CrearCuentaProfesionalDTO(
        @NotNull String tipoDocumento,
        @NotBlank @Length(max = 15) String nroDocumento,
        @NotBlank @Length(max = 10) String telefono,
        @NotBlank @Length(max = 15) String nombres,
        @NotBlank @Length(max = 15) String apellidos,
        @Length(max = 100) String direccion,
        @NotNull ObjectId especialidad,
        @NotBlank @Length(max = 40) @Email String email,
        @NotBlank @Length(min = 7 , max = 20) String password) {
}
