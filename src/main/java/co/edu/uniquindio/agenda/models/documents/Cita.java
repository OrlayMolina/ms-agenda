package co.edu.uniquindio.agenda.models.documents;

import co.edu.uniquindio.agenda.models.enums.Consultorio;
import co.edu.uniquindio.agenda.models.enums.EstadoCita;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("citas")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String codigo;
    private ObjectId idMedico;
    private ObjectId idPaciente;
    private ObjectId idSede;
    private boolean confirmado;
    private LocalDateTime fechaCita;
    private Consultorio consultorio;
    private String comentarios;
    private EstadoCita estado;

    public Cita(String codigo,
                ObjectId idMedico,
                ObjectId idPaciente,
                ObjectId idSede,
                boolean confirmado,
                LocalDateTime fechaCita,
                Consultorio consultorio,
                String comentarios,
                EstadoCita estado) {
        this.codigo = codigo;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.idSede = idSede;
        this.confirmado = confirmado;
        this.fechaCita = fechaCita;
        this.consultorio = consultorio;
        this.comentarios = comentarios;
        this.estado = estado;
    }
}
