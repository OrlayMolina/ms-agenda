package co.edu.uniquindio.agenda.models.vo;

import co.edu.uniquindio.agenda.models.documents.Especialidad;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Profesional extends Usuario{
    private List<ObjectId> especialidad;

    public Profesional(
            String tipoDocumento,
            String nroDocumento,
            String direccion,
            String nombres,
            String apellidos,
            List<ObjectId> especialidad) {
        super(tipoDocumento, nroDocumento, direccion, nombres, apellidos);
        this.especialidad = especialidad;
    }
}
