package co.edu.uniquindio.agenda.models.documents;

import co.edu.uniquindio.agenda.models.enums.EstadoCuenta;
import co.edu.uniquindio.agenda.models.enums.NivelAcceso;
import co.edu.uniquindio.agenda.models.enums.Rol;
import co.edu.uniquindio.agenda.models.enums.TipoDocumento;
import co.edu.uniquindio.agenda.models.vo.CodigoValidacion;
import co.edu.uniquindio.agenda.models.vo.Usuario;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("cuentas")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "usuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuenta {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private Rol rol;
    private String email;
    private CodigoValidacion codigoValidacionRegistro;
    private Usuario usuario;
    private String telefono;
    private LocalDateTime fechaRegistro;
    private String password;
    private EstadoCuenta estado;
    private CodigoValidacion codigoValidacionPassword;
    private String nivelAcceso;
}
