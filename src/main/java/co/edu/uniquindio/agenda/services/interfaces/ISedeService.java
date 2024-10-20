package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.controllers.exceptions.sede.SedeNoEncontradaException;
import co.edu.uniquindio.agenda.dto.sede.SedeDTO;

import java.util.List;

public interface ISedeService {

    List<SedeDTO> listaSedes() throws SedeNoEncontradaException;
}
