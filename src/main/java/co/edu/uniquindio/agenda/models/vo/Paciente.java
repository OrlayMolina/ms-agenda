package co.edu.uniquindio.agenda.models.vo;

import co.edu.uniquindio.agenda.models.enums.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Paciente extends Usuario{

    private Nacionalidad nacionalidad;
    private LocalDateTime fechaNacimiento;
    private String departamento;
    private String ciudad;
    private String celular;
    private Regimen regimen;
    private String planComplementario;

    public Paciente(
            String tipoDocumento,
            String nroDocumento,
            String direccion,
            String nombres,
            String apellidos,
            Nacionalidad nacionalidad,
            LocalDateTime fechaNacimiento,
            String departamento,
            String ciudad,
            String celular,
            Regimen regimen,
            String planComplementario) {
        super(tipoDocumento, nroDocumento, direccion, nombres, apellidos);
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.celular = celular;
        this.regimen = regimen;
        this.planComplementario = planComplementario;
    }
}
