package co.edu.uniquindio.agenda.models.vo;

import co.edu.uniquindio.agenda.models.documents.Especialidad;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("profesionales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Profesional extends Usuario{
    private Especialidad especialidad;
}
