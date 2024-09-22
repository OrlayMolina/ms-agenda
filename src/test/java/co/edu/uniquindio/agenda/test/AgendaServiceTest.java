package co.edu.uniquindio.agenda.test;

import co.edu.uniquindio.agenda.dto.agenda.CrearAgendaDTO;
import co.edu.uniquindio.agenda.exceptions.agenda.AgendaNoCreadaException;
import co.edu.uniquindio.agenda.models.enums.DuracionAgenda;
import co.edu.uniquindio.agenda.services.interfaces.IAgendaService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AgendaServiceTest {

    @Autowired
    private IAgendaService agendaService;

    @Test
    public void crearAgendaTest() throws AgendaNoCreadaException {

        List<String> medicosString = List.of("66ed977dc1c0830a7e35ae78");

        List<ObjectId> medicos = medicosString.stream()
                .map(ObjectId::new)
                .toList();

        CrearAgendaDTO crearAgendaDTO = new CrearAgendaDTO(
                medicos,
                "Medicina General",
                DuracionAgenda.TREINTA.getValue()
        );

        String mensaje = agendaService.crearAgenda(crearAgendaDTO);

        Assertions.assertEquals("Agenda creada exitosamente.", mensaje);
    }
}
