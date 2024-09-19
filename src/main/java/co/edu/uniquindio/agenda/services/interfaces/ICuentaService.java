package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.dto.cuenta.CrearCuentaPacienteDTO;
import co.edu.uniquindio.agenda.dto.cuenta.CrearCuentaProfesionalDTO;
import co.edu.uniquindio.agenda.dto.cuenta.EditarCuentaPacienteDTO;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoCreadaException;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoEditadaException;
import co.edu.uniquindio.agenda.models.documents.Cuenta;

public interface ICuentaService {

    Cuenta crearCuentaPaciente(CrearCuentaPacienteDTO cuenta) throws CuentaNoCreadaException;
    Cuenta crearCuentaProfesional(CrearCuentaProfesionalDTO cuenta) throws CuentaNoCreadaException;
    Cuenta editarCuentaPaciente(EditarCuentaPacienteDTO cuenta) throws CuentaNoEditadaException;
}
