package co.edu.uniquindio.agenda.services.implementations;

import co.edu.uniquindio.agenda.dto.cuenta.CrearCuentaPacienteDTO;
import co.edu.uniquindio.agenda.dto.cuenta.CrearCuentaProfesionalDTO;
import co.edu.uniquindio.agenda.dto.cuenta.EditarCuentaPacienteDTO;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoCreadaException;
import co.edu.uniquindio.agenda.exceptions.cuenta.CuentaNoEditadaException;
import co.edu.uniquindio.agenda.models.documents.Cuenta;
import co.edu.uniquindio.agenda.models.enums.*;
import co.edu.uniquindio.agenda.models.vo.CodigoValidacion;
import co.edu.uniquindio.agenda.models.vo.Paciente;
import co.edu.uniquindio.agenda.models.vo.Profesional;
import co.edu.uniquindio.agenda.repository.ICuentaRepository;
import co.edu.uniquindio.agenda.services.interfaces.ICuentaService;
import co.edu.uniquindio.agenda.utils.GenerarCodigo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements ICuentaService {

    private final ICuentaRepository cuentaRepository;
    private final GenerarCodigo generarCodigo;
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
            nuevaCuenta.setRol( Rol.PACIENTE );
            nuevaCuenta.setEmail( cuenta.email() );
            nuevaCuenta.setCodigoValidacionRegistro( new CodigoValidacion(
                    codigoAleatorio,
                    LocalDateTime.now()
            ) );
            nuevaCuenta.setUsuario( nuevoPaciente );
            nuevaCuenta.setNivelAcceso( NivelAcceso.BASIC.getValue() );
            nuevaCuenta.setTelefono( cuenta.telefono() );
            nuevaCuenta.setFechaRegistro( LocalDateTime.now() );
            nuevaCuenta.setPassword( cuenta.password() );
            nuevaCuenta.setEstado( EstadoCuenta.INACTIVO );

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

            if (cuenta.especialidad() == null || cuenta.especialidad().toString().isEmpty()) {
                throw new IllegalArgumentException("El campo especialidad no puede estar vacío.");
            }

            String codigoAleatorio = generarCodigo.generarCodigoAleatorio();

            Profesional nuevoProfesional = new Profesional(
                    cuenta.tipoDocumento(),
                    cuenta.nroDocumento(),
                    cuenta.direccion(),
                    cuenta.nombres(),
                    cuenta.apellidos(),
                    cuenta.especialidad()
            );

            Cuenta nuevaCuenta = new Cuenta();
            nuevaCuenta.setRol( Rol.PROFESIONAL );
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
    public Cuenta editarCuentaPaciente(EditarCuentaPacienteDTO cuenta) throws CuentaNoEditadaException {
        return null;
    }

    private boolean existeCedula(String nroDocumento) {
        return cuentaRepository.buscaNroDocumento(nroDocumento).isPresent();
    }

    private boolean existeEmail(String email) {

        return cuentaRepository.findByEmail(email).isPresent();
    }

}
