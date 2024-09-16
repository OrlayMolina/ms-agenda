package co.edu.uniquindio.agenda.models.vo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document("administradores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Administrador extends Usuario{

    private LocalDateTime fechaLabores;
}
