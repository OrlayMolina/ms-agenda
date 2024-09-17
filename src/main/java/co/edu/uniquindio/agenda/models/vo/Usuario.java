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
    private String direccion;
    private String nombres;
    private String apellidos;
    private NivelAcceso nivelAcceso;

    public Usuario(
            String direccion,
            String nombres,
            String apellidos,
            NivelAcceso nivelAcceso) {
        this.direccion = direccion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nivelAcceso = nivelAcceso;
    }
}