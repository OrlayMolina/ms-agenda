package co.edu.uniquindio.agenda.test;

import co.edu.uniquindio.agenda.dto.cita.CrearCitaDTO;
import co.edu.uniquindio.agenda.controllers.exceptions.cita.CitaNoCreadaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cita.PacienteNoAfiliadoException;
import co.edu.uniquindio.agenda.models.enums.Consultorio;
import co.edu.uniquindio.agenda.services.interfaces.ICitaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CitaServiceTest {

    @Autowired
    private ICitaService citaService;
    @Test
    public void crearCitaTest() throws CitaNoCreadaException, PacienteNoAfiliadoException {
        CrearCitaDTO registrarCitaDTO = new CrearCitaDTO(
                "66ed977dc1c0830a7e35ae78",
                "66ef7fedbdcb1e524c679d7a",
                "66ed9b9d33719f5396d8490c",
                LocalDateTime.of(2024, 9, 14, 16, 00),
                "Medicina General",
                "20 Minutos",
                Consultorio.UNO.getValue(),
                "Paciente en condiciones aceptables de salud nuevo",
                "66ef7fedbdcb1e524c679d7a",
                LocalDateTime.of(2024, 9, 14, 16, 00)
        );

        String mensaje = citaService.crearCitaMedica( registrarCitaDTO );
        Assertions.assertEquals("Cita creada exitosamente.", mensaje);
    }
}
