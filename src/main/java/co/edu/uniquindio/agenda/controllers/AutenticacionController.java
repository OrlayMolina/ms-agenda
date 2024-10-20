package co.edu.uniquindio.agenda.controllers;

import co.edu.uniquindio.agenda.controllers.exceptions.agenda.AgendaNoEncontradaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cita.CitaNoCreadaException;
import co.edu.uniquindio.agenda.controllers.exceptions.cita.PacienteNoAfiliadoException;
import co.edu.uniquindio.agenda.controllers.exceptions.cuenta.*;
import co.edu.uniquindio.agenda.controllers.exceptions.especialidad.EspecialidadNoEncontradaException;
import co.edu.uniquindio.agenda.controllers.exceptions.sede.SedeNoEncontradaException;
import co.edu.uniquindio.agenda.dto.agenda.InformacionAgendaDTO;
import co.edu.uniquindio.agenda.dto.cita.CrearCitaDTO;
import co.edu.uniquindio.agenda.dto.cuenta.*;
import co.edu.uniquindio.agenda.dto.especialidad.ItemEspecialidadDTO;
import co.edu.uniquindio.agenda.dto.sede.SedeDTO;
import co.edu.uniquindio.agenda.services.interfaces.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    private final IAutenticacionService autenticacionService;
    private final IAgendaService agendaService;
    private final IEspecialidadService especialidadService;
    private final ISedeService sedeService;
    private final ICitaService citaService;
    private final ICuentaService cuentaService;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionCliente(@Valid @RequestBody
                                                                     LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = autenticacionService.iniciarSesion(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/confirmar-cuenta")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@RequestBody Map<String, String> request) throws Exception {
        try {
            String email = request.get("email");
            String codigoValidacion = request.get("codigo");
            String resultado = cuentaService.activarCuenta(email, codigoValidacion);
            return ResponseEntity.ok().body(new MensajeDTO<>(true, resultado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500))
                    .body(new MensajeDTO<>(false, "Error al activar la cuenta: " + e.getMessage()));
        }
    }

    @Operation(summary = "Crear cuenta", description = "Permite crear una cuenta de paciente")
    @PostMapping("/crear-paciente")
    public ResponseEntity<MensajeDTO<String>> crearCuentaPaciente(@Valid @RequestBody CrearCuentaPacienteDTO cuentaPacienteDTO) throws CuentaNoCreadaException {
        try {
            cuentaService.crearCuentaPaciente(cuentaPacienteDTO);
            return ResponseEntity.ok().body( new MensajeDTO<>( false, "El paciente fue creado exitosamente."));
        } catch (Exception e){
            throw new CuentaNoCreadaException("El paciente no fue creado " + e.getMessage());
        }
    }

    @PostMapping("/crear-profesional")
    public ResponseEntity<MensajeDTO<String>> crearCuentaProfesional(@Valid @RequestBody CrearCuentaProfesionalDTO cuentaProfesionalDTO) throws CuentaNoCreadaException {
        try {
            cuentaService.crearCuentaProfesional(cuentaProfesionalDTO);
            return ResponseEntity.ok().body( new MensajeDTO<>( false, "El profesional fue creado exitosamente."));
        } catch (Exception e){
            throw new CuentaNoCreadaException("El profesional no fue creado " + e.getMessage());
        }
    }

    @Operation(summary = "Enviar codigo", description = "Permite enviar codigo para cambiar la contraseña")
    @PostMapping("/enviar-codigo-password")
    public ResponseEntity<MensajeDTO<String>> enviarCodigoCambioPassword(@RequestBody String correo) throws CodigoValidacionNoEnviadoException {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, cuentaService.enviarCodigoRecuperacionPassword(correo)) );
    }

    @Operation(summary = "Cambiar Contraseña", description = "Permite cambiar la contraseña")
    @PostMapping("/cambiar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@RequestBody CambiarPasswordDTO cambiarPasswordDTO) throws PasswordNoEditadaException {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, cuentaService.cambiarPassword(cambiarPasswordDTO)) );
    }

    @GetMapping("/sedes/lista-sedes")
    public ResponseEntity<MensajeDTO<List<SedeDTO>>> listarSedes() throws SedeNoEncontradaException {
        return ResponseEntity.ok().body( new MensajeDTO<>( false, sedeService.listaSedes()));
    }

    @GetMapping("/pacientes/lista-pacientes")
    public ResponseEntity<MensajeDTO<List<PacienteDTO>>> listarPacientes() throws CuentaNoEncontradaException {
        return ResponseEntity.ok().body( new MensajeDTO<>( false, cuentaService.listaPacientes()));
    }

    @GetMapping("/medicos/lista-medicos")
    public ResponseEntity<MensajeDTO<List<MedicoDTO>>> listarMedicos() throws CuentaNoEncontradaException {
        return ResponseEntity.ok().body( new MensajeDTO<>( false, cuentaService.listaMedicos()));
    }

    @GetMapping("/especialidades/listar-todas")
    public ResponseEntity<MensajeDTO<List<ItemEspecialidadDTO>>> listarEspecialidadesTodas() throws EspecialidadNoEncontradaException {
        return ResponseEntity.ok().body( new MensajeDTO<>( false, especialidadService.listarEspecilidades()));
    }
}
