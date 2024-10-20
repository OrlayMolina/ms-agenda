package co.edu.uniquindio.agenda.test;

import co.edu.uniquindio.agenda.dto.agenda.CrearAgendaDTO;
import co.edu.uniquindio.agenda.dto.agenda.InformacionAgendaDTO;
import co.edu.uniquindio.agenda.controllers.exceptions.agenda.AgendaNoCreadaException;
import co.edu.uniquindio.agenda.controllers.exceptions.agenda.AgendaNoEncontradaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.ProfesionalesNoEncontradosException;
import co.edu.uniquindio.agenda.controllers.exceptions.especialidad.EspecialidadNoEncontradaException;
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

        List<String> medicosString = List.of("670d94bbcbe55e694871358c");

        List<ObjectId> medicos = medicosString.stream()
                .map(ObjectId::new)
                .toList();

        CrearAgendaDTO crearAgendaDTO = new CrearAgendaDTO(
                medicos,
                "Medicina General",
                DuracionAgenda.VEINTE.getValue()
        );

        String mensaje = agendaService.crearAgenda(crearAgendaDTO);

        Assertions.assertEquals("Agenda creada exitosamente.", mensaje);
    }

    @Test
    public void obtenerAgendaTest() throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException {

        InformacionAgendaDTO informacionAgendaDTO = agendaService.obtenerInformacionAgendaDTO( "66eec9459197de431d5ea3ee" );

        Assertions.assertNotNull( informacionAgendaDTO );

        System.out.println( informacionAgendaDTO );
    }

    @Test
    public void obtenerAgendaPorEspecialidadTest() throws AgendaNoEncontradaException, EspecialidadNoEncontradaException, ProfesionalesNoEncontradosException {

        InformacionAgendaDTO informacionAgendaDTO = agendaService.obtenerInformacionAgendaPorEspecialidadDTO( "66eb9db95e23cd4478146a7b" );

        Assertions.assertNotNull( informacionAgendaDTO );

        System.out.println( informacionAgendaDTO );
    }

    @Test
    public void obtenerAgendaPorEspecialidadErrorTest() {

        try {
            InformacionAgendaDTO informacionAgendaDTO = agendaService.obtenerInformacionAgendaPorEspecialidadDTO( "idmalo" );

            Assertions.assertNotNull( informacionAgendaDTO );

            System.out.println( informacionAgendaDTO );
        } catch (Exception e){
            Assertions.assertTrue(true);
        }
    }
}
