package co.edu.uniquindio.agenda.models.vo;

import co.edu.uniquindio.agenda.models.enums.NivelAcceso;
import co.edu.uniquindio.agenda.models.enums.TipoDocumento;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario {

    private String tipoDocumento;
    private String nroDocumento;
    private String direccion;
    private String nombres;
    private String apellidos;

    public Usuario(
            String tipoDocumento,
            String nroDocumento,
            String direccion,
            String nombres,
            String apellidos) {
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.direccion = direccion;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
}