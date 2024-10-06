package co.edu.uniquindio.agenda.models.documents;

import co.edu.uniquindio.agenda.models.enums.TipoDocumento;
import co.edu.uniquindio.agenda.models.enums.TipoNotificacion;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document("sedes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notificacion {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private LocalDateTime fechaNotificacion;
    private TipoNotificacion tipoNotificacion;
    private String correoNotificado;
    private TipoDocumento tipoDocumentoNotificado;
    private String nroDocumentoNotificado;
}
