package co.edu.uniquindio.agenda.services.implementations;

import co.edu.uniquindio.agenda.dto.cuenta.CrearCuentaDTO;
import co.edu.uniquindio.agenda.dto.cuenta.EditarCuentaDTO;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoCreadaException;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoEditadaException;
import co.edu.uniquindio.agenda.models.documents.Cuenta;
import co.edu.uniquindio.agenda.services.interfaces.ICuentaService;

public class CuentaServiceImpl implements ICuentaService {
    @Override
    public Cuenta crearCuenta(CrearCuentaDTO cuenta) throws CuentaNoCreadaException {
        return null;
    }

    @Override
    public Cuenta editarCuenta(EditarCuentaDTO cuenta) throws CuentaNoEditadaException {
        return null;
    }
}
