package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.agenda.dto.cuenta.EditarCuentaDTO;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoCreadaException;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoEditadaException;
import co.edu.uniquindio.agenda.models.documents.Cuenta;

public interface ICuentaService {

    Cuenta crearCuenta(CrearCuentaDTO cuenta) throws CuentaNoCreadaException;
    Cuenta editarCuenta(EditarCuentaDTO cuenta) throws CuentaNoEditadaException;
}
