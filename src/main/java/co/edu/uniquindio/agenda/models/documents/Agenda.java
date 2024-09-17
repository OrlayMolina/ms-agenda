package co.edu.uniquindio.agenda.models.documents;

import co.edu.uniquindio.agenda.models.enums.DuracionAgenda;
import co.edu.uniquindio.agenda.models.enums.EstadoAgenda;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("agendas")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Agenda {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private ObjectId idMedico;
    private ObjectId idCita;
    private DuracionAgenda duracion;
    private Especialidad especialidad;
    private EstadoAgenda estado;
}
