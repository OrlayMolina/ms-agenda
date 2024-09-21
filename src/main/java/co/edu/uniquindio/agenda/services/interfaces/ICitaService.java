package co.edu.uniquindio.agenda.services.interfaces;

import co.edu.uniquindio.agenda.dto.cita.*;
import co.edu.uniquindio.agenda.exceptions.cita.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ICitaService {

    String crearCitaMedica(CrearCitaDTO crearCitaDTO) throws CitaNoCreadaException, PacienteNoAfiliadoException;
    String editarCitaMedica(EditarCitaDTO editarCitaDTO) throws CitaNoEditadaException;
    String eliminarCitaMedica(EliminarCitaDTO eliminarCitaDTO) throws CitaNoEliminadaException;
    InformacionCitaDTO obtenerInformacionCitaDTO(String idCita) throws CitaNoEncontradaException;
    List<ItemCitaDTO> listarCitas() throws CitaNoEncontradaException;
    List<ItemCitaDTO> listarCitasPorMedico(String idMedico) throws CitaNoEncontradaException;
    List<ItemCitaDTO> listarCitasPorEsepcialidad(String idEspecialidad) throws CitaNoEncontradaException;
    List<ItemCitaDTO> listarCitasPorFechaCita(LocalDateTime fechaCita) throws CitaNoEncontradaException;
}
