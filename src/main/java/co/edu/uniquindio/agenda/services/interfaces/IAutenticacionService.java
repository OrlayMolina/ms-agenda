package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.SesionNoIniciadaException;
import co.edu.uniquindio.agenda.dto.cuenta.LoginDTO;
import co.edu.uniquindio.agenda.dto.cuenta.TokenDTO;

public interface IAutenticacionService {
    TokenDTO iniciarSesion(LoginDTO loginDTO) throws SesionNoIniciadaException;
}
