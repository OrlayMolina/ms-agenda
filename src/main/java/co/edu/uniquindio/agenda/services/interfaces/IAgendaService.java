package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.CuentaNoEncontradaException;
import co.edu.uniquindio.agenda.dto.agenda.CrearAgendaDTO;
import co.edu.uniquindio.agenda.dto.agenda.InformacionAgendaDTO;
import co.edu.uniquindio.agenda.controllers.exceptions.agenda.AgendaNoCreadaException;
import co.edu.uniquindio.agenda.controllers.exceptions.agenda.AgendaNoEncontradaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.ProfesionalesNoEncontradosException;
import co.edu.uniquindio.agenda.controllers.exceptions.especialidad.EspecialidadNoEncontradaException;

public interface IAgendaService {

    String crearAgenda(CrearAgendaDTO crearAgendaDTO) throws AgendaNoCreadaException;
    InformacionAgendaDTO obtenerInformacionAgendaDTO(String idAgenda) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException;
    InformacionAgendaDTO obtenerInformacionAgendaPorEspecialidadDTO(String idEspecialidad) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException, EspecialidadNoEncontradaException;
    InformacionAgendaDTO obtenerInformacionAgendaPorMedicoDTO(String idMedico) throws AgendaNoEncontradaException, ProfesionalesNoEncontradosException, CuentaNoEncontradaException;
}
