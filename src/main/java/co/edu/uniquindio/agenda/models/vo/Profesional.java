package co.edu.uniquindio.agenda.models.vo;

import co.edu.uniquindio.agenda.models.documents.Especialidad;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Profesional extends Usuario{
    private ObjectId especialidad;

    public Profesional(
            String tipoDocumento,
            String nroDocumento,
            String direccion,
            String nombres,
            String apellidos,
            ObjectId especialidad) {
        super(tipoDocumento, nroDocumento, direccion, nombres, apellidos);
        this.especialidad = especialidad;
    }
}
