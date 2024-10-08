package co.edu.uniquindio.agenda.models.vo;

import co.edu.uniquindio.agenda.models.enums.Linea;
import co.edu.uniquindio.agenda.models.enums.Turno;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Agente extends Usuario{

    private Turno turno;
    private Linea nroLinea;
    private LocalDateTime fechaIngreso;
}
