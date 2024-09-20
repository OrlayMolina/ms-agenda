package co.edu.uniquindio.agenda.test;

import co.edu.uniquindio.agenda.dto.especialidad.CrearEspecialidadDTO;
import co.edu.uniquindio.agenda.dto.especialidad.ItemEspecialidadDTO;
import co.edu.uniquindio.agenda.exceptions.especialidad.EspecialidadNoCreadaException;
import co.edu.uniquindio.agenda.services.interfaces.IEspecialidadService;
import org.bson.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EspecialidadServiceTest {

    @Autowired
    private IEspecialidadService especialidadService;

    @Test
    public void crearEspecialidadTest() throws EspecialidadNoCreadaException {
        CrearEspecialidadDTO registrarEspecialidadDTO = new CrearEspecialidadDTO(
                "Dermatologia"
        );

        String id = especialidadService.crearEspecialidad( registrarEspecialidadDTO );

        Assertions.assertNotNull(id);
    }

    @Test
    public void listarEspecialidadesTest(){

        List<ItemEspecialidadDTO> listaEspecialidades = especialidadService.listarEspecilidades();

        Assertions.assertNotNull(listaEspecialidades);

        listaEspecialidades.forEach(System.out::println);
    }
}
