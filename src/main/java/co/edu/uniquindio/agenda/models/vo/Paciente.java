package co.edu.uniquindio.agenda.models.vo;

import co.edu.uniquindio.agenda.models.enums.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Paciente extends Usuario{
    private Nacionalidad nacionalidad;
    private LocalDateTime fechaNacimiento;
    private Departamento departamento;
    private Ciudad ciudad;
    private String celular;
    private Regimen regimen;
    private PlanComplementario planComplementario;

    public Paciente(
            String telefono,
            String direccion,
            String nombres,
            String apellidos,
            TipoDocumento tipoDocumento,
            String nroDocumento,
            NivelAcceso nivelAcceso,
            Nacionalidad nacionalidad,
            LocalDateTime fechaNacimiento,
            Departamento departamento,
            Ciudad ciudad,
            String celular,
            Regimen regimen) {
        super(telefono, direccion, nombres, apellidos, tipoDocumento, nroDocumento, nivelAcceso);
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.celular = celular;
        this.regimen = regimen;
    }
}
