package co.edu.uniquindio.agenda.models.documents;

import co.edu.uniquindio.agenda.models.enums.Consultorio;
import co.edu.uniquindio.agenda.models.enums.EstadoCita;
import co.edu.uniquindio.agenda.models.enums.EstadoRegistro;
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
    private boolean confirmada;
    private LocalDateTime fechaCita;
    private String consultorio;
    private String comentarios;
    private EstadoCita estado;
    private String estadoRegistro;
    private ObjectId usuarioCreacion;

    public Cita(String codigo,
                ObjectId idMedico,
                ObjectId idPaciente,
                ObjectId idSede,
                boolean confirmada,
                LocalDateTime fechaCita,
                String consultorio,
                String comentarios,
                EstadoCita estado,
                String estadoRegistro,
                ObjectId usuarioCreacion) {
        this.codigo = codigo;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.idSede = idSede;
        this.confirmada = confirmada;
        this.fechaCita = fechaCita;
        this.consultorio = consultorio;
        this.comentarios = comentarios;
        this.estado = estado;
        this.estadoRegistro = estadoRegistro;
        this.usuarioCreacion = usuarioCreacion;
    }
}
