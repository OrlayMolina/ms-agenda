package co.edu.uniquindio.agenda.services.implementations;

import co.edu.uniquindio.agenda.config.JWTUtils;
import co.edu.uniquindio.agenda.config.PlantillasEmailConfig;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.*;
import co.edu.uniquindio.agenda.dto.cuenta.*;
import co.edu.uniquindio.agenda.dto.email.EmailDTO;
import co.edu.uniquindio.agenda.controllers.exceptions.especialidad.EspecialidadNoEncontradaException;
import co.edu.uniquindio.agenda.models.documents.Cuenta;
import co.edu.uniquindio.agenda.models.documents.Especialidad;
import co.edu.uniquindio.agenda.models.enums.*;
import co.edu.uniquindio.agenda.models.vo.CodigoValidacion;
import co.edu.uniquindio.agenda.models.vo.Paciente;
import co.edu.uniquindio.agenda.models.vo.Profesional;
import co.edu.uniquindio.agenda.repository.ICuentaRepository;
import co.edu.uniquindio.agenda.repository.IEspecialidadRepository;
import co.edu.uniquindio.agenda.services.interfaces.ICuentaService;
import co.edu.uniquindio.agenda.services.interfaces.IEmailService;
import co.edu.uniquindio.agenda.utils.GenerarCodigo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements ICuentaService {

    private final ICuentaRepository cuentaRepository;
    private final IEmailService emailService;
    private final IEspecialidadRepository especialidadRepository;
    private final GenerarCodigo generarCodigo;
    private final JWTUtils jwtUtils;

    @Override
    public String activarCuenta(String email, String codigoValidacion) throws CuentaNoActivadaException {
        try {
            Optional<Cuenta> cuentaOptional = cuentaRepository.findByEmail(email);

            if (cuentaOptional.isEmpty()) {
                throw new Exception("No se encontró una cuenta con ese email");
            }

            Cuenta cuenta = cuentaOptional.get();

            if (cuenta.getEstado() != EstadoCuenta.INACTIVO) {
                throw new Exception("La cuenta ya está activa o ha sido eliminada");
            }

            CodigoValidacion codigoValidacionCuenta = cuenta.getCodigoValidacionRegistro();

            if (codigoValidacionCuenta == null ||
                    !codigoValidacionCuenta.getCodigo().equals(codigoValidacion) ||
                    codigoValidacionCuenta.getFechaCreacion().plusMinutes(15).isBefore(LocalDateTime.now())) {

                throw new Exception("El código de validación es incorrecto o ha expirado");
            }

            cuenta.setEstado(EstadoCuenta.ACTIVO);
            cuenta.setCodigoValidacionRegistro(null);

            cuentaRepository.save(cuenta);

            return "La cuenta fue activada correctamente";
        }catch(Exception e){
            throw new CuentaNoActivadaException("Error al activar la cuenta." +e.getMessage());
        }
    }

    @Override
    public Cuenta crearCuentaPaciente(CrearCuentaPacienteDTO cuenta) throws CuentaNoCreadaException {
        try {
            if( existeEmail(cuenta.email()) ){
                throw new Exception("El email " + cuenta.email()+ " ya existe");
            }

            if( existeCedula(cuenta.nroDocumento()) ){
                throw new Exception("La cédula " + cuenta.nroDocumento()+ " ya existe");
            }

            String codigoAleatorio = generarCodigo.generarCodigoAleatorio();

            Paciente nuevoPaciente = new Paciente(
                    cuenta.tipoDocumento(),
                    cuenta.nroDocumento(),
                    cuenta.direccion(),
                    cuenta.nombres(),
                    cuenta.apellidos(),
                    cuenta.nacionalidad(),
                    cuenta.fechaNacimiento(),
                    cuenta.departamento(),
                    cuenta.ciudad(),
                    cuenta.celular(),
                    cuenta.regimen(),
                    cuenta.planComplementario()
            );

            Cuenta nuevaCuenta = new Cuenta();
            nuevaCuenta.setRol( Rol.PACIENTE.getValue() );
            nuevaCuenta.setEmail( cuenta.email() );
            nuevaCuenta.setCodigoValidacionRegistro( new CodigoValidacion(
                    codigoAleatorio,
                    LocalDateTime.now()
            ) );
            nuevaCuenta.setUsuario( nuevoPaciente );
            nuevaCuenta.setNivelAcceso( NivelAcceso.BASIC.getValue() );
            nuevaCuenta.setTelefono( cuenta.telefono() );
            nuevaCuenta.setFechaRegistro( LocalDateTime.now() );
            nuevaCuenta.setPassword( encriptarPassword(cuenta.password()) );
            nuevaCuenta.setEstado( EstadoCuenta.INACTIVO );

            String body = PlantillasEmailConfig.bodyCreacionCuenta.replace("[Nombres]",
                    cuenta.nombres()).replace("[Apellidos]",
                    cuenta.apellidos()).replace("[Codigo_Activacion]",
                    codigoAleatorio
            );

            emailService.enviarCorreo( new EmailDTO(cuenta.email(), "Asunto mensaje", body) );

            return cuentaRepository.save( nuevaCuenta );
        } catch (Exception e){
            throw new CuentaNoCreadaException("Error al crear cuenta" + e.getMessage());
        }
    }

    @Override
    public Cuenta crearCuentaProfesional(CrearCuentaProfesionalDTO cuenta) throws CuentaNoCreadaException {

        try {

            if( existeEmail(cuenta.email()) ){
                throw new Exception("El email " + cuenta.email()+ " ya existe");
            }

            if( existeCedula(cuenta.nroDocumento()) ){
                throw new Exception("La cédula " + cuenta.nroDocumento()+ " ya existe");
            }

            if (cuenta.especialidades() == null || cuenta.especialidades().toString().isEmpty()) {
                throw new IllegalArgumentException("El campo especialidad no puede estar vacío.");
            }

            String codigoAleatorio = generarCodigo.generarCodigoAleatorio();

            Profesional nuevoProfesional = new Profesional(
                    cuenta.tipoDocumento(),
                    cuenta.nroDocumento(),
                    cuenta.direccion(),
                    cuenta.nombres(),
                    cuenta.apellidos(),
                    cuenta.especialidades()
            );

            Cuenta nuevaCuenta = new Cuenta();
            nuevaCuenta.setRol( Rol.PROFESIONAL.getValue() );
            nuevaCuenta.setEmail( cuenta.email() );
            nuevaCuenta.setCodigoValidacionRegistro( new CodigoValidacion(
                    codigoAleatorio,
                    LocalDateTime.now()
            ) );
            nuevaCuenta.setUsuario( nuevoProfesional );
            nuevaCuenta.setNivelAcceso( NivelAcceso.MEDIUM.getValue() );
            nuevaCuenta.setTelefono( cuenta.telefono() );
            nuevaCuenta.setFechaRegistro( LocalDateTime.now() );
            nuevaCuenta.setPassword( cuenta.password() );
            nuevaCuenta.setEstado( EstadoCuenta.INACTIVO );
            return cuentaRepository.save( nuevaCuenta );

        } catch (Exception e){
            throw new CuentaNoCreadaException("Error al crear cuenta del profesional" + e.getMessage());
        }
    }

    @Override
    public Cuenta obtenerCuentaPorId(String id) throws CuentaNoEncontradaException {
        try {
            Optional<Cuenta> cuenta = cuentaRepository.findCuentasByIdIs( id );

            if( cuenta.isEmpty() ){
                throw new CuentaNoEncontradaException("La Cuenta no fue hallada.");
            }

            return cuenta.get();

        } catch(Exception e){
            throw new CuentaNoEncontradaException("Cuenta del profesional no encontrada.");
        }
    }

    @Override
    public Cuenta editarCuentaPaciente(EditarCuentaPacienteDTO cuenta) throws CuentaNoEditadaException {
        return null;
    }

    @Override
    public String enviarCodigoRecuperacionPassword(String correo) throws CodigoValidacionNoEnviadoException {
        try {
            Optional<Cuenta>  cuentaOptional = cuentaRepository.findByEmail(correo);

            if(cuentaOptional.isEmpty()){
                throw new CodigoValidacionNoEnviadoException("No se encontró una cuenta con ese email ");
            }

            Cuenta cuenta = cuentaOptional.get();
            String codigoValidacion = generarCodigo.generarCodigoAleatorio();

            cuenta.setCodigoValidacionPassword(new CodigoValidacion(
                    codigoValidacion,
                    LocalDateTime.now()
            ));

            cuentaRepository.save(cuenta);


            return "Se ha enviado un correo con el código de validación";
        }catch (Exception e){
            throw new CodigoValidacionNoEnviadoException("Error generando codigo devalidación." +e.getMessage());
        }
    }

    @Override
    public String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws PasswordNoEditadaException {
        try {
            Optional<Cuenta>  cuentaOptional = cuentaRepository.findByEmail(cambiarPasswordDTO.email());

            if (cuentaOptional.isEmpty()){
                throw new PasswordNoEditadaException("El correo dado no esta registrado");
            }
            Cuenta cuenta = cuentaOptional.get();
            CodigoValidacion codigoValidacion = cuenta.getCodigoValidacionPassword();

            if (codigoValidacion.getCodigo().
                    equals(cambiarPasswordDTO.codigoVerificacion()))
            {
                if(codigoValidacion.getFechaCreacion().
                        plusMinutes(15).isBefore(LocalDateTime.now()))
                {
                    cuenta.setPassword(cambiarPasswordDTO.passwordNueva());
                    cuentaRepository.save(cuenta);
                }
                else{
                    throw new PasswordNoEditadaException("El código ya expiro");
                }
            }
            else{
                throw new PasswordNoEditadaException("El código de validación es incorrecto");
            }

            return "Se ha cambiado su contraseña";
        }catch (Exception e){
            throw new PasswordNoEditadaException("La contraseña no fue actualizada." + e.getMessage());
        }
    }

    @Override
    public TokenDTO iniciarSesion(LoginDTO loginDTO) throws SesionNoIniciadaException {
        try {
            Cuenta cuenta = obtenerPorEmail(loginDTO.email());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


            if( !passwordEncoder.matches(loginDTO.password(), cuenta.getPassword()) ) {
                throw new Exception("La contraseña es incorrecta");
            }


            Map<String, Object> map = construirClaims(cuenta);
            return new TokenDTO( jwtUtils.generarToken(cuenta.getEmail(), map) );

        }catch(Exception e){
            throw new SesionNoIniciadaException("Sesión no fue iniciada. " + e.getMessage());
        }
    }

    @Override
    public List<ItemProfesionalDTO> listarProfesionales() throws ProfesionalesNoEncontradosException {
        try {
            List<Cuenta> listaProfesionales = obtenerCuentasProfesionales();

            List<ItemProfesionalDTO> items = new ArrayList<>();

            for(Cuenta cuenta : listaProfesionales ){
                if (cuenta.getUsuario() instanceof Profesional profesional) {
                    List<Especialidad> especialidades = obtenerEspecialidades( profesional );
                    List<String> nombresEspecialidad = new ArrayList<>();
                    for(Especialidad especialidad : especialidades){
                        nombresEspecialidad.add( especialidad.getNombre() );
                    }
                    items.add(new ItemProfesionalDTO(
                            cuenta.getId(),
                            profesional.getNombres(),
                            profesional.getApellidos(),
                            profesional.getTipoDocumento(),
                            profesional.getNroDocumento(),
                            nombresEspecialidad,
                            cuenta.getTelefono(),
                            profesional.getDireccion(),
                            cuenta.getEmail()
                    ));
                }
            }
            return items;
        } catch (Exception e){
            throw new ProfesionalesNoEncontradosException("Error al tratar de listar a los profesionales " + e.getMessage());
        }
    }

    private boolean existeCedula(String nroDocumento) {
        return cuentaRepository.buscaNroDocumento(nroDocumento).isPresent();
    }

    private boolean existeEmail(String email) {

        return cuentaRepository.findByEmail(email).isPresent();
    }

    private List<Cuenta> obtenerCuentasProfesionales() throws ProfesionalesNoEncontradosException{
       Optional<List<Cuenta>> profesionalesEncontrados = cuentaRepository.findCuentasByRol( Rol.PROFESIONAL.getValue() );

       if( profesionalesEncontrados.isEmpty() ){
           throw new ProfesionalesNoEncontradosException("Los profesionales no fueron encontrados.");
       }

       return profesionalesEncontrados.get();
    }

    private List<Especialidad> obtenerEspecialidades(Profesional profesional) throws EspecialidadNoEncontradaException {
        List<Especialidad> especialidades = new ArrayList<>();

        for (ObjectId especialidadId : profesional.getEspecialidad()) {
            Optional<Especialidad> especialidadOpt = especialidadRepository.findById(especialidadId.toHexString());

            if (especialidadOpt.isEmpty()) {
                throw new EspecialidadNoEncontradaException("Especialidad con ID " + especialidadId.toHexString() + " no fue encontrada.");
            }

            especialidades.add(especialidadOpt.get());
        }

        if (especialidades.isEmpty()) {
            throw new EspecialidadNoEncontradaException("No se encontraron especialidades para el profesional.");
        }

        return especialidades;
    }

    private Cuenta obtenerPorEmail(String email) throws Exception{
        try {
            Optional<Cuenta> cuenta = cuentaRepository.findByEmail( email );

            if( cuenta.isEmpty() ){
                throw new Exception("Cuenta no encontrado.");
            }

            return cuenta.get();
        }catch(Exception e){
            throw new Exception("Error al buscar el correo." + e.getMessage());
        }
    }

    private String encriptarPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode( password );
    }

    public Map<String, Object> construirClaims(Cuenta cuenta) {
        return Map.of(
                "rol", cuenta.getRol(),
                "nombre", cuenta.getUsuario().getNombres(),
                "id", cuenta.getId()
        );
    }

}
