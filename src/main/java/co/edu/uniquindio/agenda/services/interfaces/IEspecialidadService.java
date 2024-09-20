package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.dto.especialidad.ActualizarEspecialidadDTO;
import co.edu.uniquindio.agenda.dto.especialidad.CrearEspecialidadDTO;
import co.edu.uniquindio.agenda.dto.especialidad.ItemEspecialidadDTO;
import co.edu.uniquindio.agenda.exceptions.especialidad.EspecialidadNoCreadaException;
import co.edu.uniquindio.agenda.exceptions.especialidad.EspecialidadNoEditadaException;

import java.util.List;

public interface IEspecialidadService {

    String crearEspecialidad(CrearEspecialidadDTO especialidadDTO) throws EspecialidadNoCreadaException;
    String editarEspecialidad(ActualizarEspecialidadDTO especialidadDTO) throws EspecialidadNoEditadaException;
    List<ItemEspecialidadDTO> listarEspecilidades();
}
