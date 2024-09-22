package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.dto.cuenta.*;
import co.edu.uniquindio.agenda.exceptions.cuenta.*;
import co.edu.uniquindio.agenda.models.documents.Cuenta;

import java.util.List;

public interface ICuentaService {

    String activarCuenta(String email, String codigoValidacion) throws CuentaNoActivadaException;
    Cuenta crearCuentaPaciente(CrearCuentaPacienteDTO cuenta) throws CuentaNoCreadaException;
    Cuenta crearCuentaProfesional(CrearCuentaProfesionalDTO cuenta) throws CuentaNoCreadaException;
    Cuenta obtenerCuentaPorId(String id) throws CuentaNoEncontradaException;
    Cuenta editarCuentaPaciente(EditarCuentaPacienteDTO cuenta) throws CuentaNoEditadaException;
    String enviarCodigoRecuperacionPassword(String correo) throws CodigoValidacionNoEnviadoException;
    String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws PasswordNoEditadaException;
    String iniciarSesion(LoginDTO loginDTO) throws SesionNoIniciadaException;
    List<ItemProfesionalDTO> listarProfesionales() throws ProfesionalesNoEncontradosException;

}
