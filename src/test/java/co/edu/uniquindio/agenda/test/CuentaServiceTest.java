package co.edu.uniquindio.agenda.test;

import co.edu.uniquindio.agenda.dto.cuenta.CrearCuentaPacienteDTO;
import co.edu.uniquindio.agenda.dto.cuenta.CrearCuentaProfesionalDTO;
import co.edu.uniquindio.agenda.dto.cuenta.ItemProfesionalDTO;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.CuentaNoCreadaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.ProfesionalesNoEncontradosException;
import co.edu.uniquindio.agenda.models.documents.Cuenta;
import co.edu.uniquindio.agenda.models.enums.*;
import co.edu.uniquindio.agenda.services.interfaces.ICuentaService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class CuentaServiceTest {

    @Autowired
    private ICuentaService cuentaService;

    @Test
    public void crearCuentaPacienteTest() throws CuentaNoCreadaException {
        CrearCuentaPacienteDTO registrarCuentaPaciente = new CrearCuentaPacienteDTO(
                TipoDocumento.CC.getValue(),
                "1094958632",
                "3152684521",
                "Andrés",
                "Molina",
                "Tebaida",
                Nacionalidad.COLOMBIANA,
                LocalDateTime.of(1998, 9, 14, 15, 30),
                Departamento.QUINDIO.getNombre(),
                Ciudad.ARMENIA.getValue(),
                "3127849645",
                Regimen.CONTRIBUTIVO,
                PlanComplementario.SIN_PLAN.getValue(),
                "andresmolina@gmail.com",
                "12345678"
        );

        Cuenta cuentaCreada = cuentaService.crearCuentaPaciente(registrarCuentaPaciente);
        Assertions.assertNotNull(cuentaCreada);
    }

    @Test
    public void crearCuentaProfesionalTest() throws CuentaNoCreadaException {
        CrearCuentaProfesionalDTO registrarCuentaProfesional = new CrearCuentaProfesionalDTO(
                TipoDocumento.CC.getValue(),
                "4782365489",
                "7425896",
                "Maria Claudia",
                "Curtidor",
                "Granada",
                List.of(new ObjectId("66eb9db95e23cd4478146a7b")),
                "mariacurtidor@gmail.com",
                "789635"
        );

        Cuenta cuentaCreada = cuentaService.crearCuentaProfesional(registrarCuentaProfesional);
        Assertions.assertNotNull(cuentaCreada);
    }

    @Test
    public void listarProfesionalesTest() throws ProfesionalesNoEncontradosException {
        List<ItemProfesionalDTO> mostrarProfesionales = cuentaService.listarProfesionales();

        Assertions.assertNotNull(mostrarProfesionales);

        mostrarProfesionales.forEach(System.out::println);
    }
}
