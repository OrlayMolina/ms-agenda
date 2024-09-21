package co.edu.uniquindio.agenda.services.implementations;

import co.edu.uniquindio.agenda.dto.cita.*;
import co.edu.uniquindio.agenda.exceptions.cita.*;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.cuenta.ProfesionalesNoEncontradosException;
import co.edu.uniquindio.agenda.exceptions.sede.SedeNoEncontradaException;
import co.edu.uniquindio.agenda.models.documents.Cita;
import co.edu.uniquindio.agenda.models.documents.Cuenta;
import co.edu.uniquindio.agenda.models.documents.Sede;
import co.edu.uniquindio.agenda.models.vo.Paciente;
import co.edu.uniquindio.agenda.models.vo.Profesional;
import co.edu.uniquindio.agenda.repository.ICuentaRepository;
import co.edu.uniquindio.agenda.repository.ISedeRepository;
import co.edu.uniquindio.agenda.services.interfaces.ICitaService;
import co.edu.uniquindio.agenda.utils.GenerarCodigo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements ICitaService {

    private final ICuentaRepository cuentaRepository;
    private final ISedeRepository sedeRepository;
    private final GenerarCodigo generarCodigo;
    @Override
    public String crearCitaMedica(CrearCitaDTO crearCitaDTO) throws CitaNoCreadaException, PacienteNoAfiliadoException {

        try {
            Paciente paciente = encontrarPacientePorId( crearCitaDTO.idPaciente() );
            Sede sede = encontrarSedePorId( crearCitaDTO.idSede() );
            Profesional profesional = encontrarProfesionalPorId( crearCitaDTO.idMedico() );
            String codigoCita = generarCodigo.generarCodigoAleatorioCita();

            Cita crearCita = new Cita();
            crearCita.setCodigo( codigoCita );
            crearCita.setIdMedico( crearCitaDTO.idMedico() );

            return null;
        } catch (PacienteNoAfiliadoException e) {
            throw new PacienteNoAfiliadoException("El paciente no está afiliado: " + e.getMessage());
        } catch (Exception e) {
            throw new CitaNoCreadaException("La cita no fue creada. " + e.getMessage());
        }
    }

    @Override
    public String editarCitaMedica(EditarCitaDTO editarCitaDTO) throws CitaNoEditadaException {
        return null;
    }

    @Override
    public String eliminarCitaMedica(EliminarCitaDTO eliminarCitaDTO) throws CitaNoEliminadaException {
        return null;
    }

    @Override
    public InformacionCitaDTO obtenerInformacionCitaDTO(String idCita) throws CitaNoEncontradaException {
        return null;
    }

    @Override
    public List<ItemCitaDTO> listarCitas() throws CitaNoEncontradaException {
        return null;
    }

    @Override
    public List<ItemCitaDTO> listarCitasPorMedico(String idMedico) throws CitaNoEncontradaException {
        return null;
    }

    @Override
    public List<ItemCitaDTO> listarCitasPorEsepcialidad(String idEspecialidad) throws CitaNoEncontradaException {
        return null;
    }

    @Override
    public List<ItemCitaDTO> listarCitasPorFechaCita(LocalDateTime fechaCita) throws CitaNoEncontradaException {
        return null;
    }

    private boolean confirmarPacienteAfiliado(String nroDocumento) throws PacienteNoAfiliadoException {
        try {
            Optional<Cuenta> paciente = cuentaRepository.buscaNroDocumento( nroDocumento );

            if( paciente.isEmpty() ){
                throw new PacienteNoAfiliadoException("El paciente no se encuentra afiliado o registrado en la Clínica.");
            }

            return true;
        } catch (Exception e){
            throw new PacienteNoAfiliadoException("El paciente no se encuentra registrado con ese número de documento." + e.getMessage() );
        }
    }

    private boolean confirmarPacienteAfiliadoPorNombre(String nombres, String apellidos) throws PacienteNoAfiliadoException {
        try {
            Optional<Cuenta> paciente = cuentaRepository.findCuentasByNombresRegexAndApellidosRegex(nombres, apellidos);

            if( paciente.isEmpty() ){
                throw new PacienteNoAfiliadoException("El paciente no se encuentra afilidado o registrado en la Clínica.");
            }
            return true;
        } catch (Exception e){
            throw new PacienteNoAfiliadoException("El paciente no se encuentra registrado con ese nombre.");
        }
    }

    private Paciente encontrarPacientePorId(String id) throws PacienteNoAfiliadoException {
        try {
            Optional<Cuenta> cuenta = cuentaRepository.findById( id );

            if( cuenta.isEmpty() ){
                throw new CuentaNoEncontradaException("La Cuenta no se encuentra registrado en la Clínica.");
            }

            if( !(cuenta.get().getUsuario() instanceof Paciente) ){
                throw new PacienteNoAfiliadoException("El id no esta asociado a un paciente.");
            }

            return (Paciente) cuenta.get().getUsuario();

        } catch (Exception e){
            throw new PacienteNoAfiliadoException("El paciente no fue encontrado " + e.getMessage());
        }
    }

    private Sede encontrarSedePorId(String id) throws SedeNoEncontradaException {
        try {
            Optional<Sede> sede = sedeRepository.findById( id );

            if( sede.isEmpty() ){
                throw new SedeNoEncontradaException("La sede no existe.");
            }

            return sede.get();
        } catch (Exception e ){
            throw new SedeNoEncontradaException("La sede indicada no existe." + e.getMessage());
        }
    }

    private Profesional encontrarProfesionalPorId(String id) throws ProfesionalesNoEncontradosException {
        try {
            Optional<Cuenta> cuenta = cuentaRepository.findById( id );

            if( cuenta.isEmpty() ){
                throw new CuentaNoEncontradaException("La Cuenta no se encuentra registrado en la Clínica.");
            }

            if( !(cuenta.get().getUsuario() instanceof Profesional) ){
                throw new PacienteNoAfiliadoException("El id no esta asociado a un profesionañ.");
            }

            return (Profesional) cuenta.get().getUsuario();

        } catch (Exception e){
            throw new ProfesionalesNoEncontradosException("El profesional no fue encontrado " + e.getMessage());
        }
    }
}
