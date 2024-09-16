package co.edu.uniquindio.agenda.models.vo;

import co.edu.uniquindio.agenda.models.enums.NivelAcceso;
import co.edu.uniquindio.agenda.models.enums.TipoDocumento;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String telefono;
    private String direccion;
    private String nombres;
    private String apellidos;
    private TipoDocumento tipoDocumento;
    private String nroDocumento;
    private NivelAcceso nivelAcceso;

    public Usuario(
            String telefono,
            String direccion,
            String nombres,
            String apellidos,
            TipoDocumento tipoDocumento,
            String nroDocumento,
            NivelAcceso nivelAcceso) {
        this.telefono = telefono;
        this.direccion = direccion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.nivelAcceso = nivelAcceso;
    }
}