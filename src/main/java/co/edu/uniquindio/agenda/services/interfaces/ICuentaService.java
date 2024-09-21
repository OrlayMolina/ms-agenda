package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.dto.cuenta.CrearCuentaPacienteDTO;
import co.edu.uniquindio.agenda.dto.cuenta.CrearCuentaProfesionalDTO;
import co.edu.uniquindio.agenda.dto.cuenta.EditarCuentaPacienteDTO;
import co.edu.uniquindio.agenda.dto.cuenta.ItemProfesionalDTO;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoCreadaException;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoEditadaException;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.cuenta.ProfesionalesNoEncontradosException;
import co.edu.uniquindio.agenda.models.documents.Cuenta;

import java.util.List;

public interface ICuentaService {

    Cuenta crearCuentaPaciente(CrearCuentaPacienteDTO cuenta) throws CuentaNoCreadaException;
    Cuenta crearCuentaProfesional(CrearCuentaProfesionalDTO cuenta) throws CuentaNoCreadaException;
    Cuenta obtenerCuentaPorId(String id) throws CuentaNoEncontradaException;
    Cuenta editarCuentaPaciente(EditarCuentaPacienteDTO cuenta) throws CuentaNoEditadaException;
    List<ItemProfesionalDTO> listarProfesionales() throws ProfesionalesNoEncontradosException;

}
