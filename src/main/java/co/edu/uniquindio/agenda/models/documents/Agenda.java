package co.edu.uniquindio.agenda.models.documents;

import co.edu.uniquindio.agenda.models.enums.DuracionAgenda;
import co.edu.uniquindio.agenda.models.enums.EstadoAgenda;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private List<ObjectId> idMedico;
    private String duracion;
    private String especialidad;
    private EstadoAgenda estado;

    public Agenda(
            List<ObjectId> idMedico,
            String duracion,
            String especialidad,
            EstadoAgenda estado) {
        this.idMedico = idMedico;
        this.duracion = duracion;
        this.especialidad = especialidad;
        this.estado = estado;
    }
}
