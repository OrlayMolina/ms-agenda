package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.dto.agenda.CrearAgendaDTO;
import co.edu.uniquindio.agenda.dto.agenda.InformacionAgendaDTO;
import co.edu.uniquindio.agenda.dto.cita.InformacionCitaDTO;
import co.edu.uniquindio.agenda.exceptions.agenda.AgendaNoCreadaException;
import co.edu.uniquindio.agenda.exceptions.agenda.AgendaNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.cita.CitaNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.cita.PacienteNoAfiliadoException;
import co.edu.uniquindio.agenda.exceptions.cuenta.ProfesionalesNoEncontradosException;
import co.edu.uniquindio.agenda.exceptions.especialidad.EspecialidadNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.sede.SedeNoEncontradaException;
import org.bson.types.ObjectId;

import java.util.List;

public interface IAgendaService {

    String crearAgenda(CrearAgendaDTO crearAgendaDTO) throws AgendaNoCreadaException;
    InformacionAgendaDTO obtenerInformacionAgendaDTO(String idAgenda) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException;
    InformacionAgendaDTO obtenerInformacionAgendaPorEspecialidadDTO(String idEspecialidad) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException, EspecialidadNoEncontradaException;
    InformacionAgendaDTO obtenerInformacionAgendaPorMedicoDTO(String idMedico) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException;
}
