package co.edu.uniquindio.agenda.test;

import co.edu.uniquindio.agenda.dto.cuenta.CrearCuentaPacienteDTO;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoCreadaException;
import co.edu.uniquindio.agenda.models.documents.Cuenta;
import co.edu.uniquindio.agenda.models.enums.*;
import co.edu.uniquindio.agenda.services.interfaces.ICuentaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CuentaServiceTest {

    @Autowired
    private ICuentaService cuentaService;

    @Test
    public void crearCuentaTest() throws CuentaNoCreadaException {
        CrearCuentaPacienteDTO registrarCuentaPaciente = new CrearCuentaPacienteDTO(
                TipoDocumento.CC.getValue(),
                "34489784",
                "7425896",
                "Jorge Andrés",
                "Mendez Rolando",
                "Barrio Los Quindos",
                Nacionalidad.COLOMBIANA,
                LocalDateTime.of(1987, 9, 14, 15, 30),
                Departamento.QUINDIO.getNombre(),
                Ciudad.ARMENIA.getValue(),
                "31278496523",
                Regimen.CONTRIBUTIVO,
                PlanComplementario.SIN_PLAN.getValue(),
                "jorgeandres@gmail.com",
                "12345678"
        );

        Cuenta cuentaCreada = cuentaService.crearCuentaPaciente(registrarCuentaPaciente);
        Assertions.assertNotNull(cuentaCreada);
    }
}
