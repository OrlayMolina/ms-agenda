package co.edu.uniquindio.agenda.models.vo;

import co.edu.uniquindio.agenda.models.documents.Especialidad;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Profesional extends Usuario{
    private Especialidad especialidad;
}
