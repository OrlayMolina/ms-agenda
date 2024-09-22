package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.dto.agenda.CrearAgendaDTO;
import co.edu.uniquindio.agenda.exceptions.agenda.AgendaNoCreadaException;

public interface IAgendaService {

    String crearAgenda(CrearAgendaDTO crearAgendaDTO) throws AgendaNoCreadaException;
}
