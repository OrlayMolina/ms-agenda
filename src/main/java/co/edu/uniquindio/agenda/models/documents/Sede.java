package co.edu.uniquindio.agenda.models.documents;

import co.edu.uniquindio.agenda.models.enums.Ciudad;
import co.edu.uniquindio.agenda.models.enums.Departamento;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("sedes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sede {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String nombre;
    private Departamento departamento;
    private Ciudad ciudad;
    private String direccion;
    private String telefono;
}
