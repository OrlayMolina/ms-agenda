package co.edu.uniquindio.agenda.services.implementations;

import co.edu.uniquindio.agenda.dto.cita.*;
import co.edu.uniquindio.agenda.exceptions.cita.*;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.especialidad.EspecialidadNoEncontradaException;
import co.edu.uniquindio.agenda.exceptions.sede.SedeNoEncontradaException;
import co.edu.uniquindio.agenda.models.documents.Cita;
import co.edu.uniquindio.agenda.models.documents.Cuenta;
import co.edu.uniquindio.agenda.models.documents.Especialidad;
import co.edu.uniquindio.agenda.models.documents.Sede;
import co.edu.uniquindio.agenda.models.enums.EstadoCita;
import co.edu.uniquindio.agenda.models.enums.EstadoRegistro;
import co.edu.uniquindio.agenda.models.vo.Paciente;
import co.edu.uniquindio.agenda.models.vo.Usuario;
import co.edu.uniquindio.agenda.repository.ICitaRepository;
import co.edu.uniquindio.agenda.repository.ICuentaRepository;
import co.edu.uniquindio.agenda.repository.IEspecialidadRepository;
import co.edu.uniquindio.agenda.repository.ISedeRepository;
import co.edu.uniquindio.agenda.services.interfaces.ICitaService;
import co.edu.uniquindio.agenda.utils.GenerarCodigo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements ICitaService {

    private final ICuentaRepository cuentaRepository;
    private final ICitaRepository citaRepository;
    private final ISedeRepository sedeRepository;
    private final IEspecialidadRepository especialidadRepository;
    private final GenerarCodigo generarCodigo;
    @Override
    public String crearCitaMedica(CrearCitaDTO crearCitaDTO) throws CitaNoCreadaException {

        try {
            String codigoCita = generarCodigo.generarCodigoAleatorioCita();
            ObjectId objectMedico = new ObjectId( crearCitaDTO.idMedico() );
            ObjectId objectPaciente = new ObjectId( crearCitaDTO.idPaciente() );
            ObjectId objectSede = new ObjectId( crearCitaDTO.idSede() );
            ObjectId objectUsuario = new ObjectId( crearCitaDTO.usuarioCreacion() );

            Cita crearCita = new Cita();
            crearCita.setCodigo( codigoCita );
            crearCita.setIdMedico( objectMedico );
            crearCita.setIdPaciente( objectPaciente );
            crearCita.setIdSede( objectSede );
            crearCita.setConfirmada( false );
            crearCita.setFechaCita( crearCitaDTO.fechaCita() );
            crearCita.setConsultorio( crearCitaDTO.consultorio() );
            crearCita.setComentarios( crearCitaDTO.comentarios() );
            crearCita.setEstado( EstadoCita.PROGRAMADA );
            crearCita.setEstadoRegistro( EstadoRegistro.ACTIVO );
            crearCita.setUsuarioCreacion( objectUsuario );

            citaRepository.save( crearCita );

            return "Cita creada exitosamente.";

        } catch (Exception e) {
            throw new CitaNoCreadaException("La cita no fue creada. " + e.getMessage());
        }
    }

    @Override
    public String editarCitaMedica(EditarCitaDTO editarCitaDTO) throws CitaNoEditadaException {

        try {

            return  "";
        } catch (Exception e){
            throw new CitaNoEditadaException("La cita no fue creada. " + e.getMessage());
        }
    }

    @Override
    public String eliminarCitaMedica(EliminarCitaDTO eliminarCitaDTO) throws CitaNoEliminadaException {
        return null;
    }

    @Override
    public InformacionCitaDTO obtenerInformacionCitaDTO(String idCita) throws CitaNoEncontradaException, PacienteNoAfiliadoException, SedeNoEncontradaException {

        try {
            Cita cita = encontrarCitaPorId( idCita );
            Paciente paciente = encontrarPacientePorId( cita.getIdPaciente().toHexString() );
            Sede sede = encontrarSedePorId( cita.getIdSede().toHexString() );
            Usuario usuario = encontrarCuentaPorId( cita.getUsuarioCreacion().toHexString() );

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String fechaCita = cita.getFechaCita().format(dateFormatter);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String horaCita = cita.getFechaCita().format(formatter);

            return new InformacionCitaDTO(
                    cita.getId(),
                    cita.getCodigo(),
                    paciente.getNombres() + " " + paciente.getApellidos(),
                    paciente.getTipoDocumento(),
                    paciente.getNroDocumento(),
                    paciente.getCelular(),
                    sede.getNombre(),
                    cita.isConfirmada(),
                    fechaCita,
                    horaCita,
                    cita.getConsultorio(),
                    cita.getComentarios(),
                    cita.getEstado().toString(),
                    cita.getEstadoRegistro().getValue(),
                    usuario.getNombres() + " " + usuario.getApellidos()
            );

        }catch (CitaNoEncontradaException e) {
            throw new CitaNoEncontradaException("Cita con ID " + idCita + " no fue encontrada.");
        } catch (PacienteNoAfiliadoException e) {
            throw new PacienteNoAfiliadoException("El paciente asociado con la cita no está afiliado.");
        } catch (SedeNoEncontradaException e) {
            throw new SedeNoEncontradaException("La sede asociada con la cita no fue encontrada.");
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado al obtener la información de la cita.", e);
        }
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

    private Cita encontrarCitaPorId(String id) throws CitaNoEncontradaException {
        try {
            Optional<Cita> cita = citaRepository.findById( id );

            if( cita.isEmpty() ){
                throw new CuentaNoEncontradaException("La Cita no se encuentra registrada en la Clínica.");
            }

            return cita.get();

        } catch (Exception e){
            throw new CitaNoEncontradaException("La Cita no fue encontrada " + e.getMessage());
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

    private Usuario encontrarCuentaPorId(String id) throws CuentaNoEncontradaException {
        try {
            Optional<Cuenta> cuenta = cuentaRepository.findById( id );

            if( cuenta.isEmpty() ){
                throw new CuentaNoEncontradaException("La Cuenta no se encuentra registrado en la Clínica.");
            }

            return cuenta.get().getUsuario();

        } catch (Exception e){
            throw new CuentaNoEncontradaException("La cuenta que creo el registro no fue encontrado " + e.getMessage());
        }
    }

    private Especialidad encontrarEspecialidadPorId( String id ) throws EspecialidadNoEncontradaException{
        try {
            Optional<Especialidad> especialidad = especialidadRepository.findById( id );

            if( especialidad.isEmpty() ){
                throw new EspecialidadNoEncontradaException("Especialidad de la cita no encontrada.");
            }

            return especialidad.get();
        } catch (Exception e){
            throw  new EspecialidadNoEncontradaException("Error al tratar de encontrar la especialidad asociada a la cita. " + e.getMessage());
        }
    }
}
